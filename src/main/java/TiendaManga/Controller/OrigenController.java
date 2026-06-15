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
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/origenes")
public class OrigenController {

    @Autowired
    private OrigenService origenService;

    @GetMapping
    public ResponseEntity<List<OrigenDTO>> listarOrigen(){
        List<OrigenDTO> origen = origenService.listarOrigen();
        if(origen.isEmpty()){
            log.warn("HTTP NO_CONTENT: No se encontraron orígenes en la base de datos.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("HTTP OK: Se han listado los orígenes.");
        return new ResponseEntity<>(origen, HttpStatus.OK);
    }

    @GetMapping("/{id_origen}")
    public ResponseEntity<OrigenDTO> buscarOrigen(@Valid @PathVariable Integer id_origen){
        try{
            OrigenDTO origen = origenService.buscarOrigen(id_origen);
            log.info("HTTP OK: Se ha encontrado el origen con la id " + id_origen);
            return new ResponseEntity<>(origen, HttpStatus.OK);
        }catch (RuntimeException e){
            log.error("HTTP NOT_FOUND: No se ha encontrado el origen con la id " + id_origen);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Origen> guardarOrigen(@Valid @RequestBody Origen origen1){
        Origen origen = origenService.guardarOrigen(origen1);
        if(origen != null){
            log.info("HTTP CREATED: Se ha creado el origen con la id " + origen.getId_origen());
            return new ResponseEntity<>(origen, HttpStatus.CREATED);
        }   
        else{
            log.error("HTTP BAD_REQUEST: No se ha podido crear el origen.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id_origen}")
    public ResponseEntity<Origen> editarOrigen(@Valid @PathVariable Integer id_origen, @RequestBody Origen origen){
        Origen origenEditado = origenService.editarOrigen(id_origen, origen);
        if(origenEditado != null){
            log.info("HTTP OK: Se ha editado el origen con la id " + id_origen);
            return new ResponseEntity<>(origenEditado, HttpStatus.OK);
        }
        else{
            log.error("HTTP NOT_FOUND: No se ha encontrado el origen con la id " + id_origen);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_origen}")
    public ResponseEntity<String> eliminarOrigen(@Valid @PathVariable Integer id_origen){
        String resultado = origenService.eliminarOrigen(id_origen);
        if(resultado.equals("El origen ha sido eliminado")){
            log.info("HTTP OK: Se ha eliminado el origen con la id " + id_origen);
            return new ResponseEntity<>(resultado,HttpStatus.OK);
        }
        else{
            log.error("HTTP NOT_FOUND: No se ha encontrado el origen con la id " + id_origen);
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

}
