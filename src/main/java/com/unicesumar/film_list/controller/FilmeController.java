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

import com.unicesumar.film_list.model.Filme;
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
        if (usuario == null) {
            model.addAttribute("msg", "Sessão expirou ou usuário deslogado");
            return "index";
        }

        List<Filme> filmesParaAssistir = filmeService.listarFilmes(usuario.getId(), "assistir");
        List<Filme> filmesAssistidos = filmeService.listarFilmes(usuario.getId(), "assistidos");

        model.addAttribute("filmesParaAssistir", filmesParaAssistir);
        model.addAttribute("filmesAssistidos", filmesAssistidos);
        model.addAttribute("usuario", usuario);
        return "home";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model modelo) {
        modelo.addAttribute("filme", new Filme());
        return "cadastro";
    }

    @PostMapping("/adicionarFilme")
    public String adicionarTarefa(HttpSession session, @ModelAttribute Filme filme, Model model, RedirectAttributes attrs) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            model.addAttribute("msg", "Sessão expirou ou usuário deslogado");
            return "index";
        }

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
        if (usuario == null) {
            model.addAttribute("msg", "Sessão expirou ou usuário deslogado");
            return "index";
        }

        Filme filme = filmeService.buscarFilme(id);
        if (filme != null) {
            filmeService.assistirFilme(filme, dataAssistido);
        }

        List<Filme> filmesParaAssistir = filmeService.listarFilmes(usuario.getId(), "assistir");
        List<Filme> filmesAssistidos = filmeService.listarFilmes(usuario.getId(), "assistidos");

        attrs.addFlashAttribute("msgSucesso", "Data de visualização adicionada com sucesso. Filme movido para assistidos.");
        model.addAttribute("filmesParaAssistir", filmesParaAssistir);
        model.addAttribute("filmesAssistidos", filmesAssistidos);
        model.addAttribute("usuario", usuario);

        return "redirect:/home";
    }

    @PostMapping("/deletarFilme")
    public String deletarFilme(@RequestParam("id") Long id, HttpSession session, Model model, RedirectAttributes attrs) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            model.addAttribute("msg", "Sessão expirou ou usuário deslogado");
            return "index";
        }
        
        filmeService.deletarFilme(id);

        List<Filme> filmesParaAssistir = filmeService.listarFilmes(usuario.getId(), "assistir");
        List<Filme> filmesAssistidos = filmeService.listarFilmes(usuario.getId(), "assistidos");

        attrs.addFlashAttribute("msg", "Filme deletado com sucesso");
        model.addAttribute("filmesParaAssistir", filmesParaAssistir);
        model.addAttribute("filmesAssistidos", filmesAssistidos);
        model.addAttribute("usuario", usuario);

        return "redirect:/home";
    }

}