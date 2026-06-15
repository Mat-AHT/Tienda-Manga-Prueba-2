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

import TiendaManga.DTO.AutorDTO;
import TiendaManga.Model.Autor;
import TiendaManga.Service.AutorService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<List<AutorDTO>> listarAutores(){
        List<AutorDTO> autores = autorService.listarAutores();
        if(autores.isEmpty()){
            log.warn("HTTP NO_CONTENT:No se encontraron autores en la base de datos.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("HTTP OK: Se han listado los autores.");
        return new ResponseEntity<>(autores, HttpStatus.OK);
    }

    @GetMapping("/{id_autor}")
    public ResponseEntity<AutorDTO> buscarAutor(@Valid @PathVariable Integer id_autor){
        try{
            AutorDTO autor = autorService.buscarAutor(id_autor);
            log.info("HTTP OK: Se ha encontrado el autor con la id " + id_autor);
            return new ResponseEntity<>(autor, HttpStatus.OK);
        }catch (RuntimeException e){
            log.error("HTTP NOT_FOUND: No se ha encontrado el autor con la id " + id_autor);
            return ResponseEntity.notFound().build();
        }
        
    }

    @PostMapping
    public ResponseEntity<Autor> guardarAutor(@Valid @RequestBody Autor autor1){
        Autor autor = autorService.guardarAutor(autor1);
        if(autor != null){
            log.info("HTTP CREATED: Se ha creado el autor con la id " + autor.getId_autor());
            return new ResponseEntity<>(autor, HttpStatus.CREATED);
        }
        else{
            log.error("HTTP BAD_REQUEST: No se ha podido crear el autor.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Autor> editarAutor(@Valid @PathVariable Integer id_autor, @RequestBody Autor autor){
        Autor autorEditado = autorService.editarAutor(id_autor, autor);
        if(autorEditado != null){
            log.info("HTTP OK: Se ha editado el autor con la id " + id_autor);
            return new ResponseEntity<>(autorEditado, HttpStatus.OK);
        }
        else{
            log.error("HTTP NOT_FOUND: No se ha encontrado el autor con la id " + id_autor);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_autor}")
    public ResponseEntity<String> eliminarAutor(@Valid @PathVariable Integer id_autor){
        String resultado = autorService.eliminarAutor(id_autor);
        if(resultado.equals("El autor ha sido eliminado.")){
            log.info("HTTP OK: Se ha eliminado el autor con la id " + id_autor);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
        else{
            log.error("HTTP NOT_FOUND: No se ha encontrado el autor con la id " + id_autor);
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

}
