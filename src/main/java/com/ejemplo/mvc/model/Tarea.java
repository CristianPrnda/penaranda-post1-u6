package com.ejemplo.mvc.model;

import java.io.Serializable;
import java.util.Date;

public class Tarea implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String titulo;
    private String categoria;
    private String prioridad;    // "Alta", "Media" o "Baja"
    private Date fechaLimite;
    private boolean completada;

    public Tarea() {}

    public Tarea(int id, String titulo, String categoria,
                 String prioridad, Date fechaLimite) {
        this.id          = id;
        this.titulo      = titulo;
        this.categoria   = categoria;
        this.prioridad   = prioridad;
        this.fechaLimite = fechaLimite;
        this.completada  = false;
    }

    // --- Getters y Setters ---
    public int    getId()             { return id; }
    public void   setId(int id)       { this.id = id; }
    public String getTitulo()         { return titulo; }
    public void   setTitulo(String t) { this.titulo = t; }
    public String getCategoria()      { return categoria; }
    public void   setCategoria(String c) { this.categoria = c; }
    public String getPrioridad()      { return prioridad; }
    public void   setPrioridad(String p) { this.prioridad = p; }
    public Date   getFechaLimite()    { return fechaLimite; }
    public void   setFechaLimite(Date f) { this.fechaLimite = f; }
    public boolean isCompletada()     { return completada; }
    public void   setCompletada(boolean c) { this.completada = c; }
}