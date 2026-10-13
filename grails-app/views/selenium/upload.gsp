<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Subir Excel</title>
</head>
<body>

<h2>Cargar archivo Excel</h2>

<g:uploadForm action="procesaExcel" method="POST" name="frmUpload" enctype="multipart/form-data">
    <div class="row g-3 align-items-end">
        <div class="col-md-2">
            <label for="obra" class="form-label">Obra:</label>
            <input type="text" name="obra" id="obra" class="form-control"/>
        </div>
        <div class="col-md-6">
            <label for="file" class="form-label">Archivo Excel:</label>
            <input type="file" name="file" id="file" accept=".xlsx" class="form-control"/>
        </div>
        <div class="col-md-2" style="margin-left: 0px">
            <a href="#" class="btn btn-success" id="btnSubmit">Subir</a>
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