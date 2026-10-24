package com.den.model.ext;

import com.den.model.impl.AbsEntity;

import javax.persistence.*;

/**
 * Created by Denis on 30.09.2014.
 */

@Entity
@Table(name = "attachment")
public class Attachment extends AbsEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
