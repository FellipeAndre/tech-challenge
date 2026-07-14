package br.com.food_city.infrastructure.mapper;

import br.com.food_city.domain.entities.TipoUsuario;
import br.com.food_city.infrastructure.entity.TipoUsuarioEntity;

public class TipoUsuarioInfraMapper {

    public static TipoUsuario toDomain(TipoUsuarioEntity tipoUsuarioEntity){
        return new TipoUsuario(tipoUsuarioEntity.getId(),tipoUsuarioEntity.getRole());
    }

    public static TipoUsuarioEntity toEntity(TipoUsuario tipoUsuario) {
        return TipoUsuarioEntity.builder()
                .role(tipoUsuario.getRole())
                .build();
    }
}
