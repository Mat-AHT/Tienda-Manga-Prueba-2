package TiendaManga.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TiendaManga.DTO.DemografiaDTO;
import TiendaManga.Model.Demografia;
import TiendaManga.Service.DemografiaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/demografias")
public class DemografiaController {

    @Autowired
    private DemografiaService demografiaService;

    @GetMapping
    public ResponseEntity<List<DemografiaDTO>> listarDemografias(){
        List<DemografiaDTO> demografias = demografiaService.listarDemografias();
        if(demografias.isEmpty()){
            log.warn("HTTP NO_CONTENT: No se encontraron demografias en la base de datos.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("HTTP OK: Se han listado las demografias.");
        return new ResponseEntity<>(demografias, HttpStatus.OK);
    }

    @GetMapping("/{id_demografia}")
    public ResponseEntity<DemografiaDTO> buscarDemografia(@Valid @PathVariable Integer id_demografia){
        try{
            DemografiaDTO demografia = demografiaService.buscarDemografia(id_demografia);
            log.info("HTTP OK: Se ha encontrado la demografia con la id " + id_demografia);
            return new ResponseEntity<>(demografia, HttpStatus.OK);
        }catch(RuntimeException e){
            log.error("HTTP NOT_FOUND: No se ha encontrado la demografia con la id " + id_demografia);
            return ResponseEntity.notFound().build();
        }
            
    }

    @PostMapping
    public ResponseEntity<Demografia> guardarDemografia(@Valid @RequestBody Demografia demografia1){
        Demografia demografia = demografiaService.guardarDemografia(demografia1);
        if(demografia != null){
            log.info("HTTP CREATED: Se ha creado la demografia con la id " + demografia.getId_demografia());
            return new ResponseEntity<>(demografia, HttpStatus.CREATED);
        }
        else{
            log.error("HTTP BAD_REQUEST: No se ha podido crear la demografia.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id_demografia}")
    public ResponseEntity<Demografia> editarDemografia(@Valid @PathVariable Integer id_demografia, @RequestBody Demografia demografia){
        Demografia demografiaEditada = demografiaService.editarDemografia(id_demografia, demografia);
        if(demografiaEditada != null){
            log.info("HTTP OK: Se ha editado la demografia con la id " + id_demografia);
            return new ResponseEntity<>(demografiaEditada, HttpStatus.OK);
        }
        else{
            log.error("HTTP NOT_FOUND: No se ha encontrado la demografia con la id " + id_demografia);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
