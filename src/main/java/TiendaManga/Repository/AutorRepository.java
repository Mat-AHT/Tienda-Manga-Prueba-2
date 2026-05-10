package TiendaManga.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TiendaManga.Model.Autor;

@Repository
public interface AutorRepository extends JpaRepository <Autor, Integer>{

}