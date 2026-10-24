package com.den.service;

import com.den.model.ext.UserHash;

/**
 * Created by Denis on 18.02.2016.
 */
public interface UserHashService extends CrudInterface<UserHash> {

    UserHash findByHash(String hash);
}
