//package com.example.ProyectoFinal_1.service;
//
//import com.example.ProyectoFinal_1.bd.BD;
//import com.example.ProyectoFinal_1.domain.Odontologo;
//import com.example.ProyectoFinal_1.dao.OdontologoDAOH2;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class OdontologoServiceTest {
//
//    @Test
//    public void cantidadBuscarTodosOdontologo() {
//        BD.crearTablas();
//        //dado
//        Odontologo tomas = new Odontologo("1321414","Tomas", "Casas");
//        Odontologo felipe = new Odontologo("134554","Felipe", "Valderrama");
//        Odontologo marcela = new Odontologo("241244", "Marcela", "Rodriguez");
//        Odontologo paula = new Odontologo("352353253","Paula","Tronic");
//
//        List<Odontologo> odontologosActuales = new ArrayList<>();
//        odontologosActuales.add(tomas);
//        odontologosActuales.add(felipe);
//        odontologosActuales.add(marcela);
//        odontologosActuales.add(paula);
//
//        OdontologoService odontologoService = new OdontologoService();
//        odontologoService.guardar(tomas);
//        odontologoService.guardar(felipe);
//        odontologoService.guardar(marcela);
//        odontologoService.guardar(paula);
//
//
//        List <Odontologo> odontologosBuscados = odontologoService.buscarTodos();
//
//        int respuestaEsperada = odontologosActuales.size();
//        //cuando
//        int respuestaActual = odontologosBuscados.size();
//        //entonces
//
//        Assert.assertEquals(respuestaEsperada,respuestaActual);
//    }
//
//    @Test
//    public void buscarTodosOdontologo () {
//        BD.crearTablas();
//        //dado
//        Odontologo tomas = new Odontologo("1321414","Tomas", "Casas");
//        Odontologo felipe = new Odontologo("134554","Felipe", "Valderrama");
//        Odontologo marcela = new Odontologo("241244", "Marcela", "Rodriguez");
//        Odontologo paula = new Odontologo("352353253","Paula","Tronic");
//
//        List<Odontologo> odontologosActuales = new ArrayList<>();
//        odontologosActuales.add(tomas);
//        odontologosActuales.add(felipe);
//        odontologosActuales.add(marcela);
//        odontologosActuales.add(paula);
//
//        OdontologoService odontologoService = new OdontologoService();
//        odontologoService.guardar(tomas);
//        odontologoService.guardar(felipe);
//        odontologoService.guardar(marcela);
//        odontologoService.guardar(paula);
//
//        List <Odontologo> odontologosBuscados = odontologoService.buscarTodos();
//
//        for (int i = 0; i < odontologosActuales.size(); i=i+1){
//            Assert.assertEquals(odontologosActuales.get(i).toString(),odontologosBuscados.get(i).toString());
//        }
//    }
//
//
//}