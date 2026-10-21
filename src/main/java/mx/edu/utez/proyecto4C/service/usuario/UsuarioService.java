package mx.edu.utez.proyecto4C.service.usuario;

import mx.edu.utez.proyecto4C.controller.usuario.dto.RequestActualizarUsuarioDTO;
import mx.edu.utez.proyecto4C.controller.usuario.dto.RequestCrearUsuarioDTO;
import mx.edu.utez.proyecto4C.exception.customExceptions.BadRequestException;
import mx.edu.utez.proyecto4C.exception.customExceptions.NotFoundException;
import mx.edu.utez.proyecto4C.model.persona.Persona;
import mx.edu.utez.proyecto4C.model.usuario.Roles;
import mx.edu.utez.proyecto4C.model.usuario.Usuario;
import mx.edu.utez.proyecto4C.repository.persona.PersonaRepository;
import mx.edu.utez.proyecto4C.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PersonaRepository personaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
    }

    //servicios
    public List<Usuario> getAllUsers(){
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario createUser(RequestCrearUsuarioDTO payload){
        //Validar si el username ya existe
        if (usuarioRepository.findByUsername(payload.getUsername()).isPresent()){
            throw new BadRequestException("Ese usuario ya existe");

        }

        //registrar persona
        Persona newPersona = new Persona();
        newPersona.setNombres(payload.getNombres());
        newPersona.setPrimerApellido(payload.getPrimerApellido());
        newPersona.setSegundoApellido(payload.getSegundoApellido());
        newPersona.setFechaNacimiento(payload.getFechaNacimiento());
        newPersona.setCorreo(payload.getCorreo());
        newPersona.setCurp(payload.getCurp());

        //Creando registro de persona y obtener sus datos como registro existente como el id
        newPersona = personaRepository.save(newPersona);

        //Crear una instancia de usuario y llenarla en base a la info de payload
        Usuario newUser = new Usuario();

        newUser.setUsername(payload.getUsername());
        newUser.setPassword(payload.getPassword());
        newUser.setDescripcion(payload.getDescripcion());
        newUser.setEnable(true);
        newUser.setRol(
                Roles.valueOf(payload.getRol())
        );
        newUser.setPersona(newPersona);

        return usuarioRepository.save(newUser);
    }

    public Usuario getUserById(Long id){
        return findUserOrThrow(id);
    }

    // Activar (true) o desactivar (false) un usuario por id
    @Transactional
    public Usuario changeEnable(Long id, boolean enable){
        Usuario usuario = findUserOrThrow(id);
        usuario.setEnable(enable);
        return usuarioRepository.save(usuario);
    }

    // Actualizar SOLO username y descripcion.
    // Se busca el usuario existente y se modifican solo esos dos campos,
    // así password, rol, enable y la relación con persona se conservan.
    @Transactional
    public Usuario updateUser(Long id, RequestActualizarUsuarioDTO payload){
        Usuario usuario = findUserOrThrow(id);

        // El username no debe pertenecer a OTRO usuario
        usuarioRepository.findByUsername(payload.getUsername())
                .filter(otro -> !otro.getId().equals(id))
                .ifPresent(otro -> {
                    throw new BadRequestException("Ese usuario ya existe");
                });

        usuario.setUsername(payload.getUsername());
        usuario.setDescripcion(payload.getDescripcion());

        return usuarioRepository.save(usuario);
    }

    // Validación de existencia reutilizable
    private Usuario findUserOrThrow(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El usuario no existe"));
    }
}
