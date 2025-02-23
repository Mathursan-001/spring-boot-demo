package com.game.demo.repository;

import com.game.demo.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<AppUser,Long> {

    Optional<AppUser> findByUserName(String userName);

}
