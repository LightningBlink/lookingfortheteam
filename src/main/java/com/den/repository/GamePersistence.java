package com.den.repository;

import com.den.model.ext.Game;
import com.den.model.ext.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * Created by Denis on 21.12.2014.
 */
public interface GamePersistence extends JpaRepository<Game, Long> {

    @Query("SELECT g FROM Game g  inner join g.userGames ug WHERE ug.pk.user = ?1")
    Set<Game> findGamesByUser(final User user);

    Game findGameByName(String name);

}

