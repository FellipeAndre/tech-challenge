package br.com.food_city.application.usecase.cadastro;

import br.com.food_city.application.dto.CadastroOutput;
import br.com.food_city.application.mapper.CadastroUsecaseMapper;
import br.com.food_city.domain.repository.CadastroRepository;
import br.com.food_city.exception.GlobalNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BuscarCadastroUseCase {

    private final CadastroRepository cadastroRepository;
    private final CadastroUsecaseMapper mapper;

    public CadastroOutput executar(UUID id) throws GlobalNotFoundException {
        var cadastro = cadastroRepository.buscarPorId(id)
            .orElseThrow(() -> new GlobalNotFoundException("Cadastro não encontrado com ID: " + id));
        
        return mapper.toOutput(cadastro);
    }
}
