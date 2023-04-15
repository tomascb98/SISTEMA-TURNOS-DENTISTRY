package com.example.ProyectoFinal_1.service;

import com.example.ProyectoFinal_1.domain.Domicilio;
import com.example.ProyectoFinal_1.domain.Paciente;
import com.example.ProyectoFinal_1.exceptions.BadRequestException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //Para poder indicar el orden de los test que vamos a ejecutar
@SpringBootTest
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPacienteTest() throws BadRequestException {
        Paciente pacienteAGuardar = new Paciente("Casas","Tomas","1018500661", LocalDate.of(2023,03,30),new Domicilio("137","55","suba","cundinamarca"),
                "t-0130@hotmail.com");
        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);
        assertEquals(1,pacienteGuardado.getId());
    }

}