<!doctype html>
<html>
    <head>
        <title><g:if env="development">Error del sistema</g:if><g:else>Error</g:else></title>
        <meta name="layout" content="main">
        <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
    </head>
    <body>
        <div class="container">
            <g:if env="development">
                <g:if test="${Throwable.isInstance(exception)}">
                    <g:renderException exception="${exception}" detailsClass="alert alert-danger" stackClass="bg-body-secondary" snippetClass="bg-body-secondary snippet" lineErrorClass="bg-danger" />
                </g:if>
                <g:elseif test="${request.getAttribute('javax.servlet.error.exception')}">
                    <g:renderException exception="${request.getAttribute('javax.servlet.error.exception')}" />
                </g:elseif>
                <g:else>
                    <ul class="errors">
                        <li>Ha ocurrido un error</li>
                        <li>Excepción: ${exception}</li>
                        <li>Mensaje: ${message}</li>
                        <li>Ruta: ${path}</li>
                    </ul>
                </g:else>
            </g:if>
            <g:else>
                <ul class="errors">
                    <li>Se ha producido un error</li>
                </ul>
            </g:else>
        </div>
    </body>
</html>
