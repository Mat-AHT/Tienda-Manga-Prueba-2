package TiendaManga.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import TiendaManga.Model.Pago;

@Repository
public interface PagoRepository extends JpaRepository <Pago, Integer>{
    // Obtiene los pagos de un usuario específico ordenados por fecha
    @Query("SELECT p FROM Pago p WHERE p.usuario.id = :usuarioId ORDER BY p.fechaTransaccion DESC")
    List<Pago> buscarHistorialPorUsuario(@Param("usuarioId") Integer usuarioId);
}

