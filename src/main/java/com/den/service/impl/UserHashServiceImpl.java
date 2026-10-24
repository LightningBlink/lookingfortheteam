package com.den.service.impl;

import com.den.model.ext.UserHash;
import com.den.repository.UserHashRepository;
import com.den.service.CrudService;
import com.den.service.UserHashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Denis on 18.02.2016.
 */
@Service
public class UserHashServiceImpl extends CrudService<UserHash> implements UserHashService {

    @Autowired
    private UserHashRepository repository;
    @Override
    protected JpaRepository<UserHash, Long> getRepository() {
        return this.repository;
    }

    @Override
    public UserHash findByHash(String hash) {
        return repository.findByHash(hash);
    }
}
