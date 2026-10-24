package com.den.repository;

import com.den.model.UserGame;
import com.den.model.ext.Game;
import com.den.model.ext.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

/**
 * Created by Denis on 05.05.2015.
 */
public interface UserGamePersistence extends JpaRepository<UserGame, Long> {

}
