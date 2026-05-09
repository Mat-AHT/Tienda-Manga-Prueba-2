package TiendaManga.DTO;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Integer id_usuario;
    private String nombre;
    private String correo;
    private String contraseña;

}
