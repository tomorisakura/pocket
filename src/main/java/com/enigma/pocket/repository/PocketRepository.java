package com.enigma.pocket.repository;

import com.enigma.pocket.entity.Pocket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PocketRepository extends JpaRepository<Pocket, String> {
}
