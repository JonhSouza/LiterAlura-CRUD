package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConversorJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private Scanner sc = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConversorJson conversor = new ConversorJson();

    private LivroRepository livroRepositorio;
    private AutorRepository autorRepositorio;

    public Principal(LivroRepository livroRepositorio, AutorRepository autorRepositorio) {
        this.livroRepositorio = livroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void exibeMenu() {
        System.out.println("Bem vindo a biblioteca LiterAlura!");
        System.out.println("---------------------------------");
        int resposta = -1;
        while (resposta != 0) {
            System.out.println("Escolha um número de sua opção: ");
            System.out.println("---------------------------------");
            System.out.println("1 - buscar e inserir livro pelo título: ");
            System.out.println("2 - listar livros registrados:  ");
            System.out.println("3 - listar autores registrados:  ");
            System.out.println("4 - listar autores vivos em um determinado ano: ");
            System.out.println("5 - listar livros em um determinado idioma: ");
            System.out.println("0 - sair ");

            resposta = sc.nextInt();
            sc.nextLine();

            switch (resposta) {
                case 1:
                    System.out.println("Digite o nome do Livro: ");
                    getDadosJackson();
                    System.out.println("---------------------------------");
                    break;
                case 2:
                    listarLivros();
                    System.out.println("---------------------------------");
                    break;
                case 3:
                    listarAutores();
                    System.out.println("---------------------------------");
                    break;
                case 4:
                    listarAutoresVivosPorAno();
                    System.out.println("---------------------------------");
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    System.out.println("---------------------------------");
                    break;
                case 0:
                    System.out.println("Finalizando operação...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    System.out.println("---------------------------------");
            }
        }
    }

    private void listarLivros() {

        List<Livro> livros = livroRepositorio.findAll();

        if (!livros.isEmpty()) {

            for (Livro livro : livros) {
                System.out.println("\n\n---------- LIVROS -------\n");
                System.out.println(" Titulo: " + livro.getTitulo());
                System.out.println(" Autor: " + livro.getAutor().getNome());
                System.out.println(" Idioma: " + livro.getIdioma());
                System.out.println(" Downloads: " + livro.getDownload());
                System.out.println("\n-------------------------\n\n");
            }

        } else {
            System.out.println("\n\n ----- Nehum livro encontrado! ---- \n\n");
        }

    }

    private void listarAutores() {
        List<Autor> autores = autorRepositorio.findAll();

        if (!autores.isEmpty()) {
            for (Autor autor : autores) {
                System.out.println("\n\n---------- Autores -------\n");
                System.out.println(" Nome: " + autor.getNome());
                System.out.println(" Ano de Nascimento: " + autor.getAnoNascimento());
                System.out.println(" Ano de falecimento: : " + autor.getAnoFalecimento());
                System.out.println(" Livros: " + autor.getLivros().getTitulo());
                System.out.println("\n-------------------------\n\n");
            }
        } else {
            System.out.println("\n\n ----- Não há resultados! ---- \n\n");

        }

    }

    private void listarAutoresVivosPorAno() {
        System.out.println("Escolha o ano de sua preferência: ");
        var ano = sc.nextInt();
        sc.nextLine();

        List<Autor> autores = autorRepositorio.findForYear(ano);

        if (!autores.isEmpty()) {
            for (Autor autor : autores) {
                System.out.println("\n\n---------- Autores Vivos -------\n");
                System.out.println(" Nome: " + autor.getNome());
                System.out.println(" Ano de Nascimento: " + autor.getAnoNascimento());
                System.out.println(" Ano de Falecimento: " + autor.getAnoFalecimento());
                System.out.println(" Livros: " + autor.getLivros().getTitulo());
                System.out.println("\n-------------------------\n\n");
            }
        } else {
            System.out.println("\n\n ----- NO SE ENCONTRARON RESULTADOS ---- \n\n");

        }
    }

    private void listarLivrosPorIdioma(){

        public void listarLivrosPorIdioma() {
            System.out.println("Digite o código do idioma (ex: en, fr, de, es, pt): ");
            String codigoIdioma = sc.nextLine();

            Idioma idioma;
            try {
                idioma = Idioma.fromCodigo(codigoIdioma);
            } catch (IllegalArgumentException e) {
                System.out.println("Código de idioma inválido: " + codigoIdioma);
                return;
            }

            List<Livro> livrosPorIdioma = // lógica para buscar livros pelo idioma
            if (livrosPorIdioma.isEmpty()) {
                System.out.println("Nenhum livro encontrado para o idioma: " + idioma.getCodigo());
            } else {
                System.out.println("Livros encontrados no idioma " + idioma.getCodigo() + ":");
                for (Livro livro : livrosPorIdioma) {
                    System.out.println("- " + livro.getTitulo());
                }
            }
        }

    private void buscarLivroPorTitulo() {
        RespostaLivros dados = getDadosJackson();

        if (!dados.results().isEmpty()) {

            Livro livro = new Livro(dados.results().get(0));
            livro = livroRepository.save(livro);

        }

        System.out.println("Dados: ");
        System.out.println(dados);
    }

    private RespostaLivros getDadosJackson() {
        System.out.println("Digite o nome do livro que deseja buscar: ");
        var titulo = sc.nextLine();
        titulo = titulo.replace(" ", "%20");
        System.out.println("Titulo : " + titulo);
        System.out.println(URL_BASE + titulo);
        var json = consumoApi.obterDados(URL_BASE + titulo);
        System.out.println(json);
        RespostaLivros dados = conversor.mesclarJson(json, RespostaLivros.class);
        return dados;
    }

}
}

