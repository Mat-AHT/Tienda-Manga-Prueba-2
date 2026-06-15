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

import TiendaManga.DTO.InventarioDTO;
import TiendaManga.Model.Inventario;
import TiendaManga.Service.InventarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/inventarios")
public class InventarioController {
    
    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<InventarioDTO>> listarInventario(){
        List<InventarioDTO> inventario = inventarioService.listarInventario();
        if(inventario.isEmpty()){
            log.warn("HTTP NO_CONTENT: No se encontraron inventarios en la base de datos.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("HTTP OK: Se han listado los inventarios.");
        return new ResponseEntity<>(inventario, HttpStatus.OK);
    }

    @GetMapping("/{id_inventario}")
    public ResponseEntity<InventarioDTO> buscarInventario(@Valid @PathVariable Integer id_inventario){
        try{
            InventarioDTO inv = inventarioService.buscarInventario(id_inventario);
            log.info("HTTP OK: Se ha encontrado el inventario con la id " + id_inventario);
            return new ResponseEntity<>(inv, HttpStatus.OK);
        }catch (RuntimeException e){
            log.error("HTTP NOT_FOUND: No se ha encontrado el inventario con la id " + id_inventario);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Inventario> guardarInventario(@Valid @RequestBody Inventario inventario1){
        Inventario inventario = inventarioService.guardarInventario(inventario1);
        if(inventario != null){
            log.info("HTTP CREATED: Se ha creado el inventario con la id " + inventario.getId_inventario());
            return new ResponseEntity<>(inventario, HttpStatus.CREATED);
        }
        else{
            log.error("HTTP BAD_REQUEST: No se ha podido crear el inventario.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id_inventario}")
    public ResponseEntity<Inventario> editarGenero(@Valid @PathVariable Integer id_inventario, @RequestBody Inventario inventario){
        Inventario inventarioActualizado = inventarioService.actualizarInventario(id_inventario, inventario);
        if(inventarioActualizado != null){
            log.info("HTTP OK: Se ha actualizado el inventario con la id " + id_inventario);
            return new ResponseEntity<>(inventarioActualizado, HttpStatus.OK);
        }
        else{
            log.error("HTTP NOT_FOUND: No se ha encontrado el inventario con la id " + id_inventario);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_inventario}")
    public ResponseEntity<String> eliminarInventario(@Valid @PathVariable Integer id_inventario){
        String resultado = inventarioService.eliminarInventario(id_inventario);
        if(resultado.equals("El inventario ha sido eliminado")){
            log.info("HTTP OK: Se ha eliminado el inventario con la id " + id_inventario);
            return new ResponseEntity<>(resultado,HttpStatus.OK);
        }
        else{
            log.error("HTTP NOT_FOUND: No se ha encontrado el inventario con la id " + id_inventario);
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }


}
