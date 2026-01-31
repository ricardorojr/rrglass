package com.rrglass.br.api.usuario;

import com.rrglass.br.api.role.Role;
import com.rrglass.br.api.role.RoleRepository;
import com.rrglass.br.api.usuario.domain.Usuario;
import com.rrglass.br.api.usuario.dto.UsuarioRequest;
import com.rrglass.br.api.usuario.dto.UsuarioResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            RoleRepository roleRepository
    ) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    public UsuarioResponse criarUsuario(UsuarioRequest request) {

        Role roleUser = roleRepository.findByNome("USER")
                .orElseThrow(() -> new RuntimeException("Role USER não encontrada"));

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        if (request.getCpf() != null && usuarioRepository.existsByCpf(request.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenhaHash(passwordEncoder.encode(request.getSenha()));
        usuario.setCpf(request.getCpf());
        usuario.setDataNascimento(request.getDataNascimento());
        usuario.setTelefone(request.getTelefone());
        usuario.setRole(roleUser);

        Usuario salvo = usuarioRepository.save(usuario);

        return mapToResponse(salvo);
    }

    private UsuarioResponse mapToResponse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setNome(usuario.getNome());
        response.setEmail(usuario.getEmail());
        response.setCpf(usuario.getCpf());
        response.setTelefone(usuario.getTelefone());
        response.setDataNascimento(usuario.getDataNascimento());
        response.setAtivo(usuario.getAtivo());
        response.setDataCriacao(usuario.getDataCriacao());
        return response;
    }
}
