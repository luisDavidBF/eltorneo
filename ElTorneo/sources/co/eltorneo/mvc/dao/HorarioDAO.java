/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.eltorneo.mvc.dao;

import co.eltorneo.common.util.LoggerMessage;
import co.eltorneo.mvc.dto.EquipoDTO;
import co.eltorneo.mvc.dto.HorarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class HorarioDAO {
    
        public ArrayList<HorarioDTO> listarHorarios(Connection conexion) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<HorarioDTO> horarios = null;
        HorarioDTO horario = null;
        StringBuilder cadSQL = null;

        try {
            cadSQL = new StringBuilder();
            cadSQL.append(" SELECT hopa_id");
            cadSQL.append(" FROM horario_partido ");
            
            ps = conexion.prepareStatement(cadSQL.toString());
            rs = ps.executeQuery();

            horarios = new ArrayList();

            while (rs.next()) {
                horario = new HorarioDTO();
                horario.setId(rs.getString("hopa_id"));
                horarios.add(horario);
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
                if (horarios != null && horarios.isEmpty()) {
                    horarios = null;
                }
            } catch (Exception e) {
                LoggerMessage.getInstancia().loggerMessageException(e);
                return null;
            }
        }

        return horarios;
    }
}
