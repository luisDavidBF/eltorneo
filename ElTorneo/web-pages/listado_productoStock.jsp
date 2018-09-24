<%-- 
    Document   : index_administrador
    Created on : 29/12/2016, 08:43:18 AM
    Author     : DANNY
--%>

<%@page import="co.mobiltech.unicloud.mvc.dto.UsuarioDTO"%>
<%
    UsuarioDTO datosUsuario = (UsuarioDTO) session.getAttribute("datosUsuario");
    String idCliente = datosUsuario.getIdCliente();
    String rol = datosUsuario.getRol();
%>
<!--<script type="text/javascript" src="assets/js/pages/form_checkboxes_radios.js"></script>-->


<style>
    label.error {
        color: #D84315 ;
        font-size: 0.9em;
        margin-top: -2px;
        padding: 0;
    }
</style>

<%-- VER PRODUCTOS--%>
<div class="content" id="tablaProductos">
    <div class="panel panel-flat">
        <div class="panel-heading">
            <div class="col-lg-10 col-md-10"></div>
           
        </div>
        <div class="panel-body">
        </div>
        <div class="table-responsive">
            <table class="table datatable-html0" >
                <thead>
                    <tr>
                        <th>Nombre</th>                        
                        <th>existencia</th>
                        <th>Cantidad minima</th>
                    </tr>
                </thead>
                <tbody id="listadoProductosAlertaStock">

                </tbody>
            </table>
        </div>
    </div>
</div>


<script>

    var listado2 = [];
    var mapa = [
        function(data) {
            return data.nombre;
        },
        function(data) {
            return data.cantidad;
        },
        function(data) {
            return data.cantidadMinima;
        }
    ];

    $(document).ready(function() {
        listarProductosAlertaStock();
    });
    function listarProductosAlertaStock() {

        ajaxUnicloud.listarProductosAlertaStock({
            callback: function(data) {
                if (data !== null) {
                    $("#alertStock").show();
                    dwr.util.removeAllRows("listadoProductosAlertaStock");
                    listado2 = data;
                    dwr.util.addRows("listadoProductosAlertaStock", listado2, mapa, {
                        escapeHtml: false
                    });
                    $('.datatable-html0').dataTable();

                }
                $(".loader-backdrop").fadeOut();
            },
            timeout: 20000
        });
    }
</script>