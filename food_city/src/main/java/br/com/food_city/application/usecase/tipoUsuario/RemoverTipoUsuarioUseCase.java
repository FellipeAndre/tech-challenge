package br.com.food_city.application.usecase.tipoUsuario;

import br.com.food_city.domain.repository.TipoUsuarioRepository;
import br.com.food_city.exception.GlobalNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RemoverTipoUsuarioUseCase {
    
    private final TipoUsuarioRepository repository;
    
    @Transactional
    public void executar(UUID id) throws GlobalNotFoundException {
        // Validar se existe antes de remover
        repository.buscarPorId(id)
            .orElseThrow(() -> new GlobalNotFoundException("Tipo de usuário não encontrado com ID: " + id));
        
        repository.remover(id);
    }
}
