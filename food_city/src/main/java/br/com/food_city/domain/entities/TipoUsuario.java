package br.com.food_city.domain.entities;

import java.util.UUID;

public class TipoUsuario {

    private UUID id;
    private String role;

    public TipoUsuario(){

    }

    public TipoUsuario(UUID id, String role) {
        this.id = id;
        this.role = role;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
