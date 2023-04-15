package com.example.ProyectoFinal_1.service;


import com.example.ProyectoFinal_1.domain.Odontologo;
import com.example.ProyectoFinal_1.domain.Paciente;
import com.example.ProyectoFinal_1.domain.Turno;
import com.example.ProyectoFinal_1.dto.TurnoDTO;
import com.example.ProyectoFinal_1.exceptions.BadRequestException;
import com.example.ProyectoFinal_1.exceptions.ResourceNotFoundException;
import com.example.ProyectoFinal_1.repository.OdontologoRepository;
import com.example.ProyectoFinal_1.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ProyectoFinal_1.repository.TurnoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    Logger logger = Logger.getLogger(TurnoService.class);
    private TurnoRepository turnoRepository;
    private PacienteRepository pacienteRepository;
    private OdontologoRepository odontologoRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, PacienteRepository pacienteRepository, OdontologoRepository odontologoRepository) throws ResourceNotFoundException {
        this.turnoRepository = turnoRepository;
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
    }


    public TurnoDTO guardar(TurnoDTO turnoDTO) throws BadRequestException {
        TurnoDTO turnoGuardado = new TurnoDTO();

        if(pacienteRepository.findById(turnoDTO.getPaciente_id()).isPresent() && odontologoRepository.findById(turnoDTO.getOdontologo_id()).isPresent()) {
            turnoGuardado = convertirTurnoPorTurnoDTO(turnoRepository.save(convertirTurnoDTOporTurno(turnoDTO)));
        }

        if(turnoGuardado.getId() != 0){
            logger.debug("Se hizo el guardado correctamente en la base de datos del turno con id: "+turnoGuardado.getId());
            return turnoGuardado;
        } else {
            logger.debug("ERROR. No se pudo guardar el turno en la base de datos.");
            throw new BadRequestException("ERROR. No se pudo guardar el turno en la base de datos.");
        }
    }


    public Optional<TurnoDTO> buscar(Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if(turnoBuscado.isPresent()){
            logger.debug("Se hizo la busqueda correctamente en la base de datos del turno con id: "+id);
            return Optional.of(convertirTurnoPorTurnoDTO(turnoBuscado.get()));
        } else {
            logger.debug("ERROR. No se encontró en la base de datos el turno con id: " + id);
            throw new ResourceNotFoundException("ERROR. No se encontró en la base de datos el turno con id:" + id);
        }
    }


    public void eliminar(Long id) throws ResourceNotFoundException {
        if (buscar(id).isPresent()){
            logger.debug("Se hizo la eliminacion correctamente del turno con id: "+id);
            turnoRepository.deleteById(id);
        }else {
            logger.debug("ERROR. No se pudo eliminar el turno en la base de datos con id" + id);
            throw new ResourceNotFoundException("ERROR. No se pudo eliminar el turno en la base de datos con id" + id);
        }
    }

    public TurnoDTO actualizar(TurnoDTO turnoDTO) throws ResourceNotFoundException {
        if (buscar(turnoDTO.getId()).isPresent() && pacienteRepository.findById(turnoDTO.getPaciente_id()).isPresent() && odontologoRepository.findById(turnoDTO.getOdontologo_id()).isPresent()) {
            logger.debug("Se hizo la actualizacion correctamente del turno con id: " +turnoDTO.getId());
            return convertirTurnoPorTurnoDTO(turnoRepository.save(convertirTurnoDTOporTurno(turnoDTO)));
        } else {
            logger.debug("ERROR. No se pudo actualizar el turno");
            throw new ResourceNotFoundException("ERROR. No se pudo actualizar el turno");
        }
    }

    public List<TurnoDTO> buscarTodos() throws ResourceNotFoundException {
        List<Turno> turnosBuscados = turnoRepository.findAll();
        List<TurnoDTO> turnosDTO = new ArrayList<>();
        for (Turno turno:turnosBuscados) {
            turnosDTO.add(convertirTurnoPorTurnoDTO(turno));
        }
        if (turnosDTO.size() != 0){
            logger.debug("Se hizo la busqueda correcta de los turnos en la base de datos.");
            return turnosDTO;
        } else {
            logger.debug("ERROR. No existen registros de turnos en el momento.");
            throw new ResourceNotFoundException("ERROR. No existen registros de turnos en el momento.");
        }
    }


    private Turno convertirTurnoDTOporTurno (TurnoDTO turnoDTO){
        Turno turno = new Turno();
        Paciente paciente = new Paciente();
        Odontologo odontologo = new Odontologo();

        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());

        odontologo.setId(turnoDTO.getOdontologo_id());
        odontologo.setNombre(turnoDTO.getNombreOdontologo());

        paciente.setId(turnoDTO.getPaciente_id());
        paciente.setNombre(turnoDTO.getNombrePaciente());

        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);

        return turno;
    }
    private TurnoDTO convertirTurnoPorTurnoDTO (Turno turno){
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setId(turno.getId());
        turnoDTO.setFecha(turno.getFecha());

        turnoDTO.setOdontologo_id(turno.getOdontologo().getId());
        turnoDTO.setPaciente_id(turno.getPaciente().getId());

        turnoDTO.setNombreOdontologo(turno.getOdontologo().getNombre());
        turnoDTO.setNombrePaciente(turno.getPaciente().getNombre());
        return turnoDTO;
    }
}
