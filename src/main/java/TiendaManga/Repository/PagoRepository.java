package TiendaManga.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TiendaManga.Model.Pago;

@Repository
public interface PagoRepository extends JpaRepository <Pago, Integer>{

}