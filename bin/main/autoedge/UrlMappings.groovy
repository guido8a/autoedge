package autoedge

class UrlMappings {

    static mappings = {
        // Redirecciona la raíz directamente al endpoint del controlador
        "/"(redirect: [controller: "selenium", action: "index"])

        "/$controller/$action?/$id?(.$format)?"{}
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
