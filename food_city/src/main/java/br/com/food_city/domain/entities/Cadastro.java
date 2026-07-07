package br.com.food_city.domain.entities;

import br.com.food_city.domain.valueObject.DataFormatter;
import br.com.food_city.domain.valueObject.Documento;

import java.time.LocalDate;
import java.util.UUID;

public class Cadastro {

    private UUID identificador;

    private String nome;

    private String email;

    private String numeroDocumento;

    private String dataNascimento;

    private Usuario usuario;

    private EnderecoDomain endereco;

    public Cadastro(){

    }

    public Cadastro(UUID identificador, String nome, String email, String numeroDocumento, String dataNascimento, EnderecoDomain endereco) {
        this.identificador = identificador;
        this.nome = nome;
        this.email = email;
        this.numeroDocumento = validarNumeroDocumento(numeroDocumento);
        this.dataNascimento = formatarDataNascimento(dataNascimento).toString();
        this.endereco = endereco;
    }

    public Cadastro(String nome, String email, String numeroDocumento, String dataNascimento, EnderecoDomain endereco) {
        this.nome = nome;
        this.email = email;
        this.numeroDocumento = validarNumeroDocumento(numeroDocumento);
        this.dataNascimento = formatarDataNascimento(dataNascimento).toString();
        this.endereco = endereco;
    }

    private String validarNumeroDocumento(String numeroDocumento){
        return Documento.validarDocumento(numeroDocumento);
    }

    private LocalDate formatarDataNascimento(String dataNascimento){
        return DataFormatter.formatarData(dataNascimento);
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean isDono(){
        return numeroDocumento.length() == 14;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public EnderecoDomain getEndereco() {
        return endereco;
    }
}
