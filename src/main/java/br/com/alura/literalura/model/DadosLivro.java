package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/*@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DadosAutor> autores,
        @JsonAlias("languages") List<Idioma> idiomas,
        @JsonAlias("download_count") Integer downloads) {

    @Override
    public String toString() {
        return "titulo='" + titulo + '\'' +
                ", autor=" + autores +
                ", idioma=" + idiomas +
                ", downloads=" + downloads;
    }
}*/

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        @JsonAlias("id") String id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<Autor> autores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Integer download
) {

}
