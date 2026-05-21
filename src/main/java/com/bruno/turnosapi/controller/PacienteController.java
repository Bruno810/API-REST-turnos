package com.bruno.turnosapi.controller;

import com.bruno.turnosapi.dto.PacienteRequest;
import com.bruno.turnosapi.dto.PacienteResponse;
import com.bruno.turnosapi.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listarPacientes() {
        List<PacienteResponse> pacientes = pacienteService.allPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @PostMapping
    public ResponseEntity<PacienteResponse> crearPaciente(@Valid @RequestBody PacienteRequest paciente){
        PacienteResponse paciente1 = pacienteService.guardarPaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(paciente1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscarPaciente(@PathVariable Long id){
        PacienteResponse paciente = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarPaciente(@Valid @RequestBody PacienteRequest paciente, @PathVariable Long id){
        pacienteService.actualizarPaciente(id, paciente);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id){
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
