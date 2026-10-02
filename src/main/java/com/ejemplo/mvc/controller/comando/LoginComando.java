package com.ejemplo.mvc.controller.comando;

import com.ejemplo.mvc.model.Usuario;
import com.ejemplo.mvc.service.AutenticacionService;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginComando implements Comando {
    private final AutenticacionService service;

    public LoginComando(AutenticacionService service) {
        this.service = service;
    }

    @Override
    public String ejecutar(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String username = req.getParameter("username");
        String clave    = req.getParameter("clave");

        // GET sin parámetros: solo mostrar el formulario de login
        if (username == null) {
            return "/WEB-INF/views/login.jsp";
        }

        Usuario u = service.autenticar(username, clave);
        if (u == null) {
            req.setAttribute("errorLogin", "Usuario o contraseña incorrectos.");
            return "/WEB-INF/views/login.jsp";
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("usuarioActual", u);
        session.setMaxInactiveInterval(1800);
        resp.sendRedirect(req.getContextPath() + "/app?comando=listar");
        return null;
    }
}