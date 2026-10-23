<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main" />
    <title>ERROR - Selenium Edge</title>
</head>

<body>

    <div class="container " style="margin-top: 10px">
        <div class="row alert alert-danger" style="margin-top: 5px;">
            <div class="col-md-12" style="font-size: 18px; text-align: center;"> <i
                    class="fa fa-triangle-exclamation fa-2x"></i> Error en la automatización de ${seccion}</div>
        </div>
        <div class="row alert alert-warning" style="margin-top: 5px;">
            <div class="col-md-12" style="font-size: 18px;"> <i class="fa fa-times"></i> ERROR: ${error}</div>
        </div>
    </div>

    <div class="col-md-2">
        <a href="#" class="btn btn-success" id="btnRegresar"><i class="fa fa-arrow-left"></i> Regresar</a>
    </div>

    <script type="text/javascript">

        $("#btnRegresar").click(function () {
            cargarLoader("Cargando...")
            location.href = "${createLink(controller: 'selenium', action: 'index')}"
        });

    </script>

</body>

</html>