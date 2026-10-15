package mx.edu.utez.proyecto4C.service.usuario;

import mx.edu.utez.proyecto4C.controller.usuario.dto.RequestCrearUsuarioDTO;
import mx.edu.utez.proyecto4C.exception.customExceptions.BadRequestException;
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
}
