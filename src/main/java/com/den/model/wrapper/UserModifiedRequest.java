package com.den.model.wrapper;

import com.den.model.UserGame;
import com.den.model.ext.Game;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Denis on 04.05.2015.
 */
public class UserModifiedRequest {

    private Long id;

    private String name;

    private String surname;

    private String city;

    private String country;

    private String aboutMe;

    private Boolean male;

    private Boolean hasAvatar;

    private Set<UserGame> userGames = new HashSet<UserGame>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public Boolean getMale() {
        return male;
    }

    public Set<UserGame> getUserGames() {
        return userGames;
    }

    public Boolean getHasAvatar() {
        return hasAvatar;
    }
}
