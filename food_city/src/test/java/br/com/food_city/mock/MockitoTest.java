package br.com.food_city.mock;

import br.com.food_city.domain.entities.Cadastro;
import br.com.food_city.domain.entities.EnderecoDomain;
import br.com.food_city.domain.entities.TipoUsuario;
import br.com.food_city.domain.entities.Usuario;

import java.util.UUID;

public class MockitoTest {

    public static EnderecoDomain enderecoValido() {
        return new EnderecoDomain("Rua dos ciripos", "345", "Silvios", "São Paulo", "Diadema");
        // ajuste a ordem se o construtor de EnderecoDomain for diferente
    }

    public static Cadastro cadastroValido() {
        return new Cadastro(
                UUID.randomUUID(),
                "nome",
                "email@teste.com",
                "34545567677",     // precisa passar em Documento.validarDocumento
                "1993-02-10",       // precisa passar em DataFormatter.formatarData
                enderecoValido()
        );
    }

    public static TipoUsuario tipoUsuarioSemRole() {
        // TipoUsuario não tem construtor com parâmetros — role é setado depois, pelo use case
        return new TipoUsuario();
    }

    public static Usuario usuarioValido() {
        return new Usuario(UUID.randomUUID(), "login.teste", tipoUsuarioSemRole());
    }

}
