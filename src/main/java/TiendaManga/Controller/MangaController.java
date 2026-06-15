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
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/mangas")
public class MangaController {

    @Autowired
    private MangaService mangaService;

    @GetMapping
    public ResponseEntity<List<MangaDTO>> obtenerManga(){
        List<MangaDTO> mangas = mangaService.listarMangas();
        if(mangas.isEmpty()){
            log.warn("HTTP NO_CONTENT: No se encontraron mangas en la base de datos.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("HTTP OK: Se han listado los mangas.");
        return new ResponseEntity<>(mangas, HttpStatus.OK);
    }

    @GetMapping("/{id_manga}")
    public ResponseEntity<MangaDTO> buscarMangaId(@Valid @PathVariable Integer id_manga){
        try{
            MangaDTO manga = mangaService.buscarManga(id_manga);
            log.info("HTTP OK: Se ha encontrado el manga con la id " + id_manga);
            return new ResponseEntity<>(manga, HttpStatus.OK);
        } catch(RuntimeException e){
            log.error("HTTP NOT_FOUND: No se ha encontrado el manga con la id " + id_manga);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/genero/{id_genero}")
    public ResponseEntity<List<MangaDTO>> filtrarPorgenero(@Valid @PathVariable Integer id_genero){
        List<MangaDTO> mangas = mangaService.buscarPorGenero(id_genero);
        if(mangas.isEmpty()){
            log.warn("HTTP NO_CONTENT: No se encontraron mangas para el género con la id " + id_genero);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("HTTP OK: Se han encontrado mangas para el género con la id " + id_genero);
        return new ResponseEntity<>(mangas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Manga> guardarManga(@Valid @RequestBody Manga mangaNuevo){
        Manga manga = mangaService.guardarManga(mangaNuevo);
        if(manga != null){
            log.info("HTTP CREATED: Se ha creado el manga con la id " + manga.getId_manga());
            return new ResponseEntity<>(manga, HttpStatus.CREATED);
        }else{
            log.error("HTTP BAD_REQUEST: No se ha podido crear el manga.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id_manga}")
    public ResponseEntity<Manga> editarManga(@Valid @PathVariable Integer id_manga, @RequestBody Manga manga){
        Manga mangaEditado = mangaService.editarManga(id_manga, manga);
        if(mangaEditado != null){
            log.info("HTTP OK: Se ha editado el manga con la id " + id_manga);
            return new ResponseEntity<>(mangaEditado, HttpStatus.OK);
        }else{
            log.error("HTTP NOT_FOUND: No se ha encontrado el manga con la id " + id_manga);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_manga}")
    public ResponseEntity<String> eliminarManga(@Valid @PathVariable Integer id_manga){
        String resultado = mangaService.eliminarManga(id_manga);
        if(resultado != null){
            log.info("HTTP OK: Se ha eliminado el manga con la id " + id_manga);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }else{
            log.error("HTTP NOT_FOUND: No se ha encontrado el manga con la id " + id_manga);
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

}