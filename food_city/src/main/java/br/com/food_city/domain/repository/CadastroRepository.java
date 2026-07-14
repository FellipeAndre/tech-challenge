package br.com.food_city.domain.repository;

import br.com.food_city.domain.entities.Cadastro;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CadastroRepository {
    Cadastro salvar(Cadastro cadastro);
    Optional<Cadastro> buscarPorId(UUID id);
    List<Cadastro> listarTodos();
    List<Cadastro> listarPorDono(Long donoId);
    void remover(UUID id);

    Optional<Cadastro> existeUsuario(UUID donoId);
}

