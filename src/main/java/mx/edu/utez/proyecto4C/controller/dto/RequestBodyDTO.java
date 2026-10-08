package mx.edu.utez.proyecto4C.controller.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestBodyDTO {
    @NotBlank(message = "El nombre es oblicatorio")
    @Size(min = 3, message = "En nombre debe teber al menos 3 caracteres")
    private String nombre;

    @Min(value = 18, message = "Debes ser mayor de edad")
    private int edad;

    @NotBlank(message = "mal X")
    @Email(message = "El formato del correo no es valido")
    private String email;

    @NotBlank(message = "Mal >:(")
    @Pattern(
            regexp = "^[A-Z][AEIOUX][A-Z]{2}\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01])[HM](?:AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TL|TS|VZ|YN|ZS|NE)[BCDFGHJKLMNPQRSTVWXYZ]{3}[A-Z\\d]\\d$",
            message = "La CURP no tiene un formato válido"
    )
    private String curp;
}
