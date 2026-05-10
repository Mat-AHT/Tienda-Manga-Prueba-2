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
//hola ahi esta el cambio
@RestController
@RequestMapping("/api/v1/inventarios")
public class InventarioController {
    
    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<InventarioDTO>> listarInventario(){
        List<InventarioDTO> inventario = inventarioService.listarInventario();
        if(inventario.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id_Inventaro}")
    public ResponseEntity<InventarioDTO> buscarInventario(@PathVariable Integer id_inventario){
        try{
            InventarioDTO inv = inventarioService.buscarInventario(id_inventario);
            return new ResponseEntity<>(inv, HttpStatus.OK);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Inventario> guardarInventario(@RequestBody Inventario inventario1){
        Inventario inventario = inventarioService.guardarInventario(inventario1);
        if(inventario != null){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Inventario> editarGenero(@PathVariable Integer id_inventario, @RequestBody Inventario inventario){
        Inventario inventarioActualizado = inventarioService.actualizarInventario(id_inventario, inventario);
        if(inventarioActualizado != null){
            return new ResponseEntity<>(inventarioActualizado, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{/id_inventario}")
    public ResponseEntity<String> eliminarInventario(@PathVariable Integer id_inventario){
        String resultado = inventarioService.eliminarInventario(id_inventario);
        if(resultado.equals("El inventario ha sido eliminado")){
            return new ResponseEntity<>(resultado,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }


}
