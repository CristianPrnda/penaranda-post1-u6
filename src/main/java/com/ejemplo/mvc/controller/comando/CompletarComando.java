package com.ejemplo.mvc.controller.comando;

import com.ejemplo.mvc.service.TareaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompletarComando implements Comando {
    private final TareaService service;

    public CompletarComando(TareaService service) { this.service = service; }

    @Override
    public String ejecutar(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        service.completar(id);
        resp.sendRedirect(req.getContextPath() + "/app?comando=listar");
        return null;
    }
}