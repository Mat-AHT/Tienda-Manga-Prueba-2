package TiendaManga.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import TiendaManga.Model.Inventario;
import TiendaManga.Model.Usuario;

@Repository
public interface InventarioRepository extends JpaRepository <Usuario, Integer>{
    // Busca productos con menos unidades de las indicadas
    @Query("SELECT i FROM Inventario i WHERE i.stock < :limite")
    List<Inventario> buscarStockBajo(@Param("limite") Integer limite);
}