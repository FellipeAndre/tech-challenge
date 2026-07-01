package br.com.food_city.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "Tipo_usuario")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoUsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity id_usuario;

    private String role;

    public UsuarioEntity getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(UsuarioEntity id_usuario) {
        this.id_usuario = id_usuario;
    }
}
