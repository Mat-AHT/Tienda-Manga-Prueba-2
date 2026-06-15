package TiendaManga.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TiendaManga.DTO.UsuarioDTO;
import TiendaManga.Model.Usuario;
import TiendaManga.Service.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioServices;
    

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuario(){
        List<UsuarioDTO> usuarios = usuarioServices.obtenerTodos();
        if(usuarios.isEmpty()){
            log.warn("HTTP NO_CONTENT: No se encontraron usuarios en la base de datos.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("HTTP OK: Se han listado los usuarios.");
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    
    @GetMapping("/{id_usuario}")
    public ResponseEntity<UsuarioDTO> buscarUsuario(@Valid @PathVariable Integer id_usuario){
        try{
            UsuarioDTO usuario = usuarioServices.buscaPorId(id_usuario);
            log.info("HTTP OK: Se ha encontrado el usuario con la id " + id_usuario);
            return new ResponseEntity<>(usuario,HttpStatus.OK);
        }catch(RuntimeException e){
            log.error("HTTP NOT_FOUND: No se ha encontrado el usuario con la id " + id_usuario);
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@Valid @RequestBody Usuario usuarioNuevo){
        Usuario usuario = usuarioServices.guardar(usuarioNuevo);
        if(usuario != null){
            log.info("HTTP CREATED: Se ha creado el usuario con la id " + usuario.getId_usuario());
            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
        }
        log.error("HTTP BAD_REQUEST: No se ha podido crear el usuario.");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id_usuario}")
    public ResponseEntity<Usuario> editarUsuario(@Valid @PathVariable Integer id_usuario, @RequestBody Usuario usuario) {
        Usuario usuarioEditado = usuarioServices.actualizar(id_usuario, usuario);
        if (usuarioEditado != null) {
            log.info("HTTP OK: Se ha actualizado el usuario con la id " + id_usuario);
            return new ResponseEntity<>(usuarioEditado, HttpStatus.OK);
        }
        log.error("HTTP NOT_FOUND: No se ha encontrado el usuario con la id " + id_usuario);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id_usuario}")
    public ResponseEntity<String> eliminarUsuario(@Valid @PathVariable Integer id_usuario) {
        String resultado = usuarioServices.eliminar(id_usuario);
        if (resultado.equals("El usuario ha sido eliminado.")) {
            log.info("HTTP OK: Se ha eliminado el usuario con la id " + id_usuario);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
        log.error("HTTP NOT_FOUND: No se ha encontrado el usuario con la id " + id_usuario);
        return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
    }
}
