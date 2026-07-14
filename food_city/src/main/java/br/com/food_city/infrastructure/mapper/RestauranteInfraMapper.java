package br.com.food_city.infrastructure.mapper;

import br.com.food_city.domain.entities.EnderecoDomain;
import br.com.food_city.domain.entities.Restaurante;
import br.com.food_city.infrastructure.entity.EnderecoEmbeddable;
import br.com.food_city.infrastructure.entity.RestauranteEntity;
import br.com.food_city.infrastructure.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInfraMapper {

    public static Restaurante toDomain(RestauranteEntity entity) {

        if (entity == null) {
            return null;
        }

        var endereco = entity.getEndereco();
        var enderecoDomain = new EnderecoDomain(endereco.getLorgadouro(), endereco.getNumero(), endereco.getBairro(), endereco.getEstado(), endereco.getMunicipio());

        Restaurante restaurante = new Restaurante(
                entity.getId(),
                entity.getNome(),
                enderecoDomain,
                entity.getTipoCozinha(),
                entity.getHorarioAbertura(),
                entity.getHorarioFechamento(),
                entity.getDono().getIdUsuario()
        );

        return restaurante;
    }

    public static RestauranteEntity toEntity(Restaurante restaurante) {

        if (restaurante == null) {
            return null;
        }

        var endereco = restaurante.getEndereco();
        var enderecoEntity = new EnderecoEmbeddable(endereco.getLorgadouro(), endereco.getNumero(), endereco.getBairro(), endereco.getEstado(), endereco.getMunicipio());

        return RestauranteEntity.builder()
                .id(restaurante.getId())
                .nome(restaurante.getNome())
                .endereco(enderecoEntity)
                .tipoCozinha(restaurante.getTipoCozinha())
                .horarioAbertura(restaurante.getHorarioAbertura())
                .horarioFechamento(restaurante.getHorarioFechamento())
                .dono(
                        UsuarioEntity.builder()
                                .idUsuario(restaurante.getDonoId())
                                .build()
                )
                .build();
    }

}
