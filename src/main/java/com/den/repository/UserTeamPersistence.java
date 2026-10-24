package com.den.repository;

import com.den.model.UserTeam;
import com.den.model.ext.Game;
import com.den.model.ext.User;
import com.den.model.id.UserTeamID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by Denis on 16.10.2015.
 */
public interface UserTeamPersistence extends JpaRepository<UserTeam, UserTeamID> {

    @Modifying
    @Transactional
    @Query("DELETE FROM UserTeam ut WHERE ut.pk.user = ?1 AND ut.pk.game in (?2) ")
    public void deleteUserGames(User user, Set<Game> games);

    @Query("SELECT ut FROM UserTeam ut WHERE ut.pk.user =?1 AND ut.post = 'LEADER'")
    public Set<UserTeam> getUserTeamsWhereUserIsLeader(User user);
}
