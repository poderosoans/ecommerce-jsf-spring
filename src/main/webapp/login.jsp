<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/login" method="POST">
            <p>
                <label for="username">Usuario</label>
                <input type="text" id="txtUsuario" name="txtUsuario">
            </p>
            <p>
                <label for="password">Contraseña</label>
                 <input type="password" id="txtConstrasena" name="txtContrasena">
            </p>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <button type="submit">Ingresar</button>
        </form>
    </body>
</html>
