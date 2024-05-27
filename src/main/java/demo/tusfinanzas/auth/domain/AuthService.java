package demo.tusfinanzas.auth.domain;


import demo.tusfinanzas.auth.dto.JwtAuthResponse;
import demo.tusfinanzas.auth.dto.LoginReq;
import demo.tusfinanzas.auth.dto.RegisterReq;
import demo.tusfinanzas.config.JwtService;
import demo.tusfinanzas.exceptions.ResourceNotFoundException;
import demo.tusfinanzas.persona.domain.Persona;
import demo.tusfinanzas.persona.domain.Roles;
import demo.tusfinanzas.persona.infrastructure.PersonaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final PersonaRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthService(PersonaRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public JwtAuthResponse login(LoginReq req) {
        Optional<Persona> user;
        user = userRepository.findByEmail(req.getEmail());

        if (user.isEmpty()) throw new UsernameNotFoundException("Email is not registered");

        if (!passwordEncoder.matches(req.getPassword(), user.get().getPassword()))
            throw new IllegalArgumentException("Password is incorrect");

        JwtAuthResponse response = new JwtAuthResponse();

        response.setToken(jwtService.generateToken(user.get()));
        return response;
    }

    public JwtAuthResponse register(RegisterReq req){
        Optional<Persona> user = userRepository.findByEmail(req.getEmail());
        if (user.isPresent()) throw new ResourceNotFoundException("Email is already registered");
        Persona persona = modelMapper.map(req, Persona.class);
        persona.setCuenta(0.0);
        persona.setRole(Roles.OWNER);
        userRepository.save(persona);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(jwtService.generateToken(persona));
        return response;
    }
}
