package br.com.food_city.domain.entities;

import br.com.food_city.domain.valueObject.Senha;

import java.util.UUID;

public class Usuario {

    private UUID identificador;

    private String login;

    private String senha;

    private TipoUsuario tipoUsuario;

    public Usuario(){

    }

    public Usuario(UUID identificador, String login, TipoUsuario tipoUsuario) {
        this.identificador = identificador;
        this.login = login;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(String login, String senha, TipoUsuario tipoUsuario) {
        this.login = login;
        this.tipoUsuario = tipoUsuario;
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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
}
