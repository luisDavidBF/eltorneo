 /*
 * ServletFachada.java
 *
 * Proyecto: Gestion de Creditos
 * Cliente: Promociones Empresariales
 * Copyright 2016 by Mobiltech SAS 
 * All rights reserved
 */
package co.eltorneo.servlet;

import co.eltorneo.common.util.LoggerMessage;
import co.eltorneo.mvc.fachada.FachadaSeguridad;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Sys. E. Diego Armando Hernandez
 *
 */
@WebServlet(name = "ServletFachada", urlPatterns = {"/servletFachada"}, loadOnStartup = 1)
public class ServletFachada extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);

        LoggerMessage logMsg = LoggerMessage.getInstancia();

        try {

            logMsg = LoggerMessage.getInstancia();

            ServletContext contexto = this.getServletContext();

            FachadaSeguridad fachadaSeguridad = new FachadaSeguridad();
            contexto.setAttribute("fachadaSeguridad", fachadaSeguridad);

            logMsg.loggerMessageDebug("FachadaSeguridad cargada en el contexto de la aplicacion");

        } catch (Exception e) {
            logMsg.loggerMessageException(e);
        }

    }

    @Override
    public void destroy() {
    }
}
