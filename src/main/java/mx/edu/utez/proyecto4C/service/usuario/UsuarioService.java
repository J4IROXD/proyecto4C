package mx.edu.utez.proyecto4C.service.usuario;

import mx.edu.utez.proyecto4C.model.usuario.Usuario;
import mx.edu.utez.proyecto4C.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import javax.swing.event.ListDataEvent;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //servicios
    public List<Usuario> getAllUsers(){
        return usuarioRepository.findAll();
    }
}
