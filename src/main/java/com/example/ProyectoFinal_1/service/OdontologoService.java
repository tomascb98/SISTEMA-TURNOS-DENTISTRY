package com.example.ProyectoFinal_1.service;


import com.example.ProyectoFinal_1.domain.Odontologo;

import com.example.ProyectoFinal_1.exceptions.BadRequestException;
import com.example.ProyectoFinal_1.exceptions.ResourceNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ProyectoFinal_1.repository.OdontologoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    Logger logger = Logger.getLogger(OdontologoService.class);
    OdontologoRepository odontologoRepository;
    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo guardar(Odontologo odontologo) throws BadRequestException {
        Odontologo odontologoGuardado = odontologoRepository.save(odontologo);
        if(odontologoGuardado.getId() != 0){
            logger.debug("Se hizo el guardado correctamente en la base de datos del odontologo con id:" + odontologoGuardado.getId());
            return odontologoGuardado;
        } else {
            logger.warn("ERROR. No se pudo guardar el odontologo en la base de datos.");
            throw new BadRequestException("ERROR. No se pudo guardar el odontologo en la base de datos.");
        }
    }

    public Optional<Odontologo> buscar(Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(id);
        if (odontologoBuscado.isPresent()){
            logger.debug("Se hizo la busqueda correcta del odontologo" + id);
            return odontologoBuscado;
        } else {
            logger.warn("ERROR. No se encontró en la base de datos el odontologo con id: "+id);
            throw new ResourceNotFoundException("ERROR. No se encontró en la base de datos el odontologo con id: "+id);
        }
    }

    public void eliminar(Long id) throws ResourceNotFoundException{
        if (odontologoRepository.findById(id).isPresent()){
            logger.debug("Se hizo la eliminacion correcta del odontologo con id: " +id);
            odontologoRepository.deleteById(id);
        } else {
            logger.warn("ERROR. No se pudo eliminar el odontologo en la base de datos con id: "+id);
            throw new ResourceNotFoundException("ERROR. No se pudo eliminar el odontologo en la base de datos con id: "+id);
        }
    }

    public Odontologo actualizar(Odontologo odontologo) throws ResourceNotFoundException{
        if (odontologoRepository.findById(odontologo.getId()).isPresent()){
            logger.debug("Se hizo la actualizacion correcta del odontologo con id: " +odontologo.getId());
            return odontologoRepository.save(odontologo);
        } else {
            logger.warn("ERROR. No se pudo actualizar el odontologo en la base de datos con id: " + odontologo.getId());
            throw new ResourceNotFoundException("ERROR. No se pudo actualizar el odontologo en la base de datos.");
        }
    }

    public List<Odontologo> buscarTodos() throws ResourceNotFoundException{
        List<Odontologo> listOdontologos = odontologoRepository.findAll();
        if(listOdontologos.size() != 0){
            logger.debug("Se hizo la busqueda correcta de los odontologos en la base de datos.");
            return listOdontologos;
        } else {
            logger.warn("ERROR. No existen registros de Odontologos en el momento.");
            throw new ResourceNotFoundException("ERROR. No existen registros de Odontologos en el momento.");
        }

    }

    public OdontologoRepository getOdontologoRepository() {
        return odontologoRepository;
    }

    public void setOdontologoRepository(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }
}
