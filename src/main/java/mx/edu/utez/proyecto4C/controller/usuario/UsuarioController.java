package mx.edu.utez.proyecto4C.controller.usuario;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto4C.controller.usuario.dto.RequestActualizarUsuarioDTO;
import mx.edu.utez.proyecto4C.controller.usuario.dto.RequestCrearUsuarioDTO;
import mx.edu.utez.proyecto4C.model.usuario.Usuario;
import mx.edu.utez.proyecto4C.service.usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Usuario> createUser (@RequestBody @Valid RequestCrearUsuarioDTO payload){
        return ResponseEntity
                .status(201)
                .body( this.usuarioService.createUser(payload));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id){
        return ResponseEntity
                .status(200)
                .body(this.usuarioService.getUserById(id));
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<Usuario> activateUser(@PathVariable Long id){
        return ResponseEntity
                .status(200)
                .body(this.usuarioService.changeEnable(id, true));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Usuario> deactivateUser(@PathVariable Long id){
        return ResponseEntity
                .status(200)
                .body(this.usuarioService.changeEnable(id, false));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id,
                                              @RequestBody @Valid RequestActualizarUsuarioDTO payload){
        return ResponseEntity
                .status(200)
                .body(this.usuarioService.updateUser(id, payload));
    }
}
