package br.com.food_city.infrastructure.gateway;

import br.com.food_city.domain.entities.Cadastro;
import br.com.food_city.domain.repository.UsuarioRepository;
import br.com.food_city.infrastructure.entity.TipoUsuarioEntity;
import br.com.food_city.infrastructure.entity.UsuarioEntity;
import br.com.food_city.infrastructure.repository.UsuarioRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioGateway implements UsuarioRepository {

    private final UsuarioRepositoryJpa jpa ;

    @Override
    public UsuarioEntity salvarUsuario(Cadastro cadastro) {
        return this.jpa.save(toEntity(cadastro));
    }

    private UsuarioEntity toEntity(Cadastro cadastro) {

        var tipoUsuario = TipoUsuarioEntity.builder()
                .role(null)
                .build();

        return UsuarioEntity.builder()
                .login(cadastro.getUsuario().getLogin())
                .hashSenha(cadastro.getUsuario().getSenha())
                .tipoUsuario(tipoUsuario)
                .build();
    }
}
