package com.den.model.id;

import com.den.model.ext.Game;
import com.den.model.ext.Team;
import com.den.model.ext.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Created by Denis on 28.05.2015.
 */
@Embeddable
public class UserTeamID implements java.io.Serializable {

    private User user;
    private Team team;
    private Game game;
    

    @ManyToOne
    @JsonIgnore
    public User getUser() {
        return user;
    }

    @ManyToOne
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JsonIgnore
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTeamID that = (UserTeamID) o;

        if (game != null ? !game.equals(that.game) : that.game != null) return false;
        if (!team.equals(that.team)) return false;
        if (!user.equals(that.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + team.hashCode();
        result = 31 * result + (game != null ? game.hashCode() : 0);
        return result;
    }
}