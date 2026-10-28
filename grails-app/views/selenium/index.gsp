<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main2"/>
    <title> MFC Automatizado</title>

<style>

.hover-card {
    transition: transform 0.3s ease, box-shadow 0.3s ease, background-color 0.3s ease, border-color 0.3s ease;
    will-change: transform, box-shadow, background-color;
}

.hover-card:hover {
    transform: translateY(-4px); /* Elevación ligeramente menor para mayor sutileza */
    box-shadow: 0 8px 15px rgba(0, 0, 0, 0.08) !important; /* Sombra más tenue */
}

.hover-card-info {
    background-color: #f7fbfe; /* Casi blanco con un toque azul */
    border-color: #e2f0fe;
    color: #055160;
}
.hover-card-info:hover {
    background-color: #ecf4fc !important; /* Cambio de color muy suave */
    border-color: #d2e6fc !important;
}

.hover-card-success {
    background-color: #f8fdfa; /* Casi blanco con un toque verde */
    border-color: #e2f5ec;
    color: #0f5132;
}
.hover-card-success:hover {
    background-color: #edfcf4 !important; /* Cambio de color muy suave */
    border-color: #d1f2e2 !important;
}

</style>

</head>
<body>

<div class="container-lg" style="margin-top: 10px;">
    <div class="row g-4 justify-content-center">        
        <div class="col-12 col-md-5">
            <a class="card h-100 text-decoration-none shadow-sm border-1 hover-card hover-card-info"
               href="${createLink(controller: 'selenium', action: 'prueba')}" onclick="cargar()">
                <div class="card-body p-4">
                    <div class="d-flex align-items-center justify-content-between mb-2">
                        <h6 class="card-title mb-0 fw-semibold"><i class="fa fa-vial"></i> Pruebas</h6>                            
                    </div>
                    <p class="card-text text-body-secondary mb-0">
                        Conjunto de pruebas de cargado a los diferentes módulos del MFC usando el sistema de automatización.
                    </p>
                </div>
            </a>
        </div>       
        <div class="col-12 col-md-5">
            <a class="card h-100 text-decoration-none shadow-sm border-1 hover-card hover-card-success"
               href="${createLink(controller: 'selenium', action: 'upload')}" onclick="cargar()" >
                <div class="card-body p-4">
                    <div class="d-flex align-items-center justify-content-between mb-2">
                        <h6 class="card-title mb-0 fw-semibold"><i class="fa fa-file-excel"></i>Cargado con excel</h6>
                    </div>
                    <p class="card-text text-body-secondary mb-0">
                        Cargado de datos a los diferentes módulos del MFC mediante un archivo excel personalizado por el usuario.
                    </p>
                </div>
            </a>
        </div>
    </div>
</div>

<script type="text/javascript">

function cargar() {
    cargarLoader("Cargando...")
}

window.addEventListener('pageshow', function(event) {
    if (event.persisted) {
        window.location.reload();
    }
});
</script>

</body>
</html>
