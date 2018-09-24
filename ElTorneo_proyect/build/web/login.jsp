<!DOCTYPE html>

<%@page import="co.eltorneo.common.util.ValidaString"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="co.eltorneo.common.util.*"%>
<%@ page import="java.util.*,java.io.*"  contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>

<%
    session.invalidate();

    String mensajeError = request.getParameter("error");

    boolean mostrarError = false;

    if (!ValidaString.isNullOrEmptyString(mensajeError)) {
        mostrarError = true;
    }
%>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Eltorneo</title>

        <!-- Global stylesheets -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
        <link href="assets/css/icons/icomoon/styles.css" rel="stylesheet" type="text/css">
        <link href="assets/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="assets/css/core.css" rel="stylesheet" type="text/css">
        <link href="assets/css/components.css" rel="stylesheet" type="text/css">
        <link href="assets/css/colors.css" rel="stylesheet" type="text/css">
        <link href="assets/css/integral-core.css" rel="stylesheet" type="text/css">

        <!-- /global stylesheets -->

        <!-- Core JS files -->
        <script type="text/javascript" src="assets/js/plugins/loaders/pace.min.js"></script>
        <script type="text/javascript" src="assets/js/core/libraries/jquery.min.js"></script>
        <script type="text/javascript" src="assets/js/core/libraries/bootstrap.min.js"></script>
        <script type="text/javascript" src="assets/js/plugins/loaders/blockui.min.js"></script>
        <!-- /core JS files -->

        <!-- Theme JS files -->
        <script type="text/javascript" src="assets/js/core/app.js"></script>
        <!-- /theme JS files -->

    </head>

    <body class="login-container">

        <!-- Main navbar -->
        <div class="navbar navbar-inverse">
            <div class="navbar-header">
                <!--                
                <a class="navbar-brand" href="login.jsp">UNICLOUD<img src="assets/images/logo_light.png" alt=""></a>

                <ul class="nav navbar-nav pull-right visible-xs-block">
                    <li><a data-toggle="collapse" data-target="#navbar-mobile"><i class="icon-tree5"></i></a></li>
                </ul>
                -->
            </div>
        </div>      





        <!-- Page container -->
        <div class="page-container login-fondo">

            <!-- Page content -->
            <div class="page-content">

                <!-- Main content -->
                <div class="content-wrapper">

                    <!-- Content area -->
                    <div class="content">

                        <!-- Simple login form -->
                        <form role="form" action="<%= request.getContextPath()%>/j_security_check" method="post" autocomplete="off">

                            <%if (mostrarError) {%>                           
                            <p>
                            <div class="alert alert-danger">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                <strong><%= mensajeError%></strong>
                            </div>
                            </p>
                            <% }%>
                            <div class="panel panel-body login-form">
                                <div class="text-center">
                                    <!--<div class="icon-object border-slate-300 text-slate-300"><i class="icon-reading"></i></div>-->
                                    <div><img src="assets/images/logo_login.jpg" alt=""></div>
                                    <h5 class="content-group">Iniciar cesión<small class="display-block"></small></h5>
                                </div>

                                <div class="form-group has-feedback has-feedback-left">                                    
                                    <input name="j_username" type="text" class="form-control text-input" placeholder="Usuario" autocomplete="off">
                                    <div class="form-control-feedback">
                                        <i class="icon-user text-muted"></i>
                                    </div>
                                </div>

                                <div class="form-group has-feedback has-feedback-left">                                    
                                    <input name="j_password" type="password" class="form-control text-input" placeholder="********" autocomplete="off">
                                    <div class="form-control-feedback">
                                        <i class="icon-lock2 text-muted"></i>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary btn-block">Ingresar <i class="icon-circle-right2 position-right"></i></button>
                                </div>

                                <div class="text-center">
                                    <a href="login_password_recover.jsp">¿Olvidó su contraseña?</a>
                                </div>
                            </div>
                        </form>
                        <!-- /simple login form -->


                        <!-- Footer 
                        <div class="footer text-muted text-center">
                            &copy; 2015. <a href="#">Limitless Web App Kit</a> by <a href="http://themeforest.net/user/Kopyov" target="_blank">Eugene Kopyov</a>
                        </div>
                        <!-- /footer -->

                    </div>
                    <!-- /content area -->

                </div>
                <!-- /main content -->

            </div>
            <!-- /page content -->

        </div>
        <!-- /page container -->

    </body>

    <!-- Mirrored from demo.interface.club/limitless/layout_1/LTR/default/login_simple.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 20 Dec 2016 15:35:31 GMT -->
</html>
