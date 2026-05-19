package com.bruno.turnosapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoRequest {
    private String nombre;
    private String apellido;
    private String email;
    private String especialidad;
}
