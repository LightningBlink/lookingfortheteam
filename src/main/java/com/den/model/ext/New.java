package com.den.model.ext;

import com.den.model.impl.AbsEntity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

/**
 * Created by Denis on 30.09.2014.
 */
@Entity
@Table(name = "new")
public class New extends AbsEntity {

    @Column(name = "header")
    private String header;

    @Column(name = "descirpion")
    @Lob
    private String description;

    @Column(name = "isShow")
    private Boolean show = false;

    @Column(name = "date", updatable = false)
    private LocalDateTime date = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));

    @Column(name = "author")
    private String authorName;

    public New(){};

    public New(String header, String description, String authorName) {
        this.header = header;
        this.description = description;
        this.authorName = authorName;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }
}
