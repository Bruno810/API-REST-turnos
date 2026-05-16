package com.bruno.turnosapi.repository;

import com.bruno.turnosapi.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
