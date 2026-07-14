package br.com.food_city.infrastructure.gateway;

import br.com.food_city.domain.entities.Cadastro;
import br.com.food_city.domain.entities.EnderecoDomain;
import br.com.food_city.domain.repository.CadastroRepository;
import br.com.food_city.infrastructure.entity.CadastroEntity;
import br.com.food_city.infrastructure.entity.EnderecoEmbeddable;
import br.com.food_city.infrastructure.entity.TipoUsuarioEntity;
import br.com.food_city.infrastructure.entity.UsuarioEntity;
import br.com.food_city.infrastructure.mapper.CadastroInfraMapper;
import br.com.food_city.infrastructure.mapper.UsuarioInfraMapper;
import br.com.food_city.infrastructure.repository.CadastrarRepositoryJPA;
import br.com.food_city.infrastructure.repository.TipoUsuarioRepositoryJpa;
import br.com.food_city.infrastructure.repository.UsuarioRepositoryJpa;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/*
*
* Classe Responsavel por ser a porta de entrada para salvar os dados na base
*  Convertendo o objeto dto para Entity usando o JPA
*
* */

@Component
@RequiredArgsConstructor
public class CadastrarGateway implements CadastroRepository {

    private final CadastrarRepositoryJPA repositoryJPA;
    private final UsuarioRepositoryJpa usuarioRepositoryJpa;
    private final TipoUsuarioRepositoryJpa tipoUsuarioRepositoryJpa;

    @Override
    @Transactional
    public Cadastro salvar(Cadastro cadastro) {

        var usuarioEntity = UsuarioInfraMapper.toEntity(cadastro.getUsuario());
        var usuarioSalvo = this.usuarioRepositoryJpa.save(usuarioEntity);


        var cadastroEntity = toEntity(cadastro);
        cadastroEntity.setUsuarioEntity(usuarioSalvo); // sobrescreve o Usuario transiente do mapper
        var cadastroSalvo = this.repositoryJPA.save(cadastroEntity);


        var tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .usuario(usuarioSalvo)
                .role(cadastro.getTipoUsuario().getRole())
                .build();
        this.tipoUsuarioRepositoryJpa.save(tipoUsuarioEntity);

        return CadastroInfraMapper.toDomain(cadastroSalvo);
    }

    @Override
    public Optional<Cadastro> buscarPorId(UUID id) {
        var cadastroEncontrado = this.repositoryJPA.findById(id).orElseThrow();
        return Optional.of(CadastroInfraMapper.toDomain(cadastroEncontrado));
    }

    @Override
    public List<Cadastro> listarTodos() {
        return List.of();
    }

    @Override
    public List<Cadastro> listarPorDono(Long donoId) {
        return List.of();
    }

    @Override
    public void remover(UUID id) {

    }

    @Override
    public Optional<Cadastro> existeUsuario(UUID donoId) {
        return buscarPorId(donoId);
    }


    private CadastroEntity toEntity(Cadastro cadastro){

        var usuario = cadastro.getUsuario();
        var usuariEntity = new UsuarioEntity(null, usuario.getLogin(), usuario.getSenha(), true, LocalDate.now());
        var enderecoDomain = cadastro.getEndereco();
        var endDomain = new EnderecoEmbeddable(enderecoDomain.getLorgadouro(), enderecoDomain.getNumero(), enderecoDomain.getBairro(), enderecoDomain.getEstado(), enderecoDomain.getMunicipio());

       return new CadastroEntity(cadastro.getIdentificador(), cadastro.getNome(), cadastro.getEmail(), cadastro.getNumeroDocumento(), cadastro.getDataNascimento(), LocalDateTime.now(), null, endDomain, usuariEntity);

    }
}
