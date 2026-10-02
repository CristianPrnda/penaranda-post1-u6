<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"   uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="${not empty cookie.idiomaPreferido ? cookie.idiomaPreferido.value : 'es'}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${applicationScope.nombreApp}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<div class="cabecera">
    <h1>${applicationScope.nombreApp}</h1>
    <p class="saludo">
        <fmt:message key="lista.bienvenida"/>, ${sessionScope.usuarioActual.nombreCompleto}
        (${sessionScope.usuarioActual.rol}) —
        <a href="${pageContext.request.contextPath}/app?comando=logout"><fmt:message key="lista.salir"/></a>
    </p>
    <p>
        <a href="${pageContext.request.contextPath}/app?comando=idioma&lang=es">Español</a> |
        <a href="${pageContext.request.contextPath}/app?comando=idioma&lang=en">English</a>
    </p>
</div>

<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>

<a href="${pageContext.request.contextPath}/app?comando=formulario"><fmt:message key="lista.nueva"/></a>

<table>
    <thead><tr>
        <th><fmt:message key="lista.titulo"/></th>
        <th><fmt:message key="lista.categoria"/></th>
        <th><fmt:message key="lista.prioridad"/></th>
        <th><fmt:message key="lista.fecha"/></th>
        <th><fmt:message key="lista.estado"/></th>
        <th><fmt:message key="lista.acciones"/></th>
    </tr></thead>
    <tbody>
    <c:forEach var="t" items="${tareas}">
        <tr>
            <td class="${t.completada ? 'completada' : ''}">${t.titulo}</td>
            <td>${t.categoria}</td>
            <td>${t.prioridad}</td>
            <td><fmt:formatDate value="${t.fechaLimite}" pattern="dd/MM/yyyy"/></td>
            <td>${t.completada ? "Completada" : "Pendiente"}</td>
            <td>
                <a href="${pageContext.request.contextPath}/app?comando=completar&id=${t.id}">
                    <fmt:message key="lista.completar"/></a> |
                <a href="${pageContext.request.contextPath}/app?comando=eliminar&id=${t.id}"
                   onclick="return confirm('¿Eliminar esta tarea?')">
                    <fmt:message key="lista.eliminar"/></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>