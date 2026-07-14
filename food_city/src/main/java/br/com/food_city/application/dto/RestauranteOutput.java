package br.com.food_city.application.dto;

import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;

import java.time.LocalTime;
import java.util.UUID;

public class RestauranteOutput {

    private UUID id;
    private String nome;
    private EnderecoOutput endereco;
    private String tipoCozinha;
    private LocalTime horarioAbertura;
    private LocalTime horarioFechamento;
    private UUID donoId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public void setTipoCozinha(String tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }

    public LocalTime getHorarioAbertura() {
        return horarioAbertura;
    }

    public void setHorarioAbertura(LocalTime horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }

    public LocalTime getHorarioFechamento() {
        return horarioFechamento;
    }

    public void setHorarioFechamento(LocalTime horarioFechamento) {
        this.horarioFechamento = horarioFechamento;
    }

    public UUID getDonoId() {
        return donoId;
    }

    public void setDonoId(UUID donoId) {
        this.donoId = donoId;
    }

    public EnderecoOutput getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoOutput endereco) {
        this.endereco = endereco;
    }
}
