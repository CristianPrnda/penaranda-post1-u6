package com.ejemplo.mvc.controller;

import com.ejemplo.mvc.controller.comando.*;
import com.ejemplo.mvc.service.AutenticacionService;
import com.ejemplo.mvc.service.TareaService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "FrontControllerServlet", urlPatterns = {"/app"})
public class FrontControllerServlet extends HttpServlet {

    private static final Set<String> PUBLICOS = Set.of("login", "idioma");
    private final Map<String, Comando> comandos = new HashMap<>();

    @Override
    public void init() throws ServletException {
        TareaService tareaService = new TareaService();
        AutenticacionService authService = new AutenticacionService();

        ServletContext ctx = getServletContext();
        ctx.setAttribute("nombreApp", ctx.getInitParameter("app.nombre"));
        ctx.setAttribute("maxLongitudTitulo",
                Integer.parseInt(ctx.getInitParameter("app.maxLongitudTitulo")));

        comandos.put("listar",     new ListarComando(tareaService));
        comandos.put("formulario", new FormularioComando(tareaService));
        comandos.put("guardar",    new GuardarComando(tareaService));
        comandos.put("eliminar",   new EliminarComando(tareaService));
        comandos.put("completar",  new CompletarComando(tareaService));
        comandos.put("login",      new LoginComando(authService));
        comandos.put("logout",     new LogoutComando());
        comandos.put("idioma",     new IdiomaComando());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        procesar(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        procesar(req, resp);
    }

    private void procesar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String nombreComando = req.getParameter("comando");
        if (nombreComando == null) nombreComando = "listar";

        // Punto único de control de sesión: se resuelve aquí, una sola vez,
        // para todos los comandos protegidos (todos excepto login/idioma)
        if (!PUBLICOS.contains(nombreComando)) {
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("usuarioActual") == null) {
                resp.sendRedirect(req.getContextPath() + "/app?comando=login");
                return;
            }
        }

        Comando comando = comandos.get(nombreComando);
        if (comando == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String vista = comando.ejecutar(req, resp);
        if (vista != null) {
            req.getRequestDispatcher(vista).forward(req, resp);
        }
    }
}