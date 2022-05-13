package com.gdscewha.ewhaclub.repository;

import com.gdscewha.ewhaclub.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> findByNameContainingOrEngNameContainingIgnoreCase(@Param("korKey") String korKey,
                                                                 @Param("engKey")String engKey);
}
