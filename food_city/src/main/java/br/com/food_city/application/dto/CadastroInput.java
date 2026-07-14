package br.com.food_city.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CadastroInput {

    private UUID dono_Id;

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String numeroDocumento;

    @NotBlank
    private String dataNascimento;

    @NotBlank
    private UsuarioInput usuarioInput;

    @NotBlank
    private EnderecoInput enderecoInput;


    public CadastroInput(String nome, String email, String numeroDocumento,UsuarioInput usuarioInput, String dataNascimento, EnderecoInput enderecoInput) {
        this.nome = nome;
        this.email = email;
        this.numeroDocumento = numeroDocumento;
        this.usuarioInput = usuarioInput;
        this.dataNascimento = dataNascimento;
        this.enderecoInput = enderecoInput;
    }

    public CadastroInput(UUID dono_Id, String nome, String email, String numeroDocumento, String dataNascimento, UsuarioInput usuarioInput, EnderecoInput enderecoInput) {
        this.dono_Id = dono_Id;
        this.nome = nome;
        this.email = email;
        this.numeroDocumento = numeroDocumento;
        this.dataNascimento = dataNascimento;
        this.usuarioInput = usuarioInput;
        this.enderecoInput = enderecoInput;
    }
}
