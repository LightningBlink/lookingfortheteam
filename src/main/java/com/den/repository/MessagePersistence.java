package com.den.repository;

import com.den.model.ext.Message;
import com.den.model.ext.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Created by Denis on 10.07.2015.
 */
public interface MessagePersistence extends JpaRepository<Message, Long> {

    Set<Message> findByUserAndReadFalseAndDateBetween(User user, Date dateFrom, Date dateTo);

    Set<Message> findByUserAndReadFalse(User user);

    Page<Message> findByUser(User user, Pageable pageable);

    @Query("DELETE FROM Message m WHERE m.invitation = 1 AND m.date < ?1")
    @Modifying
    @Transactional
    void deleteTimeouted(LocalDateTime date);


    @Query("UPDATE Message m SET m.deleted = ?1 WHERE (m.user = ?2 and m.userFrom = ?3) OR (m.user = ?4 and m.userFrom = ?5)")
    @Modifying
    @Transactional
    void deleteByUserMessages(Long id, User user, String userFrom, User user1, String userFrom1);

    @Query("DELETE FROM Message m WHERE (m.user = ?1 and m.userFrom = ?2) OR (m.user = ?3 and m.userFrom = ?4)")
    @Modifying
    @Transactional
    void deleteMessages(User user, String userFrom, User user1, String userFrom1);

    List<Message> findByUserAndUserFromAndDeletedNotOrUserAndUserFromAndDeletedNotOrderByDateAsc(User user , String userFrom, Long id, User user1, String userFrom1, Long id1);


        @Query(nativeQuery = true, value = "SELECT * FROM (SELECT * FROM ((SELECT m.id, m.date, m.game, m.invitation, m.message, m.read_ed, m.team_id, u.username as 'user_from', ?1 as 'id_user' , m.is_show, m.deleted  FROM message m " +
                "join user u on m.id_user = u.id " +
                "where m.user_from = ?2 and m.deleted <> ?1 and m.is_show = 1) UNION " +
                "(SELECT m.* FROM message m " +
                "where m.id_user=?1 and m.deleted <> ?1  and m.is_show = 1)) as k ORDER BY k.date DESC) as l GROUP BY user_from HAVING max(date) LIMIT ?3, ?4")
    List<Message> findByUserDistinct(Long id, String username, int start, int limit);

    @Query(nativeQuery = true, value = "SELECT count(*) FROM (SELECT count(date) FROM (SELECT * FROM ((SELECT m.id, m.date, m.game, m.invitation, m.message, m.read_ed, m.team_id, u.username as 'user_from', ?1 as 'id_user' , m.is_show, m.deleted  FROM message m " +
            "join user u on m.id_user = u.id " +
            "where m.user_from = ?2 and m.deleted <> ?1 and m.is_show = 1) UNION " +
            "(SELECT m.* FROM message m " +
            "where m.id_user=?1 and m.deleted <> ?1 and m.is_show = 1)) as k ORDER BY k.date DESC) as l GROUP BY user_from HAVING max(date)) as p")
    int findByUserDistinctCount(Long id, String username);



    @Query("update Message m SET m.read = true WHERE m.userFrom = ?1 and m.user = ?2")
    @Modifying
    @Transactional
    void messagesWereSeen(String userFrom, User user);

    List<Message> findByUserAndUserFromAndInvitationAndDateBetween(User user, String userFrom, Boolean invitation, LocalDateTime dateFrom, LocalDateTime dateTo);

}
