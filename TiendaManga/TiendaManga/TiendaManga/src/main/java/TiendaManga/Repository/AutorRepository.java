package TiendaManga.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TiendaManga.Model.Autor;

@Repository
// This line of code is defining an interface named `AutorRepository` that extends the `JpaRepository`
// interface.
public interface AutorRepository extends JpaRepository <Autor, Integer>{

}