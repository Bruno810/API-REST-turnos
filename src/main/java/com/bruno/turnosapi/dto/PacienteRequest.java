package com.bruno.turnosapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {
    private @NotBlank String nombre;
    private @NotBlank String apellido;
    private @Email String email;
    private @NotBlank String telefono;
}
