package com.den.service.impl;

import com.den.exception.ApplicationException;
import com.den.handler.ErrorMessageHandler;
import com.den.handler.MessageHandler;
import com.den.handler.SuccessMessageHandler;
import com.den.model.UserGame;
import com.den.model.ext.Country;
import com.den.model.ext.Game;
import com.den.model.ext.User;
import com.den.model.wrapper.UserInfoResponse;
import com.den.repository.CountryPersistence;
import com.den.repository.UserPersistence;
import com.den.repository.UserTeamPersistence;
import com.den.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.PersistenceException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Denis on 21.12.2014.
 */
@Service
public class UserServiceImpl extends CrudService<User> implements UserService {


    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserPersistence userPersistence;

    @Autowired
    private EmailConfirmationService emailConfirmationService;

    @Autowired
    private GameService gameService;

    @Autowired
    private CountryPersistence countryPersistence;

    @Autowired
    private UserTeamPersistence userTeamPersistence;

    @Autowired
    private TeamService teamService;

    @Value("${application.admin.username}")
    private String username;

    @Value("${application.admin.password}")
    private String password;

    @Value("${application.admin.mail}")
    private String mail;

    @Value("${application.admin.name}")
    private String name;





    @PostConstruct
    public void initAdmin() {
        User user = userPersistence.findOne(1l);

        if (user == null) {
            user = new User();
            user.setId(1l);
            user.setUsername(this.username);
            user.setPassword(this.password);
            user.setName(this.name);
            user.setEmail(this.mail);
            user.setRoles(RoleService.ALL_RIGHTS_SET);
            userPersistence.save(user);
        }

    }

    public User get(final String name) {
        return userPersistence.getUserByUsername(name);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MessageHandler saveWithLetter(User user) {
        User checkUser = userPersistence.getUserByUsernameOrEmail(user.getUsername(), user.getEmail());

        if (checkUser == null) {
            if((user = userPersistence.save(user)) == null){
                ErrorMessageHandler error = new ErrorMessageHandler();
                error.setMessage(messageSource.getMessage("userController.someproblem", null, LocaleContextHolder.getLocale()));
                return error;
            };

            final User finalUser = user;
            new Thread(()-> emailConfirmationService.register(finalUser)).start();
            return new SuccessMessageHandler(messageSource.getMessage("userController.usercreated", null, LocaleContextHolder.getLocale()));

        } else return new ErrorMessageHandler(messageSource.getMessage("userController.userexist", null, LocaleContextHolder.getLocale()));

    }

    @Transactional(rollbackFor = Exception.class)
    public User update(User user) {

        Set<UserGame> userGames = user.getUserGames();

        for (UserGame userGame : userGames) {
            userGame.setUser(user);
        }


        Set<UserGame> list = this.get(user.getId()).getUserGames();

        Set<Game> removedGames = new HashSet<>();
        for (UserGame ug : userGames) {

            if (!list.contains(ug)) {
                removedGames.add(ug.getGame());
            }

        }


        user = userPersistence.save(user);

        if(user == null){
            throw new PersistenceException();
        }

        return user;
//        if (!removedGames.isEmpty()) {
//
//            userTeamPersistence.deleteUserGames(user, removedGames);
//
//            Set<UserTeam> userTeams = userTeamPersistence.getUserTeamsWhereUserIsLeader(user);
//
//                    if (!userTeams.isEmpty()) {
//
//                userTeams.stream().forEach(ut -> teamService.remove(ut.getTeam().getId()));
//            }
//        }


//        return userPersistence.save(user);
    }

    @Override
    public UserInfoResponse getUserInfo(String name) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        final User user = get(name);

        if (user == null) {
            return new UserInfoResponse();
        }

        List<Game> gameList = gameService.getAllGames();

        for (UserGame userGame : user.getUserGames()) {
            gameList.remove(userGame.getGame());
        }

        final Country country = countryPersistence.findByCountryCode(user.getCountry());

        if (country != null) {
            userInfoResponse.setCountryName(country.getCountryName());
        }

        userInfoResponse.setAllGames(gameList);
        userInfoResponse.setUser(user);
        return userInfoResponse;
    }


    @SuppressWarnings("unchecked")
    @Override
    public Page<User> getLookingUsers(String game, Pageable pageable, String keyword, String country) {
        Optional<Game> optional = Optional.of(gameService.getByName(game));

        int start = pageable.getPageSize()*pageable.getPageNumber();

        if (optional.isPresent()) {
            int total;
            if(country.isEmpty()){
                total = userPersistence.getByGameLookingPlayersWithoutCountryCount(optional.get().getId(), keyword);
                return new PageImpl<>(userPersistence.getByGameLookingPlayersWithoutCountry(optional.get().getId(), keyword, start, pageable.getPageSize()),pageable, total);
            }
            total = userPersistence.getByGameLookingPlayersCount(optional.get().getId(), keyword, country);
            return new PageImpl<>(userPersistence.getByGameLookingPlayers(optional.get().getId(), keyword, country, start, pageable.getPageSize()), pageable, total);
        }

        throw new ApplicationException();

    }

    @Override
    public User findByEmail(String email) {
        return userPersistence.findByEmail(email);
    }

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return this.userPersistence;
    }

}
