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

import TiendaManga.DTO.GeneroDTO;
import TiendaManga.Model.Genero;
import TiendaManga.Service.GeneroService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> listarGeneros(){
        List<GeneroDTO> generos = generoService.listarGeneros();
        if(generos.isEmpty()){
            log.warn("HTTP NO_CONTENT: No se encontraron generos en la base de datos.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("HTTP OK: Se han listado los generos.");
        return new ResponseEntity<>(generos, HttpStatus.OK);
    }

    @GetMapping("{id_genero}")
    public ResponseEntity<GeneroDTO> buscarGenero(@Valid @PathVariable Integer id_genero){
        try{
            GeneroDTO genero = generoService.buscarGenero(id_genero);
            log.info("HTTP OK: Se ha encontrado el genero con la id " + id_genero);
            return new ResponseEntity<>(genero, HttpStatus.OK);
        }catch (RuntimeException e){
            log.error("HTTP NOT_FOUND: No se ha encontrado el genero con la id " + id_genero);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Genero> guardarGenero(@Valid @RequestBody Genero genero1){
        Genero genero = generoService.guardarGenero(genero1);
        if(genero != null){
            log.info("HTTP CREATED: Se ha creado el genero con la id " + genero.getId_genero());
            return new ResponseEntity<>(genero, HttpStatus.CREATED);
        }
        else{
            log.error("HTTP BAD_REQUEST: No se ha podido crear el genero.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Genero> editarGenero(@Valid @PathVariable Integer id_genero, @RequestBody Genero genero){
        Genero generoEditado = generoService.editarGenero(id_genero, genero);
        if(generoEditado != null){
            log.info("HTTP OK: Se ha editado el genero con la id " + id_genero);
            return new ResponseEntity<>(generoEditado, HttpStatus.OK);
        }
        else{
            log.error("HTTP NOT_FOUND: No se ha encontrado el genero con la id " + id_genero);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_genero}")
    public ResponseEntity<String> eliminarGenero(@Valid @PathVariable Integer id_genero){
        String resultado = generoService.eliminarGenero(id_genero);
        if(resultado.equals("El genero ha sido eliminado")){
            log.info("HTTP OK: Se ha eliminado el genero con la id " + id_genero);
            return new ResponseEntity<>(resultado,HttpStatus.OK);
        }
        else{
            log.error("HTTP NOT_FOUND: No se ha encontrado el genero con la id " + id_genero);
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }


}





