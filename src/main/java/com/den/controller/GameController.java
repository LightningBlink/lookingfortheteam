    package com.den.controller;

import com.den.model.ext.Game;
import com.den.model.wrapper.GameTwich;
import com.den.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Denis on 21.12.2014.
 */
@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;



    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    @ResponseBody
    public Set<Game> getUserGames(@PathVariable String userName) {
     return   gameService.gamesByUser(userName);
    }


    @RequestMapping("/getgames")
    @ResponseBody
    public List<GameTwich> getGames() {

        return gameService.getAllGames().stream().map(game->new GameTwich(game.getName(), game.getFilePath())).collect(Collectors.<GameTwich>toList());

    }
}
