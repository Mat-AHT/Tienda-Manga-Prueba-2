package TiendaManga.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TiendaManga.Model.Manga;
import TiendaManga.Service.MangaService;

@RestController
@RequestMapping("/api/mangas")
public class MangaController {
    @Autowired
    private MangaService mangaService;

    @GetMapping 
    public List<Manga> obtener(){
        return mangaService.listarMangas();
    }
    @GetMapping("/{id}")
    Public Manga buscar(@PathVariable Integer id){
        return mangaService.buscarManga(id);
    
    
}
