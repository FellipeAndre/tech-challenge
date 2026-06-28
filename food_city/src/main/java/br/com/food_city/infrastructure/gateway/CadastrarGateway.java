package br.com.food_city.infrastructure.gateway;

import br.com.food_city.domain.entities.Cadastro;
import br.com.food_city.domain.entities.EnderecoDomain;
import br.com.food_city.domain.repository.CadastroRepository;
import br.com.food_city.infrastructure.entity.CadastroEntity;
import br.com.food_city.infrastructure.entity.EnderecoEmbeddable;
import br.com.food_city.infrastructure.repository.CadastrarRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/*
*
* Classe Responsavel por ser a porta de entrada para salvar os dados na base
*  Convertendo o objeto dto para Entity usando o JPA
*
* */

@Component
@RequiredArgsConstructor
public class CadastrarGateway implements CadastroRepository {

    private final CadastrarRepositoryJPA repositoryJPA;

    @Override
    public Cadastro salvar(Cadastro cadastro) {
        return toDomain(this.repositoryJPA.save(Objects.requireNonNull(toEntity(cadastro))));
    }

    private Cadastro toDomain(CadastroEntity entity){
        var endEntity = entity.getEndereco();
        var endDomain = new EnderecoDomain(endEntity.getLorgadouro(), endEntity.getNumero(), endEntity.getBairro(), endEntity.getEstado(), endEntity.getMunicipio());

        return new Cadastro(entity.getId(), entity.getNome(), entity.getEmail(), entity.getNumeroDocumento(), entity.getDataNascimento(),endDomain);
    }

    private CadastroEntity toEntity(Cadastro cadastro){
        var enderecoDomain = cadastro.getEndereco();
        var endDomain = new EnderecoEmbeddable(enderecoDomain.getLorgadouro(), enderecoDomain.getNumero(), enderecoDomain.getBairro(), enderecoDomain.getEstado(), enderecoDomain.getMunicipio());

//        return new CadastroEntity(cadastro.getIdentificador(), cadastro.getNome(), cadastro.getEmail(), cadastro.getNumeroDocumento(), cadastro.getDataNascimento(), LocalDateTime.now(), null, endDomain);
        return null;
    }
}
