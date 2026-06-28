package br.com.food_city.domain.repository;

import br.com.food_city.domain.entities.TipoUsuario;
import br.com.food_city.infrastructure.entity.TipoUsuarioEntity;

public interface TipoUsuarioRepository {

    TipoUsuarioEntity salvarTipoUsuario(TipoUsuario tipoUsuario);
}
