package TiendaManga.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resenas")
public class Resena {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(1) @Max(5)
    private Integer calificacion;

    @NotBlank 
    @Size(max = 255)
    private String comentario;

    @ManyToOne @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne @JoinColumn(name = "manga_id")
    private Manga manga;
}
