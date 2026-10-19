<!doctype html>
<html>
    <head>
        <title>No existe la página</title>
        <meta name="layout" content="main">
        <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
    </head>
    <body>
        <div id="content" role="main">
            <div class="container">
                <section class="row">
                    <div class="alert alert-danger" role="alert">
                        <h1>No existe la página</h1>
                        <div><i class="bi-exclamation-circle"></i> Ruta: ${request.forwardURI}</div>
                    </div>
                </section>
            </div>
        </div>
    </body>
</html>
