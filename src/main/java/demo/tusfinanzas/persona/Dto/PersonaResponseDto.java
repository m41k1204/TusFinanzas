package demo.tusfinanzas.persona.Dto;

import demo.tusfinanzas.transaccion.domain.Transaccion;
import demo.tusfinanzas.transaccion.dto.TransaccionResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class PersonaResponseDto {

    Long id;

    String nombre;

    String apellido;
    Double Cuenta;
    List<TransaccionResponseDto> transaccions;
}
