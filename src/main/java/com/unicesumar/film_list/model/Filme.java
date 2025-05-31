package com.unicesumar.film_list.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String genero;
    private Integer anoDeLancamento;
    private LocalDate dataAssistido;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Filme() {}

    public Filme (String titulo, String genero, 
    Integer anoDeLancamento, LocalDate dataAssistido) {

        this.titulo = titulo;
        this.genero = genero;
        this.anoDeLancamento = anoDeLancamento;
        this.dataAssistido = dataAssistido;
    }

    public Long getId() {
        return id;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getGenero() {
        return genero;
    }
    
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public Integer getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(Integer anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public LocalDate getDataAssistido() {
        return dataAssistido;
    }

    public void setDataAssistido(LocalDate dataAssistido) {
        this.dataAssistido = dataAssistido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

