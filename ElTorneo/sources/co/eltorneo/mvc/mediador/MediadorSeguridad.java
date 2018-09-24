 /*
 * MediadorSeguridad.java
 *
 * Proyecto: Gestion de Creditos
 * Cliente: Promociones Empresariales
 * Copyright 2016 by Mobiltech SAS 
 * All rights reserved
 */
package co.eltorneo.mvc.mediador;

import co.eltorneo.common.connection.ContextDataResourceNames;
import co.eltorneo.common.connection.DataBaseConnection;
import co.eltorneo.common.util.LoggerMessage;
import co.eltorneo.mvc.dao.UsuariosDAO;
import co.eltorneo.mvc.dto.UsuarioDTO;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Sys. E. Diego Armando Hernandez
 */
public class MediadorSeguridad {

    /**
     *
     */
    private final static MediadorSeguridad instancia = null;
    private final LoggerMessage logMsg;

    /**
     *
     */
    public MediadorSeguridad() {
        logMsg = LoggerMessage.getInstancia();
    }

    /**
     *
     * @return
     */
    public static synchronized MediadorSeguridad getInstancia() {
        return instancia == null ? new MediadorSeguridad() : instancia;
    }

    /**
     *
     * @param usuario
     * @return
     */
    public UsuarioDTO consultarDatosUsuarioLogueado(String usuario) {

        DataBaseConnection dbcon = null;
        Connection conexion = null;

        UsuarioDTO datosUsuario = null;

        String mensajeError = "";
        try {

            dbcon = DataBaseConnection.getInstance();
            conexion = dbcon.getConnection(ContextDataResourceNames.MYSQL_ELTORNEO_JDBC);
            datosUsuario = new UsuariosDAO().consultarDatosUsuarioLogueado(conexion, usuario);
            System.out.println("llego al mediador de seguridad");
            if (datosUsuario != null) {
                System.out.println("Mediador seguridad >>>>> datos de usuario logueado  " + datosUsuario.toStringJson());


                datosUsuario = new UsuariosDAO().consultarDatosUsuarioLogueado(conexion, usuario);
                datosUsuario.setDescripcionErrorLogueo(mensajeError);

            }

            // datosUsuario.setFuncionalidad(null);
        } catch (Exception e) {
            logMsg.loggerMessageException(e);
        } finally {
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                    conexion = null;
                }
            } catch (Exception e) {
                logMsg.loggerMessageException(e);
            }
        }

        return datosUsuario;
    }

    public UsuarioDTO consultaUsuarioPorId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public UsuarioDTO consultarUsuarioLogeado(String usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * LOS METODOS APARTIR DE AQUI NO HAN SIDO VALIDADOS
     * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
}
