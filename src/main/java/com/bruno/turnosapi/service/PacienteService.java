package com.bruno.turnosapi.service;

import com.bruno.turnosapi.exception.ResourceNotFoundException;
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
    public List<Paciente> allPacientes() {
        return pacienteRepository.findAll();
    }

    //Busca si el id del paciente existe, si no, devuelve una excepción
    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
    }

    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public void actualizarPaciente(Long id, Paciente paciente) {
        //Primero se fija si el paciente existe para luego actualizarlo
        buscarPorId(id);
        paciente.setId(id);
        pacienteRepository.save(paciente);
    }

    public void eliminarPaciente(Long id) {
        buscarPorId(id);
        pacienteRepository.deleteById(id);
    }
}
