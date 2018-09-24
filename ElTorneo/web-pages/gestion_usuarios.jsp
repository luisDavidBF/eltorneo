<%-- 
    Document   : index_administrador
    Created on : 29/12/2016, 08:43:18 AM
    Author     : DANNY
--%>

<%@page import="co.mobiltech.unicloud.mvc.dto.UsuarioDTO"%>
<%-- TABLA ADMINISTRADOR (USUARIOS) --%>
<%

    UsuarioDTO datosUsuario = (UsuarioDTO) session.getAttribute("datosUsuario");
    String idCliente = datosUsuario.getIdCliente();
    String rol = datosUsuario.getRol();
    System.out.print("Gestion de usuarios JSP >>>> Datos usuario  " + datosUsuario.toStringJson());

%>
<script type="text/javascript" src="assets/js/pages/uploader_bootstrap.js"></script>

<style>
    label.error {
        color: #D84315 ;
        font-size: 0.9em;
        margin-top: -2px;
        padding: 0;
    }

</style>

<%-- VER USUARIOS --%>
<div class="content" id="tablaAdmin">
    <div class="panel panel-flat">
        <div class="panel-heading">
            <div class="col-lg-10 col-md-10"></div>
            <div>
                <button type="button" onclick="crearAdministrador();"  class="btn btn-primary ">Registrar</button>
            </div>
        </div>

        <div class="panel-body">
        </div>

        <div class="table-responsive">
            <table class="table datatable-html" >
                <thead>
                    <tr>

                        <th>Nombre</th>
                        <th>Usuario</th>
                        <th>Teléfono</th>
                        <th>Estado</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody id="listUsuarios">

                </tbody>
            </table>
        </div>
    </div>
</div>


<%-- FORMULARIO CREAR  SUPER ADMINISTRADOR --%>
<div class="content" id="formAdministrador">
    <form id="form" class="form-validation" action="return:false">
        <div class="widget-content padding col-sm-12">
            <div class="panel panel-flat">
                <div style="text-align: center" id="ver">
                    <h2><strong>Usuario</strong></h2>
                </div>
                <div style="text-align: center" id="actualizar">
                    <h2><strong>Actualizar usuario</strong></h2>
                </div>
                <div style="text-align: center" id="registrar">
                    <h2><strong>Registrar usuario</strong></h2>
                </div>
                <div class="form-group">
                    <div class="col-lg-12">

                        <div id="divImg" class="panel-heading">


                            <img src="" id="imgEmpresa" style="width:80px; cursor: pointer;" />
                            <!--    <img src="" style="width:100px; cursor: pointer;">-->

                            <div class="form-group" id="divCargarImg">
                                <label class="col-lg-2 control-label text-semibold">Imagen</label>
                                <div class="col-lg-5 col-md-8 col-sm-8 col-xs-8" id="divImagen">
                                    <input type="file" id="file" class="file-input col-lg-8 col-sm-8 col-md-8 col-xs-8" data-show-caption="false" data-show-upload="false">
                                </div>
                                <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                                    <!--   <button id="btnCambiarImg" hidden=""  type="submit" class="btn btn-primary position-right col-lg-5 col-md-5" onclick="montarImg();">Cambiar</button>-->
                                </div>
                            </div>

                        </div>

                        <div class="panel-body">
                            <hr>
                        </div>




                        <div class="row">
                            <div class="col-sm-12">  

                                <div class="form-group has-feedback has-feedback-left col-sm-6">
                                    <label class="control-label" for="username">*Nombres y Apellidos</label>
                                    <input type="text" name="username" id="username" class="form-control input-sm" maxlength="60" placeholder="Nombres y Apellidos" onkeypress="return soloLetras(event);" autocomplete="off" required>

                                </div>
                                <div class="form-group has-feedback has-feedback-left col-sm-6">
                                    <label class="control-label" for="telefono">*Teléfono</label> 
                                    <input type="text"  id="telefono" name="telefono" class="form-control input-sm"  type="number"  maxlength="10" minlength="7" placeholder="Teléfono" onkeypress="return soloNumeros(event);" autocomplete="off" required>
                                </div>
                            </div>

                            <div class="col-sm-12">
                                <div class="form-group has-feedback has-feedback-left col-sm-6">
                                    <label class="control-label" for="tipoDocumento">*Tipo de Documento</label>
                                    <select type="text" name="tipoDocumento" id="tipoDocumento" class="form-control input-sm" placeholder="Tipo de documento" required></select>
                                </div>
                                <div class="form-group has-feedback has-feedback-left col-sm-6">
                                    <label class="control-label" for="documento">*Documento</label> 
                                    <input  name="documento" id="documento" class="form-control input-sm"  type="text"   maxlength="12" minlength="7" placeholder="Documento" onkeypress="return soloNumeros(event);" autocomplete="off" required>
                                </div>
                            </div>

                            <div class="col-sm-12">
                                <div class="form-group has-feedback has-feedback-left col-sm-6">
                                    <label class="control-label" for="direccion">*Dirección</label> 
                                    <input type="text"  name="direccion" id="direccion" class="form-control input-sm" maxlength="80" placeholder="Dirección" autocomplete="off" required>
                                </div> 


                                <div class="form-group has-feedback has-feedback-left col-sm-6">
                                    <label class="control-label" for="rol">*Tipo de usuario</label>
                                    <select type="text" name="rol" id="rol" class="form-control input-sm" placeholder="Tipo de usuario" required></select>
                                    <input type="text" id="rolHidden" class="form-control input-sm" hidden="">

                                </div>
                            </div>

                            <div class="col-sm-12">
                                <div class="form-group has-feedback has-feedback-left col-sm-6">
                                    <label class="control-label" for="departamento">*Departamento</label>
                                    <select onchange="javascript:listarMunicipio(this.value);" name="departamento" id="departamento" class="form-control input-sm" placeholder="Seleccione departamento" title="Seleccione departamento" required></select>
                                </div>
                                <div class="form-group has-feedback has-feedback-left col-sm-6">
                                    <label class="control-label" for="municipio">*Municipio</label> 
                                    <select  id="municipio" name="municipio" class="form-control input-sm"  title="Seleccione un municipio"  placeholder="Seleccione municipio" required></select>
                                </div><br><br>
                            </div> 

                            <div class="col-sm-12">
                                <div class="form-group has-feedback has-feedback-left col-sm-6">
                                    <label class="control-label" for="correo">*Correo</label> 
                                    <input type="email" id="correo"  name="correo" maxlength="50" class="form-control input-sm" placeholder="Correo" required>
                                </div>  
                                <div class="form-group has-feedback has-feedback-left col-sm-6">
                                    <label class="control-label" for="correo2">*Confirmar correo</label>
                                    <input type="email" id="correo2" name="correo2" equalTo="#correo" maxlength="50" class="form-control input-sm" placeholder="Confirmar correo" required>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="form-group col-lg-6 col-md-6" id="divUsuario">
                                    <label class="control-label" for="usuario2">*Usuario: </label>
                                    <input type="text" remote="validarUsuario" id="usuario2" name="usuario2" maxlength="20" class="form-control input-sm" placeholder="Usuario" onblur="quitarEspacios();" required>
                                </div>
                                <div >
                                </div>
                            </div>  

                            <div class="col-sm-12" id="divContraseï¿½a">
                                <div class="form-group has-feedback has-feedback-left col-sm-6">
                                    <label class="control-label" for="clave">*Contraseña</label>
                                    <input type="password" id="clave" name="clave" class="form-control input-sm" minlength="6" maxlength="10" placeholder="Contraseña" required>
                                </div>
                                <div class="form-group has-feedback has-feedback-left col-sm-6">
                                    <label class="control-label" for="clave2">*Confirmar contraseña</label> 
                                    <input type="password" id="clave2" equalTo="#clave" maxlength="10" minlength="6" name="clave2" class="form-control input-sm" placeholder="Confirmar contraseña"required>
                                </div>
                            </div>

                            <div class="col-sm-12" id="divUsuarioInf">
                                <div class="form-group has-feedback has-feedback-left col-sm-6">
                                    <label class="control-label" for="clave">Usuario</label>
                                    <input type="text" id="usuarioUsuario" name="usuarioUsuario" disabled="" maxlength="20" onblur="quitarEspacios();" class="form-control input-sm" placeholder="Usuario">
                                </div>
                                <div class="form-group has-feedback has-feedback-left col-sm-6">
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="content">
                        <div class="form col-lg-12 col-md-12 col-sm-12">
                            <div class="form col-lg-4 col-md-4 col-sm-4">

                            </div>
                            <div class="form col-lg-4 col-md-4 col-sm-4">

                            </div>
                            <div class="form col-lg-4 col-md-4 col-sm-4">
                                <button id="btnRegistrar" style="float: right;" type="submit" class="btn btn-primary" onclick="validar(1);">Registrar</button>
                                <button id="btnGuardarEdicion" style="float: right;" type="submit" class="btn btn-primary" onclick="validar(2);">Actualizar</button>
                                <button id="btnEditar" style="float: right;" type="submit" class="btn btn-primary position-right" onclick="habilitarEdicion();"> Editar</button>
                                <button id="btnCancelar"type="button"  class="btn btn-info position-right"  onclick="estadoInicial();">Cancelar</button>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script>
    $(document).ready(function() {
        estadoInicial();
    });

    $(document).bind("cut copy paste", function(e) {
        e.preventDefault();
    });


    function estadoInicial() {
                jQuery('.datatable-html').dataTable().fnDestroy();

        
        $("#imgEmpresa").prop('src', '');
        $("#divImagen").show();

        $("#tablaAdmin").hide();
        $("#formAdministrador").hide();
        $("#btnRegistrar").hide();
        llenarComboTipoDocumento("tipoDocumento");
        llenarComboDepartamento("departamento");
        // listarMunicipio("municipio");
        llenarComboTipoRol("rol");
        listarUsuariosAdministrador();
        limpiarForm("formAdministrador");
        $("#formAdministrador :input").prop("disabled", false);

        $(".form-group").removeClass('has-error');
        $(".error").remove();

    }
    var muni;
    var listado2 = [];
    var mapa = [
        function(data) {
            return data.nombre;
        },
        function(data) {
            return data.rol;
        },
        function(data) {
            return data.telefono;
        },
        function(data) {


            if (data.estado == "1") {
                // return '<button type="button" id="' + data.idUsuario + '" onclick="javascript:actualizarEstado(this.id);" class="btn btn-danger btn-rounded">Rounded button</button>';
                return '<td>   <input  id="' + data.idUsuario + '" type="checkbox" data-on-color="success" data-off-color="danger" data-on-text="Activo" data-off-text="Inactivo" onchange="javascript:actualizarEstado(this.id,this.checked);" class="switch" checked> </td>';
            } else {
                return '<td>   <input  id="' + data.idUsuario + '" type="checkbox" data-on-color="success" data-off-color="danger" data-on-text="Activo" data-off-text="Inactivo" onchange="javascript:actualizarEstado(this.id,this.checked);" class="switch" > </td>';
            }
        },
        function(data) {
            return '<td><button type="submit"  onclick="verDatos(' + data.idUsuario + ');" class="btn btn-primary position-right">Detalle</button></td>';
        }
    ];
    function listarUsuariosAdministrador() {
        ajaxUnicloud.listarUsuariosAdministrador(<%=idCliente%>, {
            callback: function(data) {
                if (data !== null) {
                    $("#formAdministrador").hide();
                    $("#tablaAdmin").show();
                    dwr.util.removeAllRows("listUsuarios");
                    listado2 = data;
                    dwr.util.addRows("listUsuarios", listado2, mapa, {
                        escapeHtml: false
                    });
                    // // $(".switch").bootstrapSwitch();

                    $('.datatable-html').dataTable();
                }
                 // $(".switch").bootstrapSwitch();
                $(".loader-backdrop").fadeOut();

            },
            timeout: 20000
        });
    }
    var roll;

    function llenarComboTipoDocumento(idCombo, valorSeleccionado) {
        ajaxUnicloud.listarTipoDocumento({
            callback: function(data) {

                if (data !== null) {
                    dwr.util.removeAllOptions(idCombo);
                    dwr.util.addOptions(idCombo, [{
                            id: '',
                            descripcion: 'Seleccione Tipo de Documento'
                        }], 'id', 'descripcion');
                    dwr.util.addOptions(idCombo, data, 'id', 'descripcion');
                    //jQuery('#' + idCombo).val(valorSeleccionado);
                }
            },
            timeout: 20000
        });
    }

    function llenarComboDepartamento(idCombo, valorSeleccionado) {
        ajaxUnicloud.listarDepartamentos({
            callback: function(data) {
                if (data !== null) {
                    dwr.util.removeAllOptions(idCombo);
                    dwr.util.addOptions(idCombo, [{
                            id: '',
                            nombre: 'Seleccione Departamento'
                        }], 'id', 'nombre');
                    dwr.util.addOptions(idCombo, data, 'id', 'nombre');
                    //$('#' + idCombo).val(valorSeleccionado);
                    $("#" + idCombo).val(valorSeleccionado).trigger("change");

                }
            },
            timeout: 20000
        });
    }

    function listarMunicipio(idDepartamento, idCombo, idMuni) {
        ajaxUnicloud.listarMunicipioPorDepartamentoSelecionado(idDepartamento, {
            callback: function(data) {
                if (data !== null) {
                    dwr.util.removeAllOptions("municipio");
                    dwr.util.addOptions("municipio", [{
                            id: '',
                            nombre: 'Seleccione Municipio'}], 'id', 'nombre');
                    dwr.util.addOptions("municipio", data, 'id', 'nombre');
                    $("#municipio").val(idMuni);
                }
            },
            timeout: 20000
        });
    }
    var imgPerfilActualizar;
    function actualizar() {

        var datos = {
            nombre: $("#username").val(),
            telefono: $("#telefono").val(),
            direccion: $("#direccion").val(),
            documento: $("#documento").val(),
            correo: $("#correo").val(),
            tipoDocumento: $("#tipoDocumento").val(),
            municipio: $("#municipio").val(),
            idTipoRol: roll,
            idCliente: <%=idCliente%>,
            idUsuario: idUsuario,
            imagenPerfil: imgPerfilActualizar


        };
        ajaxUnicloud.actualizarUsuario(datos, {
            callback: function(data) {
                if (data) {
                    // location.reload();
                    estadoInicial();
                    notificaError("Alerta ", "Actualizaciï¿½n exitosa", 'bg-success');
                    limpiarForm("formAdministrador");
                    //  $("#rolHidden").val("Super Administrador");


                }
            },
            timeout: 20000
        });
    }
    function registrar() {

        if (<%=rol%> === 1) {
            var r = "1";
        } else {
            r = $("#rol").val();
        }

        var datos = {
            nombre: $("#username").val(),
            correo: $("#correo").val(),
            tipoDocumento: $("#tipoDocumento").val(),
            documento: $("#documento").val(),
            telefono: $("#telefono").val(),
            direccion: $("#direccion").val(),
            municipio: $("#municipio").val(),
            idTipoRol: r,
            idCliente: <%=idCliente%>,
            usuario: $("#usuario2").val(),
            clave: $("#clave").val()


        };
        ajaxUnicloud.registrarUsuario(datos, {callback: function(data) {
                if (data) {

                    if (!$('#file').val() == '') {


                        var img;
                        img = new FormData();
                        img.append('file', jQuery('#file')[0].files[0]);
                        if (jQuery('#file')[0].files[0].type === "image/jpeg" || jQuery('#file')[0].files[0].type === "image/png") {

                            jQuery.ajax({
                                url: 'SubirImagenPerfil',
                                data: img,
                                processData: false,
                                contentType: false,
                                async: false,
                                type: 'Post',
                                success: function(data) {
                                    estadoInicial();

//location.reload();
                                    // setTimeout('estadoInicial();', '1000');

                                }
                            }).done(function(html) {
                                //location.reload();

                            });
                        } else {


                            notificaError("error ", "Archivo no permitido", 'bg-warning');
                        }
                    }
                    estadoInicial();

                    //.reload();
                    //setTimeout('estadoInicial();', '1000');
                    notificaError("Alerta ", "Registro exitoso", 'bg-success');
                }
            },
            timeout: 20000
        });
    }
    function actualizarEstado(id, estado) {
        if (id ==<%=datosUsuario.getIdUsuario()%>) {
            // notificaError("", "Este usuario es usted, no puede cambiar su estado", "bg-warning");
            $("#" + id).click();

        } else {

            ajaxUnicloud.actualizarEstadoUsuario(id, estado, {callback: function(data) {
                    if (data) {

                        listado2.forEach(function(valor, indice, array) {
                            if (valor.idUsuario == id) {
                                if (estado === true && valor.estado === '0') {

                                    listado2[indice].estado = '1';
                                    notificaError("", "Cambio de estado exitoso", "bg-success");

                                    return;
                                }
                                if (estado === false && valor.estado === '1') {
                                    listado2[indice].estado = '0';
                                    notificaError("", "Cambio de estado exitoso", "bg-success");
                                    return;
                                }
                            }


                        });

                    } else {
                        listarClientes();
                        notificaError(" ", "No pudo cambiar el estado", "bg-warning");
                    }
                },
                timeout: 20000
            });

        }
    }
    function llenarComboTipoRol(idCombo, valorSeleccionado) {
        ajaxUnicloud.listarTipoRol2({
            callback: function(data) {

                if (data !== null) {
                    dwr.util.removeAllOptions(idCombo);
                    dwr.util.addOptions(idCombo, [{
                            id: '',
                            descripcion: 'Seleccione tipo de rol'
                        }], 'id', 'descripcion');
                    dwr.util.addOptions(idCombo, data, 'id', 'descripcion');
                    //jQuery('#' + idCombo).val(valorSeleccionado);
                }
            },
            timeout: 20000
        });
    }

    function quitarRequire() {
        $("#usuario2").prop("required", null);
        $("#clave").prop("required", null);
        $("#clave2").prop("required", null);
        $("#rol").prop("required", null);

        //$("#formularioRegistro :input").prop('required', null);
        //  $("#nombre").attr("required", "required");

    }

    function agregarRequire()
    {
        $("#usuario2").prop("required", 'required');
        $("#clave").prop("required", 'required');
        $("#clave2").prop("required", 'required');
        // $("#rol").prop("required", 'required');


    }

    function limpiarForm(form) {

        $('#' + form + ' input[type="text"]').each(function() {
            $(this).val("");
        });
        $('#' + form + ' input[type="number"]').each(function() {
            $(this).val("");
        });
        $('#' + form + ' input[type="email"]').each(function() {
            $(this).val("");
        });
        $('#' + form + ' select').each(function() {
            $(this).val("");
        });
        $("input:radio").attr("checked", false);
    }

    function crearAdministrador() {
        agregarRequire();
        $("#ver").hide();
        $("#actualizar").hide();
        $("#registrar").show();
        $("#divUsuario").show();
        $("#divContraseï¿½a").show();
        $("#divUsuarioInf").hide();

        if (<%=rol%> === 1) {
            $("#rol").hide();
            $("#rolHidden").show();
            $("#rolHidden").val("Super Administrador");
            $("#rolHidden").prop("disabled", true);
        } else
        {
            $("#rolHidden").hide();
        }
        $("#formAdministrador").show();
        $("#tablaAdmin").hide();
        $("#btnRegistrar").show();
        $("#btnEditar").hide();
        $("#btnGuardarEdicion").hide();
        $("#formSuperAdmin").show();
        // limpiarForm("formAdministrador");

    }



    function listarSubcategoriaCliente(idCombo) {

        ajaxUnicloud.listarSubcategoriaCliente({
            callback: function(data) {
                if (data !== null) {
                    dwr.util.removeAllOptions(idCombo);
                    dwr.util.addOptions(idCombo, [{
                            id: '',
                            descripcion: 'Seleccione subcategoria'}], 'id', 'descripcion');
                    dwr.util.addOptions(idCombo, data, 'id', 'descripcion');
                    //jQuery("#" + idCombo).val(parseInt(municipio)).trigger("change");                     
                }
            },
            timeout: 20000
        });
    }



    var idUsuario = null;
    function verDatos(data) {
        idUsuario = data;
        $("#divUsuarioInf").show();

        $("#btnGuardarEdicion").hide();
        $("#ver").show();
        $("#actualizar").hide();
        $("#registrar").hide();
        $("#formAdministrador").show();
        $("#tablaAdmin").hide();
        $("#btnEditar").show();
        $("#formAdministrador :input").prop("disabled", true);
        $("#btnCancelar").prop('disabled', false);
        $("#btnEditar").prop("disabled", false);
        $("#divUsuario").hide();
        $("#divContraseï¿½a").hide();
        $("#divImagen").hide();

        for (var i = 0; i < listado2.length; i++) {

            if (parseInt(listado2[i].idUsuario) === data) {

                console.log('usuario >>>', listado2[i]);

                $("#username").val(listado2[i].nombre);
                $("#correo").val(listado2[i].correo);
                $("#correo2").val(listado2[i].correo);
                $("#tipoDocumento").val(listado2[i].idTipoDocumento);
                $("#documento").val(listado2[i].documento);
                $("#telefono").val(listado2[i].telefono);
                $("#direccion").val(listado2[i].direccion);
                $("#rolHidden").val(listado2[i].idTipoRol);
                $("#usuarioUsuario").val(listado2[i].usuario)
                // llenarComboDepartamento("departamento", listado2[i].idDepartamento);
                imgPerfilActualizar = listado2[i].imagenPerfil;
                $("#imgEmpresa").prop('src', '<%= request.getContextPath()%>/ServletMostrarImgPerfil?imagen=' + listado2[i].imagenPerfil);


                listarMunicipio(listado2[i].idDepartamento, "municipio", listado2[i].idMunicipio);
                $("#departamento").val(listado2[i].idDepartamento);
                //    $("#rol").val(parseInt(listado2[i].rol));
                if (listado2[i].idTipoRol === "1") {
                    $("#rol").hide();
                    $("#rolHidden").val(listado2[i].rol);
                    $("#rolHidden").show();
                    roll = listado2[i].idTipoRol;
                } else {
                    $("#rol").show();
                    $("#rolHidden").hide();
                    $("#rol").val(parseInt(listado2[i].idTipoRol));
                    roll = listado2[i].idTipoRol;
                }
                $("#cliente").val(listado2[i].cliente);
            }
        }


    }

    function habilitarEdicion() {
        $("#ver").hide();
        $("#actualizar").show();
        $("#registrar").hide();
        $("#formAdministrador :input").prop("disabled", false);
        $("#btnGuardarEdicion").show();
        $("#btnEditar").hide();
        $("#rolHidden").prop("disabled", true);
        $("#usuarioUsuario").prop('disabled', 'disabled');
        quitarRequire();
    }

    function quitarEspacios() {
        var sinEspacios = $("#usuario2").val();
        console.log("entra a quitar espacio ", sinEspacios);

        $("#usuario2").val(sinEspacios.replace(/\s/g, ''));
    }


    $.extend($.fn.dataTable.defaults, {
        autoWidth: false,
        columnDefs: [{
                //  orderable: false,
                //      width: '100px'
                //targets: [ 1 ]
            }],
        dom: '<"datatable-header"fl><"datatable-scroll"t><"datatable-footer"ip>',
        language: {
            search: '<span>Filter:</span> _INPUT_',
            lengthMenu: '<span>Show:</span> _MENU_',
            paginate: {'first': 'First', 'last': 'Last', 'next': '&rarr;', 'previous': '&larr;'}
        },
        drawCallback: function() {
            $(this).find('tbody tr').slice(-3).find('.dropdown, .btn-group').addClass('dropup');
        },
        preDrawCallback: function() {
            $(this).find('tbody tr').slice(-3).find('.dropdown, .btn-group').removeClass('dropup');
        }
    });
    var re;

    function validar(registro) {
        re = registro;

        $('input[type="text"]').each(function() {
            $(this).val($(this).val().trim());
        });

        $('#form').validate({
            highlight: function(label) {
                jQuery(label).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            success: function(label) {
                jQuery(label).closest('.form-group').removeClass('has-error');
                label.remove();
            },
            errorPlacement: function(error, element) {
                var placement = element.closest('.input-group');
                if (!placement.get(0)) {
                    placement = element;
                }
                if (error.text() !== '') {
                    placement.after(error);
                }
            },
            submitHandler: function() {
                if (re == 1) {
                    registrar();
                    re = null;
                }
                if (re == 2) {
                    actualizar();
                    re = null;
                }

            }
        });
    }
    function soloLetras(e) {

        key = e.keyCode || e.which;
        tecla = String.fromCharCode(key).toLowerCase();
        letras = " ï¿½ï¿½ï¿½ï¿½ï¿½abcdefghijklmnï¿½opqrstuvwxyz";
        especiales = "8-37-39-46";
        tecla_especial = false;
        for (var i in especiales) {
            if (key === especiales[i]) {
                tecla_especial = true;
                break;
            }
        }
        if (letras.indexOf(tecla) === -1 && !tecla_especial) {
            return false;
        }
    }
    function soloNumeros(e) {

        key = e.keyCode || e.which;
        tecla = String.fromCharCode(key).toLowerCase();
        letras = "0,1,2,3,4,5,6,7,8,9";
        especiales = "8-37-39-46";
        tecla_especial = false;
        for (var i in especiales) {
            if (key === especiales[i]) {
                tecla_especial = true;
                break;
            }
        }
        if (letras.indexOf(tecla) === -1 && !tecla_especial) {
            return false;
        }
    }
    function  montarImg() {

        if (!$('#file').val() == '') {



            var data;
            data = new FormData();
            data.append('file', jQuery('#file')[0].files[0]);
            if (jQuery('#file')[0].files[0].type === "image/jpeg" || jQuery('#file')[0].files[0].type === "image/png") {

                jQuery.ajax({
                    url: 'SubirImagenPerfil?idUsuario=',
                    data: data,
                    processData: false,
                    contentType: false,
                    async: false,
                    type: 'Post',
                    success: function(data) {
                        estadoInicial();

                        //location.reload();

                    }
                }).done(function(html) {

                });
            } else {

                notificaError("error ", "Archivo no permitido", 'bg-warning');
            }


        }
        else
        {
            notificaError("error ", "Seleccione una imagen", 'bg-warning');
        }

    }

</script>