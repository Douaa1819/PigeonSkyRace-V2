package com.youcode.pigeonskyracev2.repository;

import com.youcode.pigeonskyracev2.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompetionRepository extends JpaRepository<Competition, Long> {
    Optional<Competition> findById(Long id);
}
