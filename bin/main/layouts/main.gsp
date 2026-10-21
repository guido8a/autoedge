<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title><g:layoutTitle default="Grails"/></title>

    <%-- Assets CSS --%>
    <asset:stylesheet src="application.css"/>
    <asset:stylesheet src="fontawesome.min.css"/>
    
    <%-- Assets JS --%>
    <asset:javascript src="application.js"/>
    <asset:javascript src="jquery-3.3.1.min.js"/>
    <asset:javascript src="jquery.validate.min.js"/>
    <asset:javascript src="all.min.js"/>
    <asset:javascript src="javascripts/bootbox.min.js"/>
    

    <script type="text/javascript">
        var spinner = $('<asset:image src="spinner.gif" style="padding: 40px;"/>');
    </script>
    <g:layoutHead/>
</head>

<body>

<nav class="navbar navbar-expand-lg bg-body border-bottom shadow-sm" >
    <div class="container-lg">
        <a class="navbar-brand d-flex align-items-center" style="align-items: center;" href="${request.contextPath}/">
            <i class="fa fa-wand-magic-sparkles text-info fa-2x"></i> MFC automatizado
        </a>
    </div>
</nav>

<div class="bg-body-tertiary">
    <div class="container-lg py-4">
        <g:flashMessages />
        <g:layoutBody/>
    </div>
</div>

<footer class="border-top py-5" role="contentinfo">
    <div class="container-lg">
        <div class="row g-4">
            <div class="col-12 col-md-6">
                <a class="card h-100 text-decoration-none shadow-sm border-1"
                   href="http://www.tedein.com.ec/agente" target="_blank" rel="noopener">
                    <div class="card-body p-4">
                        <div class="d-flex align-items-center justify-content-between mb-2">
                            <h6 class="card-title mb-0 fw-semibold"><i class="fa fa-check"></i> Uso del sistema</h6>                            
                        </div>
                        <p class="card-text text-body-secondary mb-0">
                            El sistema de carga automática de datos al MFC puede ser usado
                            durante un mes con un único pago de $20, durante este tiempo se
                            podrá utilizar en forma ilimitada para ofertas que se puedan 
                            tener en el MFC si pagos adicionales. 
                        </p>
                    </div>
                </a>
            </div>
            <div class="col-12 col-md-6">
                <a class="card h-100 text-decoration-none shadow-sm border-1"
                   href="https://www.tedein.com.ec/agente/manual" target="_blank" rel="noopener">
                    <div class="card-body p-4">
                        <div class="d-flex align-items-center justify-content-between mb-2">
                            <h6 class="card-title mb-0 fw-semibold"><i class="fa fa-circle-info"></i>Documentación</h6>
                        </div>
                        <p class="card-text text-body-secondary mb-0">
                            El manual de uso del sistema permite familirizarse rápidamente con 
                            su utilización  a partir de un archivo excel se puede cargar los datos
                            al MFC en una o varias ofertas, hacien reutilizable los datos de personal,
                            experiencia de la empresa y documentos de anexos.
                        </p>
                    </div>
                </a>
            </div>
        </div>
    </div>
</footer>
<div id="spinner" class="position-absolute top-0 end-0 p-1" style="display:none;">
    <div class="spinner-border spinner-border-sm" role="status">
        <span class="visually-hidden">Loading...</span>
    </div>
</div>
<asset:javascript src="application.js"/>

</body>
</html>
