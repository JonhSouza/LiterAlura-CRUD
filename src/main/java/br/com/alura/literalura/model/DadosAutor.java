package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*@JsonIgnoreProperties
public record DadosAutor(@JsonAlias("name") String nome, @JsonAlias("birth_year") Integer anoNascimento, @JsonAlias("death_year") Integer anoFalecimento) {

    @Override
    public String toString() {

        return  "nome= " + nome +  ", anoNascimento=" + anoNascimento +
                ", anoFalecimento=" + anoFalecimento;
    }
}*/

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(
        @JsonAlias("name") String nome,
        @JsonAlias("birth_year") Integer anoNascimento,
        @JsonAlias("death_year") Integer anoFalecimento
) {

}
