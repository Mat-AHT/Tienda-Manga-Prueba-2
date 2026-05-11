package TiendaManga.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TiendaManga.DTO.DemografiaDTO;
import TiendaManga.Model.Demografia;
import jakarta.transaction.Transactional;
import TiendaManga.Repository.DemografiaRepository;

@Service
@Transactional
public class DemografiaService {

    @Autowired
    private DemografiaRepository demografiaRepository;

    public List<DemografiaDTO> listarDemografias(){
        return demografiaRepository.findAll().stream().map(this::convertirDemografiaDTO).toList();
    }

    public Demografia guardarDemografia(Demografia demografia){
        return demografiaRepository.save(demografia);
    }

    public DemografiaDTO buscarDemografia(Integer id_demografia){
        Demografia demografia = demografiaRepository.findById(id_demografia).orElseThrow(() -> new RuntimeException("No se ha encontrado la demografia con la ID " + id_demografia));
        return convertirDemografiaDTO(demografia);
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

    public DemografiaDTO convertirDemografiaDTO(Demografia demografia){
        DemografiaDTO dto = new DemografiaDTO();
        dto.setId_demografia(demografia.getId_demografia());
        dto.setNombreDemografia(demografia.getNombreDemografia());
        return dto;
    }
}
