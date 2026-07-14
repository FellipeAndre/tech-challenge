package br.com.food_city.application.usecase.usuario;

import br.com.food_city.domain.repository.UsuarioRepository;
import br.com.food_city.exception.GlobalNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RemoverUsuarioUseCase {

    private final UsuarioRepository repository;

    @Transactional
    public void executar(UUID id) throws GlobalNotFoundException {
        // Validar se existe antes de remover
        repository.buscarPorId(id)
            .orElseThrow(() -> new GlobalNotFoundException("Usuário não encontrado com ID: " + id));
        
        repository.remover(id);
    }
}
