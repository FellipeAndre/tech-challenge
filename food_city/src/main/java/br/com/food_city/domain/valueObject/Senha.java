package br.com.food_city.domain.valueObject;

/*
    Classe responsavel por criar um hash da senha do usuario que esta sendo salvo
 */

import br.com.food_city.exception.SenhaInvalidaException;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Senha {

    @Getter
    private final String hash;

    private static final BCryptPasswordEncoder encode = new BCryptPasswordEncoder();

    private Senha(String senha){
        this.hash = senha;
    }

    public static String criarHashSeguro(String senha){

        if(senha == null || senha.isBlank()){
            throw new SenhaInvalidaException("Necessário informar a senha");
        }

        if(senha.length() < 8){
            throw  new SenhaInvalidaException("Senha Tem que ter mais de 8 caracteres");
        }

        var senhaHash = new Senha(encode.encode(senha));

        return senhaHash.hash;
    }

    public boolean permissao(String senha){
        return encode.matches(senha, hash);
    }

}
