package com.den.service;

import com.den.model.ext.Message;
import com.den.model.ext.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Created by Denis on 10.07.2015.
 */
public interface MessageService extends CrudInterface<Message> {

    Set<Message> getUnreadMessagesByDate(User user, Date dateFrom, Date dateTo);

    void deleteAllTimeoutInvitation(LocalDateTime date);

    Set<Message> getUnreadMessages(User user);

    Page<Message> findByUser(User user, Pageable pageable);

    void deleteByUserMessages(Long id, User user, String userFrom, User user1, String userFrom1);

    void deleteMessages(User user, String userFrom, User user1, String userFrom1);

    List<Message> findByUserAndUserFrom(User user, String userFrom, Long id, User user1, String userFrom1, Long id1);

    Page<Message> findByUserDistinct(User user, Pageable pageable);

    void setMessagesWereSeen(String userFrom, User user);

    void delete(Iterable<Message> iterable);

    boolean notExist(Message message);

}
