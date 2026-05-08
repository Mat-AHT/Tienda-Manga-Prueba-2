package TiendaManga.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TiendaManga.Model.Carrito;
import TiendaManga.Service.CarritoService;

@RestController
@RequestMapping("/api/v1/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public ResponseEntity<List<Carrito>> listarCarritos(){
        List<Carrito> carritos = carritoService.listarCarritos();
        if(carritos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id_carrito}")
    public ResponseEntity<Carrito> buscarCarrito(@PathVariable Integer id_carrito){
        Carrito carrito1 = carritoService.buscarCarrito(id_carrito);
        if(carrito1 != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Carrito> guardarCarrito(@RequestBody Carrito carrito1){
        Carrito carrito = carritoService.guardarCarrito(carrito1);
        if(carrito != null){
            return new ResponseEntity<>(carrito, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Carrito> editarCarrito(@PathVariable Integer id_carrito, @RequestBody Carrito carrito){
        Carrito carritoEditado = carritoService.editarCarrito(id_carrito, carrito);
        if(carritoEditado != null){
            return new ResponseEntity<>(carritoEditado, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_autor}")
    public ResponseEntity<String> eliminarCarrito(@PathVariable Integer id_carrito){
        String resultado = carritoService.eliminarCarrito(id_carrito);
        if(resultado.equals("El carrito ha sido eliminado.")){
            return new ResponseEntity<>(resultado, HttpStatus.OK); 
        }
        else{
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

}
