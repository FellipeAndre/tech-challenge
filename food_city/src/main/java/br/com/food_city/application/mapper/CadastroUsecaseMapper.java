package br.com.food_city.application.mapper;

import br.com.food_city.domain.entities.Cadastro;
import br.com.food_city.domain.entities.EnderecoDomain;
import br.com.food_city.application.dto.CadastroInput;
import br.com.food_city.application.dto.CadastroOutput;
import br.com.food_city.domain.entities.Usuario;
import org.springframework.stereotype.Component;

@Component
public class CadastroUsecaseMapper {

    /*
       Metodo toDomain(CadastroInput input) Responsável por ser instaciado sempre na camada useCase

       Seguindo o padrao clean Arch ( useCase input -> domain -> tera sua proprias regras de negocio)

    */

    public Cadastro toDomain(CadastroInput input){
        var usuario = input.getUsuarioInput();
        var usuarioDomain = new Usuario(usuario.getLogin(), usuario.getSenha());
        var enderecoInput = input.getEnderecoInput();
        var enderecoDomain = new EnderecoDomain(enderecoInput.getLogradouro(), enderecoInput.getNumero(), enderecoInput.getBairro(), enderecoInput.getEstado(), enderecoInput.getMunicipio());
        return new Cadastro(input.getNome(), input.getEmail(), input.getNumeroDocumento(), input.getDataNascimento(), enderecoDomain, null, usuarioDomain);
    }
    
    public CadastroOutput toOutput(Cadastro cadastro) {
        var enderecoInput = new br.com.food_city.application.dto.EnderecoInput(
            cadastro.getEndereco().getLorgadouro(),
            cadastro.getEndereco().getNumero(),
            cadastro.getEndereco().getBairro(),
            cadastro.getEndereco().getMunicipio(),
            cadastro.getEndereco().getEstado()
        );
        return new CadastroOutput(cadastro.getNome(), cadastro.getEmail(), cadastro.getNumeroDocumento(), cadastro.getDataNascimento(), enderecoInput);
    }
}
