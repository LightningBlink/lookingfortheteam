package com.den.service.impl;

import com.den.model.ext.New;
import com.den.repository.NewsPersistence;
import com.den.service.CrudService;
import com.den.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 * Created by Denis on 30.09.2014.
 */

@Service
public class NewsServiceImpl extends CrudService<New> implements NewsService {

    @Autowired
    private NewsPersistence persistence;

    @Override
    public Page<New> getAllShow(Pageable pageable) {
        return persistence.findByShowTrue(pageable);
    }

    @Override
    public Page<New> getAll(Pageable pageable) {
        return persistence.findAll(pageable);
    }


    @Override
    protected JpaRepository<New, Long> getRepository() {
        return this.persistence;
    }
}
