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

@RestController
@RequestMapping("/api/v1/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<List<AutorDTO>> listarAutores(){
        List<AutorDTO> autores = autorService.listarAutores();
        if(autores.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(autores, HttpStatus.OK);
    }

    @GetMapping("{id_autor}")
    public ResponseEntity<AutorDTO> buscarAutor(@PathVariable Integer id_autor){
        try{
            AutorDTO autor = autorService.buscarAutor(id_autor);
            return new ResponseEntity<>(autor, HttpStatus.OK);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
        
    }

    @PostMapping
    public ResponseEntity<Autor> guardarAutor(@RequestBody Autor autor1){
        Autor autor = autorService.guardarAutor(autor1);
        if(autor != null){
            return new ResponseEntity<>(autor, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Autor> editarAutor(@PathVariable Integer id_autor, @RequestBody Autor autor){
        Autor autorEditado = autorService.editarAutor(id_autor, autor);
        if(autorEditado != null){
            return new ResponseEntity<>(autorEditado, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_autor}")
    public ResponseEntity<String> eliminarAutor(@PathVariable Integer id_autor){
        String resultado = autorService.eliminarAutor(id_autor);
        if(resultado.equals("El autor ha sido eliminado.")){
            return new ResponseEntity<>(resultado, HttpStatus.OK); 
        }
        else{
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

}
