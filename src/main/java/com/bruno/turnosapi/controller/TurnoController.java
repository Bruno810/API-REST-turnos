package com.bruno.turnosapi.controller;

import com.bruno.turnosapi.model.EstadoTurno;
import com.bruno.turnosapi.model.Turno;
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
    public ResponseEntity<List<Turno>> listarTurnos() {
        List<Turno> turnos = turnoService.obtenerTurnos();
        return ResponseEntity.ok(turnos);
    }

    @PostMapping
    public ResponseEntity<Turno> crearTurno(@RequestBody Turno turno) {
        Turno turno1 = turnoService.crearTurno(turno);
        return ResponseEntity.status(201).body(turno1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> obtenerTurno(@PathVariable Long id) {
        Turno turno = turnoService.buscarPorId(id);
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
