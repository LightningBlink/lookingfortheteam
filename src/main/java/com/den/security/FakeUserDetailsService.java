package com.den.security;

import com.den.controller.BaseController;
import com.den.model.ext.User;
import com.den.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FakeUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User person = userService.get(username);
        if (person == null) {
            throw new UsernameNotFoundException(messageSource.getMessage("fakeuserdetailservice.username", null, LocaleContextHolder.getLocale()) + username + messageSource.getMessage("fakeuserdetailservice.notfound", null, LocaleContextHolder.getLocale()));
        } else if(!person.isEnabled()){
            throw new UsernameNotFoundException(messageSource.getMessage("fakeuserdetailservice.username", null, LocaleContextHolder.getLocale()) + username + messageSource.getMessage("fakeuserdetailservice.notfound", null, LocaleContextHolder.getLocale()));
        }
        return person;
    }

}