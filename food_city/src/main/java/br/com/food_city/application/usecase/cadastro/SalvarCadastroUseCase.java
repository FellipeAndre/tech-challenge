package br.com.food_city.application.usecase.cadastro;

import br.com.food_city.application.dto.CadastroInput;
import br.com.food_city.application.mapper.CadastroUsecaseMapper;
import br.com.food_city.domain.entities.Cadastro;
import br.com.food_city.domain.entities.TipoUsuario;
import br.com.food_city.domain.entities.enumerable.TipoRoleEnum;
import br.com.food_city.domain.repository.CadastroRepository;
import br.com.food_city.exception.GlobalNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SalvarCadastroUseCase {

    private final CadastroRepository cadastroRepository;

    private final CadastroUsecaseMapper mapper;

    public Cadastro created(CadastroInput input, String properties) throws GlobalNotFoundException {

        var cadastro = mapper.toDomain(input);

        var tipoUsuario = new TipoUsuario();
        tipoUsuario.setRole(TipoRoleEnum.from(properties).name());

        cadastro.setTipoUsuario(tipoUsuario);

        return this.cadastroRepository.salvar(cadastro);
    }
}
