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
import co.eltorneo.hilo.Correo;
import co.eltorneo.mvc.dao.ArbitroDAO;
import co.eltorneo.mvc.dao.EquipoDAO;
import co.eltorneo.mvc.dao.JugadorDAO;
import co.eltorneo.mvc.dao.TecnicoDAO;
import co.eltorneo.mvc.dao.UsuarioDAO;
import co.eltorneo.mvc.dto.ArbitroDTO;
import co.eltorneo.mvc.dto.EquipoDTO;
import co.eltorneo.mvc.dto.JugadorDTO;
import co.eltorneo.mvc.dto.RespuestaDTO;
import co.eltorneo.mvc.dto.TecnicoDTO;
import co.eltorneo.mvc.dto.UsuarioDTO;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;
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

            datosUsuario = new UsuarioDAO().recuperarContrasenia(conexion, documento);

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

    /**
     *
     * @param tecnico
     * @param usuario
     * @return
     */
    public RespuestaDTO registrarTecnico(TecnicoDTO tecnico, UsuarioDTO usuario) {

        DataBaseConnection dbcon = null;
        Connection conexion = null;
        RespuestaDTO respuesta = null, respuesta2 = new RespuestaDTO();

        String password = "";
        try {
            password = UUID.randomUUID().toString().substring(0, 8);
            usuario.setClave(password);
            dbcon = DataBaseConnection.getInstance();
            conexion = dbcon.getConnection(ContextDataResourceNames.MYSQL_ELTORNEO_JDBC);
            conexion.setAutoCommit(false);

            respuesta = new UsuarioDAO().registrarUsuario(conexion, usuario);
            if (!respuesta.isRegistro()) {
                respuesta.setMensaje("No se pudo registrar el tecnico");
                conexion.rollback();
                throw new Exception("ERROR: no se pudo registrar el tecnico");
            }
            tecnico.setIdUsuario(respuesta.getIdResgistrado());
            respuesta2 = new TecnicoDAO().registrarTecnico(conexion, tecnico);

            System.out.println("sale del dao---" + respuesta2.toStringJson());
            if (!respuesta2.isRegistro()) {
                respuesta2.setMensaje("No se pudo registrar el tecnico");
                conexion.rollback();
                throw new Exception("ERROR: no se pudo registrar el tecnico");

            }

            Correo envio = new Correo(usuario.getCorreo(), usuario.getClave(), "1", tecnico.getNombre(), "1", "ninguna");
            envio.start();

            respuesta.setMensaje("Se ha registrado el tecnico satisfactoriamente");
            conexion.commit();
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
            return respuesta;
        }

    }

    /**
     *
     * @return
     */
    public ArrayList<TecnicoDTO> listarTecnicos() {

        DataBaseConnection dbcon = null;
        Connection conexion = null;
        ArrayList<TecnicoDTO> tecnicos = null;
        try {

            dbcon = DataBaseConnection.getInstance();
            conexion = dbcon.getConnection(ContextDataResourceNames.MYSQL_ELTORNEO_JDBC);

            tecnicos = new TecnicoDAO().listarTecnicos(conexion);
            if (tecnicos.isEmpty()) {
                throw new Exception("ERROR: No hay tecnicos registrados");
            }

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
            return tecnicos;
        }

    }

    /**
     *
     * @param arbitro
     * @param usuario
     * @return
     */
    public RespuestaDTO registrarArbitro(ArbitroDTO arbitro, UsuarioDTO usuario) {

        DataBaseConnection dbcon = null;
        Connection conexion = null;
        RespuestaDTO respuesta = null, respuesta2 = new RespuestaDTO();

        String password = "";
        try {
            password = UUID.randomUUID().toString().substring(0, 8);
            usuario.setClave(password);
            dbcon = DataBaseConnection.getInstance();
            conexion = dbcon.getConnection(ContextDataResourceNames.MYSQL_ELTORNEO_JDBC);
            conexion.setAutoCommit(false);

            respuesta = new UsuarioDAO().registrarUsuario(conexion, usuario);
            if (!respuesta.isRegistro()) {
                respuesta.setMensaje("No se pudo registrar el usuario");
                conexion.rollback();
                throw new Exception("ERROR: no se pudo registrar el usuario");
            }
            arbitro.setIdUsuario(respuesta.getIdResgistrado());
            respuesta2 = new ArbitroDAO().registrarArbitro(conexion, arbitro);

            if (!respuesta2.isRegistro()) {
                respuesta2.setMensaje("No se pudo registrar el arbitro");
                conexion.rollback();
                throw new Exception("ERROR: no se pudo registrar el arbitro");

            }

            Correo envio = new Correo(usuario.getCorreo(), usuario.getClave(), "1", arbitro.getNombres(), "1", "ninguna");
            envio.start();

            respuesta.setMensaje("Se ha registrado el arbitro satisfactoriamente");
            conexion.commit();
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
            return respuesta;
        }

    }

    /**
     *
     * @return
     */
    public ArrayList<ArbitroDTO> listarArbitros() {

        DataBaseConnection dbcon = null;
        Connection conexion = null;
        ArrayList<ArbitroDTO> arbitros = null;
        try {

            dbcon = DataBaseConnection.getInstance();
            conexion = dbcon.getConnection(ContextDataResourceNames.MYSQL_ELTORNEO_JDBC);

            arbitros = new ArbitroDAO().listarArbitros(conexion);
            if (arbitros.isEmpty()) {
                throw new Exception("ERROR: No hay arbitros registrados");
            }

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
            return arbitros;
        }

    }

    /**
     *
     * @param equipo
     * @return
     */
    public RespuestaDTO registrarEquipo(EquipoDTO equipo) {

        DataBaseConnection dbcon = null;
        Connection conexion = null;
        RespuestaDTO respuesta = null;

        try {
            dbcon = DataBaseConnection.getInstance();
            conexion = dbcon.getConnection(ContextDataResourceNames.MYSQL_ELTORNEO_JDBC);

            respuesta = new EquipoDAO().registrarEquipo(conexion, equipo);
            if (!respuesta.isRegistro()) {
                respuesta.setMensaje("No se pudo registrar el equipo");
                conexion.rollback();
                throw new Exception("ERROR: no se pudo registrar el equipo");
            }

            respuesta.setMensaje("Se ha registrado el equipo satisfactoriamente");
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
            return respuesta;
        }

    }

    /**
     *
     * @return
     */
    public ArrayList<EquipoDTO> listarEquipos() {

        DataBaseConnection dbcon = null;
        Connection conexion = null;
        ArrayList<EquipoDTO> equipos = null;
        try {

            dbcon = DataBaseConnection.getInstance();
            conexion = dbcon.getConnection(ContextDataResourceNames.MYSQL_ELTORNEO_JDBC);

            equipos = new EquipoDAO().listarEquipos(conexion);
            if (equipos.isEmpty()) {
                throw new Exception("ERROR: No hay arbitros registrados");
            }

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
            return equipos;
        }

    }

    /**
     *
     * @param jugador
     * @param usuario
     * @return
     */
    public RespuestaDTO registrarJugador(JugadorDTO jugador, UsuarioDTO usuario) {

        DataBaseConnection dbcon = null;
        Connection conexion = null;
        RespuestaDTO respuesta = null, respuesta2 = new RespuestaDTO();

        String password = "";
        try {
            password = UUID.randomUUID().toString().substring(0, 8);
            usuario.setClave(password);
            dbcon = DataBaseConnection.getInstance();
            conexion = dbcon.getConnection(ContextDataResourceNames.MYSQL_ELTORNEO_JDBC);
            conexion.setAutoCommit(false);

            respuesta = new UsuarioDAO().registrarUsuario(conexion, usuario);
            if (!respuesta.isRegistro()) {
                respuesta.setMensaje("No se pudo registrar el usuario");
                conexion.rollback();
                throw new Exception("ERROR: no se pudo registrar el usuario");
            }
            jugador.setIdUsuario(respuesta.getIdResgistrado());
            respuesta2 = new JugadorDAO().registrarJugador(conexion, jugador);

            if (!respuesta2.isRegistro()) {
                respuesta2.setMensaje("No se pudo registrar el jugador");
                conexion.rollback();
                throw new Exception("ERROR: no se pudo registrar el jugador");

            }

            Correo envio = new Correo(usuario.getCorreo(), usuario.getClave(), "1", jugador.getNombre(), "1", "ninguna");
            envio.start();

            respuesta.setMensaje("Se ha registrado el jugador satisfactoriamente");
            conexion.commit();
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
            return respuesta;
        }

    }

    /**
     *
     * @return
     */
    public ArrayList<JugadorDTO> listarJugadores() {

        DataBaseConnection dbcon = null;
        Connection conexion = null;
        ArrayList<JugadorDTO> jugadores = null;
        try {

            dbcon = DataBaseConnection.getInstance();
            conexion = dbcon.getConnection(ContextDataResourceNames.MYSQL_ELTORNEO_JDBC);

            jugadores = new JugadorDAO().listarJugadores(conexion);
            if (jugadores.isEmpty()) {
                throw new Exception("ERROR: No hay arbitros registrados");
            }

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
            return jugadores;
        }

    }
}
