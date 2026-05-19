package com.bruno.turnosapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurnoRequest {
    private Long medicoId;
    private Long pacienteId;
    private LocalDate fecha;
    private LocalTime hora;
}
