package com.den.model.wrapper;

import com.den.model.ext.Game;
import com.den.model.ext.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis on 26.04.2015.
 */
public class UserInfoResponse {

    private List<Game> allGames = new ArrayList<>(0);

    private User user;

    private String countryName;

    public List<Game> getAllGames() {
        return allGames;
    }

    public void setAllGames(List<Game> allGames) {
        this.allGames = allGames;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
