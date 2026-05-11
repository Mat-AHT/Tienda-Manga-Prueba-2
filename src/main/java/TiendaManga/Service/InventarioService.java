package TiendaManga.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TiendaManga.DTO.InventarioDTO;
import TiendaManga.Model.Inventario;
import TiendaManga.Repository.InventarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class InventarioService {

    @Autowired
    private InventarioRepository invRepository;

    public List<InventarioDTO> listarInventario(){
        return invRepository.findAll().stream().map(this::convertirInventarioDTO).toList();
    }

    public InventarioDTO buscarInventario(Integer id_inventario){
        Inventario inventario = invRepository.findById(id_inventario).orElseThrow(() -> new RuntimeException("Genero no encontrado!"));
        return convertirInventarioDTO(inventario);
    }
    

    public Inventario guardarInventario(Inventario inventario){
        return invRepository.save(inventario);
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

    public String eliminarInventario(Integer id_inventario){
        try{
            Inventario inventario = invRepository.findById(id_inventario)
            .orElseThrow(() -> new RuntimeException("El inventario con el ID"+ id_inventario +"no existe"));
            invRepository.delete(inventario);
            return "El inventario ha sido retirado";
        }catch (RuntimeException e){
            return e.getMessage();
        }

    }

    private InventarioDTO convertirInventarioDTO(Inventario inventario) {
        InventarioDTO invdto = new InventarioDTO();
        invdto.setId_inventario(inventario.getId_inventario());
        invdto.setStock(inventario.getStock());
        invdto.setBodega(inventario.getBodega());
        return invdto;
        }

}