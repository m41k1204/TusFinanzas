package demo.tusfinanzas.persona.application;

import demo.tusfinanzas.persona.Dto.nuevaPersonaDto;
import demo.tusfinanzas.persona.domain.Persona;
import demo.tusfinanzas.persona.domain.PersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaService personaService;

    @PostMapping("/nueva")
    public ResponseEntity<Void> newPersona(@RequestBody nuevaPersonaDto personaDto) {
        personaService.newPersona(personaDto);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersona(@PathVariable Long id) {
        Persona persona = personaService.getPersona(id);
        return ResponseEntity.ok(persona);
    }

}
