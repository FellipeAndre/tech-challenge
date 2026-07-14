package br.com.food_city.presentation.dto;

import java.util.UUID;

public record CadastroRequest(
        String nome,
                              String email,
                              String numeroDocumento,
                              String dataNascimento,
                              UsuarioRequest usuario,
                              EnderecoRequest enderecoDTO) {
}
