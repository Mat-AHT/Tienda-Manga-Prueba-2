package TiendaManga.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TiendaManga.Model.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {
    
}