package br.com.alura.literalura.service;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @Transactional
    public Livro salvarLivro(Livro livro) {
        Autor autor = livro.getAutor();
        if (autor != null && autor.getId() == null) {
            autorRepository.save(autor);
        }
        return livroRepository.save(livro);
    }
}
