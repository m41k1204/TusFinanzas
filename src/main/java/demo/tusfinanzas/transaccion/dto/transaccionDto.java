package demo.tusfinanzas.transaccion.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class transaccionDto {
    @NotNull
    private Double monto;
    @NotNull
    private String recipiente;
    @NotNull
    private String fecha;
    @NotNull
    private String hora;
    @NotNull
    private String tipo;
    @NotNull
    private String email;

}
