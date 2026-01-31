package com.rrglass.br.api.auth;

import com.rrglass.br.api.usuario.UsuarioRepository;
import com.rrglass.br.api.auth.dto.LoginRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UsuarioRepository usuarioRepository, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
    }

    public String login(LoginRequest request) {

        var usuario = usuarioRepository.findByEmail(request.email)
                .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos"));

        if (!encoder.matches(request.senha, usuario.getSenhaHash())) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        return jwtService.gerarToken(usuario.getEmail());
    }
}
