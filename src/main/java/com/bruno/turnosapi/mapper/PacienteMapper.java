package com.bruno.turnosapi.mapper;

import com.bruno.turnosapi.dto.PacienteRequest;
import com.bruno.turnosapi.dto.PacienteResponse;
import com.bruno.turnosapi.model.Paciente;

public class PacienteMapper {

    public static Paciente toEntity(PacienteRequest request){
        Paciente paciente = new Paciente();
        paciente.setNombre(request.getNombre());
        paciente.setApellido(request.getApellido());
        paciente.setEmail(request.getEmail());
        paciente.setTelefono(request.getTelefono());
        return paciente;
    }

    public static PacienteResponse toResponse(Paciente paciente){
        PacienteResponse pacienteResponse = new PacienteResponse();
        pacienteResponse.setId(paciente.getId());
        pacienteResponse.setNombre(paciente.getNombre());
        pacienteResponse.setApellido(paciente.getApellido());
        pacienteResponse.setEmail(paciente.getEmail());
        pacienteResponse.setTelefono(paciente.getTelefono());
        return pacienteResponse;
    }
}
