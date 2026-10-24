package com.den.model.impl;

import com.den.model.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by Denis on 23.09.2014.
 */

@MappedSuperclass
public abstract class AbsEntity implements Entity<Long> {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbsEntity absEntity = (AbsEntity) o;

        if (!id.equals(absEntity.id)) return false;

        return true;
    }

}
