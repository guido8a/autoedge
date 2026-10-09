package autoedge

class SeleniumController {

    SeleniumService seleniumService

    // Abre el panel visual (index.gsp)
    def index() {
        render(view: "index")
    }

    def iniciar() {
        println "=== Petición web recibida: Iniciando Selenium ==="

        String numeroOferta = params.id

        try {
            // Se ejecuta la automatización en segundo plano o ventana visible según tu servicio
            seleniumService.ejecutarAutomatizacion(Integer.parseInt(numeroOferta))

            // Respondemos al navegador cuando termine con éxito
            render status: 200, text: "Automatización completada con éxito"
        } catch (Exception e) {
            log.error("Error al ejecutar Selenium desde la web", e)
            render status: 500, text: "Error en la automatización: ${e.message}"
        }
    }

    def iniciarCargaExperienciaLaboral() {
        println "=== Petición iniciarCargaExperienciaLaboral ==="

        String numeroOferta = params.id
        String numeroPersonal = params.personal

        try {
            seleniumService.ejecutarAutomatizacionExperienciaLaboral(Integer.parseInt(numeroOferta), Integer.parseInt(numeroPersonal))

            render status: 200, text: "Automatización iniciarCargaExperienciaLaboral completada con éxito"
        } catch (Exception e) {
            log.error("Error al ejecutar Selenium desde la web", e)
            render status: 500, text: "Error en la automatización: ${e.message}"
        }
    }

    def iniciarCargaAnexos() {
        println "=== Petición iniciarCargaAnexos ==="

        String numeroOferta = params.id

        try {
            seleniumService.ejecutarAutomatizacionCargaAnexos(Integer.parseInt(numeroOferta))

            render status: 200, text: "Automatización iniciarCargaAnexos completada con éxito"
        } catch (Exception e) {
            log.error("Error al ejecutar Selenium desde la web", e)
            render status: 500, text: "Error en la automatización: ${e.message}"
        }
    }

    def iniciarCargaExperienciaOferente() {
        println "=== Petición iniciar cargaExperienciaOferente==="

        String numeroOferta = params.id

        try {
            seleniumService.ejecutarAutomatizacionCargaExperienciaOferente(Integer.parseInt(numeroOferta))

            render status: 200, text: "Automatización cargaExperienciaOferente completada con éxito"
        } catch (Exception e) {
            log.error("Error al ejecutar Selenium desde la web", e)
            render status: 500, text: "Error en la automatización: ${e.message}"
        }
    }


}
