<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>AutoEdge - Prueba</title>
</head>
<body>
    <h1>Prueba de Selenium con Edge</h1>
    
    <g:if test="${resultado.exito}">
        <p><strong>✅ Éxito:</strong> ${resultado.titulo}</p>
        <p><strong>URL:</strong> ${resultado.url}</p>
    </g:if>
    <g:else>
        <p><strong>❌ Error:</strong> ${resultado.error}</p>
        <p><strong>Causa:</strong> ${resultado.causa}</p>
    </g:else>
</body>
</html>
