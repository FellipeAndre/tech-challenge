package br.com.food_city.presentation.dto;

public record CadastroRequest(String nome,
                              String email,
                              String numeroDocumento,
                              String dataNascimento,
                              EnderecoRequest enderecoDTO) {
}
