package com.bruno.turnosapi.repository;

import com.bruno.turnosapi.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface TurnoRepository extends JpaRepository<Turno, Long> {

    boolean existsByMedicoIdAndFechaAndHora(Long medicoId, LocalDate fecha, LocalTime hora);
}
