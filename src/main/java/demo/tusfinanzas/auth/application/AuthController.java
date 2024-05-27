package demo.tusfinanzas.auth.application;

import demo.tusfinanzas.auth.domain.AuthService;
import demo.tusfinanzas.auth.dto.JwtAuthResponse;
import demo.tusfinanzas.auth.dto.LoginReq;
import demo.tusfinanzas.auth.dto.RegisterReq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/hello")
    public String hello() {
        return "La nube funciona!";
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginReq req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/register")
    public ResponseEntity<JwtAuthResponse> register(@RequestBody RegisterReq req) {
        return ResponseEntity.ok(authService.register(req));
    }
}
