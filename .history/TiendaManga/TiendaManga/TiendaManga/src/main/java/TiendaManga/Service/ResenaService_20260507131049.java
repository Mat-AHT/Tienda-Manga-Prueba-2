package TiendaManga.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TiendaManga.Model.Resena;
import TiendaManga.Repository.ResenaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    public List<Resena> obtenerTodo(){
        return resenaRepository.findAll();
    }
    public Resena buscarPorId(Integer id){
        return resenaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("la reseña no existe"));
    }
    public Resena agregar(Resena resena){
        return resenaRepository.save(resena);
    }
    public Resena actualizar(Integer id, Resena resenaActualizada){
        Resena resenaExistente = buscarPorId(id);
        if(resenaActualizada.getComentario() != null){
            resenaExistente.setComentario(resenaActualizada.getComentario());
        }
        if(resenaActualizada.getCalificacion() != null){
            resenaExistente.setCalificacion(resenaActualizada.getCalificacion());
        }
        return resenaRepository.save(resenaExistente);
    }
    public String eliminar(Integer id){
        Resena resena = buscarPorId(id);
        resenaRepository.delete(resena);
        return "la reseña ha sido eliminada";
    }


}
