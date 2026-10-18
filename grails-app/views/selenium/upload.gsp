<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Subir Excel</title>
</head>
<body>

<h2>Cargar archivo Excel</h2>

<h3></h3>
<div class="bg-body-tertiary">
    <div class="container-lg py-4">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <i class="bi bi-exclamation-circle fs-2 me-2"></i>
            Los archivos de anexos deben estar colocados en el disco "C" y en la carpeta 
            <strong>C:\agente\anexos</strong>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close">
            </button>
        </div>
    </div>
        


<g:uploadForm action="procesaExcel" method="POST" name="frmUpload" enctype="multipart/form-data">
    <div class="row g-3 align-items-end">
        <div class="col-md-1">
            <label for="ofrt" class="form-label">Oferta:</label>
            <input type="text" name="ofrt" id="ofrt" class="form-control required"/>
        </div>
        <div class="col-md-6">
            <label for="file" class="form-label">Archivo Excel con los datos a subir:</label>
            <input type="file" name="archivo" id="archivo" accept=".xlsx" class="form-control"/>
            
        </div>
        <div class="col-md-2" style="margin-left: 0px">
            <a href="#" class="btn btn-success" id="btnSubmit">Procesar Archivo</a>
        </div>
    </div>
</g:uploadForm>


<script type="text/javascript">
    $(function () {
        $("#btnSubmit").click(function () {
            if ($("#frmUpload").valid()) {
                $(this).replaceWith(spinner);
                $("#frmUpload").submit();
            }
        });
    });
</script>

</body>
</html>