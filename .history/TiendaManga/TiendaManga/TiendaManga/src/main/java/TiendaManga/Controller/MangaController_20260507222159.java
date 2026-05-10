package TiendaManga.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Manga>> obtenerManga(){
        List<Manga> mangas = mangaService.listarMangas();
        if(mangas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mangas, HttpStatus.OK);
    }
    
    @GetMapping("/{id_manga}")
    public ResponseEntity<Manga> buscarMangaId(@PathVariable Integer id_manga){
        Manga manga = mangaService.buscarManga(id_manga);
        if(manga != null){
            return new ResponseEntity<>(manga, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/genero/{id_genero}")
    public ResponseEntity<List<Manga>> filtrarPorgenero(@PathVariable Integer id_genero){
        List<Manga> mangas = mangaService.buscarGenero(id_genero);
    }
    
}
