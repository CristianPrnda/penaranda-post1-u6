package com.ejemplo.mvc.controller.comando;

import jakarta.servlet.http.*;
import java.io.IOException;

public class LogoutComando implements Comando {
    @Override
    public String ejecutar(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        HttpSession session = req.getSession(false);
        if (session != null) session.invalidate();
        resp.sendRedirect(req.getContextPath() + "/app?comando=login");
        return null;
    }
}