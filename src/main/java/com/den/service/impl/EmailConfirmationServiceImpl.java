package com.den.service.impl;

import com.den.model.ext.User;
import com.den.model.ext.UserHash;
import com.den.service.EmailConfirmationService;
import com.den.service.UserHashService;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Denis on 29.01.2015.
 */
@Service
public class EmailConfirmationServiceImpl implements EmailConfirmationService{

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserHashService userHashService;

    @Value("${application.domain}")
    private String domain;

    @Value("${application.mail}")
    private String mail;

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private MessageSource messageSource;

    public EmailConfirmationServiceImpl() {
    }

    public void register(User user) {

        Map<String,Object> model = new HashMap<>();
        send(user, model, "confirmation", messageSource.getMessage("emailConfirmationServiceImpl.uaregistered", null, LocaleContextHolder.getLocale()));


    }

    public void send(User user, Map<String,Object> model, String action, String subject) {
        final UserHash userHash =  userHashService.save(new UserHash(user));
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
            message.setSubject(subject);
            message.setTo(user.getEmail());
            message.setFrom(mail);
            model.put("hash", userHash.getHash());
            model.put("user", user);
            model.put("domain",domain);
            String text = VelocityEngineUtils.mergeTemplateIntoString(
                    velocityEngine, "templates/" + action + "_" + LocaleContextHolder.getLocale().getLanguage() +".vm", "UTF-8",  model);
            message.setText(text, true);
        };
        this.mailSender.send(preparator);
    }


}
