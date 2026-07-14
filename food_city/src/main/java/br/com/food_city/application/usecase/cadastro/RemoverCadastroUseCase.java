package br.com.food_city.application.usecase.cadastro;

import br.com.food_city.domain.repository.CadastroRepository;
import br.com.food_city.exception.GlobalNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RemoverCadastroUseCase {

    private final CadastroRepository cadastroRepository;

    @Transactional
    public void executar(UUID id) throws GlobalNotFoundException {
        // Validar se existe antes de remover
        cadastroRepository.buscarPorId(id)
            .orElseThrow(() -> new GlobalNotFoundException("Cadastro não encontrado com ID: " + id));
        
        cadastroRepository.remover(id);
    }
}
