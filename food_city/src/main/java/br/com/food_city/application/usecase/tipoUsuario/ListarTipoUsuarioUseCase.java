package br.com.food_city.application.usecase.tipoUsuario;

import br.com.food_city.application.dto.TipoUsuarioOutput;
import br.com.food_city.application.mapper.TipoUsuarioUseCaseMapper;
import br.com.food_city.domain.repository.TipoUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListarTipoUsuarioUseCase {
    
    private final TipoUsuarioRepository repository;
    private final TipoUsuarioUseCaseMapper mapper;
    
    public List<TipoUsuarioOutput> executar() {
        var tipos = repository.listarTodos();
        return tipos.stream()
            .map(mapper::toOutput)
            .toList();
    }
}
