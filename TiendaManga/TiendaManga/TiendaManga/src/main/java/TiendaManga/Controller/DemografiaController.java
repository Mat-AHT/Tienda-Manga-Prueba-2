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

import TiendaManga.Model.Demografia;
import TiendaManga.Service.DemografiaService;

@RestController
@RequestMapping("/api/v1/demografias")
public class DemografiaController {

    @Autowired
    private DemografiaService demografiaService;

    @GetMapping
    public ResponseEntity<List<Demografia>> listarDemografias(){
        List<Demografia> demografias = demografiaService.listarDemografias();
        if(demografias.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id_demografia}")
    public ResponseEntity<Demografia> buscarDemografia(@PathVariable Integer id_demografia){
        Demografia demografia1 = demografiaService.buscarDemografia(id_demografia);
        if(demografia1 != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Demografia> guardarDemografia(@RequestBody Demografia demografia1){
        Demografia demografia = demografiaService.guardarDemografia(demografia1);
        if(demografia != null){
            return new ResponseEntity<>(demografia, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Demografia> editarDemografia(@PathVariable Integer id_demografia, @RequestBody Demografia demografia){
        Demografia demografiaEditada = demografiaService.editarDemografia(id_demografia, demografia);
        if(demografiaEditada != null){
            return new ResponseEntity<>(demografiaEditada, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_demografia}")
    public ResponseEntity<String> eliminarDemografia(@PathVariable Integer id_demografia){
        String resultado = demografiaService.eliminarDemografia(id_demografia);
        if(resultado.equals("La demografia ha sido eliminada.")){
            return new ResponseEntity<>(resultado, HttpStatus.OK); 
        }
        else{
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

}
