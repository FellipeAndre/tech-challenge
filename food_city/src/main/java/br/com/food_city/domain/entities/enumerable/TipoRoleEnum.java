package br.com.food_city.domain.entities.enumerable;

public enum TipoRoleEnum {

    DONO,
    CLIENTE,
    FUNCIONARIO;


    public static TipoRoleEnum from(String role) {

        for (TipoRoleEnum tipo : values()) {
            if (tipo.name().equalsIgnoreCase(role)) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Tipo de usuário inválido: " + role);
    }
}
