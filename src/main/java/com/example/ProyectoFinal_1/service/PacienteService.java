package com.example.ProyectoFinal_1.service;


import com.example.ProyectoFinal_1.domain.Paciente;
import com.example.ProyectoFinal_1.exceptions.BadRequestException;
import com.example.ProyectoFinal_1.exceptions.ResourceNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ProyectoFinal_1.repository.PacienteRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    Logger logger = Logger.getLogger(PacienteService.class);
    PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente guardarPaciente(Paciente paciente) throws BadRequestException{
        Paciente pacienteGuardado = pacienteRepository.save(paciente);
        if(pacienteGuardado.getId() != 0){
            logger.debug("Se hizo el guardado correctamente en la base de datos del paciente con id: "+pacienteGuardado.getId());
            return pacienteGuardado;
        } else {
            logger.warn("ERROR. No se pudo guardar el paciente en la base de datos.");
            throw new BadRequestException("ERROR. No se pudo guardar el paciente en la base de datos.");
        }
    }

    public Optional<Paciente> buscarPaciente(Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(id);
        if(pacienteBuscado.isPresent()){
            logger.debug("Se hizo la busqueda correctamente en la base de datos del paciente con id: "+id);
            return pacienteBuscado;
        } else {
            logger.warn("ERROR. No se encontró en la base de datos el paciente con id: " + id);
            throw new ResourceNotFoundException("ERROR. No se encontró en la base de datos el paciente con id: " + id);
        }
    }

    public void eliminarPaciente(Long id) throws ResourceNotFoundException{
        if(buscarPaciente(id).isPresent()){
            logger.debug("Se hizo la eliminacion correctamente del paciente con id: "+id);
            pacienteRepository.deleteById(id);
        }else {
            logger.warn("ERROR. No se pudo eliminar el paciente en la base de datos con id" + id);
            throw new ResourceNotFoundException("ERROR. No se pudo eliminar el paciente en la base de datos con id" + id);
        }
    }

    public List<Paciente> buscarTodosPaciente() throws ResourceNotFoundException{
        List<Paciente> pacientesBuscados = pacienteRepository.findAll();
        if(pacientesBuscados.size() != 0){
            logger.debug("Se hizo la busqueda correcta de los pacientes en la base de datos.");
            return pacientesBuscados;
        } else {
            logger.warn("ERROR. No existen registros de Pacientes en el momento.");
            throw new ResourceNotFoundException("ERROR. No existen registros de Pacientes en el momento.");
        }
    }

    public Paciente actualizarPaciente(Paciente paciente) throws ResourceNotFoundException{
        if (pacienteRepository.findById(paciente.getId()).isPresent()){
            logger.debug("Se hizo la actualizacion correctamente del paciente con id: " +paciente.getId());
            return pacienteRepository.save(paciente);
        } else{
            logger.warn("ERROR. No se pudo actualizar el paciente");
            throw new ResourceNotFoundException("ERROR. No se pudo actualizar el paciente");
        }
    }

    public PacienteRepository getPacienteRepository() {
        return pacienteRepository;
    }

    public void setPacienteRepository(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
}