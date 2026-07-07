package br.com.food_city.application.usecase;

import br.com.food_city.application.dto.CadastroInput;
import br.com.food_city.application.mapper.CadastroUsecaseMapper;
import br.com.food_city.domain.entities.Cadastro;
import br.com.food_city.domain.entities.enumerable.TipoRoleEnum;
import br.com.food_city.domain.repository.CadastroRepository;
import br.com.food_city.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SalvarDadosCadastradoUseCase {

    private final CadastroRepository cadastroRepository;

    private final UsuarioRepository usuarioRepository;

    private final CadastroUsecaseMapper mapper;


    @Transactional
    public Cadastro created(CadastroInput input, String properties) {

        var cadastro = mapper.toDomain(input);

        var cadastroSalvo = this.cadastroRepository.salvar(cadastro);
        var tipoUsuario = cadastroSalvo.getUsuario().getTipoUsuario();

        if (properties.equals("DONO")) {
            tipoUsuario.setRole(TipoRoleEnum.DONO.name());
        }
        if (properties.equals("CLIENTE")) {
            tipoUsuario.setRole(TipoRoleEnum.CLIENTE.name());
        }
        cadastroSalvo.getUsuario().getTipoUsuario().setRole(tipoUsuario.getRole());


        var usuario = this.usuarioRepository.salvarUsuario(cadastroSalvo);

        cadastroSalvo.setUsuario(usuario);

        return cadastroSalvo;
    }
}
