package br.com.food_city.infrastructure.gateway;

import br.com.food_city.domain.entities.Cadastro;
import br.com.food_city.domain.entities.TipoUsuario;
import br.com.food_city.domain.entities.Usuario;
import br.com.food_city.domain.repository.UsuarioRepository;
import br.com.food_city.infrastructure.entity.TipoUsuarioEntity;
import br.com.food_city.infrastructure.entity.UsuarioEntity;
import br.com.food_city.infrastructure.repository.TipoUsuarioJPA;
import br.com.food_city.infrastructure.repository.UsuarioRepositoryJpa;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UsuarioGateway implements UsuarioRepository {

    private final UsuarioRepositoryJpa jpa ;

    private final TipoUsuarioJPA tipoUsuarioJPA;

    @Override
    @Transactional
    public Usuario salvarUsuario(Cadastro cadastro) {

        var usuarioSalvo = this.jpa.save(toEntity(cadastro));

        var tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .usuario(usuarioSalvo)
                .role(cadastro.getUsuario().getTipoUsuario().getRole())
                .build();

        var tipoUsuariosalvo = this.tipoUsuarioJPA.save(tipoUsuarioEntity);

        return toDomain(usuarioSalvo, tipoUsuariosalvo);
    }

    private TipoUsuario tipoUsuarioDomain(TipoUsuarioEntity tipoUsuarioEntity){
        return null;
    }

    private Usuario toDomain(UsuarioEntity usuarioEntity, TipoUsuarioEntity tipoUsuarioEntity) {

        var tipoUsuarioDomain = new TipoUsuario();
        tipoUsuarioDomain.setRole(tipoUsuarioEntity.getRole());

        return new Usuario(usuarioEntity.getLogin(), usuarioEntity.getHashSenha(), tipoUsuarioDomain);
    }

    private UsuarioEntity toEntity(Cadastro cadastro) {

        return UsuarioEntity.builder()
                .login(cadastro.getUsuario().getLogin())
                .hashSenha(cadastro.getUsuario().getSenha())
                .build();
    }

    public Usuario buscarUsuarioPorId(UUID identificadorUsuario) {
        UsuarioEntity usuarioEntity = jpa.findById(identificadorUsuario)
                .orElseThrow(() -> new ClassCastException());

       TipoUsuarioEntity tipoUsuario = tipoUsuarioJPA.findByIdUsuario(identificadorUsuario);

        return toDomain(usuarioEntity, tipoUsuario);
    }
}
