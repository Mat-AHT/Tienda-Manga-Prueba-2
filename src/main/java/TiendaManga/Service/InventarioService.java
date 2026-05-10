package TiendaManga.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TiendaManga.Model.Inventario;
import TiendaManga.Repository.InventarioRepository;
import TiendaManga.Repository.MangaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private MangaRepository mangaRepository;

    public List<Inventario> obtenerTodos(){
        return inventarioRepository.findAll();
    }

    public Inventario buscarInventario(Integer id){
        return 
    }

    
}
