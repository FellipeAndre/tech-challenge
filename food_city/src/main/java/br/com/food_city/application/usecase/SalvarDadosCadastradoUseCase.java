package br.com.food_city.application.usecase;

import br.com.food_city.application.mapper.CadastroUsecaseMapper;
import br.com.food_city.domain.entities.Cadastro;
import br.com.food_city.domain.entities.enumerable.TipoRoleEnum;
import br.com.food_city.domain.repository.CadastroRepository;
import br.com.food_city.domain.repository.TipoUsuarioRepository;
import br.com.food_city.domain.repository.UsuarioRepository;
import br.com.food_city.application.dto.CadastroInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SalvarDadosCadastradoUseCase {

    private final CadastroRepository cadastroRepository;

    private final UsuarioRepository usuarioRepository;

    private final TipoUsuarioRepository tipoUsuarioRepository;

    private final CadastroUsecaseMapper mapper;


    public  Cadastro created (CadastroInput input) {

        var cadastro = mapper.toDomain(input);

        var cadastroSalvo = this.cadastroRepository.salvar(cadastro);
        var tipoUsuario = cadastroSalvo.getUsuario().getTipoUsuario();

        if(cadastroSalvo.isDono){
            tipoUsuario.setRole(TipoRoleEnum.DONO.name());
        }
        tipoUsuario.setRole(TipoRoleEnum.CLIENTE.name());


        this.usuarioRepository.salvarUsuario(cadastroSalvo);

        this.tipoUsuarioRepository.salvarTipoUsuario(cadastroSalvo.getUsuario().getTipoUsuario());

        // TODO SALAVR O USUARIO

        // TODO SALAVAR O TIPO USUARIO

        return null; // IRA RETORNA O CADASTRO QUE TERAR TODOS OS DADOS CARREGADO E FINALIZAR O FLUXO DE SALVAR NA BASE

        // IMPORTANTE ESSE FLUXO É O APENAS DE SALVAR O REGISTRO INICIAL PARA OUTRAS FUNCOES DO CRUD CADA CAMADA TERAR A SUA RESPOSABILIDADE

        // SOMENTE O CADASTRAR QUE SALVARA TODAS AS INFORMAÇÕES JUNTAS PARA NAO TER QUE CRIAR VARIOS CONTROLLERS PARA UMA MESMA NECESSIDADE DE SALVAR
    }
}
