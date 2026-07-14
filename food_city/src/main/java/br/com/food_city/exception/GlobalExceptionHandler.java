package br.com.food_city.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DocumentoInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleDocumentoInvalido(DocumentoInvalidoException ex){

        var error = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex){

        var error = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(GlobalNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlenotfound(GlobalNotFoundException ex){

        var error = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());

        return ResponseEntity.badRequest().body(error);
    }
}
