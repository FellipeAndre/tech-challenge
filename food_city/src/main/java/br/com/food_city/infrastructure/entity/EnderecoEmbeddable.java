package br.com.food_city.infrastructure.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoEmbeddable {

    private String lorgadouro;

    private String numero;

    private String bairro;

    private String estado;

    private String municipio;


}
