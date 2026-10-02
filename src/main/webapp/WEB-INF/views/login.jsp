<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"   uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="${not empty cookie.idiomaPreferido ? cookie.idiomaPreferido.value : 'es'}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="login.titulo"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<h1><fmt:message key="login.titulo"/></h1>
<c:if test="${not empty errorLogin}">
    <p class="error">${errorLogin}</p>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/app">
    <input type="hidden" name="comando" value="login">
    <label><fmt:message key="login.usuario"/>:
        <input type="text" name="username" required>
    </label>
    <label><fmt:message key="login.clave"/>:
        <input type="password" name="clave" required>
    </label>
    <button type="submit"><fmt:message key="login.entrar"/></button>
</form>
<p>
    <a href="${pageContext.request.contextPath}/app?comando=idioma&lang=es">Español</a> |
    <a href="${pageContext.request.contextPath}/app?comando=idioma&lang=en">English</a>
</p>
</body>
</html>