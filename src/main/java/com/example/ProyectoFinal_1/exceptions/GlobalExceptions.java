package com.example.ProyectoFinal_1.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {
    Logger logger = Logger.getLogger(GlobalExceptions.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> procesarErrorRNF(ResourceNotFoundException ex){
        logger.error("ERROR. ResourceNotFoundException lanzada");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> procesarBadRequest (BadRequestException ex){
        logger.error("ERROR. BadRequestException lanzada");
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
