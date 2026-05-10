package TiendaManga.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TiendaManga.DTO.ResenaDTO;
import TiendaManga.Model.Resena;
import TiendaManga.Repository.ResenaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    public List<ResenaDTO> obtenerTodo(){
        return resenaRepository.findAll().stream().map(this::convertirResenaDTO).toList();
    }
    public ResenaDTO buscarPorId(Integer id){
        Resena resena = resenaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("la reseña no existe"));
            return convertirResenaDTO(resena);
    }
    public Resena agregar(Resena resena){
        return resenaRepository.save(resena);
    }
    public Resena actualizar(Integer id_resena, Resena reseñaActualizada){
        Resena reseñaExistente = resenaRepository.findById(id_resena)
        .orElseThrow(() -> new RuntimeException("la reseña no existe en los registros"));
        
        if(reseñaActualizada.getComentario() != null){
            reseñaExistente.setComentario(reseñaActualizada.getComentario());
        }
        if(reseñaActualizada.getCalificacion() != null){
            reseñaExistente.setCalificacion(reseñaActualizada.getCalificacion());
        }
        
        return resenaRepository.save(reseñaExistente);
    }
    public String eliminar(Integer id){
        try{
            Resena reseña = resenaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("la reseña con el id '" + id +"' no existe"));
            resenaRepository.delete(reseña);
            return "la reseña ha sido retirada";
        }catch (RuntimeException e){
            return e.getMessage();
        }
    }
    private ResenaDTO convertirResenaDTO(Resena resena){
        ResenaDTO dto = new ResenaDTO();
        dto.setId_resena(resena.getId_resena());
        dto.setCalificacion(resena.getCalificacion());
        dto.setComentario(resena.getComentario());
        return dto;
    }
    

}
