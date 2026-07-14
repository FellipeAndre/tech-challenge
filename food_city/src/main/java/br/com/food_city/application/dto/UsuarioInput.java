package br.com.food_city.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioInput {

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    public UsuarioInput(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
}
