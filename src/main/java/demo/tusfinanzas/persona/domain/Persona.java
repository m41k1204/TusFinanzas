package demo.tusfinanzas.persona.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import demo.tusfinanzas.transaccion.domain.Transaccion;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Double Cuenta;

//    @Column(name = "role", nullable = false)
//    Roles role;

    @Column(nullable = false)
    private String Nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner")
    List<Transaccion> listaTransacciones;

    public void addTransaccion(Transaccion transaccion) {
        listaTransacciones.add(transaccion);
        Cuenta += transaccion.getMonto();
    }
}
