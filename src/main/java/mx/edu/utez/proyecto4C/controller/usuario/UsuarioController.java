package mx.edu.utez.proyecto4C.controller.usuario;

import mx.edu.utez.proyecto4C.model.usuario.Usuario;
import mx.edu.utez.proyecto4C.service.usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin({"*"})
public class UsuarioController {
    private final UsuarioService usuarioService;

        public UsuarioController(UsuarioService usuarioService) {
            this.usuarioService = usuarioService;
        }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsers(){
        return ResponseEntity
                .status(200)
                .body(this.usuarioService.getAllUsers());

    }

}
