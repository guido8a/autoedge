
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

<nav class="navbar navbar-expand-lg bg-body border-bottom shadow-sm">
    <div class="container-fluid d-flex justify-content-center">
        <a class="navbar-brand d-flex align-items-center justify-content-center text-center gap-2" href="#">
            <i class="fa fa-robot text-info fa-2x"></i> 
            <span>MFC (Módulo Facilitador de Contratación Pública) automatizado</span>
        </a>
    </div>
</nav>

<div class="bg-body-tertiary">
    <div class="container-lg py-4">
        <g:layoutBody/>
    </div>
</div>

<div class="container-fluid d-flex justify-content-center mb-3">
    <div class="overflow-hidden" style="max-width: 250px; max-height: 150px;">
        <asset:image src="sercop.jpeg" class="img-fluid w-100 h-100" style="object-fit: contain;" alt="Principal"/>
    </div>
</div>


<div id="spinner" class="position-absolute top-0 end-0 p-1" style="display:none;">
    <div class="spinner-border spinner-border-sm" role="status">
        <span class="visually-hidden">Loading...</span>
    </div>
</div>

<asset:javascript src="application.js"/>

</body>
</html>
