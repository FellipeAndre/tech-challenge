package br.com.food_city.domain.repository;

import br.com.food_city.domain.entities.Cadastro;

import java.util.List;
import java.util.Optional;

public interface CadastroRepository {
    Cadastro salvar(Cadastro cadastro);
    Optional<Cadastro> buscarPorId(Long id);
    List<Cadastro> listarTodos();
    List<Cadastro> listarPorDono(Long donoId);
    void remover(Long id);
}

