package autoedge

import grails.converters.JSON

class TestController {

    def seleniumService

    def index() {
        def resultado = seleniumService.probarNavegacion()
        render resultado as JSON
    }
}
