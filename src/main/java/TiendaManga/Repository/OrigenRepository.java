package TiendaManga.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TiendaManga.Model.Origen;

@Repository
public interface OrigenRepository extends JpaRepository <Origen, Integer>{

}