package com.lucasmartins.projetoweb.controller.advice;

import com.lucasmartins.projetoweb.exception.CursoNotFoundException;
import com.lucasmartins.projetoweb.exception.PeriodoNotFoundException;
import com.lucasmartins.projetoweb.exception.TurmaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TurmaNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleTurmaNotFound(TurmaNotFoundException ex) {
        Map<String,String> body = new HashMap<>(); body.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(CursoNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleCursoNotFound(CursoNotFoundException ex) {
        Map<String,String> body = new HashMap<>(); body.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(PeriodoNotFoundException.class)
    public ResponseEntity<Map<String,String>> handlePeriodoNotFound(PeriodoNotFoundException ex) {
        Map<String,String> body = new HashMap<>(); body.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> handleBadRequest(IllegalArgumentException ex) {
        Map<String,String> body = new HashMap<>(); body.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String,String> body = new HashMap<>(); body.put("error", "Validation failed: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
