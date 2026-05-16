package com.bruno.turnosapi.service;

import com.bruno.turnosapi.model.Medico;
import com.bruno.turnosapi.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<Medico> allMedicos(){
        return medicoRepository.findAll();
    }

    public Medico buscarPorId(Long id) {
        return medicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Medico no encontrado"));
    }

    public Medico guardarMedico(Medico medico){
        return medicoRepository.save(medico);
    }

    public void actualizarMedico(Long id, Medico medico){
        buscarPorId(id);
        medico.setId(id);
        medicoRepository.save(medico);
    }

    public void eliminarMedico(Long id){
        buscarPorId(id);
        medicoRepository.deleteById(id);
    }
}
