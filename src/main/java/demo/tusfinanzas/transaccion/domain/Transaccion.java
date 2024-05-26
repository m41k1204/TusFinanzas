package demo.tusfinanzas.transaccion.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import demo.tusfinanzas.persona.domain.Persona;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Double Monto;

    @Column(nullable = false)
    private String Recipiente;

    @Column(nullable = false)
    private String Fecha;

    @Column(nullable = false)
    private String Hora;

    @Column(nullable = false)
    private String tipo;

    @JoinColumn(name = "owner")
    @ManyToOne
    private Persona owner;
}
