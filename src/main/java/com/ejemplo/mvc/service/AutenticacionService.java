package com.ejemplo.mvc.service;

import com.ejemplo.mvc.model.Usuario;
import com.ejemplo.mvc.model.UsuarioDAO;

public class AutenticacionService {
    private final UsuarioDAO dao = new UsuarioDAO();

    public Usuario autenticar(String username, String clave) {
        Usuario u = dao.buscarPorUsername(username);
        if (u != null && u.getClave().equals(clave)) return u;
        return null;
    }
}