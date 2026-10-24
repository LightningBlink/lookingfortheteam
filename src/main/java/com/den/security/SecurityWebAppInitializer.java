package com.den.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by Denis on 21.12.2014.
 */
public class SecurityWebAppInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebAppInitializer() {
        super(AppSecurityConfig.class);
    }
}
