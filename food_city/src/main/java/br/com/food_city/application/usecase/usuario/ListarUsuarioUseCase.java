package br.com.food_city.application.usecase.usuario;

import br.com.food_city.domain.entities.Usuario;
import br.com.food_city.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListarUsuarioUseCase {

    private final UsuarioRepository repository;

    public List<Usuario> executar() {
        return repository.listarTodos();
    }
}
