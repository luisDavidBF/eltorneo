<%@page import="co.mobiltech.unicloud.mvc.dto.UsuarioDTO"%>
<!DOCTYPE html>


<script type="text/javascript" src="assets/js/pages/uploader_bootstrap.js"></script>
<%
    UsuarioDTO datosUsuario = (UsuarioDTO) session.getAttribute("datosUsuario");
    String imagen = datosUsuario.getImagenPerfil();
    String idUsuario = datosUsuario.getIdUsuario();

%>
<html lang="en">


    <body class="login-container">
        <div class="page-container">
            <div class="page-content">
                <div class="content-wrapper">
                    <div class="content">
                        <form>

                            <div class="panel panel-body login-form">
                                <img src="<%= request.getContextPath()%>/ServletMostrarImgPerfil?imagen=<%=imagen%>" style="width:100px; cursor: pointer;">

                                <div class="form-group" id="divCargarImg">
                                    <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                                        <input type="file" id="file" class="file-input col-lg-5 col-sm-5 col-md-5 col-xs-5" data-show-caption="false" data-show-upload="false">
                                    </div>
                                    <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                                        <button id="btnCambiarImg" hidden=""  type="reset" class="btn btn-primary position-right col-lg-5 col-md-5" onclick="montarImg();">Cambiar</button>
                                    </div>
                                </div>
                            </div>

                        </form>
                    </div>

                </div>

            </div>

        </div>



    </body>
    <script>

        $(document).bind("cut copy paste", function(e) {
            e.preventDefault();
        });
        function  montarImg() {

            if ($('#file').val() !== '') {
                var data;
                data = new FormData();
                data.append('file', jQuery('#file')[0].files[0]);
                if (jQuery('#file')[0].files[0].type === "image/jpeg" || jQuery('#file')[0].files[0].type === "image/png") {

                    jQuery.ajax({
                        url: 'SubirImagenPerfil?idUsuario=' +<%=idUsuario%>,
                        data: data,
                        processData: false,
                        contentType: false,
                        async: false,
                        type: 'Post',
                        success: function(data) {

                            location.reload();

                        }
                    }).done(function(html) {

                    });
                } else {

                    notificaError("error ", "Archivo no permitido", 'bg-warning');
                }


            }
            else {
                notificaError("", "Seleccione una imagen.", 'bg-warning');
            }

        }
    </script>

</html>
