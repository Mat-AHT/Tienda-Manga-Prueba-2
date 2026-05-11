package TiendaManga.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TiendaManga.Model.Pago;
import TiendaManga.Repository.PagoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    
    public List<Pago> obtenerTodos(){
        return pagoRepository.findAll();
    }

    public Pago buscarPorId(Integer id){
        return pagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("el registro de pago no existe"));
    }


    public Pago registrarPago(Pago pago){
        pago.setFechaTransaccion(LocalDateTime.now());
        return pagoRepository.save(pago);
    }
    public String eliminar(Integer id){
        try{
            Pago pago = pagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("el pago con el id '" + id +"' no existe"));
            pagoRepository.delete(pago);
            return "el pago ha sido retirado";
        }catch (RuntimeException e){
            return e.getMessage();
        }
    }
    // llame a la query
    public List<Pago> buscarHistorialPorUsuario(Integer usuarioId) {
        return pagoRepository.buscarHistorialPorUsuario(usuarioId);
    }
    



}
