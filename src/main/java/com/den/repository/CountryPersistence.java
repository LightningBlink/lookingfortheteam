package com.den.repository;

import com.den.model.ext.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Denis on 14.05.2015.
 */
public interface CountryPersistence extends JpaRepository<Country, Long> {

    Country findByCountryCode(String code);
}
