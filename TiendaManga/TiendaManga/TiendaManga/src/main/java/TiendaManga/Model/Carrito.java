package TiendaManga.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//aaaaaa

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Carritos")
public class Carrito {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(1)
    private Integer cantidad;

    @ManyToOne @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne @JoinColumn(name = "manga_id")
    private Manga manga;
    @OneToMany(mappedBy = "genero")
    private List<Manga> mangas;

}
