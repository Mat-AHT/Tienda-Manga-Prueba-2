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

import TiendaManga.DTO.MangaDTO;
import TiendaManga.Model.Manga;
import TiendaManga.Service.MangaService;

@RestController
@RequestMapping("/api/v1/mangas")
public class MangaController {

    @Autowired
    private MangaService mangaService;

    @GetMapping
    public ResponseEntity<List<MangaDTO>> obtenerManga(){
        List<MangaDTO> mangas = mangaService.listarMangas();
        if(mangas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mangas, HttpStatus.OK);
    }

    @GetMapping("/{id_manga}")
    public ResponseEntity<MangaDTO> buscarMangaId(@PathVariable Integer id_manga){
        try{
            MangaDTO manga = mangaService.buscarManga(id_manga);
            return new ResponseEntity<>(manga, HttpStatus.OK);
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/genero/{id_genero}")
    public ResponseEntity<List<MangaDTO>> filtrarPorgenero(@PathVariable Integer id_genero){
        List<MangaDTO> mangas = mangaService.buscarPorGenero(id_genero);
        if(mangas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mangas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Manga> guardarManga(@RequestBody Manga mangaNuevo){
        Manga manga = mangaService.guardarManga(mangaNuevo);
        if(manga != null){
            return new ResponseEntity<>(manga, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id_manga}")
    public ResponseEntity<Manga> editarManga(@PathVariable Integer id_manga, @RequestBody Manga manga){
        Manga mangaEditado = mangaService.editarManga(id_manga, manga);
        if(mangaEditado != null){
            return new ResponseEntity<>(mangaEditado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_manga}")
    public ResponseEntity<String> eliminarManga(@PathVariable Integer id_manga){
        String resultado = mangaService.eliminarManga(id_manga);
        if(resultado != null){
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

}