package com.youcode.pigeonskyracev2.repository;


import com.youcode.pigeonskyracev2.entity.Pigeon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository

public interface PigeonRepository  extends JpaRepository<Pigeon,Long> {
    Optional<Pigeon>findById(Long id);
}
