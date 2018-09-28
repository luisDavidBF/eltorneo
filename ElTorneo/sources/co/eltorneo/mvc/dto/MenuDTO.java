/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.eltorneo.mvc.dto;

import co.eltorneo.common.util.Generales;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class MenuDTO implements Serializable {
    
    String id = Generales.EMPTYSTRING;    
    String tituloMenu = Generales.EMPTYSTRING;
    String iconoMenu = Generales.EMPTYSTRING;
    String fechaRegistro = Generales.EMPTYSTRING;
    ArrayList<FuncionalidadDTO> funcionalidad = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTituloMenu() {
        return tituloMenu;
    }

    public void setTituloMenu(String tituloMenu) {
        this.tituloMenu = tituloMenu;
    }

    public String getIconoMenu() {
        return iconoMenu;
    }

    public void setIconoMenu(String iconoMenu) {
        this.iconoMenu = iconoMenu;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public ArrayList<FuncionalidadDTO> getFuncionalidad() {
        return funcionalidad;
    }

    public void setFuncionalidad(ArrayList<FuncionalidadDTO> funcionalidad) {
        this.funcionalidad = funcionalidad;
    }
    
    public String toStringJson() {
        String dtoJsonString = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            dtoJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (Exception e) {
        }
        return dtoJsonString;
    }
    
}

