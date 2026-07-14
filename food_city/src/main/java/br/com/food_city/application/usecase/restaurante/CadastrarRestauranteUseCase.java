package br.com.food_city.application.usecase.restaurante;

import br.com.food_city.application.dto.RestauranteInput;
import br.com.food_city.application.dto.RestauranteOutput;
import br.com.food_city.application.mapper.RestauranteUsecaseMapper;
import br.com.food_city.domain.entities.Restaurante;
import br.com.food_city.domain.repository.CadastroRepository;
import br.com.food_city.domain.repository.RestauranteRepository;
import br.com.food_city.domain.repository.TipoUsuarioRepository;
import br.com.food_city.domain.repository.UsuarioRepository;
import br.com.food_city.exception.GlobalNotFoundException;
import br.com.food_city.exception.UsuarioNaoEncontradoException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastrarRestauranteUseCase {

    private final RestauranteRepository restauranteRepository;
    private final UsuarioRepository usuarioRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository;
    private final CadastroRepository cadastroRepository;

    @Transactional
    public RestauranteOutput executar(RestauranteInput input) throws UsuarioNaoEncontradoException, GlobalNotFoundException {
        // Validação: Nome não pode ser vazio
        if (input.getNome() == null || input.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do restaurante é obrigatório");
        }
        
        // Validação: Tipo de cozinha não pode ser vazio
        if (input.getTipoCozinha() == null || input.getTipoCozinha().isBlank()) {
            throw new IllegalArgumentException("Tipo de cozinha é obrigatório");
        }
        
        // Validação: Horário de abertura deve ser antes do fechamento
        if (input.getHorarioAbertura().isAfter(input.getHorarioFechamento()) || 
            input.getHorarioAbertura().equals(input.getHorarioFechamento())) {
            throw new IllegalArgumentException("Horário de abertura deve ser antes do fechamento");
        }
        
        // Validação: O dono (usuário) deve existir
        var usuarioExiste = cadastroRepository.existeUsuario(input.getDonoId());
        if (usuarioExiste.isEmpty()) {
            throw new GlobalNotFoundException("Usuário (dono) não encontrado com ID: " + input.getDonoId());
        }
        
        // Validação: Verificar se é proprietário
        String tipoUsuario = tipoUsuarioRepository.qualTipoUsuario(input.getDonoId());
        if (tipoUsuario == null || (!tipoUsuario.equals("PROPRIETARIO") && !tipoUsuario.equals("DONO"))) {
            throw new UsuarioNaoEncontradoException("Este usuário não pode cadastrar um restaurante!");
        }

        Restaurante restaurante = RestauranteUsecaseMapper.toDomain(input);
        Restaurante salvo = restauranteRepository.salvar(restaurante);
        return RestauranteUsecaseMapper.toOutput(salvo);
    }
}
