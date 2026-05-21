package com.bruno.turnosapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoRequest {
    private @NotBlank String nombre;
    private @NotBlank String apellido;
    private @Email String email;
    private @NotBlank String especialidad;
}
