package com.unicesumar.film_list.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicesumar.film_list.model.Filme;
import com.unicesumar.film_list.model.Usuario;
import com.unicesumar.film_list.repository.FilmeRepository;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    public boolean adicionarFilme(Filme filme, Long usuarioId) {
        filme.setUsuario(new Usuario(usuarioId));
        filmeRepository.save(filme);
        return true;
    }

    public List<Filme> listarFilmes(Long usuarioId, String nomeLista) {
        boolean assistido = nomeLista.equalsIgnoreCase("assistidos");
        return filmeRepository.findByUsuarioIdAndAssistido(usuarioId, assistido);
    }

    public Filme buscarFilme(int id) {
        Filme filme = filmeRepository.findById((long) id).orElse(null);
        return filme;
    }

    public void assistirFilme(Filme filme, LocalDate dataAssistido) {
        filme.setDataAssistido(dataAssistido);
        filme.setAssistido(true);
        filmeRepository.save(filme);
    }

    public void deletarFilme(Long id) {
        Filme filme = filmeRepository.findById(id).orElse(null);
        if (filme != null) {
            Usuario usuario = filme.getUsuario();
            if (usuario != null) {
                usuario.listarFilmes().remove(filme);
            }
            filmeRepository.delete(filme);
        }
    }

    public boolean filmeJaCadastrado(String titulo, Long usuarioId) {
        return filmeRepository.existsByTituloIgnoreCaseAndUsuarioId(titulo, usuarioId);
    }
}
