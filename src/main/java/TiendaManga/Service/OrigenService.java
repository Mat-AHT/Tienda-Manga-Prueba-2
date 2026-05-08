package TiendaManga.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TiendaManga.Model.Manga;
import TiendaManga.Model.Origen;
import TiendaManga.Repository.MangaRepository;
import TiendaManga.Repository.OrigenRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class OrigenService {

    @Autowired
    private OrigenRepository origenRepository;

    @Autowired
    private MangaRepository mangaRepository;

    public List<Origen> obtenerTodos(){
        return origenRepository.findAll();
    }

    public Origen buscarPorId(Integer id){
        return origenRepository.findById(id).orElseThrow(() -> new RuntimeException("El origen no existe en los registros"));
    }

    public Origen guardarOrigen(Origen origen){
        return origenRepository.save(origen);
    }

    public Origen actualizarOrigen(Integer origenId, Integer mangaId){
        Origen origenExistente = origenRepository.findById(origenId)
        .orElseThrow(() -> new RuntimeException("El origen no esta acorde "));
        if()
    }



}
