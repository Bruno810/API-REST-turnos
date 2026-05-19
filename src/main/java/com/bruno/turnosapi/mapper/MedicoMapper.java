package com.bruno.turnosapi.mapper;

import com.bruno.turnosapi.dto.MedicoRequest;
import com.bruno.turnosapi.dto.MedicoResponse;
import com.bruno.turnosapi.model.Medico;

public class MedicoMapper {

    public static Medico toEntity(MedicoRequest request){
        Medico medico = new Medico();
        medico.setNombre(request.getNombre());
        medico.setApellido(request.getApellido());
        medico.setEmail(request.getEmail());
        medico.setEspecialidad(request.getEspecialidad());
        return medico;
    }

    public static MedicoResponse toResponse(Medico medico){
        MedicoResponse medicoResponse = new MedicoResponse();
        medicoResponse.setId(medico.getId());
        medicoResponse.setNombre(medico.getNombre());
        medicoResponse.setApellido(medico.getApellido());
        medicoResponse.setEmail(medico.getEmail());
        medicoResponse.setEspecialidad(medico.getEspecialidad());
        return medicoResponse;
    }
}
