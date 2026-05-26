package com.bruno.turnosapi.controller;

import com.bruno.turnosapi.dto.TurnoRequest;
import com.bruno.turnosapi.dto.TurnoResponse;
import com.bruno.turnosapi.model.EstadoTurno;
import com.bruno.turnosapi.service.TurnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/turnos")
@Tag(name = "Turnos", description = "Gestión de turnos")
public class TurnoController {

    private final TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @GetMapping
    @Operation(summary = "Ver todos los turnos")
    public ResponseEntity<List<TurnoResponse>> listarTurnos() {
        List<TurnoResponse> turnos = turnoService.obtenerTurnos();
        return ResponseEntity.ok(turnos);
    }

    @PostMapping
    @Operation(summary = "Crear un turno")
    public ResponseEntity<TurnoResponse> crearTurno(@Valid @RequestBody TurnoRequest turno) {
        TurnoResponse turno1 = turnoService.crearTurno(turno);
        return ResponseEntity.status(HttpStatus.CREATED).body(turno1);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar turno por Id")
    public ResponseEntity<TurnoResponse> obtenerTurno(@PathVariable Long id) {
        TurnoResponse turno = turnoService.buscarPorId(id);
        return ResponseEntity.ok(turno);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar turno")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        turnoService.eliminarTurno(id);
        String mensaje = "Turno eliminado correctamente";
        return ResponseEntity.ok(mensaje);
    }

    @PatchMapping("/{id}/estado")
    @Operation(summary = "Cambiar estado del turno")
    public ResponseEntity<TurnoResponse> cambiarEstado(@PathVariable Long id, @RequestBody EstadoTurno estado) {
        TurnoResponse turnoRes = turnoService.cambiarEstado(estado, id);
        return ResponseEntity.ok(turnoRes);
    }

    @GetMapping("/disponibles")
    @Operation(summary = "Ver horarios disponibles de un médico")
    public ResponseEntity<List<LocalTime>> obtenerHorariosDisponibles(@RequestParam Long medicoId, @RequestParam LocalDate fecha){
        List<LocalTime> horarios = turnoService.obtenerHorasDisponibles(medicoId, fecha);
        return ResponseEntity.ok(horarios);
    }
}
