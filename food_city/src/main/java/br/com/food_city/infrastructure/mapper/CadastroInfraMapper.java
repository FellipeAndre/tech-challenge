package br.com.food_city.infrastructure.mapper;

import br.com.food_city.domain.entities.Cadastro;
import br.com.food_city.domain.entities.EnderecoDomain;
import br.com.food_city.domain.entities.Usuario;
import br.com.food_city.infrastructure.entity.CadastroEntity;

public class CadastroInfraMapper {

    public static Cadastro toDomain(CadastroEntity input){
        var usuario = input.getUsuarioEntity();
        var usuarioDomain = new Usuario(usuario.getLogin(), usuario.getHashSenha());
        var enderecoInput = input.getEndereco();
        var enderecoDomain = new EnderecoDomain(enderecoInput.getLorgadouro(), enderecoInput.getNumero(), enderecoInput.getBairro(), enderecoInput.getEstado(), enderecoInput.getMunicipio());
        return new Cadastro(input.getNome(), input.getEmail(), input.getNumeroDocumento(), input.getDataNascimento(), enderecoDomain, null, usuarioDomain);
    }
}
