package TiendaManga.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Autores")
    public class Autor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_autor;

    @NotBlank
    @Size(min = 4, max = 60)
    private String nombreAutor;

    @NotBlank
    @Size(min = 4, max = 60)
    private String nacionalidad;

    @OneToMany(mappedBy = "autor")
    private List<Manga> mangas;
}
