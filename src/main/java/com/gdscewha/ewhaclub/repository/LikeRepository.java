package com.gdscewha.ewhaclub.repository;

import com.gdscewha.ewhaclub.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
}
