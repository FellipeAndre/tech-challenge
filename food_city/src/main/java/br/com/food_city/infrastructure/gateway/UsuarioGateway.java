package br.com.food_city.infrastructure.gateway;

import br.com.food_city.domain.entities.Cadastro;
import br.com.food_city.domain.entities.Usuario;
import br.com.food_city.domain.repository.UsuarioRepository;
import br.com.food_city.infrastructure.entity.TipoUsuarioEntity;
import br.com.food_city.infrastructure.entity.UsuarioEntity;
import br.com.food_city.infrastructure.repository.TipoUsuarioJPA;
import br.com.food_city.infrastructure.repository.UsuarioRepositoryJpa;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioGateway implements UsuarioRepository {

    private final UsuarioRepositoryJpa jpa ;

    private final TipoUsuarioJPA tipoUsuarioJPA;

    @Override
    @Transactional
    public Usuario salvarUsuario(Cadastro cadastro) {

        var usuarioSalvo = this.jpa.save(toEntity(cadastro));

        var tipoUsuario = TipoUsuarioEntity.builder()
                .id_usuario(usuarioSalvo)
                .role(cadastro.getUsuario().getTipoUsuario().getRole())
                .build();

        this.tipoUsuarioJPA.save(tipoUsuario);

        return toDomain(usuarioSalvo);
    }

    private Usuario toDomain(UsuarioEntity save) {

        //TODO Implementar o domain de usuario
        return null;
    }

    private UsuarioEntity toEntity(Cadastro cadastro) {

        return UsuarioEntity.builder()
                .login(cadastro.getUsuario().getLogin())
                .hashSenha(cadastro.getUsuario().getSenha())
                .build();
    }
}
