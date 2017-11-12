package com.stefano.briky.repository;

import com.stefano.briky.model.Tokens;
import com.stefano.briky.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Tokens, Integer> {

    @Query("select u from Tokens t join t.user u where t.value=:token")
    Optional<Users> findUserByToken(@Param("token") String token);

    @Query("select t from Tokens t where t.userId=:userId")
    Tokens findbyUserId(@Param("userId") int userId);
}
