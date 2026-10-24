package com.den.service.impl;

import com.den.model.ext.Message;
import com.den.model.ext.User;
import com.den.repository.MessagePersistence;
import com.den.service.CrudService;
import com.den.service.MessageService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Denis on 10.07.2015.
 */
@Service
public class MessageServiceImpl extends CrudService<Message> implements MessageService {

    @Autowired
    private MessagePersistence messagePersistence;


    @Override
    protected JpaRepository<Message, Long> getRepository() {
        return this.messagePersistence;
    }

    @Override
    public Set<Message> getUnreadMessagesByDate(User user, Date dateFrom, Date dateTo) {
        return messagePersistence.findByUserAndReadFalseAndDateBetween(user, dateFrom, dateTo);
    }

    @Override
    public void deleteAllTimeoutInvitation(LocalDateTime date) {
        messagePersistence.deleteTimeouted(date);
    }

    @Override
    public Set<Message> getUnreadMessages(User user) {
        return messagePersistence.findByUserAndReadFalse(user);
    }

    @Override
    public Page<Message> findByUser(User user, Pageable pageable) {
        return messagePersistence.findByUser(user, pageable);
    }

    @Override
    public void deleteByUserMessages(Long id, User user, String userFrom, User user1, String userFrom1) {
        messagePersistence.deleteByUserMessages(id, user, userFrom, user1, userFrom1);
    }

    @Override
    public void deleteMessages(User user, String userFrom, User user1, String userFrom1) {
        messagePersistence.deleteMessages(user, userFrom, user1, userFrom1);
    }

    @Override
    public List<Message> findByUserAndUserFrom(User user, String userFrom, Long id, User user1, String userFrom1, Long id1) {
        return messagePersistence.findByUserAndUserFromAndDeletedNotOrUserAndUserFromAndDeletedNotOrderByDateAsc(user, userFrom, id, user1, userFrom1, id1);
    }

    @Override
    public Page<Message> findByUserDistinct(User user, Pageable pageable) {
        int start = pageable.getPageNumber()*pageable.getPageNumber();
        int total = messagePersistence.findByUserDistinctCount(user.getId(), user.getUsername());
        return new PageImpl<>(messagePersistence.findByUserDistinct(user.getId(), user.getUsername(),start, pageable.getPageSize()), pageable, total);
    }

    @Override
    public void setMessagesWereSeen(String userFrom, User user) {
        messagePersistence.messagesWereSeen(userFrom, user);
    }

    @Override
    public void delete(Iterable<Message> iterable) {
        messagePersistence.delete(iterable);
    }

    @Override
    public boolean notExist(Message message) {
        return !messagePersistence.findByUserAndUserFromAndInvitationAndDateBetween(message.getUser(), message.getUserFrom(), message.getInvitation(), message.getDate().minusDays(1), message.getDate()).isEmpty();
    }


}
