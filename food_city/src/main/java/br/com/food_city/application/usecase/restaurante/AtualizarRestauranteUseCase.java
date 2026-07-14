package br.com.food_city.application.usecase.restaurante;

import br.com.food_city.application.dto.RestauranteInput;
import br.com.food_city.application.dto.RestauranteOutput;
import br.com.food_city.application.mapper.RestauranteUsecaseMapper;
import br.com.food_city.domain.entities.EnderecoDomain;
import br.com.food_city.domain.entities.Restaurante;
import br.com.food_city.exception.GlobalNotFoundException;
import br.com.food_city.infrastructure.gateway.RestauranteGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AtualizarRestauranteUseCase {

    private final RestauranteGateway restauranteGateway;

    @Transactional
    public RestauranteOutput executar(UUID id, RestauranteInput input) throws GlobalNotFoundException {
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

        var restauranteEncontrado = restauranteGateway.buscarPorId(id)
                .map(restaurante -> {
                    var endereco = input.getEndereco();
                    restaurante.setDonoId(input.getDonoId());
                    restaurante.setNome(input.getNome());
                    restaurante.setEndereco(new EnderecoDomain(
                        endereco.getLogradouro(), 
                        endereco.getNumero(), 
                        endereco.getBairro(), 
                        endereco.getEstado(), 
                        endereco.getMunicipio()
                    ));
                    restaurante.setTipoCozinha(input.getTipoCozinha());
                    restaurante.setHorarioAbertura(input.getHorarioAbertura());
                    restaurante.setHorarioFechamento(input.getHorarioFechamento());

                    return restaurante;
                })
                .orElseThrow(() -> new GlobalNotFoundException("Restaurante não encontrado com ID: " + id));

        return RestauranteUsecaseMapper.toOutput(this.restauranteGateway.salvar(restauranteEncontrado));
    }
}
