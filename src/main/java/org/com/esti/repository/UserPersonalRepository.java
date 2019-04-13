package org.com.esti.repository;

import org.com.esti.domain.entities.UserPersonal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPersonalRepository extends JpaRepository<UserPersonal, Long> {
    Optional<UserPersonal> findByUserId(Long userId);
}
