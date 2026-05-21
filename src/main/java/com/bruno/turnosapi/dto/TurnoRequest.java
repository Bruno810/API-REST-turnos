package com.bruno.turnosapi.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurnoRequest {
    @NotNull (message = "El Id del medico no puede estar vacío")
    private Long medicoId;

    @NotNull (message = "El Id del paciente no puede estar vacío")
    private Long pacienteId;

    @NotNull(message = "La fecha no puede estar vacía")
    @FutureOrPresent(message = "La fecha debe ser del dia de hoy o del futuro")
    private LocalDate fecha;

    @NotNull (message = "La hora no puede estar vacía")
    private LocalTime hora;
}
