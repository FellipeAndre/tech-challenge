package br.com.food_city.domain.repository;

import br.com.food_city.domain.entities.Restaurante;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestauranteRepository {

    Restaurante salvar(Restaurante restaurante);
    Optional<Restaurante> buscarPorId(UUID id);
    List<Restaurante> listarTodos();
    //List<Restaurante> listarPorDono(UUID donoId);
    void remover(UUID id);
}
