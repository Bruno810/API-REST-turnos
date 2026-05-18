package com.bruno.turnosapi.service;

import com.bruno.turnosapi.exception.BusinessException;
import com.bruno.turnosapi.exception.ResourceNotFoundException;
import com.bruno.turnosapi.model.EstadoTurno;
import com.bruno.turnosapi.model.Turno;
import com.bruno.turnosapi.repository.MedicoRepository;
import com.bruno.turnosapi.repository.PacienteRepository;
import com.bruno.turnosapi.repository.TurnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {

    private final TurnoRepository turnoRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public TurnoService(TurnoRepository turnoRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository) {
        this.turnoRepository = turnoRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public Turno crearTurno(Turno turno) {
        //Primero me fijo si tanto paciente como médico existen en mi base de datos
        Long idMedico = turno.getMedico().getId();
        medicoRepository.findById(idMedico).orElseThrow(() -> new ResourceNotFoundException("Medico no encontrado"));

        Long idPaciente = turno.getPaciente().getId();
        pacienteRepository.findById(idPaciente).orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));

        //Ahora me fijo si la fecha y la hora están libres
        if (turnoRepository.existsByMedicoIdAndFechaAndHora(idMedico, turno.getFecha(), turno.getHora())) {
            //Caso ya hay un turno registrado a esa fecha y hora
            throw new BusinessException("Ya existe un turno en esta fecha y hora");
        }

        //Declaro el turno como pendiente
        turno.setEstado(EstadoTurno.PENDIENTE);

        //Guardo y devuelvo el turno
        return turnoRepository.save(turno);
    }

    //Devuelve todos los turnos registrados
    public List<Turno> obtenerTurnos(){
        return turnoRepository.findAll();
    }

    //Busca si el id del turno existe, si no, devuelve una excepción
    public Turno buscarPorId(Long id){
        return turnoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado"));
    }

    public void eliminarTurno(Long id){
        buscarPorId(id);
        turnoRepository.deleteById(id);
    }

    public void cambiarEstado(EstadoTurno estado, Long id){
        Turno turno = buscarPorId(id);
        EstadoTurno estadoActual = turno.getEstado();

        //Me fijo si se puede cambiar el estado de un turno
        switch (estadoActual) {
            //Cuando el estado está pendiente solo puede pasar a CONFIRMADO o CANCELADO
            case PENDIENTE:
                if(estado == EstadoTurno.CONFIRMADO || estado == EstadoTurno.CANCELADO){
                    turno.setEstado(estado);
                }
                else {
                    throw new BusinessException("Estado no valido, actualmente el estado del turno se encuentra en Pendiente");
                }
                break;

            //Cuando el estado está CONFIRMADO solo puede pasar a COMPLETADO o CANCELADO
            case CONFIRMADO:
                if(estado == EstadoTurno.COMPLETADO || estado == EstadoTurno.CANCELADO){
                    turno.setEstado(estado);
                }
                else {
                    throw new BusinessException("Estado no valido, actualmente el estado del turno se encuentra en Confirmado");
                }
                break;

            case COMPLETADO:
                throw new BusinessException("El turno ya se encuentra completado");

            case CANCELADO:
                throw new BusinessException("El turno ya se encuentra cancelado");
        }

        //Actualizo el estado del turno
        turnoRepository.save(turno);
    }
}
