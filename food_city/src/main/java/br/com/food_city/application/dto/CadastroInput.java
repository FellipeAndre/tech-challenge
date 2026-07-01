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


    public CadastroInput(String nome, String email, String numeroDocumento, String dataNascimento, EnderecoInput enderecoInput) {
        this.nome = nome;
        this.email = email;
        this.numeroDocumento = numeroDocumento;
        this.dataNascimento = dataNascimento;
        this.enderecoInput = enderecoInput;
    }
}
