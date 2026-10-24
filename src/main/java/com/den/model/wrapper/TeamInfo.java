package com.den.model.wrapper;

import com.den.model.ext.Game;
import com.den.model.ext.Team;

import java.util.List;
import java.util.Set;

/**
 * Created by Denis on 01.06.2015.
 */
public class TeamInfo {

    public String leaderName;

    public Set<GameWrapper> games;

    public Set<GameWrapper> teamGames;

    public Team team;

    public List<TeammateInfo> teammateInfos;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<TeammateInfo> getTeammateInfos() {
        return teammateInfos;
    }

    public void setTeammateInfos(List<TeammateInfo> teammateInfos) {
        this.teammateInfos = teammateInfos;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public Set<GameWrapper> getGames() {
        return games;
    }

    public void setGames(Set<GameWrapper> games) {
        this.games = games;
    }

    public Set<GameWrapper> getTeamGames() {
        return teamGames;
    }

    public void setTeamGames(Set<GameWrapper> teamGames) {
        this.teamGames = teamGames;
    }
}
