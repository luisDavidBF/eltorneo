<%-- 
    Document   : index
    Created on : 27-dic-2016, 10:48:29
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   
    <frameset rows="0,*" framespacing="0" frameborder="NO" border="0" onUnload="makeRequest();">
        <frame name="topFrame" scrolling="NO" src="blank.htm" noresize/>
        <frame id="mainFrame" name="mainFrame" scrolling="AUTO" src="index_ctr.jsp" />
    </frameset>
    <noframes/>
</html>
