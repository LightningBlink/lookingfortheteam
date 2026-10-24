package com.den.model.ext;

import com.den.model.Entity;
import com.den.model.impl.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by Denis on 14.05.2015.
 */
@javax.persistence.Entity
@Table(name = "countries")
public class Country extends AbsEntity {

    @Column(name = "country_code", length = 2)
    private String countryCode;

    @Column(name = "country_name")
    private String countryName;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
