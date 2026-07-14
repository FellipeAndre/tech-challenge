package br.com.food_city.application.usecase.cadastro;

import br.com.food_city.application.dto.CadastroInput;
import br.com.food_city.application.dto.CadastroOutput;
import br.com.food_city.application.mapper.CadastroUsecaseMapper;
import br.com.food_city.domain.entities.EnderecoDomain;
import br.com.food_city.domain.repository.CadastroRepository;
import br.com.food_city.exception.GlobalNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AtualizarCadastroUseCase {

    private final CadastroRepository cadastroRepository;
    private final CadastroUsecaseMapper mapper;

    @Transactional
    public CadastroOutput executar(UUID id, CadastroInput input) throws GlobalNotFoundException {
        // Validação: Nome não pode ser vazio
        if (input.getNome() == null || input.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        
        // Validação: Email não pode ser vazio
        if (input.getEmail() == null || input.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }
        
        // Validação: Email válido
        if (!isEmailValido(input.getEmail())) {
            throw new IllegalArgumentException("Email inválido");
        }

        // Buscar cadastro existente
        var cadastro = cadastroRepository.buscarPorId(id)
            .orElseThrow(() -> new GlobalNotFoundException("Cadastro não encontrado com ID: " + id));
        
        // Atualizar campos do cadastro
        cadastro.setNome(input.getNome());
        cadastro.setEmail(input.getEmail());
        
        // Atualizar endereço se fornecido
        if (input.getEnderecoInput() != null) {
            var enderecoInput = input.getEnderecoInput();
            var enderecoDomain = new EnderecoDomain(
                enderecoInput.getLogradouro(), 
                enderecoInput.getNumero(), 
                enderecoInput.getBairro(), 
                enderecoInput.getEstado(), 
                enderecoInput.getMunicipio()
            );
            cadastro.setEndereco(enderecoDomain);
        }
        
        var atualizado = cadastroRepository.salvar(cadastro);
        return mapper.toOutput(atualizado);
    }
    
    private boolean isEmailValido(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }
}
