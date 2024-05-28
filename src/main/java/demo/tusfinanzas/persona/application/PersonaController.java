package demo.tusfinanzas.persona.application;

import demo.tusfinanzas.persona.Dto.PersonaResponseDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<PersonaResponseDto> getPersona(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.getPersona(id));
    }



}
