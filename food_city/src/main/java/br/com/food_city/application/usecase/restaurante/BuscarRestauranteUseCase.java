package br.com.food_city.application.usecase.restaurante;

import br.com.food_city.application.dto.RestauranteOutput;
import br.com.food_city.application.mapper.RestauranteUsecaseMapper;
import br.com.food_city.exception.GlobalNotFoundException;
import br.com.food_city.infrastructure.gateway.RestauranteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BuscarRestauranteUseCase {

    private final RestauranteGateway restauranteGateway;

    public RestauranteOutput executar(UUID id) throws GlobalNotFoundException {
         var restaurante = restauranteGateway.buscarPorId(id)
                 .orElseThrow(
                         () -> new GlobalNotFoundException("Restaurante não encontrado com ID: " + id)
                 );
        return RestauranteUsecaseMapper.toOutput(restaurante);
    }
}
