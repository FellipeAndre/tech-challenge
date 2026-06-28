package br.com.food_city.domain.entities;

import br.com.food_city.application.dto.EnderecoInput;

public class EnderecoDomain {

    private String lorgadouro;

    private String numero;

    private String bairro;

    private String estado;

    private String municipio;

    public EnderecoDomain(String lorgadouro, String numero, String bairro, String estado, String municipio) {
        this.lorgadouro = lorgadouro;
        this.numero = numero;
        this.bairro = bairro;
        this.estado = estado;
        this.municipio = municipio;
    }

    public static EnderecoDomain created(EnderecoInput input){
        return null;
    }

    public String getLorgadouro() {
        return lorgadouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getEstado() {
        return estado;
    }

    public String getMunicipio() {
        return municipio;
    }
}
