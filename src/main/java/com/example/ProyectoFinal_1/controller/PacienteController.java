package com.example.ProyectoFinal_1.controller;

import com.example.ProyectoFinal_1.domain.Paciente;
import com.example.ProyectoFinal_1.exceptions.BadRequestException;
import com.example.ProyectoFinal_1.exceptions.ResourceNotFoundException;
import com.example.ProyectoFinal_1.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPaciente (@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);
        return ResponseEntity.ok(pacienteBuscado);
    }

    @GetMapping
    public ResponseEntity <List<Paciente>> buscarTodosPaciente () throws ResourceNotFoundException{
        List<Paciente> pacientesBuscados = pacienteService.buscarTodosPaciente();
        return ResponseEntity.ok(pacienteService.buscarTodosPaciente());
    }

   @PostMapping
   public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
       Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
       return ResponseEntity.ok(pacienteGuardado);
   }

   @PutMapping
   public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException{
       Paciente pacienteActualizado = pacienteService.actualizarPaciente(paciente);
           return ResponseEntity.ok(pacienteActualizado);
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente (@PathVariable Long id) throws ResourceNotFoundException{
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("Se ha eliminado el paciente con id: " + id);
   }






//    @GetMapping("/index")
//    public String encontrarPacientePorEmail (Model model, @RequestParam("email") String email){
//        Paciente paciente = pacienteService.buscarPorEmail(email);
//        model.addAttribute("nombre",paciente.getNombre());
//        model.addAttribute("apellido",paciente.getApellido());
//        return "index";
//
//    }
}
