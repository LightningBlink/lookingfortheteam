package com.den.model.wrapper;

import com.den.model.ext.Message;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Denis on 15.07.2015.
 */
public class MessageEvent {

    private Long date;

    private Set<Message> messages = new HashSet<>(0);


    public MessageEvent(Long date, Set<Message> messages) {
        this.date = date;
        this.messages = messages;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
