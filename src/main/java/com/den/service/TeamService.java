package com.den.service;

import com.den.model.ext.Game;
import com.den.model.ext.Team;
import com.den.model.ext.User;
import com.den.model.wrapper.TeammateInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Denis on 22.05.2015.
 */

public interface TeamService extends CrudInterface<Team> {
//
    Page<Team> getLookingTeams(Game game, Pageable pageable, String keyword, String country);

    List<Object[]> getTeamInfo(Long id);

    Page<Team> findByGame(Game game, Team team, Pageable pageable, String keyword, String country);

    Team findByName(String name);
}
