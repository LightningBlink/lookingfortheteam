package com.den.controller;

import com.den.exception.ApplicationException;
import com.den.handler.ErrorMessageHandler;
import com.den.handler.MessageHandler;
import com.den.handler.SuccessMessageHandler;
import com.den.model.Entity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by Denis on 10.02.2015.
 */
public class BaseController {

    @Autowired
    private Validator validatorFactory;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private MessageSource messageSource;

    private javax.validation.Validator validator;

    @PostConstruct
    private void init() {
        validator = ((OptionalValidatorFactoryBean) validatorFactory).getValidator();
    }


    protected <T> MessageHandler validate(T t) {

        Set<ConstraintViolation<T>> errors = validator.validate(t);

        if (!errors.isEmpty()) {
            ConstraintViolation<T> error = errors.iterator().next();
            return new ErrorMessageHandler(
                    error.getMessage() + " for field '" + error.getPropertyPath() + "'!");
        }

        return new SuccessMessageHandler();
    }

    protected Map<String, Boolean> createRoleMap(UserDetails userDetails) {
        Map<String, Boolean> roles = new HashMap<String, Boolean>();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            roles.put(authority.getAuthority(), Boolean.TRUE);
        }

        return roles;
    }

    protected String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = ((UserDetails) authentication.getPrincipal());
        return principal.getUsername();
    }

    protected void ifNullException(Object... o){
        Arrays.asList(o).stream().forEach(t -> {
            if(t == null) {
              throw new ApplicationException();
            }
        });

    }

    public  Locale getLocale(){
        return LocaleContextHolder.getLocale();
    }

    public  String getMessage(String code){
        return messageSource.getMessage(code, null, getLocale());
    }



    protected boolean validateLength(int min, int max, String value) {
        return value != null && value.length() >= min && value.length() <= max;
    }

}
