package com.den.service;

import com.den.model.ext.Game;

import java.util.List;
import java.util.Set;

/**
 * Created by Denis on 21.12.2014.
 */
public interface GameService extends CrudInterface<Game>{

    List<Game> getAllGames();

    Set<Game> gamesByUser(final String userName);

    Game getByName(String name);


}
