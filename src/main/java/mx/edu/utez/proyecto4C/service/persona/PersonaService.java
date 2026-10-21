package mx.edu.utez.proyecto4C.service.persona;

import mx.edu.utez.proyecto4C.controller.persona.dto.RequestActualizarPersonaDTO;
import mx.edu.utez.proyecto4C.exception.customExceptions.NotFoundException;
import mx.edu.utez.proyecto4C.model.persona.Persona;
import mx.edu.utez.proyecto4C.repository.persona.PersonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Transactional
    public Persona updatePersona(Long id, RequestActualizarPersonaDTO payload){
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La persona no existe"));

        persona.setNombres(payload.getNombres());
        persona.setPrimerApellido(payload.getPrimerApellido());
        persona.setSegundoApellido(payload.getSegundoApellido());
        persona.setFechaNacimiento(payload.getFechaNacimiento());
        persona.setCorreo(payload.getCorreo());
        persona.setCurp(payload.getCurp());

        return personaRepository.save(persona);
    }
}
