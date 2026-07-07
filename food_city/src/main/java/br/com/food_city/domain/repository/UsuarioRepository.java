package br.com.food_city.domain.repository;

import br.com.food_city.domain.entities.Cadastro;
import br.com.food_city.domain.entities.TipoUsuario;
import br.com.food_city.domain.entities.Usuario;
import br.com.food_city.infrastructure.entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {

    Usuario salvarUsuario(Cadastro cadastro);
    Usuario buscarUsuarioPorId(UUID identificadorUsuario);
    Optional<Usuario> buscarPorId(Long id);
    List<Usuario> listarTodos();
    List<Usuario> listarPorDono(Long donoId);
    void remover(Long id);

}
