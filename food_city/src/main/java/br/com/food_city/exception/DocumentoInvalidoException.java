package br.com.food_city.exception;

public class DocumentoInvalidoException extends RuntimeException{

    public DocumentoInvalidoException(String message) {
        super(message);
    }
}
