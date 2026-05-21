package com.bruno.turnosapi.service;

import com.bruno.turnosapi.dto.PacienteRequest;
import com.bruno.turnosapi.dto.PacienteResponse;
import com.bruno.turnosapi.exception.ResourceNotFoundException;
import com.bruno.turnosapi.mapper.PacienteMapper;
import com.bruno.turnosapi.model.Paciente;
import com.bruno.turnosapi.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    //Devuelve todos los pacientes registrados
    public List<PacienteResponse> allPacientes() {
        return pacienteRepository.findAll().stream().map(PacienteMapper::toResponse).toList();
    }

    //Busca si el id del paciente existe, si no, devuelve una excepción
    public PacienteResponse buscarPorId(Long id) {
        return pacienteRepository.findById(id).map(PacienteMapper::toResponse).orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
    }

    public PacienteResponse guardarPaciente(PacienteRequest paciente) {
        Paciente paciente1 = PacienteMapper.toEntity(paciente);
        Paciente pacienteGuardado = pacienteRepository.save(paciente1);
        return PacienteMapper.toResponse(pacienteGuardado);
    }

    public PacienteResponse actualizarPaciente(Long id, PacienteRequest paciente) {
        //Primero se fija si el paciente existe para luego actualizarlo
        buscarPorId(id);
        Paciente paciente1 = PacienteMapper.toEntity(paciente);
        paciente1.setId(id);
        Paciente guardado = pacienteRepository.save(paciente1);
        return PacienteMapper.toResponse(guardado);
    }

    public void eliminarPaciente(Long id) {
        buscarPorId(id);
        pacienteRepository.deleteById(id);
    }
}
