package com.bruno.turnosapi.controller;

import com.bruno.turnosapi.model.Medico;
import com.bruno.turnosapi.service.MedicoService;
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
    public ResponseEntity<List<Medico>> listarMedicos() {
        List<Medico> medicos = medicoService.allMedicos();
        return ResponseEntity.ok(medicos);
    }

    @PostMapping
    public ResponseEntity<Medico> crearMedico(@RequestBody Medico medico) {
        Medico medico1 = medicoService.guardarMedico(medico);
        return ResponseEntity.status(201).body(medico1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarMedico(@PathVariable Long id) {
        Medico medico = medicoService.buscarPorId(id);
        return ResponseEntity.ok(medico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarMedico(@PathVariable Long id, @RequestBody Medico medico) {
        medicoService.actualizarMedico(id, medico);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMedico(@PathVariable Long id) {
        medicoService.eliminarMedico(id);
        return ResponseEntity.noContent().build();
    }
}
