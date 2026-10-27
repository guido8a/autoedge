<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main" />
    <title>CARGADO CORRECTAMENTE - Selenium Edge</title>
</head>

<body>

    <div class="container " style="margin-top: 10px">
        <div class="row alert alert-success" style="margin-top: 5px;">
            <div class="col-md-12" style="font-size: 18px; text-align: center;"> <i
                    class="fa fa-thumbs-up fa-2x"></i>Automatización de ${seccion} realizada correctamente
            </div>
        </div>
        <%-- <div class="row alert alert-warning" style="margin-top: 5px;">
            <div class="col-md-12" style="font-size: 18px;"> <i class="fa fa-times"></i> ERRORES: ${error}</div>
    </div> --%>
    <div class="row alert alert-success" style="margin-top: 5px;">
        <g:if test="${seccion == ' experiencia laboral EXCEL'}">
            <div class="col-md-12" style="font-size: 18px;"> <i class="fa fa-check fa-2x texty-info"></i> Experiencia
                laboral cargada correctamente: </div>
            <ul>
                <g:each var="correcto" in="${ flash.correctos }">
                    <li>
                        <strong> Persona n: ${correcto[0]} </strong> Empresa : ${correcto[1]}
                    </li>
                </g:each>
            </ul>
        </g:if>
        <g:else>
            <g:if test="${seccion == ' experiencia oferente EXCEL'}">
                <div class="col-md-12" style="font-size: 18px;"> <i class="fa fa-check fa-2x texty-info"></i>
                    Experiencia del oferente cargada correctamente: </div>
                <ul>
                    <g:each var="correcto" in="${ flash.correctos }">
                        <li>
                            <strong> Experiencia n° ${correcto[0]} </strong> Contratante: ${correcto[1]}
                        </li>
                    </g:each>
                </ul>
            </g:if>
            <g:else>
                <div class="col-md-12" style="font-size: 18px;"> <i class="fa fa-check fa-2x texty-info"></i> Cargados
                    correctamente: </div>
                <ul>
                    <g:each var="correcto" in="${ flash.correctos }">
                        <li>
                            <strong> ${correcto[0]} </strong> ${correcto[1]}
                        </li>
                    </g:each>
                </ul>
            </g:else>
        </g:else>
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