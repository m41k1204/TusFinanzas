package demo.tusfinanzas.persona.Dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class nuevaPersonaDto {
    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @NotNull
    private String email;

    @NotNull
    private String password;
}
