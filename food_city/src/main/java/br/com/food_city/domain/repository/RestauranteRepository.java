package br.com.food_city.domain.repository;

import br.com.food_city.domain.entities.Restaurante;

import java.util.List;
import java.util.Optional;

public interface RestauranteRepository {

    Restaurante salvar(Restaurante restaurante);
    Optional<Restaurante> buscarPorId(Long id);
    List<Restaurante> listarTodos();
    List<Restaurante> listarPorDono(Long donoId);
    void remover(Long id);
    boolean existeUsuario(Long usuarioId);
}
