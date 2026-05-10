package TiendaManga.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TiendaManga.Model.Origen;
import TiendaManga.Repository.OrigenRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class OrigenService {

    @Autowired
    private OrigenRepository origenRepository;

    public List<Origen> listarOrigen(){
        return origenRepository.findAll();
    }

    public Origen buscarOrigen(Integer id_origen){
        return origenRepository.findById(id_origen).orElseThrow(() -> new RuntimeException("El origen no existe en los registros"));
    }

    public Origen guardarOrigen(Origen origen){
        return origenRepository.save(origen);
    }

    public Origen editarOrigen(Integer id_origen, Origen origen1){
        Origen origen = origenRepository.findById(id_origen).orElseThrow(() -> new RuntimeException("El origen no esta acorde "));
        if(origen1.getPais() != null){
            origen.setPais(origen1.getPais());
        }
        return origenRepository.save(origen);
    }

    public String eliminarOrigen(Integer id_origen){
        try{
            Origen origen = origenRepository.findById(id_origen).orElseThrow(() -> new RuntimeException("No se ha encontrado el origen con el ID " + id_origen));
            origenRepository.delete(origen);
            return "El origen ha sido eliminado";
        }catch(RuntimeException e){
            return e.getMessage();
        }
    }



}
