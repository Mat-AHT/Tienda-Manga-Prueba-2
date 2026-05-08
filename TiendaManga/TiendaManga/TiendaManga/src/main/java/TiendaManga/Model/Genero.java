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
@Table(name = "Generos")
public class Genero {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_genero;

    @NotBlank
    @Size(min = 4, max = 60)
    private String nombreGenero;

    //Relaciones
    @OneToMany(mappedBy = "genero")
    private List<Manga> mangas;
}
