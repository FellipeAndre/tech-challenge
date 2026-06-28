package br.com.food_city.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "Tipo_usuario")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoUsuarioEntity {

    private UUID id;

    private String role;
}
