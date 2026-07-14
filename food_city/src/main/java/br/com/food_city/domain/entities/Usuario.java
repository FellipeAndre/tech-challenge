package br.com.food_city.domain.entities;

import br.com.food_city.domain.valueObject.Senha;

import java.util.UUID;

public class Usuario {

    private UUID identificador;

    private String login;

    private String senha;


    public Usuario(){

    }

    public Usuario(UUID identificador, String login) {
        this.identificador = identificador;
        this.login = login;

    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = Senha.criarHashSeguro(senha);
    }

    public void setSenha(String senha) {
        this.senha = Senha.criarHashSeguro(senha);
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

}
