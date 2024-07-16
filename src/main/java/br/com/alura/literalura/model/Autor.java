package br.com.alura.literalura.model;

import br.com.alura.literalura.utilidade.Util;
import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;


    @OneToOne
    @JoinTable(
            name = "Livros",
            joinColumns = @JoinColumn(name = "autor_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private Livro livros;


    public Autor() {

    }

    public Autor(Autor autor) {
        this.nome = Util.limitarLongitud(autor.getNome(), 200);

        if (DadosAutor.anoNascimento() == null)
            this.anoFalecimento = 1980;
        else
            this.anoFalecimento = DadosAutor.anoFalecimento();

        if (DadosAutor.anoFalecimento() == null)
            this.anoFalecimento = 3022;
        else
            this.anoFalecimento = DadosAutor.anoFalecimento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }


    @Override
    public String toString() {
        return "[id=" + id + ", nome=" + nome + ", ano de nascimento=" + anoNascimento
                + ", ano de falecimento=" + anoFalecimento;
    }

    public Livro getLivros() {
        return livros;
    }

    public void setLivros(Livros livros) {
        this.livros = livros;
    }

}
