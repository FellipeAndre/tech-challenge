package br.com.food_city.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CadastroInput {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String numeroDocumento;

    @NotBlank
    private String dataNascimento;

    @NotBlank
    private EnderecoInput enderecoInput;

    @NotBlank
    private String role;

    public CadastroInput(String nome, String email, String numeroDocumento, String dataNascimento,String role, EnderecoInput enderecoInput) {
        this.nome = nome;
        this.email = email;
        this.role = role;
        this.numeroDocumento = numeroDocumento;
        this.dataNascimento = dataNascimento;
        this.enderecoInput = enderecoInput;
    }
}
