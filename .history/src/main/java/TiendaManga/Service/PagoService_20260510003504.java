package TiendaManga.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TiendaManga.DTO.PagoDTO;
import TiendaManga.Model.Pago;
import TiendaManga.Repository.PagoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    
    public List<PagoDTO> obtenerTodos(){
        return pagoRepository.findAll().stream().map(this::convertirPagoDTO).toList();
    }

    public PagoDTO buscarPorId(Integer id){
        Pago pago = pagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("el registro de pago no existe"));
            return convertirPagoDTO(pago);
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
    public List<PagoDTO> buscarHistorialPorUsuario(Integer usuarioId) {
        return pagoRepository.buscarHistorialPorUsuario(usuarioId).stream().map(this::convertirPagoDTO).toList();
    }
   
    private PagoDTO convertirPagoDTO(Pago pago){
        PagoDTO dto = new PagoDTO();
        dto.setId_pago(pago.getId());
        dto.setFechaTransaccion(pago.getFechaTransaccion());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setMonto(pago.getMonto());
        return dto;
    }


}
