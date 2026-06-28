package br.com.food_city.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Cadastro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CadastroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 230)
    private String nome;

    @Column(unique = true)
    private String email;

    private String numeroDocumento;

    private String dataNascimento;

    private LocalDateTime dataInclusao;

    private LocalDateTime dataAlteracao;

    @Embedded
    private EnderecoEmbeddable endereco;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private UsuarioEntity entity;
}
