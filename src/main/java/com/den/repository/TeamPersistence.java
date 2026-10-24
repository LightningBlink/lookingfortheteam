package com.den.repository;

import com.den.model.ext.Game;
import com.den.model.ext.Team;
import com.den.model.ext.User;
import com.den.model.wrapper.TeammateInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Denis on 22.05.2015.
 */
public interface TeamPersistence extends JpaRepository<Team,Long> {

    @Query("SELECT t FROM Team t  join t.userTeams ut WHERE ?1 = ut.pk.game and t.name LIKE  ?2% and t.nation = ?3 GROUP BY t ORDER BY t.date DESC")
    Page<Team> findLookingTeams(Game game, Pageable pageable, String keyword, String country);


    @Query("SELECT t FROM Team t  join t.userTeams ut WHERE ?1 = ut.pk.game and t.name LIKE  ?2%  GROUP BY t ORDER BY t.date DESC")
    Page<Team> findLookingTeamsWithoutCountry(Game game, Pageable pageable, String keyword);


    @Query("SELECT ut.pk.user.username as username, ut.pk.user.id as id, ut.pk.game.name as game FROM Team t  inner join t.userTeams ut WHERE t.id = ?1 AND ut.post <> 'LEADER' ")
    List<Object[]> findTeamUsers(Long id);

    @Query("SELECT t FROM Team t  join t.userTeams ut WHERE ?1 = ut.pk.game and t.name  like ?3% and t.nation = ?4 GROUP BY t HAVING t <> ?2 ORDER BY t.date  DESC")
    Page<Team> findByGame( Game game, Team team, Pageable pageable, String keyword, String country);


    @Query("SELECT t FROM Team t  join t.userTeams ut WHERE ?1 = ut.pk.game and t.name  like ?3%  GROUP BY t HAVING t <> ?2 ORDER BY t.date  DESC")
    Page<Team> findByGameWithoutCountry( Game game, Team team, Pageable pageable, String keyword);


    Team findByName(String name);
}
