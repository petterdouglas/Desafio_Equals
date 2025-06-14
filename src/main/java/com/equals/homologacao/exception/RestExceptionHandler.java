package com.equals.homologacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(DadoNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleDadoNaoEncontrado(DadoNaoEncontradoException exception) {
        Map<String, Object> body = new HashMap<>();

        body.put("error_time", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND);
        body.put("mensagem", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SemConteudoException.class)
    public ResponseEntity<Map<String, Object>> handleSemConteudo(SemConteudoException exception) {
        Map<String, Object> body = new HashMap<>();

        body.put("error_time", LocalDateTime.now());
        body.put("status", HttpStatus.NO_CONTENT);
        body.put("mensagem", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<Map<String, Object>> handleRegraDeNegocio(RegraDeNegocioException exception) {
        Map<String, Object> body = new HashMap<>();
        body.put("error_time", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST);
        body.put("mensagem", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
