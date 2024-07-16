package br.com.alura.literalura.model;

import br.com.alura.literalura.utilidade.Util;
import jakarta.persistence.*;

import java.util.stream.Collectors;

/*@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idiomas;
    private Integer downloads;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    // Construtor padrão
    public Livro() {}

    // Construtor que aceita DadosLivro
    public class Livro {
        private String titulo;
        private String idiomas;
        private int downloads;
        private Autor autor;

        public Livro(DadosLivro dados) {
            this.titulo = dados.titulo();
            this.idiomas = String.join(", ", dados.idiomas().stream()
                    .map(Idioma::getCodigo)
                    .toList());
            this.downloads = dados.downloads();

            if (dados.autores() != null && !dados.autores().isEmpty()) {
                DadosAutor dadosAutor = dados.autores().get(0);
                this.autor = new Autor();
            }
        }

        // Getters e setters, se necessário
    }


    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}*/

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private Integer download;
    @OneToOne(mappedBy = "livros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Autor autor;

    public Livro() {

    }

    public Livro(DadosLivro livro) {
        this.titulo = Util.limitarLongitud(livro.titulo(), 200);
        this.download = livro.download();
        if (!livro.idiomas().isEmpty())
            this.idioma = livro.idiomas().get(0);
        if (!livro.autores().isEmpty()) {
            for (Autor autor : livro.autores()) {
                this.autor = new Autor(autor);
                break;
            }
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", titulo=" + titulo + ", idioma=" + idioma + ", downloads=" + download
                + ", autores=" + autor + "]";
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

}
