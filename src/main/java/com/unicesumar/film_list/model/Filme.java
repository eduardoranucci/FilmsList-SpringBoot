package com.unicesumar.film_list.model;

public class Filme {

    private int id;
    private int usuarioId;
    private String titulo;
    private String genero;
    private int anoDeLancamento;

    public Filme() {}

    public Filme (int id, int usuarioId, String titulo, String genero, 
    int anoDeLancamento) {
        
        this.id = id;
        this.usuarioId = usuarioId;
        this.titulo = titulo;
        this.genero = genero;
        this.anoDeLancamento = anoDeLancamento;
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
    
    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(int anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }
}

