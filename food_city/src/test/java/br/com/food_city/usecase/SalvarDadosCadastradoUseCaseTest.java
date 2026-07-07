package br.com.food_city.usecase;

import br.com.food_city.application.dto.CadastroInput;
import br.com.food_city.application.dto.EnderecoInput;
import br.com.food_city.application.mapper.CadastroUsecaseMapper;
import br.com.food_city.application.usecase.SalvarDadosCadastradoUseCase;
import br.com.food_city.config.CadastroProperties;
import br.com.food_city.domain.entities.Cadastro;
import br.com.food_city.domain.entities.Usuario;
import br.com.food_city.domain.entities.enumerable.TipoRoleEnum;
import br.com.food_city.domain.repository.CadastroRepository;
import br.com.food_city.domain.repository.UsuarioRepository;
import br.com.food_city.mock.MockitoTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalvarDadosCadastradoUseCaseTest {

    @InjectMocks
    SalvarDadosCadastradoUseCase useCase;

    @Mock
    CadastroRepository cadastroRepository;

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    CadastroUsecaseMapper mapper;

    @Mock
    CadastroProperties properties;

    private CadastroInput cadastroInputValido() {
        var enderecoInput = new EnderecoInput("Rua dos ciripos", "345", "Silvios", "São Paulo", "Diadema");
        return new CadastroInput("nome", "email@teste.com", "34545567677", "02-10-1993", enderecoInput);
    }

    @Test
    void deveDefinirRoleDono_quandoCampoIsDonoForTrue() {

        // ARRANGE
        CadastroInput input = cadastroInputValido();

        Cadastro cadastroMapeado = MockitoTest.cadastroValido();

        Cadastro cadastroSalvo = MockitoTest.cadastroValido();
        Usuario usuarioComTipo = MockitoTest.usuarioValido();
        cadastroSalvo.setUsuario(usuarioComTipo);

        when(properties.getProprietary()).thenReturn("DONO");
        when(mapper.toDomain(input)).thenReturn(cadastroMapeado);
        when(cadastroRepository.salvar(cadastroMapeado)).thenReturn(cadastroSalvo);
        when(usuarioRepository.salvarUsuario(cadastroSalvo)).thenReturn(usuarioComTipo);

        // ACT
        var result = this.useCase.created(input, properties.getProprietary());

        // ASSERT
        Assertions.assertNotNull(result);
        Assertions.assertEquals(TipoRoleEnum.DONO.name(), result.getUsuario().getTipoUsuario().getRole());
    }

    @Test
    void deveDefinirRoleCliente_quandoCampoIsDonoForFalse() {

        // ARRANGE
        CadastroInput input = cadastroInputValido();

        Cadastro cadastroMapeado = MockitoTest.cadastroValido();

        Cadastro cadastroSalvo = MockitoTest.cadastroValido();
        Usuario usuarioComTipo = MockitoTest.usuarioValido();
        cadastroSalvo.setUsuario(usuarioComTipo);

        when(properties.getClient()).thenReturn("CLIENTE");
        when(mapper.toDomain(input)).thenReturn(cadastroMapeado);
        when(cadastroRepository.salvar(cadastroMapeado)).thenReturn(cadastroSalvo);
        when(usuarioRepository.salvarUsuario(cadastroSalvo)).thenReturn(usuarioComTipo);

        // ACT
        var result = this.useCase.created(input, properties.getClient());

        // ASSERT
        Assertions.assertNotNull(result);
        Assertions.assertEquals(TipoRoleEnum.CLIENTE.name(), result.getUsuario().getTipoUsuario().getRole());
    }
}