package com.unicesumar.film_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicesumar.film_list.model.Filme;
import java.util.List;
import java.util.Optional;


public interface FilmeRepository extends JpaRepository<Filme, Long> {

    Optional<Filme> findById(Long id);
    List<Filme> findByUsuarioIdAndAssistido(Long usuarioId, boolean assistido);
    boolean existsByTituloIgnoreCaseAndUsuarioId(String titulo, Long usuarioId);
}
