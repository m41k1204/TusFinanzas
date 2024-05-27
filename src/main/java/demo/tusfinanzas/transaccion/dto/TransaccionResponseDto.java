package demo.tusfinanzas.transaccion.dto;


import lombok.Data;

@Data
public class TransaccionResponseDto {


    private Long id;

    private Double Monto;

    private String Recipiente;

    private String Fecha;

    private String Hora;

    private String tipo;
}
