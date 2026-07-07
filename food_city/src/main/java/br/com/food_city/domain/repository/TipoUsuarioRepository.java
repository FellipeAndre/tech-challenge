package br.com.food_city.domain.repository;

import br.com.food_city.domain.entities.Cadastro;
import br.com.food_city.domain.entities.TipoUsuario;
import br.com.food_city.infrastructure.entity.TipoUsuarioEntity;

import java.util.List;
import java.util.Optional;

public interface TipoUsuarioRepository {

    TipoUsuarioEntity salvarTipoUsuario(TipoUsuario tipoUsuario);
    Optional<TipoUsuario> buscarPorId(Long id);
    List<TipoUsuario> listarTodos();
    List<TipoUsuario> listarPorDono(Long donoId);
    void remover(Long id);
}
