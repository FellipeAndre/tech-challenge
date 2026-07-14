package br.com.food_city.application.usecase.restaurante;

import br.com.food_city.exception.GlobalNotFoundException;
import br.com.food_city.infrastructure.gateway.RestauranteGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RemoverRestauranteUseCase {

    private final RestauranteGateway restauranteGateway;

    @Transactional
    public void executar(UUID id) throws GlobalNotFoundException {
        var restaurante = restauranteGateway.buscarPorId(id)
                .orElseThrow(() -> new GlobalNotFoundException("Restaurante não encontrado com ID: " + id));

        this.restauranteGateway.remover(restaurante.getId());
    }
}
