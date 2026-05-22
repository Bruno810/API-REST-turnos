package com.bruno.turnosapi.service;

import com.bruno.turnosapi.dto.MedicoRequest;
import com.bruno.turnosapi.dto.MedicoResponse;
import com.bruno.turnosapi.exception.BusinessException;
import com.bruno.turnosapi.exception.ResourceNotFoundException;
import com.bruno.turnosapi.mapper.MedicoMapper;
import com.bruno.turnosapi.model.Medico;
import com.bruno.turnosapi.repository.MedicoRepository;
import com.bruno.turnosapi.repository.TurnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final TurnoRepository turnoRepository;

    public MedicoService(MedicoRepository medicoRepository, TurnoRepository turnoRepository) {
        this.medicoRepository = medicoRepository;
        this.turnoRepository = turnoRepository;
    }

    //Devuelve todos los medicos registrados
    public List<MedicoResponse> allMedicos(){
        return medicoRepository.findAll().stream().map(MedicoMapper::toResponse).toList();
    }

    //Busca si el id del medico existe, si no, devuelve una excepción
    public MedicoResponse buscarPorId(Long id) {
        return medicoRepository.findById(id).map(MedicoMapper::toResponse).orElseThrow(() -> new ResourceNotFoundException("Medico no encontrado"));
    }

    public MedicoResponse guardarMedico(MedicoRequest medico){
        Medico medico1 = MedicoMapper.toEntity(medico);
        Medico medicoGuardado = medicoRepository.save(medico1);
        return MedicoMapper.toResponse(medicoGuardado);
    }

    public MedicoResponse actualizarMedico(Long id, MedicoRequest medico){
        //Primero se fija si el médico existe para luego actualizarlo
        buscarPorId(id);
        Medico medico1 = MedicoMapper.toEntity(medico);
        medico1.setId(id);
        Medico guardado = medicoRepository.save(medico1);
        return  MedicoMapper.toResponse(guardado);
    }

    public void eliminarMedico(Long id){
        buscarPorId(id);
        if(turnoRepository.existsByMedicoId(id)){
            throw new BusinessException("No se puede eliminar el medico porque tiene turnos asociados");
        }
        medicoRepository.deleteById(id);
    }
}
