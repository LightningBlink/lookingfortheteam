package com.den.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import javax.persistence.Persistence;

/**
 * Created by Denis on 21.12.2014.
 */
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class AppSecurityConfig extends WebSecurityConfigurerAdapter   {

    @Autowired
    private FakeUserDetailsService userDetailsService;

    @Autowired
    private AuthSuccess authSuccess;

    @Autowired
    private AuthFailure authFailure;

    @Autowired
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;


    @Autowired
    private RememberMeAuthenticationProvider rememberMeAuthenticationProvider;

    @Autowired
    private PersistentTokenBasedRememberMeServices tokenBasedRememberMeServices;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(rememberMeAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().rememberMe().rememberMeServices(tokenBasedRememberMeServices)
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPointUnauthorizedHandler).and()
                .formLogin()
                .successHandler(authSuccess)
                .failureHandler(authFailure)
                .loginPage("/login").loginProcessingUrl("/user/loginuser").and()
                .logout().logoutUrl("/logout").and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/message/**").hasRole("USER")
                .antMatchers("/team/**").hasRole("USER")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated().and().httpBasic();
    }

}