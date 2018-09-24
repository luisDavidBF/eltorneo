/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.eltorneo.mvc.mediador;

import co.eltorneo.common.connection.ContextDataResourceNames;
import co.eltorneo.common.connection.DataBaseConnection;
import co.eltorneo.common.util.Constantes;
import co.eltorneo.common.util.Formato;
import co.eltorneo.common.util.LoggerMessage;
import co.eltorneo.mvc.dao.UsuariosDAO;
import co.eltorneo.mvc.dto.UsuarioDTO;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.directwebremoting.WebContextFactory;

/**
 *
 * @author DANNY
 */
public class MediadorElTorneo {

    /**
     *
     */
    private final static MediadorElTorneo instancia = null;
    private final LoggerMessage logMsg;

    /**
     *
     */
    public MediadorElTorneo() {
        logMsg = LoggerMessage.getInstancia();
    }

    /**
     *
     * @return
     */
    public static synchronized MediadorElTorneo getInstancia() {
        return instancia == null ? new MediadorElTorneo() : instancia;
    }

    /**
     *
     * @param documento
     * @return
     */
    public UsuarioDTO recuperarContrasenia(String documento) {

        DataBaseConnection dbcon = null;
        Connection conexion = null;
        UsuarioDTO datosUsuario = null;

        try {

            dbcon = DataBaseConnection.getInstance();
            conexion = dbcon.getConnection(ContextDataResourceNames.MYSQL_ELTORNEO_JDBC);

            datosUsuario = new UsuariosDAO().recuperarContrasenia(conexion, documento);

            conexion.close();
            conexion = null;
        } catch (Exception e) {
            LoggerMessage.getInstancia().loggerMessageException(e);
        } finally {
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                    conexion = null;
                }
            } catch (Exception e) {
                LoggerMessage.getInstancia().loggerMessageException(e);
            } finally {
                try {
                    if (conexion != null && !conexion.isClosed()) {
                        conexion.close();
                        conexion = null;
                    }

                } catch (Exception e) {
                    LoggerMessage.getInstancia().loggerMessageException(e);
                }
            }
            return datosUsuario;
        }

    }
}
