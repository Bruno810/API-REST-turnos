package com.bruno.turnosapi.service;

import com.bruno.turnosapi.exception.ResourceNotFoundException;
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

    //Devuelve todos los medicos registrados
    public List<Medico> allMedicos(){
        return medicoRepository.findAll();
    }

    //Busca si el id del medico existe, si no, devuelve una excepción
    public Medico buscarPorId(Long id) {
        return medicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Medico no encontrado"));
    }

    public Medico guardarMedico(Medico medico){
        return medicoRepository.save(medico);
    }

    public void actualizarMedico(Long id, Medico medico){
        //Primero se fija si el médico existe para luego actualizarlo
        buscarPorId(id);
        medico.setId(id);
        medicoRepository.save(medico);
    }

    public void eliminarMedico(Long id){
        buscarPorId(id);
        medicoRepository.deleteById(id);
    }
}
