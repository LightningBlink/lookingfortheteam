package com.den.repository;

import com.den.model.ext.New;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Denis on 30.09.2014.
 */
public interface NewsPersistence extends JpaRepository<New, Long> {

    Page<New> findByShowTrue(Pageable pageable);
}
