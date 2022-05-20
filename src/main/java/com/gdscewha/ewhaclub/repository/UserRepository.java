package com.gdscewha.ewhaclub.repository;

import com.gdscewha.ewhaclub.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
