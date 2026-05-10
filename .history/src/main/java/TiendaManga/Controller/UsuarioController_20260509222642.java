package TiendaManga.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TiendaManga.Model.Usuario;
import TiendaManga.Service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioServices;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario(){
        List<Usuario> usuarios = usuarioServices.obtenerTodos();
        if(usuarios.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    
    @GetMapping("/{id_usuario}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Integer id_usuario){
        Usuario usuario = usuarioServices.buscaPorId(id_usuario);
        if(usuario != null){
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuarioNuevo){
        Usuario usuario = usuarioServices.guardar(usuarioNuevo);
        if(usuario != null){
            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id_usuario}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Integer id_usuario) {
        String resultado = usuarioServices.eliminar(id_usuario);
        if (resultado.equals("El usuario ha sido eliminado.")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
        return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
    }
}
