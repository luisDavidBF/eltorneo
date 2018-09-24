 /*
 * 
 *
 * Proyecto: ElTorneo
 * Copyright EdinsonAC
 * All rights reserved
 */
package co.eltorneo.mvc.fachada;
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


}
