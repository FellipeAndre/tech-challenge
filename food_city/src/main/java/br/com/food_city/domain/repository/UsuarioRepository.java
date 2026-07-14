package br.com.food_city.domain.repository;

import br.com.food_city.domain.entities.Cadastro;
import br.com.food_city.domain.entities.Usuario;
import br.com.food_city.exception.GlobalNotFoundException;
import br.com.food_city.exception.UsuarioNaoEncontradoException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {

    Usuario salvarUsuario(Cadastro cadastro) throws GlobalNotFoundException;
    Usuario buscarUsuarioPorId(UUID identificadorUsuario) throws UsuarioNaoEncontradoException;
    Optional<Usuario> buscarPorId(UUID id);
    List<Usuario> listarTodos();
    List<Usuario> listarPorDono(UUID donoId);
    void remover(UUID id);

    void salvar(Usuario usuario);
}
