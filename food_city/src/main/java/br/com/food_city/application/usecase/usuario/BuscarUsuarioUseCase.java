package br.com.food_city.application.usecase.usuario;

import br.com.food_city.domain.entities.Usuario;
import br.com.food_city.domain.repository.UsuarioRepository;
import br.com.food_city.exception.GlobalNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BuscarUsuarioUseCase {

    private final UsuarioRepository repository;

    public Usuario executar(UUID id) throws GlobalNotFoundException {
        return repository.buscarPorId(id)
            .orElseThrow(() -> new GlobalNotFoundException("Usuário não encontrado com ID: " + id));
    }
}
