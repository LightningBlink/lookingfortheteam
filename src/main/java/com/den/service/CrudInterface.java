package com.den.service;

/**
 * Created by Denis on 14.03.2015.
 */
public interface CrudInterface<T> {

    T save(T o);

    T get(Long id);

    void remove(Long id);

}
