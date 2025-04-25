<%-- 
    Document   : Tarea
    Created on : 25/04/2025, 11:31:38 a. m.
    Author     : Personal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nueva Tarea</title>
    </head>
    <body>
        <h1>Nueva Tarea</h1>
        <form action="!${pageContext.request.contextPath}/tareas/guardar" method ="post">
            <label for =""descripcion">Descripcion</label>
            <input type="text" id="descripcion" name="descripcion" required>
            <br><br>
            <input type="submit" value="Guardar">
        </form>
            <br><br>
            <a href="${pageContext.request.contextPath}/tareas">Volver a la lista</a>
    </body>
</html>
