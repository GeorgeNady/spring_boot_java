package com.george.demo.springdemo.springdemo.user;

import org.springframework.data.geo.GeoResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// for dependency injection
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select s from User s where s.email = ?1")
    Optional<User> findUserByEmail (String email);

    @Query("select count(u) from User u")
    long usersCount();


}
