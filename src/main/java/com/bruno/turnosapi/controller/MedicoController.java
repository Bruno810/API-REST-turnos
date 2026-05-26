package com.bruno.turnosapi.controller;

import com.bruno.turnosapi.dto.MedicoRequest;
import com.bruno.turnosapi.dto.MedicoResponse;
import com.bruno.turnosapi.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
@Tag(name = "Médicos", description = "Gestión de médicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    @Operation(summary = "Ver todos los médicos")
    public ResponseEntity<List<MedicoResponse>> listarMedicos() {
        List<MedicoResponse> medicos = medicoService.allMedicos();
        return ResponseEntity.ok(medicos);
    }

    @PostMapping
    @Operation(summary = "Crear un médico")
    public ResponseEntity<MedicoResponse> crearMedico(@Valid @RequestBody MedicoRequest medico) {
        MedicoResponse medico1 = medicoService.guardarMedico(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(medico1);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar médico por Id")
    public ResponseEntity<MedicoResponse> buscarMedico(@PathVariable Long id) {
        MedicoResponse medico = medicoService.buscarPorId(id);
        return ResponseEntity.ok(medico);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar médico")
    public ResponseEntity<MedicoResponse> actualizarMedico(@PathVariable Long id, @Valid @RequestBody MedicoRequest medico) {
        MedicoResponse medicoRes = medicoService.actualizarMedico(id, medico);
        return ResponseEntity.ok(medicoRes);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar médico")
    public ResponseEntity<String> eliminarMedico(@PathVariable Long id) {
        medicoService.eliminarMedico(id);
        String mensaje = "Medico eliminado correctamente";
        return ResponseEntity.ok(mensaje);
    }
}
