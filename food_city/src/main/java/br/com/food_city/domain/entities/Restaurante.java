package br.com.food_city.domain.entities;

import java.time.LocalTime;

public class Restaurante {

    private Long id;
    private String nome;
    private EnderecoDomain endereco;
    private String tipoCozinha;
    private LocalTime horarioAbertura;
    private LocalTime horarioFechamento;
    private Long donoId;

    public Restaurante(Long id, String nome, String tipoCozinha,
                       LocalTime horarioAbertura, LocalTime horarioFechamento, Long donoId) {
        validar(nome, tipoCozinha, horarioAbertura, horarioFechamento, donoId);
        this.id = id;
        this.nome = nome;
        this.tipoCozinha = tipoCozinha;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.donoId = donoId;
    }

    private void validar(String nome, String tipoCozinha,
                         LocalTime abertura, LocalTime fechamento, Long donoId) {
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
            throw new IllegalArgumentException("Restaurante precisa de um dono (usuário responsável)");
    }

    // getters...
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public EnderecoDomain getEndereco() { return endereco; }
    public String getTipoCozinha() { return tipoCozinha; }
    public LocalTime getHorarioAbertura() { return horarioAbertura; }
    public LocalTime getHorarioFechamento() { return horarioFechamento; }
    public Long getDonoId() { return donoId; }
}
