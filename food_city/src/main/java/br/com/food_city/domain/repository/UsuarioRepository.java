package br.com.food_city.domain.repository;

import br.com.food_city.domain.entities.Cadastro;
import br.com.food_city.domain.entities.Usuario;
import br.com.food_city.infrastructure.entity.UsuarioEntity;

public interface UsuarioRepository {

    Usuario salvarUsuario(Cadastro cadastro);
}
