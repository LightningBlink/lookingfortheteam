package com.den.model;

import java.io.Serializable;

/**
 * Created by Denis on 23.09.2014.
 */
public interface Entity<ID> extends Serializable {

    ID getId();

    void setId(ID id);
}
