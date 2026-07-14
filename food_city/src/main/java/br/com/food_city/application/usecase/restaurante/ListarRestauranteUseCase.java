package br.com.food_city.application.usecase.restaurante;

import br.com.food_city.application.dto.EnderecoOutput;
import br.com.food_city.application.dto.RestauranteOutput;
import br.com.food_city.application.mapper.RestauranteUsecaseMapper;
import br.com.food_city.domain.entities.Restaurante;
import br.com.food_city.infrastructure.gateway.RestauranteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class ListarRestauranteUseCase {

    private final RestauranteGateway restauranteGateway;

    public List<RestauranteOutput> executar() {

        var listaDeRestaurantes = restauranteGateway.listarTodos();

        return RestauranteUsecaseMapper.toOutputList(listaDeRestaurantes);
    }
}
