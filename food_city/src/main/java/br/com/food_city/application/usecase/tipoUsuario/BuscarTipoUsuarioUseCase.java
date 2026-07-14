package br.com.food_city.application.usecase.tipoUsuario;

import br.com.food_city.application.dto.TipoUsuarioOutput;
import br.com.food_city.application.mapper.TipoUsuarioUseCaseMapper;
import br.com.food_city.domain.repository.TipoUsuarioRepository;
import br.com.food_city.exception.GlobalNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BuscarTipoUsuarioUseCase {
    
    private final TipoUsuarioRepository repository;
    private final TipoUsuarioUseCaseMapper mapper;
    
    public TipoUsuarioOutput executar(UUID id) throws GlobalNotFoundException {
        var tipoUsuario = repository.buscarPorId(id)
            .orElseThrow(() -> new GlobalNotFoundException("Tipo de usuário não encontrado com ID: " + id));
        
        return mapper.toOutput(tipoUsuario);
    }
}
