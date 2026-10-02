package com.ejemplo.mvc.controller.comando;

import com.ejemplo.mvc.model.Tarea;
import com.ejemplo.mvc.service.TareaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class GuardarComando implements Comando {
    private final TareaService service;

    public GuardarComando(TareaService service) { this.service = service; }

    @Override
    public String ejecutar(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String titulo    = req.getParameter("titulo");
        String categoria = req.getParameter("categoria");
        String prioridad = req.getParameter("prioridad");
        String fechaStr  = req.getParameter("fechaLimite");

        // Regla de negocio configurable: viene del contexto de aplicación (web.xml)
        int maxLongitud = (Integer)
                req.getServletContext().getAttribute("maxLongitudTitulo");
        Map<String, String> errores = new LinkedHashMap<>();

        if (titulo == null || titulo.trim().isEmpty()) {
            errores.put("titulo", "El título es obligatorio.");
        } else if (titulo.trim().length() > maxLongitud) {
            errores.put("titulo",
                    "El título no debe superar " + maxLongitud + " caracteres.");
        }

        if (categoria == null || categoria.trim().isEmpty()) {
            errores.put("categoria", "La categoría es obligatoria.");
        }

        if (!"Alta".equals(prioridad) && !"Media".equals(prioridad)
                && !"Baja".equals(prioridad)) {
            errores.put("prioridad", "Seleccione una prioridad válida.");
        }

        Date fechaLimite = null;
        try {
            fechaLimite = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
            if (fechaLimite.before(new Date())) {
                errores.put("fechaLimite", "La fecha límite no puede estar en el pasado.");
            }
        } catch (ParseException | NullPointerException e) {
            errores.put("fechaLimite", "Use el formato yyyy-MM-dd (ej: 2026-12-31).");
        }

        // Si hay errores se devuelve el formulario con los valores ya ingresados
        if (!errores.isEmpty()) {
            req.setAttribute("errores",     errores);
            req.setAttribute("titulo",      titulo);
            req.setAttribute("categoria",   categoria);
            req.setAttribute("prioridad",   prioridad);
            req.setAttribute("fechaLimite", fechaStr);
            return "/WEB-INF/views/formulario.jsp";
        }

        service.guardar(new Tarea(0, titulo.trim(), categoria.trim(),
                prioridad, fechaLimite));
        resp.sendRedirect(req.getContextPath() + "/app?comando=listar");
        return null;
    }
}