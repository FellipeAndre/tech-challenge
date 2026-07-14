package br.com.food_city.application.usecase.cadastro;

import br.com.food_city.application.dto.CadastroOutput;
import br.com.food_city.application.mapper.CadastroUsecaseMapper;
import br.com.food_city.domain.repository.CadastroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListarCadastroUseCase {

    private final CadastroRepository cadastroRepository;
    private final CadastroUsecaseMapper mapper;

    public List<CadastroOutput> executar() {
        var cadastros = cadastroRepository.listarTodos();
        return cadastros.stream()
            .map(mapper::toOutput)
            .toList();
    }
}
