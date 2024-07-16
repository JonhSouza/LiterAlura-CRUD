package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Idioma {
    EN("en"),
    FR("fr"),
    DE("de"),
    ES("es"),
    PT("pt");

    private final String codigo;

    Idioma(String codigo) {
        this.codigo = codigo;
    }

    @JsonValue
    public String getCodigo() {
        return codigo;
    }

    @JsonCreator
    public static Idioma fromCodigo(String codigo) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.codigo.equals(codigo)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Código de idioma desconhecido: " + codigo);
    }
}
