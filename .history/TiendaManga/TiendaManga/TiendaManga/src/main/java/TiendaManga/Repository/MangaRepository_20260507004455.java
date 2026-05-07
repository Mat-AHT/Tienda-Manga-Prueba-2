package TiendaManga.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import TiendaManga.Model.Manga;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Integer>{
    // lo dejaremos vacio porque el jpa hace todo 
    //hacer query personalizada necesario 3 
    // Busca todos los mangas que pertenecen a un género específico
    @Query("SELECT m FROM Manga m WHERE m.genero.id = :generoId")
    List<Manga> buscarPorGenero(@Param("generoId") Integer generoId);
}
