package TiendaManga.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TiendaManga.Model.Genero;
import TiendaManga.Repository.MangaRepository;
import jakarta.transaction.Transactional;
import TiendaManga.Repository.GeneroRepository;

@Service
@Transactional
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private MangaRepository mangaRepository;

    public List<Genero> obtenerTodos(){
        return generoRepository.findAll();
    }

    public Genero buscarGenero(Integer id){
        return generoRepository.findById(id).orElseThrow(() -> new RuntimeException("El genero no existe en los registros"));
    }
    
    public Genero guardarGenero(Genero genero){
        return generoRepository.save(genero);
    }

    public Genero editarGenero(Integer id, Genero genero){
        Genero genero1 = generoRepository.findById(id).orElseThrow(() -> new RuntimeException("No se ha encontrado el genero."));
        if(genero.getNombreGenero() != null){
            genero1.setNombreGenero(genero.getNombreGenero());
        }
        if(genero.getMangas() != null){
            genero1.setMangas(genero.getMangas());
        }
        return generoRepository.save(genero);
    }



}
