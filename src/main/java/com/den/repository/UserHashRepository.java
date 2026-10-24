package com.den.repository;

import com.den.model.ext.UserHash;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Denis on 18.02.2016.
 */
public interface UserHashRepository extends JpaRepository<UserHash, Long> {

    UserHash findByHash(String hash);

}
