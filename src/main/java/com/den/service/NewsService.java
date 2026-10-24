package com.den.service;

import com.den.model.ext.New;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by Denis on 30.09.2014.
 */
public interface NewsService extends CrudInterface<New> {

    Page<New>  getAllShow(Pageable pageable);

    Page<New>  getAll(Pageable pageable);

}
