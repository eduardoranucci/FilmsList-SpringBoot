package com.unicesumar.film_list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unicesumar.film_list.model.Usuario;
import com.unicesumar.film_list.repository.UsuarioRepository;

@Service
public class AuthService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void adicionarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Usuario autenticarUsuario(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmailAndSenha(email, senha);
        if (usuario != null) {
            return usuario;
        }
        return null;
    }

    public boolean emailJaCadastrado(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}
