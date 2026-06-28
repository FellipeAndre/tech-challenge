package br.com.food_city.infrastructure.gateway;

import br.com.food_city.domain.entities.TipoUsuario;
import br.com.food_city.domain.repository.TipoUsuarioRepository;
import br.com.food_city.infrastructure.entity.TipoUsuarioEntity;
import br.com.food_city.infrastructure.repository.TipoUsuarioJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TipoUsuarioGateway implements TipoUsuarioRepository {

    private final TipoUsuarioJPA jpa;
    @Override
    public TipoUsuarioEntity salvarTipoUsuario(TipoUsuario tipoUsuario) {
        return this.jpa.save(toEntity(tipoUsuario));
    }

    private TipoUsuarioEntity toEntity(TipoUsuario tipoUsuario) {
        return TipoUsuarioEntity.builder()
                .role(tipoUsuario.getRole())
                .build();
    }
}
