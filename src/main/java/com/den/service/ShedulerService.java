package com.den.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by Denis on 02.02.2016.
 */
@Service
public class ShedulerService {

    @Autowired
    private MessageService messageService;

    @Scheduled(fixedDelay = 1000 * 60 * 20)
    public void doSomething() {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));
        dateTime = dateTime.minusDays(1);
        messageService.deleteAllTimeoutInvitation(dateTime);
    }
}
