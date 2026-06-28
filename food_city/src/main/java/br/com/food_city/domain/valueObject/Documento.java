package br.com.food_city.domain.valueObject;


import br.com.food_city.exception.DocumentoInvalidoException;
import lombok.Getter;

public class Documento {

    // Por se tratar de uma classe ValueObject meus atributos são imutáveis

    @Getter
    private final String documento;


    private Documento(String documento) {
        this.documento = documento;
    }

    public static String validarDocumento(String documento) {

        if (documento == null || documento.isBlank()) {
            throw new IllegalArgumentException("Documento obrigatório");
        }

        var possuiCaracterRepitido = documento.chars()
                .allMatch(c -> c == documento.charAt(0));
        if (possuiCaracterRepitido) {
            throw new IllegalArgumentException("Documento Inválido");
        }

        var docLimpo = documento.replaceAll("\\D", "");

        if (docLimpo.length() == 11 || docLimpo.length() == 14) {
            return docLimpo;

        }
        throw new DocumentoInvalidoException("Formato inválido: esperado CPF ou CNPJ");

    }

}
