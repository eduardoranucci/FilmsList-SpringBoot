package com.unicesumar.film_list.model;

import java.time.LocalDate;

public class Filme {

    private int id;
    private int usuarioId;
    private String titulo;
    private String genero;
    private Integer anoDeLancamento;
    private LocalDate dataAssistido;

    public Filme() {}

    public Filme (int id, int usuarioId, String titulo, String genero, 
    Integer anoDeLancamento, LocalDate dataAssistido) {

        this.id = id;
        this.usuarioId = usuarioId;
        this.titulo = titulo;
        this.genero = genero;
        this.anoDeLancamento = anoDeLancamento;
        this.dataAssistido = dataAssistido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int id) {
        this.usuarioId = id;
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
}

