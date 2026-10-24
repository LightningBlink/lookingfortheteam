package com.den.controller;

import com.den.handler.ErrorMessageHandler;
import com.den.handler.MessageHandler;
import com.den.handler.SuccessMessageHandler;
import com.den.model.UserTeam;
import com.den.model.enumes.POST;
import com.den.model.ext.Game;
import com.den.model.ext.Team;
import com.den.model.ext.User;
import com.den.model.id.UserTeamID;
import com.den.model.wrapper.GameWrapper;
import com.den.model.wrapper.TeamInfo;
import com.den.model.wrapper.TeammateInfo;
import com.den.service.GameService;
import com.den.service.TeamService;
import com.den.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Denis on 18.12.2014.
 */
@Controller
@RequestMapping("/team")
public class TeamController extends BaseController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;



    @RequestMapping(value = "/removegamefromteam", method = RequestMethod.POST)
    @ResponseBody
    public MessageHandler removeGameFromTeam(@RequestParam(required = true) String game, @RequestParam(required = true) Long teamId){
        Optional<Game> opGame = Optional.of(gameService.getByName(game));

        if(opGame.isPresent()){
            Optional<Team> opTeam = Optional.of(teamService.get(teamId));

            User user = userService.get(getUsername());

            if(opTeam.isPresent()) {
                boolean isLeader = opTeam.get().getUserTeams().stream().anyMatch(ut -> user.equals(ut.getUser()) && ut.getPost().equals(POST.LEADER));
                if(!isLeader){
                    return new ErrorMessageHandler(getMessage("userController.password.error"));
                }
            } else return new ErrorMessageHandler(getMessage("userController.norights"));



            new ArrayList<>(opTeam.get().getUserTeams()).stream().filter(ut -> ut.getGame().equals(opGame.get())).forEach(ut -> {
                opTeam.get().getUserTeams().remove(ut);
            });

            if(opTeam.get().getUserTeams().isEmpty()){

                 return new ErrorMessageHandler(getMessage("teamController.cantdelete.lineup"));
            }
            teamService.save(opTeam.get());

            return new SuccessMessageHandler(getMessage("teamController.line.up") + game + getMessage("teamController.hasbeendeleted"));
        } else {
            return new ErrorMessageHandler(getMessage("teamController.gamenotadded"));
        }

    }

    @RequestMapping(value = "/leaveteam", method = RequestMethod.POST)
    @ResponseBody
    public MessageHandler leaveTeam(@RequestParam(required = false) String game,
                                    @RequestParam  Long idTeam,
                                    @RequestParam  String username) {

        Team team = teamService.get(idTeam);
        ifNullException(team);

        User user = userService.get(getUsername());

        boolean isLeader = team.getUserTeams().stream().anyMatch(ut -> user.equals(ut.getUser()) && ut.getPost().equals(POST.LEADER));

        boolean isDeleteTeam = isLeader && user.getUsername().equals(username);


        if (!isLeader) {
            for (UserTeam ut : team.getUserTeams()) {
                if (user.equals(ut.getUser()) && game.equals(ut.getGame().getName())) {

                    user.getUserTeams().remove(ut);
                    userService.save(user);
                    break;
                }
            }

                return new SuccessMessageHandler(getMessage("teamController.uhadleaveteam"), user.getUsername());
        } else {

            if(isDeleteTeam){
                teamService.remove(team.getId());
                return new SuccessMessageHandler(getMessage("teamController.teamhasbeendeleted"));
            }else {

                for (UserTeam ut : team.getUserTeams()) {
                    if (ut.getUser().getUsername().equals(username) && game.equals(ut.getGame().getName())) {

                        ut.getUser().getUserTeams().remove(ut);
                        userService.save(ut.getUser());
                        return new SuccessMessageHandler(getMessage("teamController.user") + ut.getUser().getUsername() + getMessage("teamController.hasbeenkickedfromteam"), user.getUsername());
                    }
                }

                return new ErrorMessageHandler(getMessage("teamController.userfordeletionnotexist"));

            }
        }


    }



    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public MessageHandler save(@RequestParam String name,
                               @RequestParam String nation,
                               @RequestParam String aboutTeam,
                               @RequestParam(required = true) Long idGame) {

        if(teamService.findByName(name)!=null){
            return new ErrorMessageHandler(getMessage("teamController.teamexistwithname"));
        }

        final User user = userService.get(getUsername());

        Set<UserTeam> teamSet = user.getUserTeams();

        final Game game = gameService.get(idGame);

        ifNullException(game);

        if(teamSet.stream().anyMatch(ut-> ut.getGame().getId().equals(idGame))){

            return new ErrorMessageHandler(getMessage("teamController.uhavetheteam"));
        }

        Team team = new Team();
        team.setName(name);
        team.setAboutTeam(aboutTeam);
        team.setNation(nation);

        UserTeam userTeam = new UserTeam();
        userTeam.setTeam(team);
        userTeam.setUser(user);
        userTeam.setGame(game);
        userTeam.setPost(POST.LEADER);
        team.setUserTeams(Collections.singleton(userTeam));

        ifNullException(teamService.save(team));

        MessageHandler messageHandler = new SuccessMessageHandler(getMessage("teamController.teamcreated"));

        messageHandler.setJson(team);

        return messageHandler;
    }

    @RequestMapping(value = "/getteams/{username}")
    @ResponseBody
    public List<Team> getUserTeams(@PathVariable String username) {

        ifNullException(username);

        final User user = userService.get(username);

        ifNullException(user);

        return user.getUserTeams().stream().map(UserTeam::getTeam).collect(Collectors.toList());
    }




    @RequestMapping(value = "/get/{id}")
    @ResponseBody
    public TeamInfo getTeam(@PathVariable Long id) {

        TeamInfo teamInfo = new TeamInfo();
        List<Object[]> objects = teamService.getTeamInfo(id);

        Team team = teamService.get(id);
        if(team == null){
            return null;
        }
        teamInfo.setTeam(team);

        for(UserTeam ut : teamInfo.getTeam().getUserTeams()){
            if(Objects.equals(POST.LEADER,ut.getPost())){
                teamInfo.setLeaderName(ut.getUser().getUsername());
                break;
            }
        }

        Set<Game> games = new HashSet<>();
        teamInfo.getTeam().getUserTeams().stream().forEach(ut-> games.add(ut.getGame()));

        List<TeammateInfo> teammateInfos = objects.stream().map(objectsTeammate -> {
            TeammateInfo teammateInfo = new TeammateInfo();
            teammateInfo.setUsername(Objects.isNull(objectsTeammate[0]) ? StringUtils.EMPTY : (String) objectsTeammate[0]);
            teammateInfo.setId((Long)objectsTeammate[1]);
            teammateInfo.setGame(Objects.isNull(objectsTeammate[2]) ? StringUtils.EMPTY : (String) objectsTeammate[2]);
            return teammateInfo;
        }).collect(Collectors.toList());

        List<Game> allGames = gameService.getAllGames();
        allGames.removeAll(games);

        Set<GameWrapper> gamesAsString = allGames.stream().map(game -> new GameWrapper(game.getId(), game.getName(), game.getFilePath())).collect(Collectors.toSet());

        teamInfo.setGames(gamesAsString);

        teamInfo.setTeammateInfos(teammateInfos);

        teamInfo.setTeamGames(games.stream().map(game -> new GameWrapper(game.getId(), game.getName(), game.getFilePath())).collect(Collectors.toSet()));

        return teamInfo;

    }

    @RequestMapping(value = "/addgametoteam", method = RequestMethod.POST)
    @ResponseBody
    public MessageHandler addGameToTeam(@RequestParam(required = true) Long teamId, @RequestParam(required = true) String game){


        Game gameObj = gameService.getByName(game);
        Team team = teamService.get(teamId);
        ifNullException(team, gameObj);

        final User user = userService.get(getUsername());

        if(user.getUserGames().stream().noneMatch(ug -> ug.getGame().equals(gameObj))){
            return new ErrorMessageHandler(getMessage("teamController.pleaseaddgame"));
        }


        if(user.getUserTeams().stream().anyMatch(ut->ut.getGame().equals(gameObj))){
            return new ErrorMessageHandler(getMessage("teamController.uhavetheteamforthisgame"));
        }

        if(gameService.getAllGames().stream().noneMatch(g->g.getName().equals(game))){
            return new ErrorMessageHandler();
        }




        boolean isLeader = false;
        for(UserTeam ut : team.getUserTeams()){
            if(ut.getPost().equals(POST.LEADER) && ut.getUser().equals(user)){
                isLeader = true;
                break;
            }
        }

        if(!isLeader){
            return new ErrorMessageHandler(getMessage("teamController.uarenotleader"));
        }


        UserTeamID userTeamID = new UserTeamID();
        userTeamID.setTeam(team);
        userTeamID.setUser(user);
        UserTeam userTeam = new UserTeam();
        userTeam.setPost(POST.LEADER);
        userTeam.setPk(userTeamID);
        userTeam.setGame(gameObj);
        team.getUserTeams().add(userTeam);



        if(teamService.save(team) != null){
            return new SuccessMessageHandler(getMessage("teamController.gameadded"));
        }

        return new ErrorMessageHandler(getMessage("teamController.wrong"));

    }



    @RequestMapping(value = "/lookingteams")
    @ResponseBody
    public Page<Team> getTeamsByGame(@PageableDefault Pageable pageable, @RequestParam String game, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "") String country) {

        if(country.equals("INT")){
            country = "";
        }

        Game gameObj = gameService.getByName(game);

        ifNullException(gameObj);

        Team team = null;
        for(UserTeam ut : userService.get(getUsername()).getUserTeams()){
            if(ut.getGame().getName().equals(game)){
                team = ut.getTeam();
                break;
            }
        }

        if(team != null) {
            return teamService.findByGame(gameObj, team, pageable, keyword, country);
        } else {
            return teamService.getLookingTeams(gameObj, pageable, keyword, country);
        }

    }

}
