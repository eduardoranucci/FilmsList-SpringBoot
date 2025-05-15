package com.unicesumar.film_list.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.unicesumar.film_list.model.Filme;

@Service
public class FilmeService {

    private final List<Filme> filmesParaAssistir = new ArrayList<>();
    private final List<Filme> filmesAssistidos = new ArrayList<>();

    public boolean adicionarFilme(Filme filme, int usuarioId) {
        filme.setId(filmesParaAssistir.size() + 1);
        filme.setUsuarioId(usuarioId);
        filmesParaAssistir.add(filme);
        return true;
    }

    public List<Filme> listarFilmes(int id) {
        List<Filme> filmesUsuario = new ArrayList<>();
        for (Filme filme : filmesParaAssistir) {
            if (id == filme.getUsuarioId()) {
                filmesUsuario.add(filme);
            }
        }
        return filmesUsuario;
    }

    public Filme buscarFilme(int id) {
        for (Filme filme : filmesParaAssistir) {
            if (filme.getId() == id) {
                return filme;
            }
        }
        return null;
    }
}
