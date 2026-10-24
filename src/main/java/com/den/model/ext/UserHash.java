package com.den.model.ext;

import com.den.model.impl.AbsEntity;
import com.den.utils.PasswordUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Denis on 18.02.2016.
 */

@Entity
@Table(name = "user_hash")
public class UserHash extends AbsEntity {

    @OneToOne
    private User user;

    @Column(name = "hash", length = 32)
    private String hash;

    @Column(name ="date_created")
    private Date date = new Date();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public UserHash() {
    }


    public UserHash(User user) {
        this.user = user;
        this.hash = PasswordUtils.generatePassword(32);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
