package com.den.model;

import com.den.model.enumes.POST;
import com.den.model.ext.Game;
import com.den.model.ext.Team;
import com.den.model.ext.User;
import com.den.model.id.UserTeamID;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Denis on 28.05.2015.
 */
@javax.persistence.Entity
@Table(name = "user_team")
@AssociationOverrides({
        @AssociationOverride(name = "pk.team",
                joinColumns = @JoinColumn(name = "id_team", referencedColumnName = "id")),
        @AssociationOverride(name = "pk.user",
                joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id")),
        @AssociationOverride(name = "pk.game",
                joinColumns = @JoinColumn(name = "id_game", referencedColumnName = "id"))})
public class UserTeam implements java.io.Serializable {

    private UserTeamID pk = new UserTeamID();

    @Enumerated(EnumType.STRING)
    @Column(name = "post")
    private POST post;

    public UserTeam() {
    }

    @EmbeddedId
    public UserTeamID getPk() {
        return pk;
    }

    public void setPk(UserTeamID pk) {
        this.pk = pk;
    }

    @Transient
    public Team getTeam() {
        return getPk().getTeam();
    }

    public void setTeam(Team team) {
        getPk().setTeam(team);
    }

    @Transient
    @JsonIgnore
    public Game getGame() {
        return getPk().getGame();
    }

    public void setGame(Game game) {
        getPk().setGame(game);
    }



    @Transient
    @JsonIgnore
    public User getUser() {
        return getPk().getUser();
    }

    public void setUser(User user) {
        getPk().setUser(user);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTeam userTeam = (UserTeam) o;

        if (!pk.equals(userTeam.pk)) return false;

        return true;
    }



    public POST getPost() {
        return post;
    }

    public void setPost(POST post) {
        this.post = post;
    }


}