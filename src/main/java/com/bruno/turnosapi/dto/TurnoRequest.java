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
    private @NotNull Long medicoId;
    private @NotNull Long pacienteId;
    private @FutureOrPresent LocalDate fecha;
    private @NotNull LocalTime hora;
}
