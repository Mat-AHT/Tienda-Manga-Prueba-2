package TiendaManga.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TiendaManga.Model.Manga;
import TiendaManga.Repository.MangaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MangaService {

    @Autowired
    private MangaRepository mangaRepository;

    public List<Manga> listarMangas(){
        return mangaRepository.findAll();
    }

    public Manga guardarManga(Manga manga){
        return mangaRepository.save(manga);
    }

    public Manga buscarManga(Integer id){
        return mangaRepository.findById(id).orElseThrow(() -> new RuntimeException("No se ha encontrado el manga con la ID " + id));
    }

    public Manga editarManga(Integer id, Manga manga){
        Manga manga1 = mangaRepository.findById(id).orElseThrow(() -> new RuntimeException("No se ha encontrado el manga."));
        if(manga.getNombre() != null){
            manga1.setNombre(manga.getNombre());
        }
        if(manga.getPrecio() != null){
            manga1.setPrecio(manga.getPrecio());
        }
        if(manga.getSinopsis() != null){
            manga1.setSinopsis(manga.getSinopsis());
        }
        return mangaRepository.save(manga1);
    }

    public String eliminarManga(Integer id){
        try{
            Manga manga = mangaRepository.findById(id).orElseThrow(() -> new RuntimeException("No es posible eliminar el manga con ID " + id + "ya que no existe!."));
            mangaRepository.delete(manga);
            return "El manga '" + manga.getNombre() + "' ha sido eliminado!.";
        }catch (RuntimeException e) {
            return e.getMessage();
        }
    }
    public List<Manga> buscarPorGenero(Integer id_genero){
            return mangaRepository.buscarPorGenero(id_genero);
        }





}
