package br.com.food_city.domain.entities;

import java.time.LocalTime;
import java.util.UUID;

public class Restaurante {

    private UUID id;
    private String nome;
    private EnderecoDomain endereco;
    private String tipoCozinha;
    private LocalTime horarioAbertura;
    private LocalTime horarioFechamento;
    private UUID donoId;

    public Restaurante(UUID id, String nome, EnderecoDomain endereco, String tipoCozinha,
                       LocalTime horarioAbertura, LocalTime horarioFechamento, UUID donoId) {
        validar(nome, tipoCozinha, endereco, horarioAbertura, horarioFechamento, donoId);
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.donoId = donoId;
    }

    private void validar(String nome, String tipoCozinha,EnderecoDomain endereco,
                         LocalTime abertura, LocalTime fechamento, UUID donoId) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome do restaurante é obrigatório");
        if (endereco == null)
            throw new IllegalArgumentException("Endereço é obrigatório");
        if (tipoCozinha == null || tipoCozinha.isBlank())
            throw new IllegalArgumentException("Tipo de cozinha é obrigatório");
        if (abertura == null || fechamento == null)
            throw new IllegalArgumentException("Horário de funcionamento é obrigatório");
        if (!abertura.isBefore(fechamento))
            throw new IllegalArgumentException("Horário de abertura deve ser antes do fechamento");
        if (donoId == null)
            throw new IllegalArgumentException("Restaurante precisa de um PROPRIETARIO (usuário responsável)");
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(EnderecoDomain endereco) {
        this.endereco = endereco;
    }

    public void setTipoCozinha(String tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }

    public void setHorarioAbertura(LocalTime horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }

    public void setHorarioFechamento(LocalTime horarioFechamento) {
        this.horarioFechamento = horarioFechamento;
    }

    public void setDonoId(UUID donoId) {
        this.donoId = donoId;
    }

    // getters...
    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public EnderecoDomain getEndereco() { return endereco; }
    public String getTipoCozinha() { return tipoCozinha; }
    public LocalTime getHorarioAbertura() { return horarioAbertura; }
    public LocalTime getHorarioFechamento() { return horarioFechamento; }
    public UUID getDonoId() { return donoId; }
}
