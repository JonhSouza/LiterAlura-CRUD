package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByIdiomas(String idiomas); // Corrigido o nome da propriedade para 'idiomas'

    List<Livro> findAllByOrderByTituloAsc();
}*/

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l WHERE l.idioma >= :idioma")
    List<Livro> findForIdioma(String idioma);

}
