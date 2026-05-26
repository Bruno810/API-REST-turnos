package com.bruno.turnosapi.controller;

import com.bruno.turnosapi.dto.PacienteRequest;
import com.bruno.turnosapi.dto.PacienteResponse;
import com.bruno.turnosapi.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@Tag(name = "Paciente", description = "Gestión de pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    @Operation(summary = "Ver todos los pacientes")
    public ResponseEntity<List<PacienteResponse>> listarPacientes() {
        List<PacienteResponse> pacientes = pacienteService.allPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @PostMapping
    @Operation(summary = "Crear paciente")
    public ResponseEntity<PacienteResponse> crearPaciente(@Valid @RequestBody PacienteRequest paciente){
        PacienteResponse paciente1 = pacienteService.guardarPaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(paciente1);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar paciente por Id")
    public ResponseEntity<PacienteResponse> buscarPaciente(@PathVariable Long id){
        PacienteResponse paciente = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(paciente);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar paciente")
    public ResponseEntity<PacienteResponse> actualizarPaciente(@Valid @RequestBody PacienteRequest paciente, @PathVariable Long id){
        PacienteResponse pacienteRes = pacienteService.actualizarPaciente(id, paciente);
        return ResponseEntity.ok(pacienteRes);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar paciente")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id){
        pacienteService.eliminarPaciente(id);
        String mensaje = "Paciente eliminado correctamente";
        return ResponseEntity.ok(mensaje);
    }
}
