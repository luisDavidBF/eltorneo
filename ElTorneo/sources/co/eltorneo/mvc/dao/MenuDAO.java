/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.eltorneo.mvc.dao;

import co.eltorneo.common.util.AsignaAtributoStatement;
import co.eltorneo.common.util.LoggerMessage;
import co.eltorneo.mvc.dto.MenuDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class MenuDAO {

    public ArrayList<MenuDTO> listarMenusPorUsuario(Connection conexion, String idUsuario) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<MenuDTO> listado = null;

        MenuDTO datosMenu = null;
        StringBuilder cadSQL = null;
        try {
            cadSQL = new StringBuilder();
            cadSQL.append(" SELECT DISTINCT usfu.usua_id,menu.menu_id,menu.menu_titulo,menu.menu_icono");
            cadSQL.append(" FROM  usuario_paginas usfu ");
            cadSQL.append(" INNER JOIN pagina func ON func.pagi_id = usfu.pagi_id ");
            cadSQL.append(" INNER JOIN menu menu ON menu.menu_id = func.menu_id");
            cadSQL.append(" WHERE usfu.usua_id = ?");
            
            ps = conexion.prepareStatement(cadSQL.toString());
            AsignaAtributoStatement.setString(1, idUsuario, ps);

            rs = ps.executeQuery();
            listado = new ArrayList();
            while (rs.next()) {
                datosMenu = new MenuDTO();
                datosMenu.setId(rs.getString("menu_id"));
                datosMenu.setTituloMenu(rs.getString("menu_titulo"));
                datosMenu.setIconoMenu(rs.getString("menu_icono"));
                listado.add(datosMenu);
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
