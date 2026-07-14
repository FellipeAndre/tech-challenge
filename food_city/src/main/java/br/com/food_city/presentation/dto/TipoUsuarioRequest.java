package br.com.food_city.presentation.dto;

import java.util.UUID;

public class TipoUsuarioRequest {

    private UUID id;
    private UUID IdDono;
    private String role;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdDono() {
        return IdDono;
    }

    public void setIdDono(UUID idDono) {
        IdDono = idDono;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
