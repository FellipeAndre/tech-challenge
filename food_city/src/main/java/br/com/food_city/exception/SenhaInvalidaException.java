package br.com.food_city.exception;

public class SenhaInvalidaException extends RuntimeException{

    public SenhaInvalidaException(String message) {
        super(message);
    }
}
