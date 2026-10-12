<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Subir Excel - AutoEdge</title>
    <%-- <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        .form-box { max-width: 500px; padding: 20px; border: 1px solid #ccc; border-radius: 8px; background: #f9f9f9; }
        .form-box label { display: block; margin-top: 12px; font-weight: bold; }
        .form-box input[type="text"],
        .form-box input[type="file"] { width: 100%; padding: 6px; margin-top: 4px; box-sizing: border-box; }
        .form-box button { margin-top: 18px; padding: 10px 20px; background: #0078d4; color: #fff; border: none; border-radius: 4px; cursor: pointer; }
        .form-box button:hover { background: #005a9e; }
        .alert { padding: 10px; margin-top: 15px; border-radius: 4px; }
        .alert-error { background: #fdecea; color: #b71c1c; border: 1px solid #f5c6cb; }
        .alert-info  { background: #e8f4fd; color: #0c5460; border: 1px solid #bee5eb; }
    </style> --%>
</head>
<body>

<h2>Automatización Edge - Carga de Excel</h2>

<div class="form-box">

    <g:if test="${flash.error}">
        <div class="alert alert-error">${flash.error}</div>
    </g:if>
    <g:if test="${flash.message}">
        <div class="alert alert-info">${flash.message}</div>
    </g:if>

    <g:form action="upload" method="POST" enctype="multipart/form-data">

        <label for="obra">Oferta:</label>
        <input type="text" name="obra" id="obra" style="width: 80px;"
             value="${params.obra ?: ''}" required/>

        <label for="file">Archivo Excel (.xlsx):</label>
        <input type="file" name="file" id="file" accept=".xlsx" required/>

        <button type="submit">Subir y procesar</button>

        <div id="list-grupo" class="col-md-12" role="main">
            <div class="col-md-12" style="margin-top: 20px; margin-bottom: 20px">
                <div class="col-md-2"><b>Archivo Excel a subir:</b></div>
                <div class="col-md-4">
                    <input type="file" class="required" name="file" multiple accept=".xlsx" style="width: 100%; font-size: 12pt" value="Arch"/>
                </div>
                <div class="col-md-4">
                    <a href="#" class="btn btn-info" id="btnSubmitCrono"><i class="fa fa-upload"></i>
                        Procesar archivo APUs
                    </a>
                </div>
                <div class="col-md-4">
                    <a href="#" class="btn btn-success" id="btnRubros"><i class="fa fa-check"></i>
                        1. Comprobar Hojas de APUs
                    </a>
                </div>
                <div class="col-md-4">
                    <a href="#" class="btn btn-success" id="btnRevisar"><i class="fa fa-check"></i>
                        2. Comprobar APUs
                    </a>
                </div>
            </div>
        </div>
    </g:form>
</div>

</body>
</html>