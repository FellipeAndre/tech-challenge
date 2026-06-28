package br.com.food_city.domain.entities.enumerable;

import lombok.Getter;

@Getter
public enum TipoDocumento {

    CPF("CPF"),
    CNPJ("CNPJ");

    private String descricao;

   TipoDocumento(String descricao){
        this.descricao = descricao;
    }
}
