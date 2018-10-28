/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.eltorneo.servlet;

import co.eltorneo.common.util.Generales;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Administrador
 */
@WebServlet(name = "ServletVisualizarImagen", urlPatterns = {"/ServletVisualizarImagen"})
public class ServletVisualizarImagen extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    response.setContentType("image/jpeg");

        try {

            String pathToWeb = Generales.EMPTYSTRING;
            System.out.println("Ingresa al servlet cargar logo cliente");

            if (System.getProperty("os.name").toLowerCase().startsWith("window")) {
                pathToWeb = (String) new InitialContext().lookup("java:comp/env/ruta_img_perfil_windows");
            } else if (System.getProperty("os.name").toLowerCase().startsWith("linux")) {
                pathToWeb = (String) new InitialContext().lookup("java:comp/env/ruta_img_perfil_linux");
            }else if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
                pathToWeb = (String) new InitialContext().lookup("java:comp/env/ruta_img_perfil_mac");
            }


            String imagen = request.getParameter("imagen");

            InputStream is = null;

            try {
                System.out.println("ddddd"+ pathToWeb + imagen); 
                is = new BufferedInputStream(new FileInputStream(pathToWeb + imagen));
            } catch (Exception e) {
                System.out.println("Error al cargar imagen");
                is = new BufferedInputStream(new FileInputStream(pathToWeb + "perfil.png"));
            }

            IOUtils.copy(is, response.getOutputStream());

        } catch (Exception e) {
            
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
