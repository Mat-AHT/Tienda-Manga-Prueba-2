package TiendaManga.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/resenas")
public class ResenaController {
    @Autowired
    private ResenaService resenaServices;

    @GetMapping 
    public List<Resena> listar(){
        return resenaServices.obtenerTodo();
    }
    @PostMapping
    public Resena publicar(@ResquestBody Resena resena){
        return resenaServices.agregar(resena);
    }
    @PutMapping("/{id}")
    public Resena actualizar(@PathVariable Integer id, @RequestBody Resena resena){
        return resenaServices.actualizar(id, resena);
    }
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id){
        return resenaServices.eliminar(id);
    }
}
