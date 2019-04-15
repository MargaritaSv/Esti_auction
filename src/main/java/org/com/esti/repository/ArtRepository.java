package org.com.esti.repository;

import org.com.esti.domain.entities.Art;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtRepository extends JpaRepository<Art, Long> {
    List<Art> findAll();
}
