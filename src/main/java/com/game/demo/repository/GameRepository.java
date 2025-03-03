package com.game.demo.repository;

import com.game.demo.entity.AppUser;
import com.game.demo.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


public interface GameRepository extends JpaRepository<Score,Long> {
   Set<Score> findByUser(AppUser user);
}
