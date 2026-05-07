package TiendaManga.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TiendaManga.Model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Integer>{

}