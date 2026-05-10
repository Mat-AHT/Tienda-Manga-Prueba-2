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

import TiendaManga.Model.Resena;
import TiendaManga.Service.ResenaService;

@RestController
@RequestMapping("/api/v1/resenas")
public class ResenaController {
    @Autowired
    private ResenaService resenaServices;

    @GetMapping
    public ResponseEntity<List<Resena>> listarResenas() {
        List<Resena> resenas = resenaServices.obtenerTodo();
        if (resenas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(resenas, HttpStatus.OK);
    }
    @GetMapping("/{id_resena}")
    public ResponseEntity<Resena> buscarResena(@PathVariable Integer id_resena) {
        Resena resena = resenaServices.buscarPorId(id_resena);
        if (resena != null) {
            return new ResponseEntity<>(resena, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Resena> guardarResena(@RequestBody Resena resenaNueva) {
        Resena resena = resenaServices.agregar(resenaNueva);
        if (resena != null) {
            return new ResponseEntity<>(resena, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/{id_resena}")
    public ResponseEntity<Resena> editarResena(@PathVariable Integer id_resena, @RequestBody Resena resena) {
        Resena resenaEditada = resenaServices.actualizar(id_resena, resena);
        if (resenaEditada != null) {
            return new ResponseEntity<>(resenaEditada, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id_resena}")
    public ResponseEntity<String> eliminarResena(@PathVariable Integer id_resena) {
        String resultado = resenaServices.eliminar(id_resena);
        if (resultado.contains("retirada")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
        return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
    }
}
