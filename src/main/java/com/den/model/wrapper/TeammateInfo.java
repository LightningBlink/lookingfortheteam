package com.den.model.wrapper;

import com.den.model.enumes.POST;

/**
 * Created by Denis on 01.06.2015.
 */
public class TeammateInfo {

    public String username;

    public Long id;

    public String game;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
