package demo.tusfinanzas.transaccion.infrastructure;

import demo.tusfinanzas.transaccion.domain.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
}
