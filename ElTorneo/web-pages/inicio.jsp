<!DOCTYPE html>

<%@page import="java.util.ArrayList"%>
    <%@page import="co.eltorneo.mvc.dto.UsuarioDTO"%>
        <%

    UsuarioDTO datosUsuario = (UsuarioDTO) session.getAttribute("datosUsuario");


%>

            <html lang="en">

            <head>
                <!--        <style type="text/css">
                    IMG.imgcenter{
                        display: block;
                        margin-left: auto;
                        margin-right: auto;
                        border:none;
                    }
                </style>-->
                <meta charset="utf-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <title>ElTorneo</title>

                <link href="assets/css/integral-core.css" rel="stylesheet">


                <!-- Global stylesheets -->
                <link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
                <link href="assets/css/icons/icomoon/styles.css" rel="stylesheet" type="text/css">
                <link href="assets/css/bootstrap.css" rel="stylesheet" type="text/css">
                <link href="assets/css/core.css" rel="stylesheet" type="text/css">
                <link href="assets/css/components.css" rel="stylesheet" type="text/css">
                <link href="assets/css/colors.css" rel="stylesheet" type="text/css">
                
                <!-- /global stylesheets -->

                <!-- Core JS files -->
                <script type="text/javascript" src="assets/js/plugins/loaders/pace.min.js"></script>
                <script type="text/javascript" src="assets/js/core/libraries/jquery.min.js"></script>
                <script type="text/javascript" src="assets/js/core/libraries/bootstrap.min.js"></script>
                <script type="text/javascript" src="assets/js/core/app.js"></script>
                <script type="text/javascript" src="assets/js/plugins/loaders/blockui.min.js"></script>

                <!-- /core JS files -->

                <!-- Modal SCRIPT -->
                <script type="text/javascript" src="assets/js/pages/components_modals.js"></script>
                <!-- Modal SCRIPT -->

                <!-- Theme JS files -->
                <script type="text/javascript" src="assets/js/plugins/visualization/d3/d3.min.js"></script>
                <script type="text/javascript" src="assets/js/plugins/visualization/d3/d3_tooltip.js"></script>
                <script type="text/javascript" src="assets/js/plugins/forms/styling/switchery.min.js"></script>
                <script type="text/javascript" src="assets/js/plugins/forms/styling/uniform.min.js"></script>
                <script type="text/javascript" src="assets/js/plugins/forms/selects/bootstrap_multiselect.js"></script>
                <script type="text/javascript" src="assets/js/plugins/ui/moment/moment.min.js"></script>
                <script type="text/javascript" src="assets/js/plugins/pickers/daterangepicker.js"></script>
                <script type="text/javascript" src="assets/js/plugins/pickers/datepicker.js"></script>

                <script type='text/javascript' src='/ElTorneo/dwr/interface/ajaxElTorneo.js'></script>
                <script type='text/javascript' src='/ElTorneo/dwr/interface/ajaxSeguridad.js'></script>
                <script type='text/javascript' src='/ElTorneo/dwr/engine.js'></script>
                <script type='text/javascript' src='/ElTorneo/dwr/util.js'></script>
                        

                <!-- Theme JS files -->
                <script type="text/javascript" src="assets/js/core/libraries/jquery_ui/interactions.min.js"></script>
                <script type="text/javascript" src="assets/js/core/libraries/jquery_ui/touch.min.js"></script>

                <script type="text/javascript" src="assets/js/pages/components_navs.js"></script>
                <!--        /theme JS files -->

                <!--        Theme JS files -->
                <script type="text/javascript" src="assets/js/plugins/tables/datatables/datatables.min.js"></script>

                <script type="text/javascript" src="assets/js/plugins/forms/selects/selectboxit.min.js"></script>

                <script type="text/javascript" src="assets/js/plugins/forms/styling/switch.min.js"></script>
                <!--        Theme JS files-->
                <script type="text/javascript" src="assets/js/plugins/notifications/pnotify.min.js"></script>

                <script type="text/javascript" src="assets/js/plugins/uploaders/fileinput.min.js"></script>

                <script src="assets/libs/bootstrap-validator/js/jquery.validate.js"></script>
                <script src="assets/libs/bootstrap-validator/js/bootstrapValidator.min.js"></script>

                <script type="text/javascript" src="assets/js/plugins/forms/wizards/form_wizard/form_wizard.min.js"></script>
                <script type="text/javascript" src="assets/js/plugins/forms/wizards/steps.min.js"></script>

                <script type="text/javascript" src="assets/js/plugins/loaders/pace.min.js"></script>
                <script type="text/javascript" src="assets/js/plugins/loaders/blockui.min.js"></script>
                <script type="text/javascript" src="assets/js/plugins/forms/styling/uniform.min.js"></script>
<!--                <script type="text/javascript" src="assets/js/core/libraries/jasny_bootstrap.min.js"></script>
                <script type="text/javascript" src="assets/js/core/libraries/jasny_bootstrap.min.js"></script>-->


                <script type="text/javascript" src="assets/js/plugins/forms/validation/validate.min.js"></script>

            </head>

            <body>
                <div class="container">
                    <header>
                        <nav class="navbar navbar-default navbar-fixed-top">
                            <div class="container-fluid">
                                <div class="navbar-header">
                                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#nav1">
                                        <span class="sr-only">menu</span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                    </button>
                                    <a href="#" class="navbar-brand">Eltorneo</a>
                                </div>
                                <div class="collapse navbar-collapse" id="nav1">
                                    <ul class="nav navbar-nav">
                                        <li>
                                            <a href="">menu 1</a>
                                        </li>
                                        <li>
                                            <a href="">menu 2</a>
                                        </li>
                                        <li>
                                            <a href="">menu 3</a>
                                        </li>
                                    </ul>

                                </div>
                            </div>
                        </nav>
                    </header>
                </div>
                <div id="contenidoPrincipal" class="page-container">

                </div>
            </body>

            </html>