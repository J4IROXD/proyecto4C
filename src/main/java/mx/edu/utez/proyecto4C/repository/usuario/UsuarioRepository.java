package mx.edu.utez.proyecto4C.repository.usuario;

import mx.edu.utez.proyecto4C.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
