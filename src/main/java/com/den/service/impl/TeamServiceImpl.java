package com.den.service.impl;

import com.den.model.ext.Game;
import com.den.model.ext.Team;
import com.den.model.ext.User;
import com.den.model.wrapper.TeammateInfo;
import com.den.repository.TeamPersistence;
import com.den.service.CrudService;
import com.den.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Denis on 22.05.2015.
 */
@Service
public class TeamServiceImpl extends CrudService<Team> implements TeamService {

    @Autowired
    private TeamPersistence teamPersistence;

    @Override
    protected JpaRepository<Team, Long> getRepository() {
        return this.teamPersistence;
    }

    @Override
    public Page<Team> getLookingTeams(Game game, Pageable pageable, String keyword, String country) {
        if(country.isEmpty()){
           return teamPersistence.findLookingTeamsWithoutCountry(game, pageable, keyword);
        }
        return teamPersistence.findLookingTeams(game, pageable, keyword, country);
    }

    @Override
    public List<Object[]> getTeamInfo(Long id) {
        return teamPersistence.findTeamUsers(id);
    }

    @Override
    public Page<Team> findByGame(Game game, Team team, Pageable pageable, String keyword, String country) {
        if(country.isEmpty()){
            return teamPersistence.findByGameWithoutCountry(game, team, pageable, keyword);
        }
        return teamPersistence.findByGame(game, team, pageable, keyword, country);
    }

    @Override
    public Team findByName(String name) {
        return teamPersistence.findByName(name);
    }
}
