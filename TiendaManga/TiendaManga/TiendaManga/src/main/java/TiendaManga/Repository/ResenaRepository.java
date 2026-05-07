package TiendaManga.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TiendaManga.Model.Resena;

@Repository
public interface ResenaRepository extends JpaRepository <Resena, Integer>{

}