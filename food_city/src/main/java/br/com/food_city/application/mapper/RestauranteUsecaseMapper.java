package br.com.food_city.application.mapper;

import br.com.food_city.application.dto.RestauranteInput;
import br.com.food_city.application.dto.RestauranteOutput;
import br.com.food_city.domain.entities.Restaurante;

public class RestauranteUsecaseMapper {

    public static Restaurante toDomain(RestauranteInput input) {
        return new Restaurante(
                null,
                input.getNome(),
                //EnderecoUsecaseMapper.toDomain(input.getEndereco()),
                input.getTipoCozinha(),
                input.getHorarioAbertura(),
                input.getHorarioFechamento(),
                input.getDonoId()
        );
    }

    public static RestauranteOutput toOutput(Restaurante restaurante) {
        RestauranteOutput output = new RestauranteOutput();
        output.setId(restaurante.getId());
        output.setNome(restaurante.getNome());
       // output.setEndereco(EnderecoUsecaseMapper.toOutput(restaurante.getEndereco()));
        output.setTipoCozinha(restaurante.getTipoCozinha());
        output.setHorarioAbertura(restaurante.getHorarioAbertura());
        output.setHorarioFechamento(restaurante.getHorarioFechamento());
        output.setDonoId(restaurante.getDonoId());
        return output;
    }
}
