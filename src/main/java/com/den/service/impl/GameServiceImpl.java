package com.den.service.impl;

import com.den.model.ext.Game;
import com.den.model.ext.User;
import com.den.repository.GamePersistence;
import com.den.repository.UserGamePersistence;
import com.den.repository.UserPersistence;
import com.den.service.CrudService;
import com.den.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by Denis on 21.12.2014.
 */
@Service
public class GameServiceImpl extends CrudService<Game> implements GameService {

    private final static Set<Game> HAVE_NO_GAMES = new HashSet<>(0);

    @Autowired
    private GamePersistence gamePersistence;


    @Autowired
    private UserPersistence userPersistence;

    @Override
    protected JpaRepository<Game, Long> getRepository() {
        return this.gamePersistence;
    }

    public List<Game> getAllGames() {
        return gamePersistence.findAll();
    }

    @Override
    public Set<Game> gamesByUser(String userName) {
        Optional<User> opt = Optional.of(userPersistence.getUserByUsername(userName));

        return Optional.of(gamePersistence.findGamesByUser(opt.orElseThrow(NullPointerException::new))).orElse(HAVE_NO_GAMES);


    }

    @Override
    public Game getByName(String name) {
        return gamePersistence.findGameByName(name);
    }
}
