package TiendaManga.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TiendaManga.DTO.GeneroDTO;
import TiendaManga.Model.Genero;
import jakarta.transaction.Transactional;
import TiendaManga.Repository.GeneroRepository;

@Service
@Transactional
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public List<GeneroDTO> listarGeneros(){
        return generoRepository.findAll().stream().map(this::convertirGeneroDTO).toList();
    }

    public GeneroDTO buscarGenero(Integer id_genero){
        Genero genero = generoRepository.findById(id_genero).orElseThrow(() -> new RuntimeException("Genero no encontrado!"));
        return convertirGeneroDTO(genero);
    }
    
    public Genero guardarGenero(Genero genero){
        return generoRepository.save(genero);
    }

    public Genero editarGenero(Integer id_genero, Genero genero){
        Genero genero1 = generoRepository.findById(id_genero).orElseThrow(() -> new RuntimeException("No se ha encontrado el genero."));
        if(genero.getNombreGenero() != null){
            genero1.setNombreGenero(genero.getNombreGenero());
        }
        if(genero.getMangas() != null){
            genero1.setMangas(genero.getMangas());
        }
        return generoRepository.save(genero1);
    }

    public String eliminarGenero(Integer id_genero){
        try{
            Genero genero = generoRepository.findById(id_genero).orElseThrow(() -> new RuntimeException("No se ha encontrado el Genero con el ID " + id_genero));
            generoRepository.delete(genero);
            return "El genero ha sido eliminado";
        }catch(RuntimeException e){
            return e.getMessage();
        }
    }

    private GeneroDTO convertirGeneroDTO(Genero genero) {
        GeneroDTO gdto = new GeneroDTO();
        gdto.setId_genero(genero.getId_genero());
        gdto.setNombreGenero(genero.getNombreGenero());
        return gdto;
    }



}
