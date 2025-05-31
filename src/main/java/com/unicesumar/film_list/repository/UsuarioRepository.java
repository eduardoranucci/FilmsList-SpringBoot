package com.unicesumar.film_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicesumar.film_list.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Usuario findByEmailAndSenha(String login, String senha);
    
    Usuario findByNome(String nome);
    boolean existsByEmail(String email);
}
