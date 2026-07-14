package br.com.food_city.application.usecase.tipoUsuario;

import br.com.food_city.application.dto.TipoUsuarioInput;
import br.com.food_city.application.dto.TipoUsuarioOutput;
import br.com.food_city.application.mapper.TipoUsuarioUseCaseMapper;
import br.com.food_city.domain.repository.TipoUsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastrarTipoUsuarioUseCase {

    private final TipoUsuarioRepository repository;
    private final TipoUsuarioUseCaseMapper mapper;

    @Transactional
    public TipoUsuarioOutput executar(TipoUsuarioInput input) {
        // Validação: tipo não pode ser null ou vazio
        if (input == null || input.nomeTipo() == null || input.nomeTipo().isBlank()) {
            throw new IllegalArgumentException("Tipo de usuário não pode ser vazio");
        }
        
        // Validação: apenas tipos válidos são permitidos (PROPRIETARIO, CLIENTE, FUNCIONARIO)
        String tipoValidado = input.nomeTipo().toUpperCase().trim();
        if (!isValidTipo(tipoValidado)) {
            throw new IllegalArgumentException("Tipo de usuário inválido. Tipos válidos: PROPRIETARIO, CLIENTE, FUNCIONARIO");
        }
        
        var tipoUsuario = mapper.toDomain(input);
        var salvo = repository.salvar(tipoUsuario);
        return mapper.toOutput(salvo);
    }
    
    private boolean isValidTipo(String tipo) {
        return tipo.equals("PROPRIETARIO") || tipo.equals("CLIENTE") || tipo.equals("FUNCIONARIO");
    }
}
