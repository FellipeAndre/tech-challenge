package br.com.food_city.usecase;

import br.com.food_city.application.usecase.SalvarDadosCadastradoUseCase;
import br.com.food_city.domain.repository.CadastroRepository;
import br.com.food_city.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SalvarDadosCadastradoUseCaseTest {

    @InjectMocks
    SalvarDadosCadastradoUseCase salvarDadosCadastradoUseCase;

    @Mock
    CadastroRepository cadastroRepository;

    @Mock
    UsuarioRepository usuarioRepository;

    void salvarDadosCadastradoComSucesso(){


    }
}