package br.com.food_city.infrastructure.mapper;

import br.com.food_city.domain.entities.Usuario;
import br.com.food_city.infrastructure.entity.UsuarioEntity;

import java.time.LocalDate;

public class UsuarioInfraMapper {

    public static UsuarioEntity toEntity(Usuario usuario) {
        return UsuarioEntity.builder()
                .login(usuario.getLogin())
                .hashSenha(usuario.getSenha())
                .idUsuario(usuario.getIdentificador())
                .ativo(true)
                .dataInclusao(LocalDate.now())
                .build();
    }

    public static Usuario toDomain(UsuarioEntity usuarioEntity) {
        return new Usuario(usuarioEntity.getLogin(), usuarioEntity.getHashSenha());
    }
}
