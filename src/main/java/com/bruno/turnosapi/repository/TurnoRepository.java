package com.bruno.turnosapi.repository;

import com.bruno.turnosapi.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
}
