/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.eltorneo.mvc.dao;

import co.eltorneo.common.util.AsignaAtributoStatement;
import co.eltorneo.common.util.LoggerMessage;
import co.eltorneo.mvc.dto.ArbitroDTO;
import co.eltorneo.mvc.dto.RespuestaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class ArbitroDAO {

    /**
     *
     * @param conexion
     * @param arbitro
     * @return
     * @throws SQLException
     */
    public RespuestaDTO registrarArbitro(Connection conexion, ArbitroDTO arbitro) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int nRows = 0;
        StringBuilder cadSQL = null;
        RespuestaDTO registro = null;

        try {
            registro = new RespuestaDTO();
            System.out.println("tecnico " + arbitro.toStringJson());
            cadSQL = new StringBuilder();
            cadSQL.append(" INSERT INTO arbitro(arbi_nombre, arbi_apellido, arbi_telefono, arbi_celular,usua_id,arbi_documento)");
            cadSQL.append(" VALUES (?, ?, ?, ?, ?, ?) ");

            ps = conexion.prepareStatement(cadSQL.toString(), Statement.RETURN_GENERATED_KEYS);

            AsignaAtributoStatement.setString(1, arbitro.getNombres(), ps);
            AsignaAtributoStatement.setString(2, arbitro.getApellidos(), ps);
            AsignaAtributoStatement.setString(3, arbitro.getTelefono(), ps);
            AsignaAtributoStatement.setString(4, arbitro.getCelular(), ps);
            AsignaAtributoStatement.setString(5, arbitro.getIdUsuario(), ps);
            AsignaAtributoStatement.setString(6, arbitro.getDocumento(), ps);

            nRows = ps.executeUpdate();
            if (nRows > 0) {
                rs = ps.getGeneratedKeys();
                registro.setRegistro(true);
                if (rs.next()) {
                    registro.setIdResgistrado(rs.getString(1));

                }
                rs.close();
                rs = null;
            }
            ps.close();
            ps = null;

        } catch (SQLException se) {
            LoggerMessage.getInstancia().loggerMessageException(se);
            return null;
        }
        return registro;
    }

    /**
     *
     * @param conexion
     * @return
     */
    public ArrayList<ArbitroDTO> listarArbitros(Connection conexion) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ArbitroDTO> listadoArbitro = null;
        ArbitroDTO arbitro = null;
        StringBuilder cadSQL = null;

        try {

            cadSQL = new StringBuilder();
            cadSQL.append(" SELECT arbi_id, arbi_nombre, arbi_apellido,arbi_telefono,arbi_celular,arbi_documento");
            cadSQL.append(" FROM arbitro ");
            ps = conexion.prepareStatement(cadSQL.toString());

            rs = ps.executeQuery();

            listadoArbitro = new ArrayList();

            while (rs.next()) {
                arbitro = new ArbitroDTO();
                arbitro.setId(rs.getString("arbi_id"));
                arbitro.setNombres(rs.getString("arbi_nombre"));
                arbitro.setApellidos(rs.getString("arbi_apellido"));
                arbitro.setTelefono(rs.getString("arbi_apellido"));
                arbitro.setCelular(rs.getString("arbi_celular"));
                arbitro.setDocumento(rs.getString("arbi_documento"));

                listadoArbitro.add(arbitro);

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
                if (listadoArbitro != null && listadoArbitro.isEmpty()) {
                    listadoArbitro = null;
                }
            } catch (Exception e) {
                LoggerMessage.getInstancia().loggerMessageException(e);
                return null;
            }
        }

        return listadoArbitro;
    }
}
