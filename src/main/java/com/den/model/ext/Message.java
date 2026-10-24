package com.den.model.ext;

import com.den.model.impl.AbsEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by Denis on 10.07.2015.
 */
@Entity
@Table(name = "message")
public class Message extends AbsEntity {


    @Column(name = "user_from")
    private String userFrom;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @NotNull
    @NotBlank
    @Length(min = 1, max = 1000)
    @Column(name = "message")
    private String message;

    @Column(name = "read_ed")
    private Boolean read;

    @Column(name = "date", updatable = false)
    private LocalDateTime date = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));

    private Boolean invitation = false;

    private Long teamId;

    private String game;

    private Boolean isShow = true;

    private Long deleted = 0l;



    public Message() {
    }

    public Message(String userFrom, User userTo, String message) {
        this.userFrom = userFrom;
        this.user = userTo;
        this.message = message;
        this.read = false;
    }

    public Message(String userFrom, User userTo, String message, Boolean invitation) {
        this.userFrom = userFrom;
        this.user = userTo;
        this.message = message;
        this.read = false;
        this.invitation = invitation;
    }

    public Boolean getInvitation() {
        return invitation;
    }

    public void setInvitation(Boolean invitation) {
        this.invitation = invitation;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userTo) {
        this.user = userTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public Long getDeleted() {
        return deleted;
    }

    public void setDeleted(Long deleted) {
        this.deleted = deleted;
    }
}
