package com.example.ProyectoFinal_1.controller;

import com.example.ProyectoFinal_1.domain.Odontologo;
import com.example.ProyectoFinal_1.exceptions.BadRequestException;
import com.example.ProyectoFinal_1.exceptions.ResourceNotFoundException;
import com.example.ProyectoFinal_1.service.OdontologoService;
import com.example.ProyectoFinal_1.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarOdontologo (@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoBuscado = odontologoService.buscar(id);
        return ResponseEntity.ok(odontologoBuscado);
    }

    @GetMapping
    public ResponseEntity <List<Odontologo>> buscarTodosOdontologo() throws ResourceNotFoundException{
        List<Odontologo> odontologosBuscados = odontologoService.buscarTodos();
        return ResponseEntity.ok(odontologosBuscados);
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        Odontologo odontologoGuardado = odontologoService.guardar(odontologo); //Si no se realiza el guardado, throw la exception y llegan al return de abajo con el .ok
        return ResponseEntity.ok(odontologoGuardado);
    }

    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException{
        Odontologo odontologoActualizado = odontologoService.actualizar(odontologo);
        return ResponseEntity.ok(odontologoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo (@PathVariable Long id) throws ResourceNotFoundException{
        odontologoService.eliminar(id);
        return ResponseEntity.ok("Se ha eliminado el Odontologo con id: " + id);
    }

}
