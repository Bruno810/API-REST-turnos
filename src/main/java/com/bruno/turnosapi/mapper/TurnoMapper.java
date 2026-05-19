package com.bruno.turnosapi.mapper;

import com.bruno.turnosapi.dto.TurnoRequest;
import com.bruno.turnosapi.dto.TurnoResponse;
import com.bruno.turnosapi.model.Medico;
import com.bruno.turnosapi.model.Paciente;
import com.bruno.turnosapi.model.Turno;

public class TurnoMapper {

    public static Turno toEntity(TurnoRequest request){
        Turno turno = new Turno();
        Medico medico = new Medico();
        Paciente paciente = new Paciente();
        medico.setId(request.getMedicoId());
        paciente.setId(request.getPacienteId());
        turno.setMedico(medico);
        turno.setPaciente(paciente);
        turno.setFecha(request.getFecha());
        turno.setHora(request.getHora());
        return turno;
    }

    public static TurnoResponse toResponse(Turno turno){
        TurnoResponse turnoResponse = new TurnoResponse();
        turnoResponse.setId(turno.getId());
        turnoResponse.setFecha(turno.getFecha());
        turnoResponse.setHora(turno.getHora());
        turnoResponse.setEstado(turno.getEstado());
        turnoResponse.setNombreMedico(turno.getMedico().getNombre() + " " +  turno.getMedico().getApellido());
        turnoResponse.setNombrePaciente(turno.getPaciente().getNombre() + " " +  turno.getPaciente().getApellido());
        return turnoResponse;
    }
}
