package com.bruno.turnosapi.service;

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

    public List<Paciente> allPacientes() {
        return pacienteRepository.findAll();
    }

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public void actualizarPaciente(Long id, Paciente paciente) {
        buscarPorId(id);
        paciente.setId(id);
        pacienteRepository.save(paciente);
    }

    public void eliminarPaciente(Long id) {
        buscarPorId(id);
        pacienteRepository.deleteById(id);
    }
}
