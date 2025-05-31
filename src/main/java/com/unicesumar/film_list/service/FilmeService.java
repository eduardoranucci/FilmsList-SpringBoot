package com.unicesumar.film_list.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.unicesumar.film_list.model.Filme;

@Service
public class FilmeService {

    private final List<Filme> filmesParaAssistir = new ArrayList<>();
    private final List<Filme> filmesAssistidos = new ArrayList<>();

    public boolean adicionarFilme(Filme filme, Long usuarioId) {
        filmesParaAssistir.add(filme);
        return true;
    }

    public List<Filme> listarFilmes(Long id, String nomeLista) {

        List<Filme> filmesList = new ArrayList<>();
        if (nomeLista.equals("assistir")) {
            filmesList.addAll(filmesParaAssistir);
        } else if (nomeLista.equals("assistidos")) {
            filmesList.addAll(filmesAssistidos);
        }

        List<Filme> filmesUsuario = new ArrayList<>();
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

    public void assistirFilme(Filme filme) {
        for (int i = 0; i < filmesParaAssistir.size(); i++) {
            if (filmesParaAssistir.get(i).getId() == filme.getId()) {
                filmesAssistidos.add(filme);
                filmesParaAssistir.remove(i);
                break;
            }
        }
    }

    public void deletarFilme(int id) {
        for (int i = 0; i < filmesParaAssistir.size(); i++) {
            if (filmesParaAssistir.get(i).getId() == id) {
                filmesParaAssistir.remove(i);
                break;
            }
        }
    }

    public boolean filmeJaCadastrado(String titulo) {
        List<Filme> filmesList = new ArrayList<>();
        
        filmesList.addAll(filmesParaAssistir);
        filmesList.addAll(filmesAssistidos);
        return filmesList.stream()
            .anyMatch(f -> f.getTitulo().equalsIgnoreCase(titulo));
    }
}
