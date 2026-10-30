<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main" />
    <title>Subir Excel</title>

    <style type="text/css">
        .alert-secondary .invalid-feedback.posicionado {
            color: #dc3545 !important;
            font-size: 15px;
            font-weight: bold;
            margin-top: 4px;
            display: block;
            clear: both;
        }
    </style>
</head>

<body>
    <div class="container-lg py-1">
        <div class="row justify-content-center">
            <div class="col-lg-7">
                <h4>
                    <i class="fa fa-file-excel text-success"></i> Cargar datos al MFC desde un archivo Excel (datos
                    reales)
                </h4>
            </div>
        </div>
    </div>

    <div class="container-lg py-1">
        <div class="row justify-content-center">
            <div class="col-lg-9 alert alert-secondary p-4">
                <div class="row justify-content-center mb-3 text-success">
                    <div class="col-auto" style="font-size: 18px; font-weight: bold;">
                        <i class="fa fa-file-pdf"></i> Cargar compromiso de participación
                    </div>
                </div>
                <g:uploadForm action="procesaExcelParticipacion" method="POST" name="frmUploadParticipacion"
                    enctype="multipart/form-data">
                    <div class="row g-3 align-items-end">
                        <div class="col-md-1">
                            <label for="ofrtParticipacion" class="form-label">Oferta:</label>
                            <input type="text" name="ofrtParticipacion" id="ofrtParticipacion"
                                class="form-control required" min="1" value="${1}" />
                        </div>

                        <div class="col-md-6">
                            <label for="archivoParticipacion" class="form-label">Archivo Excel con los datos a
                                subir:</label>
                            <input type="file" name="archivoParticipacion" id="archivoParticipacion" accept=".xlsx"
                                class="form-control required" />
                        </div>

                        <div class="col-md-5 d-flex gap-2">
                            <a href="#" class="btn btn-success w-70 text-nowrap" id="btnSubmitParticipacion">
                                <i class="fa fa-upload"></i> Procesar Archivo
                            </a>
                            <a href="#" class="btn btn-warning w-70 text-nowrap" id="btnVerFormatoCompromiso">
                                <i class="fa fa-list"></i> Formato
                            </a>
                        </div>
                    </div>
                </g:uploadForm>
            </div>
        </div>
    </div>
 
    <div class="container-lg py-1">
        <div class="row justify-content-center">
            <div class="col-lg-9 alert alert-secondary p-4">
                <div class="row justify-content-center mb-3 text-success">
                    <div class="col-auto" style="font-size: 18px; font-weight: bold;">
                        <i class="fa fa-file-pdf"></i> Cargar experiencia laboral
                    </div>
                </div>
    
                <g:uploadForm action="procesaExcelExperienciaLaboral" method="POST" name="frmUploadExperiencia"
                    enctype="multipart/form-data">
                    <div class="row g-3 align-items-end">
                        <div class="col-md-1">
                            <label for="ofrt" class="form-label">Oferta:</label>
                            <input type="text" name="ofrtExperiencia" id="ofrtExperiencia" class="form-control required"
                                min="1" value="${1}" />
                        </div>
                        <div class="col-md-6">
                            <label for="file" class="form-label">Archivo Excel con los datos a subir:</label>
                            <input type="file" name="archivoExperiencia" id="archivoExperiencia" accept=".xlsx"
                                class="form-control required" />
    
                        </div>
                        <div class="col-md-5 d-flex gap-2">
                            <a href="#" class="btn btn-success w-70 text-nowrap" id="btnSubmitExperiencia">
                                <i class="fa fa-upload"></i> Procesar Archivo
                            </a>
                            <a href="#" class="btn btn-warning w-70 text-nowrap" id="btnVerFormatoExperiencia">
                                <i class="fa fa-list"></i> Formato
                            </a>
                        </div>
                    </div>
                </g:uploadForm>
            </div>
        </div>
    </div>

     <div class="container-lg py-1">
        <div class="row justify-content-center">
            <div class="col-lg-9 alert alert-secondary p-4">
                <div class="row justify-content-center mb-3 text-success">
                    <div class="col-auto" style="font-size: 18px; font-weight: bold;">
                       <i class="fa fa-file-pdf"></i> Cargar Anexos
                    </div>
                </div>
    
                <g:uploadForm action="procesaExcel" method="POST" name="frmUpload"
                    enctype="multipart/form-data">
                    <div class="row g-3 align-items-end">
                        <div class="col-md-1">
                            <label for="ofrt" class="form-label">Oferta:</label>
                            <input type="text" name="ofrt" id="ofrt" class="form-control required" min="1" value="${1}" />
                        </div>
                        <div class="col-md-6">
                            <label for="file" class="form-label">Archivo Excel con los datos a subir:</label>
                            <input type="file" name="archivo" id="archivo" accept=".xlsx" class="form-control required" />
                        </div>
                        <div class="col-md-5 d-flex gap-2">
                            <a href="#" class="btn btn-success w-70 text-nowrap" id="btnSubmit">
                                <i class="fa fa-upload"></i> Procesar Archivo
                            </a>
                            <a href="#" class="btn btn-warning w-70 text-nowrap" id="btnVerFormatoAnexos">
                                <i class="fa fa-list"></i> Formato
                            </a>
                        </div>
                    </div>
                </g:uploadForm>
            </div>
        </div>
    </div>

     <div class="container-lg py-1">
        <div class="row justify-content-center">
            <div class="col-lg-9 alert alert-secondary p-4">
                <div class="row justify-content-center mb-3 text-success">
                    <div class="col-auto" style="font-size: 18px; font-weight: bold;">
                      <i class="fa fa-file-pdf"></i> Cargar experiencia del oferente
                    </div>
                </div>
    
                <g:uploadForm action="procesaExcelExperienciaOferente" method="POST" name="frmUploadExperienciaOferente"
                    enctype="multipart/form-data">
                    <div class="row g-3 align-items-end">
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
                        <div class="col-md-5 d-flex gap-2">
                            <a href="#" class="btn btn-success w-70 text-nowrap" id="btnSubmitExperienciaOferente">
                                <i class="fa fa-upload"></i> Procesar Archivo
                            </a>
                            <a href="#" class="btn btn-warning w-70 text-nowrap" id="btnVerFormatoExperienciaOferente">
                                <i class="fa fa-list"></i> Formato
                            </a>
                        </div>
                    </div>
                </g:uploadForm>
            </div>
        </div>
    </div>
    
    <div class="container-lg py-1">
        <div class="row justify-content-center">
            <div class="col-lg-9 d-flex justify-content-between align-items-center">
                <div>
                    <a href="#" class="btn btn-success" id="btnRegresar">
                        <i class="fa fa-arrow-left"></i> Regresar
                    </a>
                </div>
                <div>
                    <a href="#" class="btn btn-warning" id="btnVerFormatoParticipacion">
                        <i class="fa fa-list"></i> Estructura de archivos Excel
                    </a>
                </div>
            </div>
        </div>
    </div>


    <script type="text/javascript">

      $("#btnVerFormatoCompromiso").click(function () {
            cargarFormatoSeccion(3);
        })

      $("#btnVerFormatoExperiencia").click(function () {
            cargarFormatoSeccion(4);
        })

      $("#btnVerFormatoAnexos").click(function () {
            cargarFormatoSeccion(1);
        })

        $("#btnVerFormatoExperienciaOferente").click(function () {
            cargarFormatoSeccion(2);
        })

        $("#btnVerFormatoParticipacion").click(function () {
            cargarFormato()
        });

        function cargarFormato() {
            $.ajax({
                type: "POST",
                url: "${createLink(action:'columnas_ajax')}",
                data: {},
                success: function (msg) {
                    var b = bootbox.dialog({
                        id: "dlgCreateEdit",
                        title: "<i class='fa fa-file-excel'></i>  Formato Excel para el archivo ha ser cargado",
                        className: 'modal-xl',
                        message: msg,
                        buttons: {
                            cancelar: {
                                label: "<i class='fa fa-times'></i>  Cancelar",
                                className: "btn-success",
                                callback: function () {
                                }
                            }
                        } //buttons
                    }); //dialog
                } //success
            }); //ajax
        }

             function cargarFormatoSeccion(tipo) {
            $.ajax({
                type: "POST",
                url: "${createLink(action:'formato_ajax')}",
                data: {
                    tipo: tipo
                },
                success: function (msg) {
                    var b = bootbox.dialog({
                        id: "dlgCreateEdit",
                        title: "<i class='fa fa-file-excel'></i>  Formato Excel para el archivo ha ser cargado",
                        className: 'modal-xl',
                        message: msg,
                        buttons: {
                            cancelar: {
                                label: "<i class='fa fa-times'></i>  Cancelar",
                                className: "btn-success",
                                callback: function () {
                                }
                            }
                        } //buttons
                    }); //dialog
                } //success
            }); //ajax
        }


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