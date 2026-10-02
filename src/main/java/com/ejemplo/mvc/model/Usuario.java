package com.ejemplo.mvc.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String clave;
    private String nombreCompleto;
    private String rol;   // "ADMIN" o "USER"

    public Usuario(String username, String clave,
                   String nombreCompleto, String rol) {
        this.username       = username;
        this.clave          = clave;
        this.nombreCompleto = nombreCompleto;
        this.rol            = rol;
    }

    public String getUsername()       { return username; }
    public String getClave()          { return clave; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getRol()            { return rol; }
}