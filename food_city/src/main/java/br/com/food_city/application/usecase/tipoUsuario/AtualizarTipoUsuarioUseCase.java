package br.com.food_city.application.usecase.tipoUsuario;

import br.com.food_city.application.dto.TipoUsuarioInput;
import br.com.food_city.application.dto.TipoUsuarioOutput;
import br.com.food_city.application.mapper.TipoUsuarioUseCaseMapper;
import br.com.food_city.domain.repository.TipoUsuarioRepository;
import br.com.food_city.exception.GlobalNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AtualizarTipoUsuarioUseCase {
    
    private final TipoUsuarioRepository repository;
    private final TipoUsuarioUseCaseMapper mapper;
    
    @Transactional
    public TipoUsuarioOutput executar(UUID id, TipoUsuarioInput tipoUsuarioInput) throws GlobalNotFoundException {
        // Validação: tipo não pode ser null ou vazio
        if (tipoUsuarioInput == null || tipoUsuarioInput.nomeTipo() == null || tipoUsuarioInput.nomeTipo().isBlank()) {
            throw new IllegalArgumentException("Tipo de usuário não pode ser vazio");
        }
        
        // Validação: apenas tipos válidos são permitidos
        String tipoValidado = tipoUsuarioInput.nomeTipo().toUpperCase().trim();
        if (!isValidTipo(tipoValidado)) {
            throw new IllegalArgumentException("Tipo de usuário inválido. Tipos válidos: PROPRIETARIO, CLIENTE, FUNCIONARIO");
        }
        
        // Buscar tipo existente
        var tipoUsuario = repository.buscarPorId(id)
            .orElseThrow(() -> new GlobalNotFoundException("Tipo de usuário não encontrado com ID: " + id));
        
        // Atualizar
        tipoUsuario.setRole(tipoValidado);
        var atualizado = repository.salvar(tipoUsuario);
        
        return mapper.toOutput(atualizado);
    }
    
    private boolean isValidTipo(String tipo) {
        return tipo.equals("PROPRIETARIO") || tipo.equals("CLIENTE") || tipo.equals("FUNCIONARIO");
    }
}
