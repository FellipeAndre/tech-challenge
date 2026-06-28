package br.com.food_city.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ErrorResponse {

    private final String mensagem;

    private final int cod;

    public ErrorResponse(String mensagem, int cod) {
        this.mensagem = mensagem;
        this.cod = cod;
    }
}
