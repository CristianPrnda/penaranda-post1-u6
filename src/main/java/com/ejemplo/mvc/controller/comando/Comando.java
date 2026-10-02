package com.ejemplo.mvc.controller.comando;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Comando {
    /**
     * Ejecuta la acción y devuelve la ruta de la vista JSP a la que el
     * FrontControllerServlet debe hacer forward. Si el propio comando ya
     * resolvió la respuesta (por ejemplo con sendRedirect), devuelve null.
     */
    String ejecutar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException;
}