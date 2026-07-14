package br.com.food_city.application.mapper;

import br.com.food_city.application.dto.TipoUsuarioInput;
import br.com.food_city.application.dto.TipoUsuarioOutput;
import br.com.food_city.domain.entities.TipoUsuario;
import org.springframework.stereotype.Component;

@Component
public class TipoUsuarioUseCaseMapper {

    public TipoUsuario toDomain(TipoUsuarioInput input) {
        return new TipoUsuario(null, input.nomeTipo());
    }

    public TipoUsuarioOutput toOutput(TipoUsuario tipoUsuario) {
        return new TipoUsuarioOutput(tipoUsuario.getId(), tipoUsuario.getRole());
    }
}
