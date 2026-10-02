<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"   uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${applicationScope.nombreApp}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<h1>${applicationScope.nombreApp}</h1>
<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>

<a href="${pageContext.request.contextPath}/app?comando=formulario">+ Nueva tarea</a>

<table>
    <thead><tr>
        <th>Título</th><th>Categoría</th><th>Prioridad</th>
        <th>Fecha límite</th><th>Estado</th><th>Acciones</th>
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
                <a href="${pageContext.request.contextPath}/app?comando=completar&id=${t.id}">Completar</a> |
                <a href="${pageContext.request.contextPath}/app?comando=eliminar&id=${t.id}"
                   onclick="return confirm('¿Eliminar esta tarea?')">Eliminar</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>