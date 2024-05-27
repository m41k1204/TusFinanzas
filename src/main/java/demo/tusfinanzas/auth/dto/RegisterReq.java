package demo.tusfinanzas.auth.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class RegisterReq {
    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @NotNull
    private String email;

    @NotNull
    private String password;
}
