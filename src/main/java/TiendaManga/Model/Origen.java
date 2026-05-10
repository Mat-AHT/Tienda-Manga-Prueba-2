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
@Table(name = "ORIGEN")
public class Origen {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_origen;

    @NotBlank
    @Size(min = 4, max = 60)
    private String pais;
    
    @NotBlank
    @Size(min = 4, max = 60)
    private String editorialOriginal;

    @OneToMany(mappedBy = "origen")
    private List<Manga> mangas;
}
