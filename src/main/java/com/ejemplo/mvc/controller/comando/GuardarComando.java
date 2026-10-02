package com.ejemplo.mvc.controller.comando;

import com.ejemplo.mvc.model.Tarea;
import com.ejemplo.mvc.service.TareaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GuardarComando implements Comando {
    private final TareaService service;

    public GuardarComando(TareaService service) { this.service = service; }

    @Override
    public String ejecutar(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String titulo = req.getParameter("titulo");

        if (titulo == null || titulo.trim().isEmpty()) {
            req.setAttribute("error", "El título es obligatorio.");
            return "/WEB-INF/views/formulario.jsp";
        }

        try {
            Tarea t = new Tarea(0, titulo.trim(),
                    req.getParameter("categoria"),
                    req.getParameter("prioridad"),
                    new SimpleDateFormat("yyyy-MM-dd")
                            .parse(req.getParameter("fechaLimite")));
            service.guardar(t);
        } catch (ParseException e) {
            req.setAttribute("error", "La fecha límite debe tener el formato yyyy-MM-dd.");
            return "/WEB-INF/views/formulario.jsp";
        }

        resp.sendRedirect(req.getContextPath() + "/app?comando=listar");
        return null;
    }
}