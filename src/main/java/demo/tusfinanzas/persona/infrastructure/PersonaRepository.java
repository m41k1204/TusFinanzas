package demo.tusfinanzas.persona.infrastructure;

import demo.tusfinanzas.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findByEmail(String email);
}
