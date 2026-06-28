package br.com.food_city.presentation.dto;

public record EnderecoRequest(String logradouro,
                              String numero,
                              String cidade,
                              String bairro,
                              String estado,
                              String municipio) {
}
