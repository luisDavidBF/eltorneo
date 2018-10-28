
<div class="card tc-card">
    <div class="card-body">
        <h4 class="card-title" id="tituloForm">Registrar Tecnico</h4> 
        <br>
        <form class="forms-sample" id="reg_usuario"  onsubmit="return false;">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="ap1_usuario">Nombres *</label>
                        <input type="text" class="form-control nombre" id="nom1_usuario" name="ap1_usuario" autocomplete="off" maxlength="45"  required>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="ap2_usuario">Apellidos *</label>
                        <input type="text" class="form-control nombre" id="ap1_usuario" name="ap2_usuario" autocomplete="off" maxlength="45" >
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="documento">Número documento: *</label>
                        <input type="text" maxlength="10"  class="form-control numeros"  id="documentoB" name="documento" autocomplete="off" >
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label for="email">Correo: *</label>
                        <input type="email" class="form-control" id="email" maxlength="45" name="email" autocomplete="off" required>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="cel_usuario">Celular: *</label>
                        <input type="text" class="form-control numeros" id="cel_usuario" name="cel_usuario" maxlength="10" autocomplete="off" required>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="dir_usuario">Dirección: *</label>
                        <input type="text" class="form-control" id="dir_usuario" name="dir_usuario" maxlength="45" autocomplete="off" required>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="tel_usuario">Telefono: *</label>
                        <input type="text" class="form-control numeros" id="tel_usuario" name="tel_usuario" maxlength="10" autocomplete="off">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="usuario">Usuario *</label>
                        <input type="text" class="form-control" id="usuario" name="usuario" maxlength="15" autocomplete="off">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="col-sm-6 mx-auto" style="text-align: -webkit-center;">
                        <button type="submit" id="boton" class="btn btn-success mr-2" onclick="validar('reg_usuario', 1);">Registrar</button>   
                    </div>
                    <input class="btn btn-light" type="button" value="Volver" onclick="javascript:redireccionar();">
                </div>
            </div>
        </form>
        <br>
    </div>
</div>
<script type="text/javascript" src="assets/js/Constantes.js"></script>
<script>
                        function recargar() {
                            jQuery("#contenidoPpal").load("admin/registrar-usuario.jsp");
                        }
                        $(".numeros").on("input", function () {
                            this.value = this.value.replace(/[^0-9]/g, '');
                        });
                        $(".nombre").on("input", function () {
                            this.value = this.value.replace(/[^a-zA-ZñÑ\s]*$/g, ''); // /^[a-zA-Z\s]*$/
                        });
                        $(".alfanumerico").on("input", function () {
                            this.value = this.value.replace(/[^A-Za-z0-9\s]*$/g, '');
                        });
                        $("#usuario").on("input", function () {
                            this.value = this.value.replace(/[^A-Za-z0-9\s]*$/g, '').replace(' ', '');
                        });
                        //globales
                        var operacion = null;

                        function validar(form, r) {
                            operacion = r;
                        }

                        function postValidate() {
                            if (operacion == 1)
                                registro();
                            operacion = null;
                        }

                        $(document).ready(function () {
//        $("#email").attr("remote", URL + "SevletValidarCorreo?bandera=usuario");
//        $("#documentoA").attr("remote", URL + "SevletValidarDocumento?bandera=usuario");
//        $("#usuario").attr("remote", URL + "ServletValidarCrendencial?bandera=usuario");

                            $("#reg_usuario").validate({
                                errorPlacement: function (label, element) {
                                    label.addClass('mt-2 text-danger');
                                    $(element).parent().append(label);
                                },
                                highlight: function (element, errorClass) {
                                    $(element).parent().addClass('has-danger');
                                    $(element).addClass('form-control-danger');
                                    // operacion = null;
                                },
                                success: function (label) {
                                    jQuery(label).closest('.has-danger').removeClass('has-danger');
                                    label.remove();
                                },
                                submitHandler: function () {
                                    postValidate();
                                }
                            });

                            $(".loader-backdrop").fadeOut();

                        });


                        function registro() {
                            $("#boton").prop('disabled', true);
                            var usuario = {
                                correo: $("#email").val(),
                                idTipoUsuario: TECNICO,
                                usuario: $("#usuario").val(),
                                registradoPor: nombreUsuario

                            };

                            var tecnico = {
                                nombre: $("#nom1_usuario").val(),
                                apellido: $("#ap1_usuario").val(),
                                documento: $("#documentoB").val(),
                                direccion: $("#dir_usuario").val(),
                                celular: $("#cel_usuario").val(),
                                registradoPor: nombreUsuario

                            };
                            ajaxElTorneo.registrarTecnico(tecnico, usuario, {
                                callback: function (data) {
                                    if (data !== null) {
                                        $("#boton").prop('disabled', false);
                                        limpiar();
                                    } else {
                                        $("#boton").prop('disabled', false);
                                    }
                                },
                                timeout: 20000
                            });

                            // recargar();
                        }

                        function limpiar() {
                            $('input').not(":button").val('');
                        }

</script>