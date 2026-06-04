package TiendaManga.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TiendaManga.DTO.PagoDTO;
import TiendaManga.Model.Pago;
import TiendaManga.Service.PagoService;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {
    @Autowired
    private PagoService pagoService;
    
    
    
    @GetMapping
    public ResponseEntity<List<PagoDTO>> listarPagos() {
        List<PagoDTO> pagos = pagoService.obtenerTodos();
        if (pagos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }
    @GetMapping("/{id_pago}")
    public ResponseEntity<PagoDTO> buscarPago(@PathVariable Integer id_pago) {
        try{
            PagoDTO pago = pagoService.buscarPorId(id_pago);
            return new ResponseEntity<>(pago, HttpStatus.OK);
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Pago> procesarPago(@RequestBody Pago pagoNuevo) {
        Pago pago = pagoService.registrarPago(pagoNuevo);
        if (pago != null) {
            return new ResponseEntity<>(pago, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PagoDTO>> buscarHistorialPorUsuario(@PathVariable Integer usuarioId) {
        List<PagoDTO> pagos = pagoService.buscarHistorialPorUsuario(usuarioId);
        if (pagos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }
    
}
