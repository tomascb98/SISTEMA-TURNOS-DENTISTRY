package com.example.ProyectoFinal_1.service;

import com.example.ProyectoFinal_1.domain.Odontologo;
import com.example.ProyectoFinal_1.exceptions.BadRequestException;
import com.example.ProyectoFinal_1.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;
    Odontologo tomas = new Odontologo("1321414","Tomas", "Casas");
    Odontologo felipe = new Odontologo("134554","Felipe", "Valderrama");
    Odontologo marcela = new Odontologo("241244", "Marcela", "Rodriguez");
    Odontologo paula = new Odontologo("352353253","Paula","Tronic");


    @Test
    @Order(1)
    public void guardarOdontologoTest() throws BadRequestException {
        Odontologo odontologoGuardado = odontologoService.guardar(tomas);
        assertEquals(1,odontologoGuardado.getId());
    }
    @Test
    @Order(2)
    public void buscarOdontologoTest() throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado =  odontologoService.buscar(1L);
        assertEquals("Tomas",odontologoBuscado.get().getNombre());
        assertEquals("Casas",odontologoBuscado.get().getApellido());
        assertEquals("1321414",odontologoBuscado.get().getNumeroMatricula());
    }

    @Test
    @Order(3)
    public void buscarTodosOdontologosTest() throws ResourceNotFoundException, BadRequestException{

        List<Odontologo> odontologosActuales = new ArrayList<>();
        odontologosActuales.add(tomas);
        odontologosActuales.add(felipe);
        odontologosActuales.add(marcela);
        odontologosActuales.add(paula);

        odontologoService.guardar(felipe);
        odontologoService.guardar(marcela);
        odontologoService.guardar(paula);

        List <Odontologo> odontologosBuscados = odontologoService.buscarTodos();

        int respuestaEsperada = odontologosActuales.size();
        //cuando
        int respuestaActual = odontologosBuscados.size();
        //entonces

        assertEquals(respuestaEsperada,respuestaActual);

    }

    @Test
    @Order(4)
    public void actualizarOdontologoTest() throws ResourceNotFoundException{
        tomas.setId(1L);
        tomas.setNombre("Tomasin");
        tomas.setApellido("Casitas");
        tomas.setNumeroMatricula("1234");
        Odontologo odontologoActualizado = odontologoService.actualizar(tomas);
        assertEquals(1,odontologoActualizado.getId());
        assertEquals("Tomasin",odontologoActualizado.getNombre());
        assertEquals("Casitas",odontologoActualizado.getApellido());
        assertEquals("1234",odontologoActualizado.getNumeroMatricula());
    }

    @Test
    @Order(5)
    public void eliminarOdontologoTest() throws ResourceNotFoundException {
        odontologoService.eliminar(1L);

        Exception exception = assertThrows(ResourceNotFoundException.class,()->{odontologoService.buscar(1L);});

        String expectedMessage = "ERROR. No se encontró en la base de datos el odontologo con id: 1";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);
    }
}