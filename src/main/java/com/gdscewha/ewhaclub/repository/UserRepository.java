package com.gdscewha.ewhaclub.repository;

import com.gdscewha.ewhaclub.domain.Club;
import com.gdscewha.ewhaclub.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNickName(@Param("nickname") String nickname);
}
