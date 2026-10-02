<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Nueva tarea</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<h1>Nueva tarea</h1>
<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/app">
    <input type="hidden" name="comando" value="guardar">
    <label>Título:
        <input type="text" name="titulo" required>
    </label>
    <label>Categoría:
        <input type="text" name="categoria">
    </label>
    <label>Prioridad:
        <select name="prioridad">
            <option value="Alta">Alta</option>
            <option value="Media" selected>Media</option>
            <option value="Baja">Baja</option>
        </select>
    </label>
    <label>Fecha límite (yyyy-MM-dd):
        <input type="text" name="fechaLimite" placeholder="2026-12-31" required>
    </label>
    <button type="submit">Guardar</button>
    <a href="${pageContext.request.contextPath}/app">Cancelar</a>
</form>
</body>
</html>