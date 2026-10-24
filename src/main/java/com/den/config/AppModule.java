package com.den.config;

import com.den.security.FakeUserDetailsService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.jms.ConnectionFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Denis on 22.12.2014.
 */
@Configuration
@ComponentScan(basePackages={"com.den"})
public class AppModule  {

    @Autowired
    private FakeUserDetailsService userDetailsService;


    @Bean
    public JavaMailSenderImpl mailSender(){
        JavaMailSenderImpl mailSender = new  JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("onesLightning@gmail.com");
        mailSender.setPassword("GeroyII22");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.debug", "true");
        properties.put("mail.mime.charset", "utf-8");
        mailSender.setJavaMailProperties(properties);
        return mailSender;
    }

    @Bean
    public VelocityEngine engineFactoryBean(){
        final VelocityEngineFactoryBean velocityEngineFactoryBean=new VelocityEngineFactoryBean();
        Properties velocityEngineProperties=new Properties();
        velocityEngineProperties.setProperty("resource.loader","сlass");
        velocityEngineProperties.setProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngineFactoryBean.setVelocityProperties(velocityEngineProperties);
        try {
            return velocityEngineFactoryBean.createVelocityEngine();
        }
        catch (  IOException e) {
            throw new RuntimeException("Unable to create com.den.velocity engine instance");
        }
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/sg");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource());
        return db;
    }

    @Bean
    public RememberMeAuthenticationProvider provider(){
        return new RememberMeAuthenticationProvider("springRocks");
    }

    @Bean
    public PersistentTokenBasedRememberMeServices tokenBasedRememberMeServices(){
        PersistentTokenBasedRememberMeServices rememberMeServices =  new PersistentTokenBasedRememberMeServices("springRocks", userDetailsService, persistentTokenRepository());
        rememberMeServices.setCookieName("authToken");
        rememberMeServices.setAlwaysRemember(true);
        // 30 days
        rememberMeServices.setTokenValiditySeconds(60*60*24*30);
        return rememberMeServices;
    }

//    @Bean
//    public ConnectionFactory connectionFactory() {
//        return new ActiveMQConnectionFactory("vm://embedded?broker.persistent=false,useShutdownHook=false");
//    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }


    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new MyCustomizer();
    }

    private static class MyCustomizer implements EmbeddedServletContainerCustomizer {

        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
            container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error"));
        }

    }
}
