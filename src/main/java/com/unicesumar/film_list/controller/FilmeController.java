package com.unicesumar.film_list.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unicesumar.film_list.model.FilmeNaoAssistido;
import com.unicesumar.film_list.model.FilmeAssistido;
import com.unicesumar.film_list.model.Usuario;
import com.unicesumar.film_list.service.FilmeService;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;

@Controller
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        List<FilmeNaoAssistido> filmesParaAssistir = filmeService.listarFilmesNaoAssistidos(usuario.getId());
        List<FilmeAssistido> filmesAssistidos = filmeService.listarFilmesAssistidos(usuario.getId());

        model.addAttribute("filmesParaAssistir", filmesParaAssistir);
        model.addAttribute("filmesAssistidos", filmesAssistidos);
        model.addAttribute("usuario", usuario);
        return "home";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model modelo) {
        modelo.addAttribute("filme", new FilmeNaoAssistido());
        return "cadastro";
    }

    @PostMapping("/adicionarFilme")
    public String adicionarFilme(HttpSession session, @ModelAttribute FilmeNaoAssistido filme, Model model, RedirectAttributes attrs) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (filmeService.filmeJaCadastrado(filme.getTitulo(), usuario.getId())) {
            model.addAttribute("msg", "Filme já cadastrado");
            model.addAttribute("filme", filme);
            return "cadastro";
        }

        attrs.addFlashAttribute("msgSucesso", "Filme cadastrado com sucesso!");
        filmeService.adicionarFilme(filme, usuario.getId());
        return "redirect:/home";
    }

    @PostMapping("/adicionarData")
    public String adicionarData(
            @RequestParam("id") int id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataAssistido,
            HttpSession session, Model model,
            RedirectAttributes attrs) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        FilmeNaoAssistido filme = filmeService.buscarFilmeNaoAssistido(id);
        if (filme != null) {
            filmeService.assistirFilme(filme, dataAssistido);
        }

        List<FilmeNaoAssistido> filmesParaAssistir = filmeService.listarFilmesNaoAssistidos(usuario.getId());
        List<FilmeAssistido> filmesAssistidos = filmeService.listarFilmesAssistidos(usuario.getId());

        attrs.addFlashAttribute("msgSucesso", "Data de visualização adicionada com sucesso. Filme movido para assistidos.");
        model.addAttribute("filmesParaAssistir", filmesParaAssistir);
        model.addAttribute("filmesAssistidos", filmesAssistidos);
        model.addAttribute("usuario", usuario);

        return "redirect:/home";
    }

    @PostMapping("/deletarFilme")
    public String deletarFilme(@RequestParam("id") Long id, HttpSession session, Model model, RedirectAttributes attrs) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        
        filmeService.deletarFilme(id);

        List<FilmeNaoAssistido> filmesParaAssistir = filmeService.listarFilmesNaoAssistidos(usuario.getId());
        List<FilmeAssistido> filmesAssistidos = filmeService.listarFilmesAssistidos(usuario.getId());

        attrs.addFlashAttribute("msgSucesso", "Filme deletado com sucesso");
        model.addAttribute("filmesParaAssistir", filmesParaAssistir);
        model.addAttribute("filmesAssistidos", filmesAssistidos);
        model.addAttribute("usuario", usuario);

        return "redirect:/home";
    }

}