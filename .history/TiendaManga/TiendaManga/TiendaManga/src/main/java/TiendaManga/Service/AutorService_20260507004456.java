package TiendaManga.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import TiendaManga.Model.Autor;
import TiendaManga.Repository.AutorRepository;
import java.util.List;

@Service
@Transactional
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> obtenerTodos(){
        return autorRepository.findAll();
    }
    public Autor buscarPorId(Integer id){
        return autorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("El autor no existe en los registros"));
    }
    public Autor guardar(Autor autor){
        return autorRepository.save(autor);
    }
    public String eliminar(Integer id){
        try{
            Autor autor = autorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("el autor con el id '" + id +"' no existe"));
            autorRepository.delete(autor);
            return "el autor ha sido retirado";
        }catch (RuntimeException e){
            return e.getMessage();
        }
    }
    public Autor actualizar(Integer id_autor, Autor autorActualizado){
        Autor autorExistente = autorRepository.findById(id_autor)
        .orElseThrow(() -> new RuntimeException("el autor no existe en los registros"));
        if(autorActualizado.getNombreAutor() != null){
            autorExistente.setNombreAutor(autorActualizado.getNombreAutor());
        }
        if(autorActualizado.getNacionalidad() != null){
            autorExistente.setNacionalidad(autorActualizado.getNacionalidad());
        }
        return autorRepository.save(autorExistente);

    }
}
