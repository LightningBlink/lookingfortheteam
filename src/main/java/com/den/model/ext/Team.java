package com.den.model.ext;

import com.den.model.UserTeam;
import com.den.model.impl.AbsEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Denis on 18.12.2014.
 */
@Entity
@Table(name = "team")
public class Team extends AbsEntity {

    @NotBlank
    @Length(min = 2, max = 20)
    @Column(name = "name",unique = true)
    private String name;

    @Column
    private Boolean hasAvatar = false;

    @NotBlank
    @Length(min = 0, max = 2000)
    @Column(name = "about")
    private String aboutTeam;

    @NotBlank
    @Column(length = 3)
    private String nation;

    @Column(name ="date")
    private Date date = new Date();



    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.team", orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<UserTeam> userTeams = new HashSet<>(0);



    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAboutTeam() {
        return aboutTeam;
    }

    public void setAboutTeam(String aboutTeam) {
        this.aboutTeam = aboutTeam;
    }

    public Set<UserTeam> getUserTeams() {
        return userTeams;
    }

    public void setUserTeams(Set<UserTeam> userTeams) {
        this.userTeams = userTeams;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Date getDate() {
        return date;
    }

    public Boolean getHasAvatar() {
        return hasAvatar;
    }

    public void setHasAvatar(Boolean hasAvatar) {
        this.hasAvatar = hasAvatar;
    }
}
