package br.com.food_city.infrastructure.gateway;

import br.com.food_city.domain.entities.Cadastro;
import br.com.food_city.domain.entities.Usuario;
import br.com.food_city.domain.repository.UsuarioRepository;
import br.com.food_city.exception.GlobalNotFoundException;
import br.com.food_city.exception.UsuarioNaoEncontradoException;
import br.com.food_city.infrastructure.entity.TipoUsuarioEntity;
import br.com.food_city.infrastructure.entity.UsuarioEntity;
import br.com.food_city.infrastructure.mapper.UsuarioInfraMapper;
import br.com.food_city.infrastructure.repository.CadastrarRepositoryJPA;
import br.com.food_city.infrastructure.repository.TipoUsuarioRepositoryJpa;
import br.com.food_city.infrastructure.repository.UsuarioRepositoryJpa;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static br.com.food_city.infrastructure.mapper.UsuarioInfraMapper.toDomain;

@Component
@RequiredArgsConstructor
public class UsuarioGateway implements UsuarioRepository {

    private final UsuarioRepositoryJpa usuarioRepositoryJpa;


    @Override
    public Usuario salvarUsuario(Cadastro cadastro) throws GlobalNotFoundException {
        var usuarioEntity = UsuarioInfraMapper.toEntity(cadastro.getUsuario());
        return toDomain(this.usuarioRepositoryJpa.save(usuarioEntity));
    }

    public Usuario buscarUsuarioPorId(UUID identificadorUsuario) throws UsuarioNaoEncontradoException {
        UsuarioEntity usuarioEntity = usuarioRepositoryJpa.findById(identificadorUsuario)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario nao encontrado!!!"));

        return toDomain(usuarioEntity);
    }

    @Override
    public Optional<Usuario> buscarPorId(UUID id) {
        var retorno = this.usuarioRepositoryJpa.findById(id);
        return Optional.of(toDomain(retorno.get()));
    }

    @Override
    public List<Usuario> listarTodos() {
        return List.of();
    }

    @Override
    public List<Usuario> listarPorDono(UUID donoId) {
        return List.of();
    }

    @Override
    public void remover(UUID id) {

    }

    @Override
    public void salvar(Usuario usuario) {
        this.usuarioRepositoryJpa.save(UsuarioInfraMapper.toEntity(usuario));
    }

}
