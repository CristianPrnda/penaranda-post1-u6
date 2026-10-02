package com.ejemplo.mvc.controller.comando;

import com.ejemplo.mvc.model.Tarea;
import com.ejemplo.mvc.service.TareaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FormularioComando implements Comando {
    private final TareaService service;

    public FormularioComando(TareaService service) { this.service = service; }

    @Override
    public String ejecutar(HttpServletRequest req, HttpServletResponse resp) {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Tarea t = service.obtenerPorId(Integer.parseInt(idParam));
            req.setAttribute("tarea", t);
        }
        return "/WEB-INF/views/formulario.jsp";
    }
}