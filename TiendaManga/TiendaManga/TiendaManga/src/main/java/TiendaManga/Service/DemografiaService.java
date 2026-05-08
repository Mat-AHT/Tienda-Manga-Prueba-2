package TiendaManga.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TiendaManga.Model.Demografia;
import jakarta.transaction.Transactional;
import TiendaManga.Repository.DemografiaRepository;

@Service
@Transactional
public class DemografiaService {

    @Autowired
    private DemografiaRepository demografiaRepository;

    public List<Demografia> listarDemografias(){
        return demografiaRepository.findAll();
    }

    public Demografia guardarDemografia(Demografia demografia){
        return demografiaRepository.save(demografia);
    }

    public Demografia buscarDemografia(Integer id_demografia){
        return demografiaRepository.findById(id_demografia).orElseThrow(() -> new RuntimeException("No se ha encontrado la demografia con la ID " + id_demografia));
    }

    public Demografia editarDemografia(Integer id_demografia, Demografia demografia1){
        Demografia demografia = demografiaRepository.findById(id_demografia).orElseThrow(() -> new RuntimeException("No se ha encontrado la demografia con la ID " + id_demografia));
        if(demografia.getNombreDemografia() != null){
            demografia.setNombreDemografia(demografia1.getNombreDemografia());
        }
        return demografiaRepository.save(demografia);
    }

    public String eliminarDemografia(Integer id_demografia){
        try{
            Demografia demografia = demografiaRepository.findById(id_demografia).orElseThrow(() -> new RuntimeException("No se ha encontrado la demografia con la ID" + id_demografia));
            demografiaRepository.delete(demografia);
            return "La demografia ha sido eliminada.";
        }catch(RuntimeException e){
            return e.getMessage();
        }
    }
}
