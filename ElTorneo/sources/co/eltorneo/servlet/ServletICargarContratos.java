 /*
 * ServletImagenPerfil.java
 *
 * Proyecto: Doctor Pin
 * Cliente: Promociones Empresariales
 * Copyright 2016 by Mobiltech SAS 
 * All rights reserved
 */
package co.eltorneo.servlet;

import co.eltorneo.common.util.Generales;
import co.eltorneo.common.util.LoggerMessage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Sys. E. Diego Armando Hernandez
 */
@WebServlet(name = "ServletCargarContrato", urlPatterns = {"/cargarContrato"})
public class ServletICargarContratos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/pdf");

        try {

            String pathToWeb = Generales.EMPTYSTRING;

            if (System.getProperty("os.name").toLowerCase().startsWith("window")) {
                pathToWeb = (String) new InitialContext().lookup("java:comp/env/ruta_archivo_contrato_windows");
            } else if (System.getProperty("os.name").toLowerCase().startsWith("linux")) {
                pathToWeb = (String) new InitialContext().lookup("java:comp/env/ruta_archivo_contrato_linux");
            }

            String nombreContrato = request.getParameter("nombreContrato");

            System.out.println("Nombre del contrato "+nombreContrato);
            InputStream is = null;

           
                is = new BufferedInputStream(new FileInputStream(pathToWeb + nombreContrato));
           

            IOUtils.copy(is, response.getOutputStream());

        } catch (Exception e) {

            LoggerMessage.getInstancia().loggerMessageException(e);
        } finally {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
