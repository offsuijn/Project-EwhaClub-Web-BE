package com.gdscewha.ewhaclub.repository;

import com.gdscewha.ewhaclub.domain.Club;
import com.gdscewha.ewhaclub.domain.Like;
import com.gdscewha.ewhaclub.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndClub(@Param("user") User user, @Param("club") Club club);

    List<Like> findByUser(User user);
}
