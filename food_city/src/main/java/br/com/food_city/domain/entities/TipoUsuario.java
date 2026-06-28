package br.com.food_city.domain.entities;

import java.util.UUID;

public class TipoUsuario {

    private UUID id;

    private String role;

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
