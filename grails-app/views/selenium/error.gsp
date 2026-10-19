<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>ERROR - Selenium Edge</title>
</head>
<body>

<div class="container " style="text-align: center; margin-top: 10px">
    <div class="row alert alert-danger" style="margin-top: 5px;">
        <div class="col-md-5" style="font-size: 18px;"> <i class="fa fa-users"></i> Error al procesar el archivo de anexos</div>
    </div>
     <div class="row alert alert-danger" style="margin-top: 5px;">
        <div class="col-md-5" style="font-size: 18px;"> <i class="fa fa-users"></i> ${error}</div>
    </div>
</div>

        <div class="col-md-2">
            <a href="#" class="btn btn-success" id="btnRegresar"><i class="fa fa-arrow-left"></i> Regresar</a>
        </div>

        <script type="text/javascript">
        
        $("#btnRegresar").click(function (){
            redirect(controller:'selenium', action:'index')
        })
        
        </script>

</body>
</html>
