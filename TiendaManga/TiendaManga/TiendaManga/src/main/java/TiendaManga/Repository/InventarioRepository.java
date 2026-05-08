package TiendaManga.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TiendaManga.Model.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository <Inventario, Integer>{
    
}