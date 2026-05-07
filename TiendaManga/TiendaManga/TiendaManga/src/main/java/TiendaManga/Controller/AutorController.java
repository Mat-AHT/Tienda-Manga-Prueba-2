package TiendaManga.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TiendaManga.Model.Autor;
import TiendaManga.Service.AutorService;

@RestController
@RequestMapping("/api/v1/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public List<Autor> listarAutores(){
        return autorService.listarAutores();
    }

    @PostMapping
    public Autor guardarAutor(Autor autor){
        return autorService.guardarAutor(autor);
    }

    @GetMapping({"/id_autor"})
    public Autor buscarAutor(@RequestBody Integer id_autor){
        return autorService.buscarAutor(id_autor);
    }

    @PatchMapping({"/id_autor"})


    @DeleteMapping({"/id_autor"})

}
