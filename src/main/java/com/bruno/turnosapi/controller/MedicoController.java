package com.bruno.turnosapi.controller;

import com.bruno.turnosapi.dto.MedicoRequest;
import com.bruno.turnosapi.dto.MedicoResponse;
import com.bruno.turnosapi.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponse>> listarMedicos() {
        List<MedicoResponse> medicos = medicoService.allMedicos();
        return ResponseEntity.ok(medicos);
    }

    @PostMapping
    public ResponseEntity<MedicoResponse> crearMedico(@Valid @RequestBody MedicoRequest medico) {
        MedicoResponse medico1 = medicoService.guardarMedico(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(medico1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> buscarMedico(@PathVariable Long id) {
        MedicoResponse medico = medicoService.buscarPorId(id);
        return ResponseEntity.ok(medico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> actualizarMedico(@PathVariable Long id, @Valid @RequestBody MedicoRequest medico) {
        MedicoResponse medicoRes = medicoService.actualizarMedico(id, medico);
        return ResponseEntity.ok(medicoRes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMedico(@PathVariable Long id) {
        medicoService.eliminarMedico(id);
        return ResponseEntity.noContent().build();
    }
}
