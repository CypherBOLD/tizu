package com.br.ecommerce.controllers.exceptions;

import com.br.ecommerce.services.exceptions.DatabaseWineException;
import com.br.ecommerce.services.exceptions.EntityWineNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ManipulatorException {

    @ExceptionHandler(EntityWineNotFoundException.class)
    public ResponseEntity<ErrorWine> entityNotFound(EntityWineNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorWine error = new ErrorWine();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Registro Não Encontrado!");
        error.setMsg(e.getMessage());
        error.setLink(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseWineException.class)
    public ResponseEntity<ErrorWine> integrityViolation(DatabaseWineException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorWine error = new ErrorWine();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Violação de Integridade (Chave Estrangeira)!");
        error.setMsg(e.getMessage());
        error.setLink(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

}
