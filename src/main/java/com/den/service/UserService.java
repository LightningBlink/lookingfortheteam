package com.den.service;

import com.den.handler.MessageHandler;
import com.den.model.ext.User;
import com.den.model.wrapper.UserInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Denis on 21.12.2014.
 */
public interface UserService  extends CrudInterface<User>{

    User get(final String name);

    MessageHandler saveWithLetter(final User user);

    User update(final User user);

    UserInfoResponse getUserInfo(final String name);

    Page<User> getLookingUsers(String game, Pageable pageable, String keyword, String country);

    User findByEmail(String email);

}
