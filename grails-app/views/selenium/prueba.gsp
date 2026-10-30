<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Panel de Control - Selenium Edge</title>
</head>
<body>

<div class="container">
<div class="row" style="font-size: 20px;">
<div class="col-md-12" style="text-align: left;">
<h2><i class="fa-regular fa-square-check"></i>Demo de ingreso de datos al MFC</h2>
</div>
</div>
</div>

<div class="container " style="text-align: center; margin-top: 10px">
    <div class="row alert alert-secondary" style="margin-top: 5px;">
        <div class="col-md-5" style="font-size: 18px; text-align: left;"> <i class="fa fa-users"></i>
         Ingreso de compromiso de participación</div>
        <div class="col-md-2"></div>
        <div class="col-md-2">
            <label style="font-size: 16px">Número de la oferta</label>
        </div>       
        <div class="col-md-1">
            <g:textField name="numero" id="numeroOferta" class="form-control" value="${1}"/>
        </div>
        <div class="col-md-2">
            <a href="#" class="btn btn-dark" id="btnAceptar"><i class="fa fa-check"></i> Aceptar</a>
        </div>
    </div>
</div>

<div class="container " style="text-align: center; margin-top: 10px">
    <div class="row alert alert-secondary" style="margin-top: 5px;">
        <div class="col-md-4" style="font-size: 18px; text-align: left;">  <i class="fa fa-list"></i>
           Ingreso de experiencia laboral</div>
        <div class="col-md-2">
            <label style="font-size: 16px">Número de la oferta</label>
        </div>       
        <div class="col-md-1">
            <g:textField name="numeroOfertaExperiencia" id="numeroOfertaExperiencia" class="form-control" value="${1}"/>
        </div>
            <div class="col-md-2">
            <label style="font-size: 16px">Número del personal </label>
        </div>
        <div class="col-md-1">
            <g:textField name="numeroPersonal" id="numeroPersonal" class="form-control" value="${1}"/>
        </div>
        <div class="col-md-2">
           <a href="#" class="btn btn-dark" id="btnCargarExperiencia"><i class="fa fa-check"></i> Aceptar</a>
        </div>
    </div>
</div>

<div class="container " style="text-align: center; margin-top: 10px">
    <div class="row alert alert-secondary" style="margin-top: 5px;">
        <div class="col-md-5" style="font-size: 18px; text-align: left;"> <i class="fa fa-upload"></i> 
           Cargar archivos de anexos</div>
        <div class="col-md-2"></div>
        <div class="col-md-2">
            <label style="font-size: 16px">Número de la oferta</label>
        </div>       
        <div class="col-md-1">
            <g:textField name="numeroOfertaCargaArchivo" id="numeroOfertaCargaArchivo" class="form-control" value="${1}"/>
        </div>
        <div class="col-md-2">
            <a href="#" class="btn btn-dark" id="btnCargarArchivo"><i class="fa fa-check"></i> Aceptar</a>
        </div>
    </div>
</div>
<div class="container">
<div class="row ">
<div class="col-md-3"></div>
<div class="col-md-6 alert alert-warning" style="text-align: left; font-size: 13px;">
<i class="fa fa-exclamation-triangle text-warning fa-2x"></i>
   Los archivos de prueba de carga de anexo debe tener las siguientes características:
    <ul>
    <li>El nombre del archivo debe ser "anexo"</li>
    <li>El formato debe ser pdf</li>
    <li>Debe estar almacenado en C:/agente/anexos</li>
    </ul>
</div>
</div>
</div>

<div class="container " style="text-align: center; margin-top: 10px">
    <div class="row alert alert-secondary" style="margin-top: 5px;">
        <div class="col-md-5" style="font-size: 18px; text-align: left;"> <i class="fa fa-address-book"></i>
           Ingreso experiencia del oferente</div>
        <div class="col-md-2"></div>
        <div class="col-md-2">
            <label style="font-size: 16px">Número de la oferta</label>
        </div>       
        <div class="col-md-1">
           <g:textField name="numeroOfertaExperienciaOferente" id="numeroOfertaExperienciaOferente" 
           class="form-control" value="${1}"/>
        </div>
        <div class="col-md-2">
           <a href="#" class="btn btn-dark" id="btnCargarExperienciaOferente"><i class="fa fa-check"></i> Aceptar</a>
        </div>
    </div>
</div>

    <div class="col-md-2">
        <a href="#" class="btn btn-dark" id="btnRegresar"><i class="fa fa-arrow-left"></i> Regresar</a>
    </div>

<script type="text/javascript">

    $("#btnRegresar").click(function () {
        location.href="${createLink(controller: 'selenium', action: 'index')}"
    });

    $("#btnAceptar").click(function () {
        var no = $("#numeroOferta").val();
        cargarLoader("Cargando...")
        location.href="${createLink(controller: 'selenium', action: 'iniciar')}?id=" + no
    });

    $("#btnCargarExperiencia").click(function () {
        var numeroOferta = $("#numeroOfertaExperiencia").val();
        var numeroPersonal = $("#numeroPersonal").val();
        cargarLoader("Cargando...")
        location.href="${createLink(controller: 'selenium', action: 'iniciarCargaExperienciaLaboral')}?id=" + numeroOferta + "&personal=" + numeroPersonal
    });

    $("#btnCargarArchivo").click(function () {
        var no = $("#numeroOfertaCargaArchivo").val();
        cargarLoader("Cargando...")
        location.href="${createLink(controller: 'selenium', action: 'iniciarCargaAnexos')}?id=" + no
    });

    $("#btnCargarExperienciaOferente").click(function () {
        var no = $("#numeroOfertaExperienciaOferente").val();
        cargarLoader("Cargando...")
        location.href="${createLink(controller: 'selenium', action: 'iniciarCargaExperienciaOferente')}?id=" + no
    });


</script>

</body>
</html>
