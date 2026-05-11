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

@RestController
@RequestMapping("/api/v1/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> listarGeneros(){
        List<GeneroDTO> generos = generoService.listarGeneros();
        if(generos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(generos, HttpStatus.OK);
    }

    @GetMapping("{id_genero}")
    public ResponseEntity<GeneroDTO> buscarGenero(@PathVariable Integer id_genero){
        try{
            GeneroDTO genero = generoService.buscarGenero(id_genero);
            return new ResponseEntity<>(genero, HttpStatus.OK);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Genero> guardarGenero(@RequestBody Genero genero1){
        Genero genero = generoService.guardarGenero(genero1);
        if(genero != null){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Genero> editarGenero(@PathVariable Integer id_genero, @RequestBody Genero genero){
        Genero generoEditado = generoService.editarGenero(id_genero, genero);
        if(generoEditado != null){
            return new ResponseEntity<>(generoEditado, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_genero}")
    public ResponseEntity<String> eliminarGenero(@PathVariable Integer id_genero){
        String resultado = generoService.eliminarGenero(id_genero);
        if(resultado.equals("El genero ha sido eliminado")){
            return new ResponseEntity<>(resultado,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }


}





