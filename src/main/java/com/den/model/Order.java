package com.den.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Denis on 19.06.2015.
 */
public class Order implements Serializable {

    private final String id;

    private Date date;

    private Set<Integer> ports = new HashSet<>(0);

    public Order(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate() {
        this.date = new Date();
    }

    public Set<Integer> getPorts() {
        return ports;
    }
}