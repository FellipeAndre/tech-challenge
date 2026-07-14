package br.com.food_city.domain.repository;

import br.com.food_city.domain.entities.Cadastro;
import br.com.food_city.domain.entities.TipoUsuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TipoUsuarioRepository {

    TipoUsuario salvarTipoUsuario(Cadastro tipoUsuario);
    Optional<TipoUsuario> buscarPorId(UUID id);
    List<TipoUsuario> listarTodos();
    void remover(UUID id);

    // ponto-chave: associação do tipo com um usuário já existente
    void associarUsuario(UUID usuarioId, UUID tipoUsuarioId);
    boolean existeUsuario(UUID usuarioId);

    TipoUsuario salvar(TipoUsuario tipoUsuario);

    String qualTipoUsuario(UUID donoId);
}
