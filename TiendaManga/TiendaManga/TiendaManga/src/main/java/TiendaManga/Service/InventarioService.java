package TiendaManga.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TiendaManga.Model.Inventario;
import TiendaManga.Repository.InventarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class InventarioService {

    @Autowired
<<<<<<< HEAD
    private InventarioRepository inventarioRepository;
=======
    private InventarioRepository invRepository;
>>>>>>> f9108e579cbd8fc4fd85dd45250c74883cf32fd7

    public List<Inventario> obtenerTodos(){
        return invRepository.findAll();
    }

    public Inventario buscarInventario(Integer id){
<<<<<<< HEAD
        return inventarioRepository.findById(id).orElseThrow(() -> new RuntimeException("El inventario con el ID " + id + "no existe"));
=======
        return invRepository.findById(id).orElseThrow(() -> new RuntimeException("El manga no existe en el inventario"));
>>>>>>> f9108e579cbd8fc4fd85dd45250c74883cf32fd7
    }

    public Inventario guardarInventario(Inventario inventario){
        return invRepository.save(inventario);
    }

    public String eliminarInventario(Integer id){
        try{
            Inventario inventario = invRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("El inventario con el ID"+ id +"no existe"));
            invRepository.delete(inventario);
            return "El inventario ha sido retirado";
        }catch (RuntimeException e){
            return e.getMessage();
        }

    }

    public Inventario actualizarInventario(Integer id_inventario, Inventario invActualizado){
            Inventario inventario = invRepository.findById(id_inventario).orElseThrow(() -> new RuntimeException("No se ha encontrado el invemtario con la ID " + id_inventario));
            if(inventario.getStock() != null){
                inventario.setStock(invActualizado.getStock());
            }
            if(inventario.getBodega() != null){
                inventario.setBodega(invActualizado.getBodega());
            }
            return invRepository.save(inventario);
        }

    
}
