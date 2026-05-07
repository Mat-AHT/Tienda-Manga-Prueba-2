package TiendaManga.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TiendaManga.Model.Carrito;
import TiendaManga.Repository.CarritoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> listarAutores(){
        return carritoRepository.findAll();
    }

    public Carrito guardarAutor(Carrito carrito){
        return carritoRepository.save(carrito);
    }

    public Carrito buscarAutores(Integer id_carrito){
        return carritoRepository.findById(id_carrito).orElseThrow(() -> new RuntimeException("No se ha encontrado el carrito con la ID " + id_carrito));
    }

    public Carrito editarCarrito(Integer id_carrito, Carrito carrito1){
        Carrito carrito = carritoRepository.findById(id_carrito).orElseThrow(() -> new RuntimeException("No se ha encontrado el carrito con la ID " + id_carrito));
        if(carrito1.getCantidad() !=  null){
            carrito.setCantidad(carrito1.getCantidad());
        }
        return carritoRepository.save(carrito);
    }

    public String eliminarCarrito(Integer id_carrito){
        try{
            Carrito carrito = carritoRepository.findById(id_carrito).orElseThrow(() -> new RuntimeException("No se ha encontrado el carrito con la ID " + id_carrito));
            carritoRepository.delete(carrito);
            return "El carrito ha sido eliminado";
        }catch(RuntimeException e){
            return e.getMessage();
        }
    }
}
