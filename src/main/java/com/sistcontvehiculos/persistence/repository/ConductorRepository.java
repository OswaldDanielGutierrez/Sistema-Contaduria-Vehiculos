package com.sistcontvehiculos.persistence.repository;

import com.sistcontvehiculos.persistence.entity.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Long> {
}
