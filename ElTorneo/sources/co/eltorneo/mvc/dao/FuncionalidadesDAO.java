/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.eltorneo.mvc.dao;

import co.eltorneo.common.util.AsignaAtributoStatement;
import co.eltorneo.common.util.LoggerMessage;
import co.eltorneo.mvc.dto.FuncionalidadDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class FuncionalidadesDAO {

    public ArrayList<FuncionalidadDTO> listarFuncionalidadesPorMenu(Connection conexion, String idMenu, String idUsuario) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<FuncionalidadDTO> listado = null;
        FuncionalidadDTO datos = null;
        StringBuilder cadSQL = null;

        try {
            cadSQL = new StringBuilder();
            cadSQL.append(" SELECT DISTINCT usfu.usua_id,usfu.pagi_id,func.pagi_pagina,func.pagi_titulo,func.pagi_icono ");
            cadSQL.append(" FROM  usuario_paginas usfu   ");
            cadSQL.append(" INNER JOIN pagina func ON func.pagi_id = usfu.pagi_id   ");
            cadSQL.append(" INNER JOIN menu menu ON menu.menu_id = func.menu_id    ");
            cadSQL.append(" WHERE usfu.usua_id = ? and menu.menu_id = ? ");

            ps = conexion.prepareStatement(cadSQL.toString());

            AsignaAtributoStatement.setString(1, idUsuario, ps);
            AsignaAtributoStatement.setString(2, idMenu, ps);
            rs = ps.executeQuery();
            listado = new ArrayList();
            while (rs.next()) {
                datos = new FuncionalidadDTO();
                datos.setId(rs.getString("pagi_id"));
                datos.setPagina(rs.getString("pagi_pagina"));
                datos.setTitulo(rs.getString("pagi_titulo"));
                datos.setIcono(rs.getString("pagi_icono"));
                listado.add(datos);
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
                if (listado != null && listado.isEmpty()) {
                    listado = null;
                }
            } catch (Exception e) {
                LoggerMessage.getInstancia().loggerMessageException(e);
                return null;
            }
        }
        return listado;
    }

}
