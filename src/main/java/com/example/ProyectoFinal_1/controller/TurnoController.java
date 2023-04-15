package com.example.ProyectoFinal_1.controller;

import com.example.ProyectoFinal_1.domain.Turno;
import com.example.ProyectoFinal_1.dto.TurnoDTO;
import com.example.ProyectoFinal_1.exceptions.BadRequestException;
import com.example.ProyectoFinal_1.exceptions.ResourceNotFoundException;
import com.example.ProyectoFinal_1.service.OdontologoService;
import com.example.ProyectoFinal_1.service.PacienteService;
import com.example.ProyectoFinal_1.service.TurnoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;


    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;

    }

    @GetMapping("/{id}")
    public ResponseEntity <Optional<TurnoDTO>> buscarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscar(id);
        return ResponseEntity.ok(turnoBuscado);
    }

    @GetMapping
    public ResponseEntity <List<TurnoDTO>> buscarTodosTurno()throws ResourceNotFoundException {
        List<TurnoDTO> turnosBuscados = turnoService.buscarTodos();
        return ResponseEntity.ok(turnosBuscados);

    }

    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno (@RequestBody TurnoDTO turnoDTO) throws BadRequestException {
            TurnoDTO turnoGuardado = turnoService.guardar(turnoDTO);
            return ResponseEntity.ok(turnoGuardado);
    }

    @PutMapping
    public ResponseEntity<TurnoDTO> actualizarTurno (@RequestBody TurnoDTO turnoDTO) throws ResourceNotFoundException{
        TurnoDTO turnoActualizado = turnoService.actualizar(turnoDTO);
        return ResponseEntity.ok(turnoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno (@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminar(id);
        return ResponseEntity.ok("Se ha eliminado el turno con id: " + id);
    }
}
