package com.bruno.turnosapi.repository;

import com.bruno.turnosapi.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
