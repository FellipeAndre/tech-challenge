package br.com.food_city.application.usecase.usuario;

import br.com.food_city.application.dto.UsuarioInput;
import br.com.food_city.domain.entities.Usuario;
import br.com.food_city.domain.repository.UsuarioRepository;
import br.com.food_city.exception.GlobalNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AtualizarUsuarioUseCase {

    private final UsuarioRepository repository;

    @Transactional
    public void executar(UUID id, UsuarioInput input) throws GlobalNotFoundException {
        // Validação: login não pode ser vazio
        if (input.getLogin() == null || input.getLogin().isBlank()) {
            throw new IllegalArgumentException("Login não pode ser vazio");
        }
        
        // Validação: senha não pode ser vazia
        if (input.getSenha() == null || input.getSenha().isBlank()) {
            throw new IllegalArgumentException("Senha não pode ser vazia");
        }

        // Buscar usuário existente
        var usuario = repository.buscarPorId(id)
            .orElseThrow(() -> new GlobalNotFoundException("Usuário não encontrado com ID: " + id));
        
        // Atualizar apenas a senha
        usuario.setSenha(input.getSenha());
        
        repository.salvar(usuario);
    }
}
