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
        return usuarioRepository.findAllById(id)
        .orElseThrow(() -> new RuntimeException("El usuario no existe en los registros"));
    }
    public Usuario

    
}
