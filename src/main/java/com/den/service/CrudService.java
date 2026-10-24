package com.den.service;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Denis on 14.03.2015.
 */
public abstract class CrudService<T> implements CrudInterface<T> {


    public T save(T o){
       return getRepository().save(o);
    }

    public T get(Long id){
        return getRepository().findOne(id);
    }

    public void remove(Long id){
         getRepository().delete(id);
    }



    protected abstract JpaRepository<T, Long> getRepository();

}
