package com.unicesumar.film_list.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unicesumar.film_list.model.Filme;
import com.unicesumar.film_list.model.Usuario;
import com.unicesumar.film_list.service.FilmeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FilmeController {
    
    // @Autowired
    // private FilmeService filmeService;

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            model.addAttribute("msg", "Sessão expirou ou usuário deslogado");
            return "index";
        }
        model.addAttribute("usuario", usuario);
        return "home";
    }
    
}