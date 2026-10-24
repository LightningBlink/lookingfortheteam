package com.den.service;

import com.den.model.ext.User;

import java.util.Map;

/**
 * Created by Denis on 29.01.2015.
 */
public interface EmailConfirmationService {

    void register(User user);

    void send(User user, Map<String,Object> model, String action, String subject);
}
