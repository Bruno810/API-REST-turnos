package com.bruno.turnosapi.dto;

import com.bruno.turnosapi.model.EstadoTurno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurnoResponse {
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private EstadoTurno estado;
    private String nombreMedico;
    private String nombrePaciente;
}
