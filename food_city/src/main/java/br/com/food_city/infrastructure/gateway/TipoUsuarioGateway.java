package br.com.food_city.infrastructure.gateway;

import br.com.food_city.domain.entities.Cadastro;
import br.com.food_city.domain.entities.TipoUsuario;
import br.com.food_city.domain.repository.TipoUsuarioRepository;
import br.com.food_city.infrastructure.entity.TipoUsuarioEntity;
import br.com.food_city.infrastructure.mapper.TipoUsuarioInfraMapper;
import br.com.food_city.infrastructure.mapper.UsuarioInfraMapper;
import br.com.food_city.infrastructure.repository.TipoUsuarioRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.com.food_city.infrastructure.mapper.TipoUsuarioInfraMapper.toDomain;

@Component
@RequiredArgsConstructor
public class TipoUsuarioGateway implements TipoUsuarioRepository {

    private final TipoUsuarioRepositoryJpa tipoUsuarioRepositoryJpa;

    @Override
    public TipoUsuario salvar(TipoUsuario tipoUsuario) {
        return toDomain(this.tipoUsuarioRepositoryJpa.save(TipoUsuarioInfraMapper.toEntity(tipoUsuario)));
    }

    @Override
    public String qualTipoUsuario(UUID donoId) {
        var tipoUsuario = this.tipoUsuarioRepositoryJpa.findByUsuario_IdUsuario(donoId);
        return tipoUsuario.getRole();
    }

    @Override
    public TipoUsuario salvarTipoUsuario(Cadastro cadastro) {

        var entity = UsuarioInfraMapper.toEntity(cadastro.getUsuario());

        var tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .usuario(entity)
                .role(cadastro.getTipoUsuario().getRole())
                .build();

        return toDomain(this.tipoUsuarioRepositoryJpa.save(tipoUsuarioEntity));
    }

    @Override
    public Optional<TipoUsuario> buscarPorId(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<TipoUsuario> listarTodos() {
        return List.of();
    }


    @Override
    public void remover(UUID id) {

    }

    @Override
    public void associarUsuario(UUID usuarioId, UUID tipoUsuarioId) {

    }

    @Override
    public boolean existeUsuario(UUID usuarioId) {
        return false;
    }
}
