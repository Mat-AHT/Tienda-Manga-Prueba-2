package TiendaManga.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TiendaManga.Model.Pago;
import TiendaManga.Repository.PagoRepository;
import TiendaManga.Repository.MangaRepository;
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
        pago.setfechaTransaccion(LocalDateTime.now());
        return pagoRepository.save(pago);
    }
    public String eliminar(Integer id){
        Pago pago = buscarPorId(id);
        pagoRepository.delete(pago);
        return "el pago con el id "+ id + " ha sido eliminado";
    }
    



}
