package br.com.food_city.domain.repository;

import br.com.food_city.domain.entities.Cadastro;

public interface CadastroRepository {
    Cadastro salvar(Cadastro cadastro);
}

