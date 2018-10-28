/*
 * 
 *
 * Proyecto: ElTorneo
 * Copyright EdinsonAC
 * All rights reserved
 */
package co.eltorneo.mvc.fachada;

import co.eltorneo.mvc.dto.ArbitroDTO;
import co.eltorneo.mvc.dto.EquipoDTO;
import co.eltorneo.mvc.dto.JugadorDTO;
import co.eltorneo.mvc.dto.RespuestaDTO;
import co.eltorneo.mvc.dto.TecnicoDTO;
import co.eltorneo.mvc.dto.UsuarioDTO;
import co.eltorneo.mvc.mediador.MediadorElTorneo;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

//import co.mobiltech.doctorpin.mvc.mediador.MediadorAppUniCloud;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.annotations.ScriptScope;

/**
 *
 * @author Edinson AC
 */
@RemoteProxy(name = "ajaxElTorneo", scope = ScriptScope.SESSION)
public class FachadaElTorneo {

    /**
     *
     */
    public FachadaElTorneo() {
    }

    /**
     *
     * @return
     */
    @RemoteMethod
    public boolean servicioActivo() {
        return true;
    }

    /**
     *
     * @param documento
     * @return
     */
    public UsuarioDTO recuperarContrassenia(String documento) {
        return MediadorElTorneo.getInstancia().recuperarContrasenia(documento);
    }

    /**
     *
     * @param tecnico
     * @param usuario
     * @return
     */
    @RemoteMethod
    public RespuestaDTO registrarTecnico(TecnicoDTO tecnico, UsuarioDTO usuario) {
        return MediadorElTorneo.getInstancia().registrarTecnico(tecnico, usuario);
    }

    /**
     *
     * @return
     */
    @RemoteMethod
    public ArrayList<TecnicoDTO> listarTecnicos() {
        return MediadorElTorneo.getInstancia().listarTecnicos();
    }

    /**
     *
     * @param arbitro
     * @param usuario
     * @return
     */
    @RemoteMethod
    public RespuestaDTO registrarArbitro(ArbitroDTO arbitro, UsuarioDTO usuario) {
        return MediadorElTorneo.getInstancia().registrarArbitro(arbitro, usuario);
    }

    /**
     *
     * @return
     */
    @RemoteMethod
    public ArrayList<ArbitroDTO> listarArbitros() {
        return MediadorElTorneo.getInstancia().listarArbitros();
    }

    /**
     *
     * @param equipo
     * @return
     */
    @RemoteMethod
    public RespuestaDTO registrarEquipo(EquipoDTO equipo) {
        return MediadorElTorneo.getInstancia().registrarEquipo(equipo);
    }

    /**
     *
     * @return
     */
    @RemoteMethod
    public ArrayList<EquipoDTO> listarEquipos() {
        return MediadorElTorneo.getInstancia().listarEquipos();
    }

    /**
     *
     * @param equipo
     * @param usuario
     * @return
     */
    @RemoteMethod
    public RespuestaDTO registrarJugador(JugadorDTO equipo, UsuarioDTO usuario) {
        return MediadorElTorneo.getInstancia().registrarJugador(equipo, usuario);
    }

    /**
     *
     * @return
     */
    @RemoteMethod
    public ArrayList<JugadorDTO> listarJugadores() {
        return MediadorElTorneo.getInstancia().listarJugadores();
    }
}
