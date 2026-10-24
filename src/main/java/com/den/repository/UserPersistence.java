package com.den.repository;

import com.den.model.ext.Game;
import com.den.model.ext.User;
import com.den.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by Denis on 23.09.2014.
 */
@Transactional
public interface UserPersistence extends JpaRepository<User, Long> {

    public User getUserByUsername(String username);

    public User getUserByUsernameOrEmail(String username, String email);

    @Query(nativeQuery = true, value = "SELECT u.* FROM (SELECT id_user FROM user_team ut WHERE ut.id_game = ?1) as a right join user u on a.id_user = u.id join game_user gu on u.id = gu.id_user WHERE a.id_user is NULL AND gu.id_game = ?1 AND u.username like ?2%  AND u.country = ?3  ORDER BY u.date DESC LIMIT ?4, ?5")
    public List<User> getByGameLookingPlayers(Long gameId, String keyword, String country,  int start, int limit);

    @Query(nativeQuery = true, value = "SELECT count(*) FROM (SELECT id_user FROM user_team ut WHERE ut.id_game = ?1) as a right join user u on a.id_user = u.id join game_user gu on u.id = gu.id_user WHERE a.id_user is NULL AND gu.id_game = ?1 AND u.username like ?2%  AND u.country = ?3")
    public int getByGameLookingPlayersCount(Long gameId, String keyword, String country);


    @Query(nativeQuery = true, value = "SELECT u.* FROM (SELECT id_user FROM user_team ut WHERE ut.id_game = ?1) as a right join user u on a.id_user = u.id join game_user gu on u.id = gu.id_user WHERE a.id_user is NULL AND gu.id_game = ?1 AND u.username like ?2% ORDER BY u.date DESC LIMIT ?3, ?4")
    public List<User> getByGameLookingPlayersWithoutCountry(Long gameId, String keyword, int start, int limit);


    @Query(nativeQuery = true, value = "SELECT count(*) FROM (SELECT id_user FROM user_team ut WHERE ut.id_game = ?1) as a right join user u on a.id_user = u.id join game_user gu on u.id = gu.id_user WHERE a.id_user is NULL AND gu.id_game = ?1 AND u.username like ?2%")
    public int getByGameLookingPlayersWithoutCountryCount(Long gameId, String keyword);


    public User findByEmail(String email);


}

//Native query for method getByGameLookingPlayers

//SELECT u.*, ut.id_game FROM user u  join game_user gu on u.id =  gu.id_user join user_team ut on u.id = ut.id_user WHERE  gu.id_game = 2  GROUP BY u.id HAVING 2 not in (ut.id_game)


