package br.com.food_city.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "user.cadastro")
@Getter
@Setter
public class CadastroProperties {

    private String client;
    private String proprietary;
}
