package com.bruno.turnosapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PacienteRequest {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
}
