package TiendaManga.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TiendaManga.Model.Usuario;
import TiendaManga.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;

import java.util.List;
@Service
@Transactional
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<Usuario> obtenerTodos(){
        return usuarioRepository.findAll();
    }
    public Usuario buscaPorId(Integer id){
        return usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("El usuario no existe en los registros"));
    }
    public Usuario guardar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    public Usuario actualizar(Integer id_usuario, Usuario usuarioActualizado){
        Usuario usuarioExistente = usuarioRepository.findById(id_usuario)
        .orElseThrow(() -> new RuntimeException("el usuario no existe en los registros"));
        
        
        if(usuarioActualizado.getNombre() != null){
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
        }
        if(usuarioActualizado.getCorreo() != null){
            usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
        }
        
        return usuarioRepository.save(usuarioExistente);
    }
    public String eliminar(Integer id){
        try{
            Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("el usuario con el id " + id +"' no existe"));
            usuarioRepository.delete(usuario);
            return "el usuario ha sido retirado";
        }catch (RuntimeException e){
            return e.getMessage();
        }
    }  
    
}
