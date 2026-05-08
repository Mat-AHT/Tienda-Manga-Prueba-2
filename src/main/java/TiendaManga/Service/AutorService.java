package TiendaManga.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import TiendaManga.DTO.AutorDTO;
import TiendaManga.Model.Autor;
import TiendaManga.Repository.AutorRepository;
import java.util.List;

@Service
@Transactional
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public List<AutorDTO> listarAutores(){
        return autorRepository.findAll().stream().map(this::convertirAutorDTO).toList();
    }

    public Autor guardarAutor(Autor autor){
        return autorRepository.save(autor);
    }

    public AutorDTO buscarAutor(Integer id_autor){
        Autor autor = autorRepository.findById(id_autor).orElseThrow(() -> new RuntimeException("No se ha encontrado el autor con la ID " + id_autor));
        return convertirAutorDTO(autor);
    }
    
    public Autor editarAutor(Integer id_autor, Autor autor1){
        Autor autor = autorRepository.findById(id_autor).orElseThrow(() -> new RuntimeException("No se ha encontrado el autor con la ID " + id_autor));
        if(autor1.getNombreAutor() != null){
            autor.setNombreAutor(autor1.getNombreAutor());
        }
        if(autor1.getNacionalidad() != null){
            autor.setNacionalidad(autor1.getNacionalidad());
        }
        return autorRepository.save(autor);
    }

    public String eliminarAutor(Integer id_autor){
        try{
            Autor autor = autorRepository.findById(id_autor).orElseThrow(() -> new RuntimeException("el autor con el id '" + id_autor +"' no existe"));
            autorRepository.delete(autor);
            return "El autor ha sido eliminado.";
        }catch (RuntimeException e){
            return e.getMessage();
        }
    }

    private AutorDTO convertirAutorDTO(Autor autor){
        AutorDTO dto = new AutorDTO();
        dto.setId_autor(autor.getId_autor());
        dto.setNombreAutor(autor.getNombreAutor());
        dto.setNacionalidad(autor.getNacionalidad());
        return dto;

    }
}
