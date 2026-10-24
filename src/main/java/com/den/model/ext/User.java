package com.den.model.ext;

import com.den.constraint.LengthWithEmpty;
import com.den.model.UserGame;
import com.den.model.UserTeam;
import com.den.model.impl.AbsEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Denis on 23.09.2014.
 */
@Entity
@Table(name = "user")
public class User extends AbsEntity implements UserDetails {

    @NotBlank
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z0-9-]+")
    @Length(min = 4, max = 15)
    @Column(unique = true, length = 15, nullable = false)
    private String username;

    @JsonIgnore
    @NotBlank
    @NotEmpty
    @Length(min = 4, max = 20)
    @Column(length = 20, nullable = false)
    private String password;

    @Length(max = 40)
    @Email
    @Column(length = 40, nullable = false)
    private String email;


    @LengthWithEmpty(min = 2, max = 20)
    @Column(length = 20)
    private String name;

    @LengthWithEmpty(min = 2, max = 20)
    @Column(length = 20)
    private String surname;


    @LengthWithEmpty(min = 2, max = 30)
    @Column(length = 30)
    private String city;


    @LengthWithEmpty(min = 2, max = 3)
    @Column(length = 3)
    private String country = "INT";


    @LengthWithEmpty(max = 1000)
    @Column
    private String aboutMe;

    @Column
    private Boolean male;

    @Column
    private Boolean enabled = false;

    @Column
    private Boolean hasAvatar = false;

    @Column
    private Date date = new Date();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Message> messages = new ArrayList<>();


    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<String>();


    public User() {
        /* Reflection instantiation */
    }


    public User(String username, String passwordHash) {
        this.username = username;
        this.password = passwordHash;
    }


    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user", orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<UserTeam> userTeams = new HashSet<>(0);

    @Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user", orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<UserGame> userGames = new HashSet<>(0);



    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<String> roles = this.getRoles();

        if (roles == null) {
            return Collections.emptyList();
        }

        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserTeam> getUserTeams() {
        return userTeams;
    }

    public void setUserTeams(Set<UserTeam> userTeams) {
        this.userTeams = userTeams;
    }

    public Set<UserGame> getUserGames() {
        return userGames;
    }

    public void setUserGames(Set<UserGame> userGames) {
        this.userGames.clear();
        this.userGames.addAll(userGames);
    }


    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHasAvatar() {
        return hasAvatar;
    }

    public void setHasAvatar(Boolean hasAvatar) {
        this.hasAvatar = hasAvatar;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
                ", male=" + male +
                ", hasAvatar=" + hasAvatar +
                ", roles=" + roles +
                '}';
    }
}
