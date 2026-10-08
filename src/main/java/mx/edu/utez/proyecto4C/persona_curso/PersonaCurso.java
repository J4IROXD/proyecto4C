package mx.edu.utez.proyecto4C.persona_curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.proyecto4C.model.cursos.Curso;
import mx.edu.utez.proyecto4C.model.persona.Persona;

@Entity
@Table(name = "persona_curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaCurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double calificacion;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
}
