package demo.tusfinanzas.transaccion.domain;

import ch.qos.logback.core.model.Model;
import demo.tusfinanzas.config.Mapper;
import demo.tusfinanzas.exceptions.ResourceNotFoundException;
import demo.tusfinanzas.persona.domain.Persona;
import demo.tusfinanzas.persona.infrastructure.PersonaRepository;
import demo.tusfinanzas.transaccion.dto.transaccionDto;
import demo.tusfinanzas.transaccion.infrastructure.TransaccionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransaccionService {

    @Autowired
    TransaccionRepository transaccionRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    ModelMapper modelMapper;

    public void newTransaccion(transaccionDto transaccion) {
        Transaccion nuevaTransaccion = modelMapper.map(transaccion, Transaccion.class);
        String email = transaccion.getEmail().toLowerCase();
        Persona persona = personaRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Este usuario no existe"));
        nuevaTransaccion.setTipo(transaccion.getTipo());
        nuevaTransaccion.setRecipiente(transaccion.getRecipiente());
        persona.addTransaccion(nuevaTransaccion);
        personaRepository.save(persona);
        nuevaTransaccion.setOwner(persona);
        transaccionRepository.save(nuevaTransaccion);
    }

}
