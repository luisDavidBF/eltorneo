/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.eltorneo.mvc.dao;

import co.eltorneo.common.util.AsignaAtributoStatement;
import co.eltorneo.common.util.LoggerMessage;
import co.eltorneo.mvc.dto.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.directwebremoting.WebContextFactory;

/**
 *
 * @author Administrator
 */
public class UsuariosDAO {

    /**
     *
     * @param conexion
     * @param usuario
     * @return
     */
    public UsuarioDTO consultarUsuarioPorId(Connection conexion, String usuario) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        UsuarioDTO datosUsuario = null;
        StringBuilder cadSQL = null;

        try {

            cadSQL = new StringBuilder();
            cadSQL.append(" SELECT usua.usua_id, usua.usua_nombre,  usua.usua_telefono, usua.usua_direccion, usua.usua_documento, usua.usua_correo, "
                    + " usua.usua_imagenperfil, usse.usse_usuario, usse.usse_clave, tido.tido_id, tido.tido_descripcion, rol.tiro_id, rol.tiro_descripcion, tius.tius_id, "
                    + " tius.tius_descripcion, muni.muni_id, muni.muni_nombre, muni.depa_id ");
            cadSQL.append(" FROM pindoctor.usuario usua ");
            cadSQL.append(" INNER JOIN pindoctor.usuario_seguridad usse ON usse.usua_id = usua.usua_id  ");
            cadSQL.append(" INNER JOIN pindoctor.tipo_documento tido ON tido.tido_id = usua.tido_id ");
            cadSQL.append(" INNER JOIN pindoctor.tipo_rol rol ON usua.tiro_id = rol.tiro_id ");
            cadSQL.append(" INNER JOIN pindoctor.tipo_usuario tius ON usua.tius_id = tius.tius_id ");
            cadSQL.append(" INNER JOIN pindoctor.municipio muni ON usua.muni_id = muni.muni_id ");
            cadSQL.append(" WHERE usua.usua_id  = ? ");

            ps = conexion.prepareStatement(cadSQL.toString());
            AsignaAtributoStatement.setString(1, usuario, ps);
            rs = ps.executeQuery();

            if (rs.next()) {
                datosUsuario = new UsuarioDTO();
                datosUsuario.setIdUsuario(rs.getString("usua_id"));
                datosUsuario.setNombre(rs.getString("usua_nombre"));
                datosUsuario.setTelefono(rs.getString("usua_telefono"));
                datosUsuario.setDireccion(rs.getString("usua_direccion"));
                datosUsuario.setDocumento(rs.getString("usua_documento"));
                datosUsuario.setCorreo(rs.getString("usua_correo"));
                datosUsuario.setImagenPerfil(rs.getString("usua_imagenperfil"));
                datosUsuario.setUsuario(rs.getString("usse_usuario"));
                datosUsuario.setClave(rs.getString("usse_clave"));
                datosUsuario.setIdTipoDocumento(rs.getString("tido_id"));
                datosUsuario.setTipoDocumento(rs.getString("tido_descripcion"));
                datosUsuario.setIdTipoRol(rs.getString("tiro_id"));
                datosUsuario.setRol(rs.getString("tiro_descripcion"));
                datosUsuario.setIdTipoUsuario(rs.getString("tius_id"));
                datosUsuario.setTipoUsuario(rs.getString("tius_descripcion"));
                datosUsuario.setIdMunicipio(rs.getString("muni_id"));
                datosUsuario.setMunicipio(rs.getString("muni_nombre"));
                datosUsuario.setIdDepartamento(rs.getString("depa_id"));
            }

            ps.close();
            ps = null;

        } catch (Exception e) {
            LoggerMessage.getInstancia().loggerMessageException(e);
            return null;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
            } catch (Exception e) {
                LoggerMessage.getInstancia().loggerMessageException(e);
                return null;
            }
        }

        return datosUsuario;
    }

    /**
     *
     * @param conexion
     * @param documento
     * @return
     */
    public UsuarioDTO consultarUsuarioPorCedula(Connection conexion, String documento) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        UsuarioDTO datosUsuario = null;
        StringBuilder cadSQL = null;

        try {

            cadSQL = new StringBuilder();
            cadSQL.append(" SELECT usua.usua_id, usua.usua_nombre,  usua.usua_telefono, usua.usua_direccion, usua.usua_documento, usua.usua_correo, "
                    + " usua.usua_imagenperfil, usse.usse_usuario, usse.usse_clave, tido.tido_id, tido.tido_descripcion, rol.tiro_id, rol.tiro_descripcion, tius.tius_id, "
                    + " tius.tius_descripcion, muni.muni_id, muni.muni_nombre, muni.depa_id ");
            cadSQL.append(" FROM pindoctor.usuario usua ");
            cadSQL.append(" INNER JOIN pindoctor.usuario_seguridad usse ON usse.usua_id = usua.usua_id  ");
            cadSQL.append(" INNER JOIN pindoctor.tipo_documento tido ON tido.tido_id = usua.tido_id ");
            cadSQL.append(" INNER JOIN pindoctor.tipo_rol rol ON usua.tiro_id = rol.tiro_id ");
            cadSQL.append(" INNER JOIN pindoctor.tipo_usuario tius ON usua.tius_id = tius.tius_id ");
            cadSQL.append(" INNER JOIN pindoctor.municipio muni ON usua.muni_id = muni.muni_id ");
            cadSQL.append(" WHERE usua.usua_documento  = ? ");

            ps = conexion.prepareStatement(cadSQL.toString());
            AsignaAtributoStatement.setString(1, documento, ps);
            rs = ps.executeQuery();

            if (rs.next()) {
                datosUsuario = new UsuarioDTO();
                datosUsuario.setIdUsuario(rs.getString("usua_id"));
                datosUsuario.setNombre(rs.getString("usua_nombre"));
                datosUsuario.setTelefono(rs.getString("usua_telefono"));
                datosUsuario.setDireccion(rs.getString("usua_direccion"));
                datosUsuario.setDocumento(rs.getString("usua_documento"));
                datosUsuario.setCorreo(rs.getString("usua_correo"));
                datosUsuario.setImagenPerfil(rs.getString("usua_imagenperfil"));
                datosUsuario.setUsuario(rs.getString("usse_usuario"));
                datosUsuario.setClave(rs.getString("usse_clave"));
                datosUsuario.setIdTipoDocumento(rs.getString("tido_id"));
                datosUsuario.setTipoDocumento(rs.getString("tido_descripcion"));
                datosUsuario.setIdTipoRol(rs.getString("tiro_id"));
                datosUsuario.setRol(rs.getString("tiro_descripcion"));
                datosUsuario.setIdTipoUsuario(rs.getString("tius_id"));
                datosUsuario.setTipoUsuario(rs.getString("tius_descripcion"));
                datosUsuario.setIdMunicipio(rs.getString("muni_id"));
                datosUsuario.setMunicipio(rs.getString("muni_nombre"));
                datosUsuario.setIdDepartamento(rs.getString("depa_id"));
            }
            ps.close();
            ps = null;

        } catch (Exception e) {
            LoggerMessage.getInstancia().loggerMessageException(e);
            return null;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
            } catch (Exception e) {
                LoggerMessage.getInstancia().loggerMessageException(e);
                return null;
            }
        }

        return datosUsuario;
    }

    /**
     *
     * @param conexion
     * @return
     */
    public ArrayList<UsuarioDTO> listarClientes(Connection conexion) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<UsuarioDTO> listarClientes = null;
        UsuarioDTO datosUsuario = null;

        StringBuilder cadSQL = null;

        try {

            cadSQL = new StringBuilder();
            cadSQL.append(" SELECT usua.usua_id, usua.usua_nombre,  usua.usua_telefono, usua.usua_direccion, usua.usua_documento, usua.usua_correo, "
                    + " usua.usua_imagenperfil, usse.usse_usuario, usse.usse_clave, tido.tido_id, tido.tido_descripcion, rol.tiro_id, rol.tiro_descripcion, tius.tius_id, "
                    + " tius.tius_descripcion, muni.muni_id, muni.muni_nombre, muni.depa_id ");
            cadSQL.append(" FROM pindoctor.usuario usua ");
            cadSQL.append(" INNER JOIN pindoctor.usuario_seguridad usse ON usse.usua_id = usua.usua_id  ");
            cadSQL.append(" INNER JOIN pindoctor.tipo_documento tido ON tido.tido_id = usua.tido_id ");
            cadSQL.append(" INNER JOIN pindoctor.tipo_rol rol ON usua.tiro_id = rol.tiro_id ");
            cadSQL.append(" INNER JOIN pindoctor.tipo_usuario tius ON usua.tius_id = tius.tius_id ");
            cadSQL.append(" INNER JOIN pindoctor.municipio muni ON usua.muni_id = muni.muni_id ");

            ps = conexion.prepareStatement(cadSQL.toString());

            rs = ps.executeQuery();

            listarClientes = new ArrayList();

            while (rs.next()) {

                datosUsuario = new UsuarioDTO();
                datosUsuario.setIdUsuario(rs.getString("usua_id"));
                datosUsuario.setNombre(rs.getString("usua_nombre"));
                datosUsuario.setTelefono(rs.getString("usua_telefono"));
                datosUsuario.setDireccion(rs.getString("usua_direccion"));
                datosUsuario.setDocumento(rs.getString("usua_documento"));
                datosUsuario.setCorreo(rs.getString("usua_correo"));
                datosUsuario.setImagenPerfil(rs.getString("usua_imagenperfil"));
                datosUsuario.setUsuario(rs.getString("usse_usuario"));
                datosUsuario.setClave(rs.getString("usse_clave"));
                datosUsuario.setIdTipoDocumento(rs.getString("tido_id"));
                datosUsuario.setTipoDocumento(rs.getString("tido_descripcion"));
                datosUsuario.setIdTipoRol(rs.getString("tiro_id"));
                datosUsuario.setRol(rs.getString("tiro_descripcion"));
                datosUsuario.setIdTipoUsuario(rs.getString("tius_id"));
                datosUsuario.setTipoUsuario(rs.getString("tius_descripcion"));
                datosUsuario.setIdMunicipio(rs.getString("muni_id"));
                datosUsuario.setMunicipio(rs.getString("muni_nombre"));
                datosUsuario.setIdDepartamento(rs.getString("depa_id"));

                listarClientes.add(datosUsuario);

            }
            ps.close();
            ps = null;

        } catch (Exception e) {
            LoggerMessage.getInstancia().loggerMessageException(e);
            return null;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (listarClientes != null && listarClientes.isEmpty()) {
                    listarClientes = null;
                }
            } catch (Exception e) {
                LoggerMessage.getInstancia().loggerMessageException(e);
                return null;
            }
        }

        return listarClientes;
    }

    /**
     *
     * @param conexion
     * @param id
     * @return
     */
    public boolean eliminarUsuario(Connection conexion, String id) {

        PreparedStatement ps = null;
        int nRows = 0;
        boolean deleteExitoso = false;
        StringBuilder cadSQL = null;

        try {
            cadSQL = new StringBuilder();
            cadSQL.append("DELETE us,usu FROM usuario_seguridad AS usu INNER JOIN usuario AS us WHERE us.usua_id=usu.usua_id AND us.usua_id LIKE ?");
            ps = conexion.prepareStatement(cadSQL.toString(), Statement.RETURN_GENERATED_KEYS);

            AsignaAtributoStatement.setString(1, id, ps);
            nRows = ps.executeUpdate();

            if (nRows > 0) {
                deleteExitoso = true;
            }
            ps.close();
            ps = null;

        } catch (SQLException e) {
            LoggerMessage.getInstancia().loggerMessageException(e);
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
            } catch (SQLException e) {
                LoggerMessage.getInstancia().loggerMessageException(e);
                return false;
            }
        }
        return deleteExitoso;
    }

    /**
     *
     * @param conexion
     * @param datos
     * @return
     */
    public boolean cambiarImagen(Connection conexion, UsuarioDTO datos) {

        HttpSession session = WebContextFactory.get().getSession();
        PreparedStatement ps = null;
        int nRows = 0;
        StringBuilder cadSQL = null;
        boolean registroExitoso = false;

        try {

            datos = (UsuarioDTO) session.getAttribute("datosUsuario");

            cadSQL = new StringBuilder();
            cadSQL.append("UPDATE usuario SET usua_imagenperfil = ? WHERE  usua_id = ?");

            ps = conexion.prepareStatement(cadSQL.toString(), Statement.RETURN_GENERATED_KEYS);
            AsignaAtributoStatement.setString(1, datos.getImagenPerfil(), ps);
            AsignaAtributoStatement.setString(2, datos.getIdUsuario(), ps);

            nRows = ps.executeUpdate();
            if (nRows > 0) {
                registroExitoso = true;
            }

        } catch (SQLException se) {
            LoggerMessage.getInstancia().loggerMessageException(se);
            return false;
        }
        return registroExitoso;
    }

    /**
     *
     * @param conexion
     * @return
     */
    public ArrayList<UsuarioDTO> listarUsuarios(Connection conexion) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<UsuarioDTO> listarUsuarios = null;
        UsuarioDTO datosUsuario = null;

        StringBuilder cadSQL = null;

        try {

            cadSQL = new StringBuilder();
            cadSQL.append(" SELECT usua.usua_id, usua.usua_nombre,  usua.usua_telefono, usua.usua_direccion, usua.usua_cedula, usua.usua_correo, rol.rol_descripcion, muni.muni_nombre, depa.depa_nombre");
            cadSQL.append(" FROM usuario usua ");
            cadSQL.append(" INNER JOIN rol rol ON usua.rol_id = rol.rol_id ");
            cadSQL.append(" INNER JOIN municipio muni ON usua.muni_id = muni.muni_id ");
            cadSQL.append(" INNER JOIN departamento depa ON muni.depa_id = depa.depa_id ");

            ps = conexion.prepareStatement(cadSQL.toString());

            rs = ps.executeQuery();

            listarUsuarios = new ArrayList();

            while (rs.next()) {

                datosUsuario = new UsuarioDTO();
                datosUsuario.setIdUsuario(rs.getString("usua_id"));
                datosUsuario.setNombre(rs.getString("usua_nombre"));
                datosUsuario.setTelefono(rs.getString("usua_telefono"));
                datosUsuario.setDireccion(rs.getString("usua_direccion"));
                datosUsuario.setDocumento(rs.getString("usua_cedula"));
                datosUsuario.setCorreo(rs.getString("usua_correo"));
                datosUsuario.setRol(rs.getString("rol_descripcion"));
                datosUsuario.setMunicipio(rs.getString("muni_nombre"));
                datosUsuario.setDepartamento(rs.getString("depa_nombre"));

                listarUsuarios.add(datosUsuario);

            }
            ps.close();
            ps = null;

        } catch (Exception e) {
            LoggerMessage.getInstancia().loggerMessageException(e);
            return null;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (listarUsuarios != null && listarUsuarios.isEmpty()) {
                    listarUsuarios = null;
                }
            } catch (Exception e) {
                LoggerMessage.getInstancia().loggerMessageException(e);
                return null;
            }
        }

        return listarUsuarios;
    }

    /**
     *
     * @param conexion
     * @param idCliente
     * @return
     */
    public ArrayList<UsuarioDTO> listarUsuariosAdministrador(Connection conexion, String idCliente) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<UsuarioDTO> listarUsuariosAdministrador = null;
        UsuarioDTO datosUsuario = null;

        StringBuilder cadSQL = null;

        try {

            cadSQL = new StringBuilder();
            cadSQL.append(" SELECT usua.usua_id,usua.usua_usuario, usua.usua_nombre, usua.usua_imagen, usua.usua_telefono,usua.usua_estado, usua.tido_id,usua.usua_direccion, usua.usua_cedula, usua.usua_correo, rol.rol_id, rol.rol_descripcion,muni.muni_id, depa.depa_nombre,depa.depa_id");
            cadSQL.append(" FROM usuario usua ");
            cadSQL.append(" INNER JOIN rol rol ON usua.rol_id = rol.rol_id ");
            cadSQL.append(" INNER JOIN municipio muni ON usua.muni_id = muni.muni_id ");
            cadSQL.append(" INNER JOIN tipo_documento tido ON tido.tido_id = usua.tido_id ");

            cadSQL.append(" INNER JOIN departamento depa ON muni.depa_id = depa.depa_id ");
            cadSQL.append(" WHERE usua.clie_id=? ");
            // cadSQL.append(" WHERE usua.rol_id != 1 and usua.clie_id=? ");

            ps = conexion.prepareStatement(cadSQL.toString());

            AsignaAtributoStatement.setString(1, idCliente, ps);

            rs = ps.executeQuery();

            listarUsuariosAdministrador = new ArrayList();

            while (rs.next()) {

                datosUsuario = new UsuarioDTO();
                datosUsuario.setIdUsuario(rs.getString("usua_id"));
                datosUsuario.setNombre(rs.getString("usua_nombre"));
                datosUsuario.setTelefono(rs.getString("usua_telefono"));
                datosUsuario.setDireccion(rs.getString("usua_direccion"));
                datosUsuario.setDocumento(rs.getString("usua_cedula"));
                datosUsuario.setCorreo(rs.getString("usua_correo"));
                datosUsuario.setRol(rs.getString("rol_descripcion"));
                datosUsuario.setIdTipoRol(rs.getString("rol_id"));
                datosUsuario.setIdMunicipio(rs.getString("muni_id"));
                datosUsuario.setIdDepartamento(rs.getString("depa_id"));
                datosUsuario.setIdTipoDocumento(rs.getString("tido_id"));
                datosUsuario.setEstado(rs.getString("usua_estado"));
                datosUsuario.setImagenPerfil(rs.getString("usua_imagen"));
                datosUsuario.setUsuario(rs.getString("usua_usuario"));

                listarUsuariosAdministrador.add(datosUsuario);

            }
            ps.close();
            ps = null;

        } catch (Exception e) {
            LoggerMessage.getInstancia().loggerMessageException(e);
            return null;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (listarUsuariosAdministrador != null && listarUsuariosAdministrador.isEmpty()) {
                    listarUsuariosAdministrador = null;
                }
            } catch (Exception e) {
                LoggerMessage.getInstancia().loggerMessageException(e);
                return null;
            }
        }

        return listarUsuariosAdministrador;
    }

    /**
     * @param conexion
     * @param correo
     * @param clave
     * @return
     */
    public boolean actualizarClave(Connection conexion, String correo, String clave) {

        PreparedStatement ps = null;
        int nRows = 0;
        StringBuilder cadSQL = null;
        boolean registroExitoso = false;

        try {

            cadSQL = new StringBuilder();
            cadSQL.append("UPDATE usuario SET usuario.usua_clave= SHA2(?,256) WHERE   usua_correo = ?");

            ps = conexion.prepareStatement(cadSQL.toString(), Statement.RETURN_GENERATED_KEYS);
            AsignaAtributoStatement.setString(1, clave, ps);
            AsignaAtributoStatement.setString(2, correo, ps);

            nRows = ps.executeUpdate();
            if (nRows > 0) {
                registroExitoso = true;
            }
        } catch (SQLException se) {
            LoggerMessage.getInstancia().loggerMessageException(se);
            return false;
        }
        return registroExitoso;
    }

    /**
     *
     * @param conexion
     * @param usuario
     * @return
     * @throws java.sql.SQLException *
     */
    public boolean registrarUsuario(Connection conexion, UsuarioDTO usuario) throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        int nRows = 0;
        StringBuilder cadSQL = null;
        boolean registroExitoso = false;

        try {

            System.out.println("usuario " + usuario.toStringJson());
            cadSQL = new StringBuilder();
            cadSQL.append(" INSERT INTO usuario( usua_nombre, usua_telefono, usua_direccion, usua_cedula, usua_correo, clie_id, usua_usuario, usua_clave, muni_id, rol_id, tido_id)");
            cadSQL.append(" VALUES (?, ?, ?, ?, ?, ?, ?,  SHA2(?,256),?, ?, ?) ");

            ps = conexion.prepareStatement(cadSQL.toString(), Statement.RETURN_GENERATED_KEYS);

            AsignaAtributoStatement.setString(1, usuario.getNombre(), ps);
            AsignaAtributoStatement.setString(2, usuario.getTelefono(), ps);
            AsignaAtributoStatement.setString(3, usuario.getDireccion(), ps);
            AsignaAtributoStatement.setString(4, usuario.getDocumento(), ps);
            AsignaAtributoStatement.setString(5, usuario.getCorreo(), ps);
            AsignaAtributoStatement.setString(6, usuario.getIdCliente(), ps);
            AsignaAtributoStatement.setString(7, usuario.getUsuario(), ps);
            AsignaAtributoStatement.setString(8, usuario.getClave(), ps);
            AsignaAtributoStatement.setString(9, usuario.getMunicipio(), ps);
            AsignaAtributoStatement.setString(10, usuario.getIdTipoRol(), ps);
            AsignaAtributoStatement.setString(11, usuario.getTipoDocumento(), ps);

            nRows = ps.executeUpdate();
            if (nRows > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    registroExitoso = true;
                    usuario.setIdUsuario(rs.getString(1));

                }
                rs.close();
                rs = null;
            }
        } catch (SQLException se) {
            LoggerMessage.getInstancia().loggerMessageException(se);
            return false;
        }
        return registroExitoso;
    }

    /**
     * @param conexion
     * @param datosUsuario
     * @return
     */
    public boolean actualizarUsuario(Connection conexion, UsuarioDTO datosUsuario) {

        PreparedStatement ps = null;
        int nRows = 0;
        StringBuilder cadSQL = null;
        boolean registroExitoso = false;

        try {
            System.out.println("datos: " + datosUsuario.toStringJson());
            cadSQL = new StringBuilder();
            cadSQL.append(" UPDATE usuario SET usua_nombre = ?, usua_correo = ?, usua_telefono = ?, tido_id = ?, usua_cedula = ?, usua_direccion = ?, muni_id = ?, rol_id = ? ,usua_imagen=? WHERE   usua_id = ? ");

            ps = conexion.prepareStatement(cadSQL.toString(), Statement.RETURN_GENERATED_KEYS);

            AsignaAtributoStatement.setString(1, datosUsuario.getNombre(), ps);
            AsignaAtributoStatement.setString(2, datosUsuario.getCorreo(), ps);
            AsignaAtributoStatement.setString(3, datosUsuario.getTelefono(), ps);
            AsignaAtributoStatement.setString(4, datosUsuario.getTipoDocumento(), ps);
            AsignaAtributoStatement.setString(5, datosUsuario.getDocumento(), ps);
            AsignaAtributoStatement.setString(6, datosUsuario.getDireccion(), ps);
            AsignaAtributoStatement.setString(7, datosUsuario.getMunicipio(), ps);
            AsignaAtributoStatement.setString(8, datosUsuario.getIdTipoRol(), ps);
            AsignaAtributoStatement.setString(9, datosUsuario.getImagenPerfil(), ps);
            AsignaAtributoStatement.setString(10, datosUsuario.getIdUsuario(), ps);

            nRows = ps.executeUpdate();

            if (nRows > 0) {
                registroExitoso = true;
            }
        } catch (SQLException se) {
            LoggerMessage.getInstancia().loggerMessageException(se);
            return false;
        }
        return registroExitoso;
    }

    /**
     *
     * @param conexion
     * @param idCliente
     * @param estado
     * @return
     */
    public boolean actualizarEstadoUsuario(Connection conexion, String idCliente, String estado) {

        PreparedStatement ps = null;
        int nRows = 0;
        StringBuilder cadSQL = null;
        boolean registroExitoso = false;

        try {
            System.out.println(" >>>>>> Entra actualizarEstadoUsuario  >>>>>>");

            cadSQL = new StringBuilder();
            cadSQL.append(" UPDATE   usuario SET usuario.usua_estado =? WHERE   usuario.usua_id = ? ");
            ps = conexion.prepareStatement(cadSQL.toString());

            if (estado.equals("false")) {
                AsignaAtributoStatement.setString(1, "0", ps);
            } else {
                AsignaAtributoStatement.setString(1, "1", ps);
            }
            AsignaAtributoStatement.setString(2, idCliente, ps);
            nRows = ps.executeUpdate();
            if (nRows > 0) {
                registroExitoso = true;
                System.out.println(" >>>>>>actualización exitosa  >>>>>>");
            }
        } catch (SQLException se) {
            LoggerMessage.getInstancia().loggerMessageException(se);
            return false;
        }
        return registroExitoso;
    }

    /**
     *
     * @param conexion
     * @param usuario
     * @return
     */
    public UsuarioDTO consultarDatosUsuarioLogueado(Connection conexion, String usuario) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        UsuarioDTO datosUsuario = null;
        StringBuilder cadSQL = null;

        try {
            System.out.println("Usuario logueado : " + usuario);
            cadSQL = new StringBuilder();
            cadSQL.append(" SELECT usua_id,usua_estado,usua_correo,usua_usuario,usua_clave,tius_id");
            cadSQL.append(" FROM usuario WHERE usua_usuario = ?");

            //   cadSQL.append(" WHERE clie.clie_estado!=0 ");
            ps = conexion.prepareStatement(cadSQL.toString());
            AsignaAtributoStatement.setString(1, usuario, ps);
            rs = ps.executeQuery();

            if (rs.next()) {
                datosUsuario = new UsuarioDTO();
                datosUsuario.setIdUsuario(rs.getString("usua_id"));
                datosUsuario.setEstado(rs.getString("usua_estado"));
                datosUsuario.setCorreo(rs.getString("usua_correo"));
                datosUsuario.setUsuario(rs.getString("usua_usuario"));
                datosUsuario.setClave(rs.getString("usua_clave"));
                datosUsuario.setIdTipoUsuario(rs.getString("tius_id"));

            }
            System.out.println("y sale---" + datosUsuario.toStringJson());
            ps.close();
            ps = null;

        } catch (Exception e) {
            LoggerMessage.getInstancia().loggerMessageException(e);
            return null;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
            } catch (Exception e) {
                LoggerMessage.getInstancia().loggerMessageException(e);
                return null;
            }
        }

        return datosUsuario;
    }

    /**
     *
     * @param conexion
     * @param documento
     * @return
     */
    public UsuarioDTO recuperarContrasenia(Connection conexion, String documento) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        UsuarioDTO datosUsuario = null;
        StringBuilder cadSQL = null;

        try {
            System.out.println("#####  " + documento);

            cadSQL = new StringBuilder();
            cadSQL.append(" SELECT usua_correo  FROM usuario usua  ");
            cadSQL.append(" WHERE  usua_correo = ?");

            ps = conexion.prepareStatement(cadSQL.toString());
            AsignaAtributoStatement.setString(1, documento, ps);
            rs = ps.executeQuery();

            if (rs.next()) {
                datosUsuario = new UsuarioDTO();
                datosUsuario.setCorreo(rs.getString("usua_correo"));
                System.out.println(datosUsuario.getCorreo());
            }

            ps.close();
            ps = null;

        } catch (Exception e) {
            LoggerMessage.getInstancia().loggerMessageException(e);
            return null;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
            } catch (Exception e) {
                LoggerMessage.getInstancia().loggerMessageException(e);
                return null;
            }
        }
        return datosUsuario;
    }

    public boolean validarUsuario(Connection conexion, String usuario) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<UsuarioDTO> listarUsuarios = null;
        boolean usuarioValido = false;

        StringBuilder cadSQL = null;

        try {

            cadSQL = new StringBuilder();
            cadSQL.append(" SELECT usua.usua_id,  usua.usua_usuario");
            cadSQL.append(" FROM usuario usua ");

            cadSQL.append(" WHERE  usua.usua_usuario=? ");

            ps = conexion.prepareStatement(cadSQL.toString());
            AsignaAtributoStatement.setString(1, usuario, ps);

            rs = ps.executeQuery();

            listarUsuarios = new ArrayList();

            while (rs.next()) {
                usuarioValido = true;
            }
            ps.close();
            ps = null;

        } catch (Exception e) {
            LoggerMessage.getInstancia().loggerMessageException(e);
            return usuarioValido;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (listarUsuarios != null && listarUsuarios.isEmpty()) {
                    listarUsuarios = null;
                }
            } catch (Exception e) {
                LoggerMessage.getInstancia().loggerMessageException(e);
                return usuarioValido;
            }
        }

        return usuarioValido;
    }

    public boolean cambiarClave(Connection conexion, String claveActual, String claveNueva, String idUsuario) {

        PreparedStatement ps = null;
        int nRows = 0;
        StringBuilder cadSQL = null;
        boolean registroExitoso = false;

        try {

            cadSQL = new StringBuilder();
            cadSQL.append("UPDATE usuario SET usuario.usua_clave= SHA2(?,256) WHERE  usuario.usua_id=?  and usuario.usua_clave=SHA2(?,256) ");

            ps = conexion.prepareStatement(cadSQL.toString(), Statement.RETURN_GENERATED_KEYS);
            AsignaAtributoStatement.setString(1, claveNueva, ps);
            AsignaAtributoStatement.setString(2, idUsuario, ps);
            AsignaAtributoStatement.setString(3, claveActual, ps);

            nRows = ps.executeUpdate();
            if (nRows > 0) {
                registroExitoso = true;
            }
        } catch (SQLException se) {
            LoggerMessage.getInstancia().loggerMessageException(se);
            return false;
        }
        return registroExitoso;
    }

}
