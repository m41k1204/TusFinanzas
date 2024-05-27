package demo.tusfinanzas.persona.domain;

import demo.tusfinanzas.exceptions.ResourceNotFoundException;
import demo.tusfinanzas.persona.Dto.nuevaPersonaDto;
import demo.tusfinanzas.persona.infrastructure.PersonaRepository;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    ModelMapper modelMapper;
    public void newPersona(nuevaPersonaDto personaDto) {
        Persona persona = modelMapper.map(personaDto, Persona.class);
        persona.setCuenta(0.0);
        personaRepository.save(persona);
    }

    public Persona getPersona(Long id) {
        return personaRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No se encontro a esta persona"));
    }

    @Bean(name = "UserDetailsService")
    public UserDetailsService userDetailsService() {
        return username -> {
            Persona user =  personaRepository
                    .findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return (UserDetails) user;
        };
    }

}
