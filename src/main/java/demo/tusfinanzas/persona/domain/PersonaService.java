package demo.tusfinanzas.persona.domain;

import demo.tusfinanzas.exceptions.ResourceNotFoundException;
import demo.tusfinanzas.persona.Dto.PersonaResponseDto;
import demo.tusfinanzas.persona.Dto.nuevaPersonaDto;
import demo.tusfinanzas.persona.infrastructure.PersonaRepository;
import demo.tusfinanzas.transaccion.domain.Transaccion;
import demo.tusfinanzas.transaccion.domain.TransaccionService;
import demo.tusfinanzas.transaccion.dto.TransaccionResponseDto;
import demo.tusfinanzas.transaccion.infrastructure.TransaccionRepository;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    ModelMapper modelMapper;
    public void newPersona(nuevaPersonaDto personaDto) {
        Persona persona = modelMapper.map(personaDto, Persona.class);
        persona.setCuenta(0.0);
        personaRepository.save(persona);
    }

    public PersonaResponseDto getPersona(Long id) {
        Persona persona =  personaRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No se encontro a esta persona"));
        PersonaResponseDto response = modelMapper.map(persona, PersonaResponseDto.class);
        List<TransaccionResponseDto> transaccionResponseDtos = new ArrayList<>();
        persona.getListaTransacciones().forEach(transaccion -> {
            transaccionResponseDtos.add(modelMapper.map(transaccion, TransaccionResponseDto.class));
        });
        response.setTransaccions(transaccionResponseDtos);
        return response;
    }

    public void deleteAllTransacciones() {
        transaccionRepository.deleteAll();
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
