package com.bruno.turnosapi.controller;

import com.bruno.turnosapi.dto.TurnoRequest;
import com.bruno.turnosapi.dto.TurnoResponse;
import com.bruno.turnosapi.model.EstadoTurno;
import com.bruno.turnosapi.service.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    private final TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @GetMapping
    public ResponseEntity<List<TurnoResponse>> listarTurnos() {
        List<TurnoResponse> turnos = turnoService.obtenerTurnos();
        return ResponseEntity.ok(turnos);
    }

    @PostMapping
    public ResponseEntity<TurnoResponse> crearTurno(@RequestBody TurnoRequest turno) {
        TurnoResponse turno1 = turnoService.crearTurno(turno);
        return ResponseEntity.status(201).body(turno1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoResponse> obtenerTurno(@PathVariable Long id) {
        TurnoResponse turno = turnoService.buscarPorId(id);
        return ResponseEntity.ok(turno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Long id) {
        turnoService.eliminarTurno(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Void> cambiarEstado(@PathVariable Long id, @RequestBody EstadoTurno estado) {
        turnoService.cambiarEstado(estado, id);
        return ResponseEntity.noContent().build();
    }
}
