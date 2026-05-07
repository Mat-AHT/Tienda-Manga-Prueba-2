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
    public Usuario actualizar(Integer id, Usuario usuarioActualizado){
        Usuario usuarioExistente = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("usuario no encontrado");
        if(usuarioActualizado.getNombre() != null) usuarioExistente.setNombre(usuarioActualizado.getNombre());
        if(usuarioActualizado.getCorreo() != null) usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
        if(usuarioActualizado.getContraseña() != null)usuarioExistente.setContraseña(usuarioActualizado.getContraseña());
        
        return usuarioRepository.save(usuarioExistente);
    }
    public String eliminar(Integer id){
        Usuario usuario = buscaPorId(id);
        usuarioRepository.delete(usuario);
        return "el usuario " + usuario.getNombre() + " ha sido eliminado";
    }

    
}
