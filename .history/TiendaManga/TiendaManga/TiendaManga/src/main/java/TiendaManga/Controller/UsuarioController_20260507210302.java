package TiendaManga.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TiendaManga.Model.Usuario;
import TiendaManga.Service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioServices;

    @GetMapping
    public List<Usuario> listar(){
        return usuarioServices.obtenerTodos();
    }
    @PostMapping 
    public Usuario registrar(@ResquestBody Usuario usuario){
        return usuarioServices.guardar(usuario);
    }
    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Integer id, @RequestBody Usuario usuario){
        return usuarioServices.actualizar(id, usuario);
    }
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id){
        return usuarioServices.eliminar(id);
    }
}
