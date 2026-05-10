package TiendaManga.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TiendaManga.Model.Manga;
import TiendaManga.Model.Demografia;
import TiendaManga.Repository.MangaRepository;
import jakarta.transaction.Transactional;
import TiendaManga.Repository.DemografiaRepository;

@Service
@Transactional
public class DemografiaService {

    @Autowired
    private DemografiaRepository demografiaRepository;

    @Autowired
    private MangaRepository mangaRepository;

    public List<Demografia> obtenerTodas(){
        return demografiaRepository.findAll();
    }

    public Demografia buscarPorId(Integer id){
        return demografiaRepository.findById(id).orElseThrow(() -> new RuntimeException("La demografia no existe en los registros"));
    }

    public Demografia guardar(Demografia demo){
        return demografiaRepository.save(demo);
    }

    public String añadirDemografia(Integer demografiaId, Integer mangaId){
        Manga manga = mangaRepository.findById(mangaId)
            .orElseThrow(() -> new RuntimeException("Imposible añadir El manga con ID" + mangaId + "no existe"));
        Demografia demografia = demografiaRepository.findById(demografiaId)
            .orElseThrow(() -> new RuntimeException("Demografia no existe"));
        
        manga.setDemografia(demografia);
        mangaRepository.save(manga);
        return "Manga ID: " + mangaId + "Agregado al manga ID: " + demografiaId;

    }
    // actualizar 



}
