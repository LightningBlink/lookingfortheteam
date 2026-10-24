package com.den.model.ext;

import com.den.model.UserGame;
import com.den.model.impl.AbsEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Denis on 18.12.2014.
 */
@Entity
@Table(name = "game")
public class Game extends AbsEntity {

    @Column(name="name", unique = true)
    private String name;

    @Column(name="nickname")
    private String nickname;

    @Column(name="filepath")
    private String filePath;


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.game")
    private Set<UserGame> userGames = new HashSet<>(0);

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserGame> getUserGames() {
        return userGames;
    }

    public void setUserGames(Set<UserGame> userGames) {
        this.userGames = userGames;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
