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

import TiendaManga.DTO.OrigenDTO;
import TiendaManga.Model.Origen;
import TiendaManga.Service.OrigenService;

@RestController
@RequestMapping("/api/v1/origenes")
public class OrigenController {

    @Autowired
    private OrigenService origenService;

    @GetMapping
    public ResponseEntity<List<OrigenDTO>> listarOrigen(){
        List<OrigenDTO> origen = origenService.listarOrigen();
        if(origen.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id_origen}")
    public ResponseEntity<OrigenDTO> buscarOrigen(@PathVariable Integer id_origen){
        try{
            OrigenDTO origen = origenService.buscarOrigen(id_origen);
            return new ResponseEntity<>(origen, HttpStatus.OK);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Origen> guardarGenero(@RequestBody Origen origen1){
        Origen origen = origenService.guardarOrigen(origen1);
        if(origen != null){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Origen> editarOrigen(@PathVariable Integer id_origen, @RequestBody Origen origen){
        Origen origenEditado = origenService.editarOrigen(id_origen, origen);
        if(origenEditado != null){
            return new ResponseEntity<>(origenEditado, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_origen}")
    public ResponseEntity<String> eliminarOrigen(@PathVariable Integer id_origen){
        String resultado = origenService.eliminarOrigen(id_origen);
        if(resultado.equals("El origen ha sido eliminado")){
            return new ResponseEntity<>(resultado,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

}
