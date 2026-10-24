package com.den.model.id;

import com.den.model.ext.Game;
import com.den.model.ext.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Created by Denis on 04.05.2015.
 */
@Embeddable
public class UserGameID implements java.io.Serializable {

    private User user;
    private Game game;

    @ManyToOne
    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserGameID that = (UserGameID) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (game != null ? !game.equals(that.game) : that.game != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (user != null ? user.hashCode() : 0);
        result = 31 * result + (game != null ? game.hashCode() : 0);
        return result;
    }

}
