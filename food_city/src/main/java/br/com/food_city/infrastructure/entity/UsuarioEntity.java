package br.com.food_city.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Table(name = "usuario")
@Builder
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String login;

    private String hashSenha;

    @ManyToOne
    @JoinColumn(name = "tipo_usuario_id")
    private TipoUsuarioEntity tipoUsuario;

    public UsuarioEntity() {
    }

    public UsuarioEntity(UUID id, String login, String hashSenha, TipoUsuarioEntity tipoUsuario) {
        this.id = id;
        this.login = login;
        this.hashSenha = hashSenha;
        this.tipoUsuario = tipoUsuario;
    }
}
