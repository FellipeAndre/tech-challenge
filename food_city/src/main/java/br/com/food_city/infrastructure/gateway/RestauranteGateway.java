package br.com.food_city.infrastructure.gateway;
import br.com.food_city.domain.entities.Restaurante;
import br.com.food_city.domain.repository.RestauranteRepository;
import br.com.food_city.infrastructure.entity.RestauranteEntity;
import br.com.food_city.infrastructure.repository.RestauranteRepositoryJPA;
import br.com.food_city.infrastructure.repository.UsuarioRepositoryJpa;
import br.com.food_city.infrastructure.mapper.RestauranteInfraMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RestauranteGateway implements RestauranteRepository {

    private final RestauranteRepositoryJPA repositoryJPA;

    @Override
    public Restaurante salvar(Restaurante restaurante) {
        RestauranteEntity entity = RestauranteInfraMapper.toEntity(restaurante);
        RestauranteEntity salvo = repositoryJPA.save(entity);
        return RestauranteInfraMapper.toDomain(salvo);
    }

    @Override
    public Optional<Restaurante> buscarPorId(UUID id) {
        return repositoryJPA.findById(id).map(RestauranteInfraMapper::toDomain);
    }

    @Override
    public List<Restaurante> listarTodos() {
        return repositoryJPA.findAll().stream()
                .map(RestauranteInfraMapper::toDomain)
                .toList();
    }

//    @Override
//    public List<Restaurante> listarPorDono(UUID donoId) {
//        return repositoryJPA.findByDonoId(donoId).stream()
//                .map(RestauranteInfraMapper::toDomain)
//                .toList();
//    }

    @Override
    public void remover(UUID id) {
        repositoryJPA.deleteById(id);
    }

}
