<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main" />
    <title>Subir Excel</title>

    <style type="text/css">
        .alert-success .invalid-feedback.posicionado {
            color: #dc3545 !important;
            font-size: 15px;
            font-weight: bold;
            margin-top: 4px;
            display: block;
            clear: both;
        }
    </style>

    </style>
</head>

<body>

    <h2><i class="fa fa-file-excel text-success"></i> Carga de datos mediante un archivo Excel</h2>

    <div class="container-lg py-4 alert alert-success">
        <div class="row justify-content-center">
            <div class="col-5 col-md-4" style="font-size: 18px;">
                <i class="fa fa-file-pdf"></i> Cargar compromiso de participación
            </div>
        </div>

        <g:uploadForm action="procesaExcelParticipacion" method="POST" name="frmUploadParticipacion"
            enctype="multipart/form-data">
            <div class="row g-3">
                <div class="col-md-1">
                    <label for="ofrt" class="form-label">Oferta:</label>
                    <input type="text" name="ofrtParticipacion" id="ofrtParticipacion" class="form-control required"
                        min="1" value="${1}" />
                </div>
                <div class="col-md-6">
                    <label for="file" class="form-label">Archivo Excel con los datos a subir:</label>
                    <input type="file" name="archivoParticipacion" id="archivoParticipacion" accept=".xlsx"
                        class="form-control required" />

                </div>
                <div class="col-md-3" style="margin-top: 47px;">
                    <a href="#" class="btn btn-success" id="btnSubmitParticipacion"><i class="fa fa-upload"></i>
                        Procesar Archivo
                    </a>
                </div>
            </div>
        </g:uploadForm>
    </div>

    <div class="container-lg py-4 alert alert-success">
        <div class="row justify-content-center">
            <div class="col-5 col-md-4" style="font-size: 18px;">
                <i class="fa fa-file-pdf"></i> Cargar experiencia laboral
            </div>
        </div>

        <g:uploadForm action="procesaExcelExperienciaLaboral" method="POST" name="frmUploadExperiencia"
            enctype="multipart/form-data">
            <div class="row g-3">
                <div class="col-md-1">
                    <label for="ofrt" class="form-label">Oferta:</label>
                    <input type="text" name="ofrtExperiencia" id="ofrtExperiencia" class="form-control required" min="1"
                        value="${1}" />
                </div>
                <div class="col-md-6">
                    <label for="file" class="form-label">Archivo Excel con los datos a subir:</label>
                    <input type="file" name="archivoExperiencia" id="archivoExperiencia" accept=".xlsx"
                        class="form-control required" />

                </div>
                <div class="col-md-3" style="margin-top: 47px;">
                    <a href="#" class="btn btn-success" id="btnSubmitExperiencia"><i class="fa fa-upload"></i> Procesar
                        Archivo
                    </a>
                </div>
            </div>
        </g:uploadForm>
    </div>

    <div class="container-lg py-4 alert alert-success">

        <div class="row justify-content-center">
            <div class="col-5 col-md-4" style="font-size: 18px;">
                <i class="fa fa-file-pdf"></i> Cargar Anexos
            </div>
        </div>

        <div class="alert alert-warning alert-dismissible fade show" role="alert">
            <i class="fa fa-exclamation-triangle fa-2x"></i>
            Los archivos de anexos deben estar colocados en el disco "C" y en la carpeta
            <strong>C:\agente\anexos</strong>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close">
            </button>
        </div>

        <g:uploadForm action="procesaExcel" method="POST" name="frmUpload" enctype="multipart/form-data">
            <div class="row g-3">
                <div class="col-md-1">
                    <label for="ofrt" class="form-label">Oferta:</label>
                    <input type="text" name="ofrt" id="ofrt" class="form-control required" min="1" value="${1}" />
                </div>
                <div class="col-md-6">
                    <label for="file" class="form-label">Archivo Excel con los datos a subir:</label>
                    <input type="file" name="archivo" id="archivo" accept=".xlsx" class="form-control required" />

                </div>
                <div class="col-md-3" style="margin-top: 47px;">
                    <a href="#" class="btn btn-success" id="btnSubmit"><i class="fa fa-upload"></i>
                        Procesar Archivo
                    </a>
                </div>
            </div>
        </g:uploadForm>
    </div>

    <div class="container-lg py-4 alert alert-success">
        <div class="row justify-content-center">
            <div class="col-5 col-md-4" style="font-size: 18px;">
                <i class="fa fa-file-pdf"></i> Cargar experiencia del oferente
            </div>
        </div>

        <g:uploadForm action="procesaExcelExperienciaOferente" method="POST" name="frmUploadExperienciaOferente"
            enctype="multipart/form-data">
            <div class="row g-3">
                <div class="col-md-1">
                    <label for="ofrt" class="form-label">Oferta:</label>
                    <input type="text" name="ofrtExperienciaOferente" id="ofrtExperienciaOferente"
                        class="form-control required" min="1" value="${1}" />
                </div>
                <div class="col-md-6">
                    <label for="file" class="form-label">Archivo Excel con los datos a subir:</label>
                    <input type="file" name="archivoExperienciaOferente" id="archivoExperienciaOferente" accept=".xlsx"
                        class="form-control required" />

                </div>
                <div class="col-md-3" style="margin-top: 47px;">
                    <a href="#" class="btn btn-success" id="btnSubmitExperienciaOferente"><i class="fa fa-upload"></i>
                        Procesar Archivo</a>
                </div>
            </div>
        </g:uploadForm>
    </div>

    <div class="col-md-2" style="margin-top: 20px;">
        <a href="#" class="btn btn-success" id="btnRegresar"><i class="fa fa-arrow-left"></i> Regresar</a>
    </div>

    <script type="text/javascript">
        var configValidacion = {
            errorClass: 'is-invalid',
            validClass: 'is-valid',

            errorPlacement: function (error, element) {
                error.addClass('invalid-feedback posicionado');
                if (element.attr('name').indexOf('ofrt') !== -1) {
                    element.closest('.row').append(error);
                } else {
                    element.after(error);
                }
            },

            success: function (label, element) {
                $(element).removeClass('is-invalid');
                label.remove();
            },

            messages: {
                ofrt: { required: "Obligatorio", min: "Debe ser mayor o igual a 1" },
                ofrtParticipacion: { required: "Obligatorio.", min: "Debe ser mayor o igual a 1" },
                ofrtExperiencia: { required: "Obligatorio", min: "Debe ser mayor o igual a 1" },
                ofrtExperienciaOferente: { required: "Obligatorio", min: "Debe ser mayor o igual a 1" },
                archivo: { required: "Obligatorio" },
                archivoParticipacion: { required: "Obligatorio" },
                archivoExperiencia: { required: "Obligatorio" },
                archivoExperienciaOferente: { required: "Obligatorio" }
            }
        };

        $("#btnRegresar").click(function () {
            location.href = "${createLink(controller: 'selenium', action: 'index')}"
        });

        $(function () {

            $("#frmUpload").validate(configValidacion);
            $("#frmUploadParticipacion").validate(configValidacion);
            $("#frmUploadExperiencia").validate(configValidacion);
            $("#frmUploadExperienciaOferente").validate(configValidacion);

            $("#btnSubmit").click(function () {
                if ($("#frmUpload").valid()) {
                    var spinner = cargarLoader("Procesando...");
                    $("#frmUpload").submit();
                }
            });

            $("#btnSubmitParticipacion").click(function () {
                if ($("#frmUploadParticipacion").valid()) {
                    var spinner = cargarLoader("Procesando...");
                    $("#frmUploadParticipacion").submit();
                }
            });

            $("#btnSubmitExperiencia").click(function () {
                if ($("#frmUploadExperiencia").valid()) {
                    var spinner = cargarLoader("Procesando...");
                    $("#frmUploadExperiencia").submit();
                }
            });

            $("#btnSubmitExperienciaOferente").click(function () {
                if ($("#frmUploadExperienciaOferente").valid()) {
                    var spinner = cargarLoader("Procesando...");
                    $("#frmUploadExperienciaOferente").submit();
                }
            });

        });
    </script>

</body>

</html>