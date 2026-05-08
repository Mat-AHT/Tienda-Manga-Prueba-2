package TiendaManga.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TiendaManga.Model.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository <Carrito, Integer>{
    
}