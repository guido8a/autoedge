package autoedge

import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class SeleniumController {

    SeleniumService seleniumService

    // Abre el panel visual (index.gsp)
    def index() {
       render(view: "index")
    }

     def prueba() {
    //    render(view: "prueba")
    }

    def iniciar() {
        println "=== Petición web recibida: Iniciando Selenium ==="

        String numeroOferta = params.id

        try {
          seleniumService.ejecutarAutomatizacion(Integer.parseInt(numeroOferta))

            flash.error = "Automatización correcta de ingreso de compromiso de participación en la oferta ${numeroOferta}"
            redirect(action: "prueba")
        } catch (Exception e) {
              redirect(action: "error", params: [mensajeError: e.message, seccion: "carga de anexos"])
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

    def upload() {
    }

    /**
     * Recibe el archivo subido y lo procesa.
     * POST /autoedge/selenium/upload
     */
    def procesaExcel() {
        println "params: $params"
        def f = request.getFile('archivo') 

        // 1. Validar que el archivo llegó y no está vacío
        if (!f || f.empty) {
            flash.error = "Debes seleccionar un archivo Excel."
            redirect(action: "index")
            return
        } else {
            println "archivo con contenido"
        }

        def os = System.getProperty("os.name").toLowerCase()
        def path = "" 

        if (os.contains("win")) {
            println "Estás en Windows"
            path = "C:\\agente\\oferta_${params.ofrt}" 
        } else if (os.contains("linux")) {
            println "Estás en Linux"
            path = "/var/agente/oferta_${params.ofrt}" 
        } else if (os.contains("mac")) {
            println "Estás en macOS"
            path = "/var/agente/oferta_${params.ofrt}" 
        }
        new File(path).mkdirs()

        // 2. Obtener nombre original y extensión
        def fileName = f.getOriginalFilename()
        def parts = fileName.split("\\.")
        def ext = parts.length > 1 ? parts[parts.length - 1] : ""

        // 3. Validar extensión
        if (ext != "xlsx") {
            flash.error = "El archivo debe tener extensión .xlsx (recibido: .${ext})."
            redirect(action: "index")
            return
        }
        println "archivo xlsx"

        // 4. Construir el nombre final: "oferta_<obra>.xlsx"
        def nombreFinal = "oferta_" + params.ofrt + "." + ext

        def pathFile = path + File.separator + nombreFinal

        // 6. Borrar el archivo anterior si existe
        def oldFile = new File(pathFile)
        if (oldFile.exists()) {
            oldFile.delete()
        }
        
        // 7. Guardar el archivo subido en el nuevo path
        f.transferTo(new File(pathFile))
        
        // 8. Procesar el Excel
        def htmlInfo = "", errores = "", doneHtml = "", done = 0

        println "inicia procesar excel"

        try {
            FileInputStream excelFile = new FileInputStream(pathFile)
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile)

            int hojas = workbook.getNumberOfSheets()
            println "Número de hojas: $hojas"

            def txHoja = "", hjRubro = ""

            // Recorre las hojas
            for (int hj = 0; hj < hojas; hj++) {

                XSSFSheet sheet = workbook.getSheetAt(hj)
                txHoja = sheet.getSheetName()

                // Orden numérico del nombre de la hoja (si aplica)
                println "Procesando hoja: $hj --> $txHoja"

                Iterator rows = sheet.rowIterator()
                def fila = 0

                // -------- Ejemplo: procesar solo la hoja "Archivos" --------
                if (txHoja.toLowerCase() == "archivos") {
                    println "Procesando la hoja **archivos**"

                    def rgst = []

                    while (rows.hasNext()) {
                        XSSFRow row = (XSSFRow) rows.next()
                        fila++

                        Iterator cells = row.cellIterator()
                       
                        while (cells.hasNext()) {
                            XSSFCell cell = (XSSFCell) cells.next()

                    if(cell.getStringCellValue() != 'Nombre del archivo' && cell.getStringCellValue() != 'Descripción del archivo máx 50c'){
                            switch (cell.getCellType()) {
                                case CellType.NUMERIC:
                                    rgst.add(cell.getNumericCellValue())
                                    break
                                case CellType.STRING:
                                    rgst.add(cell.getStringCellValue())
                                    break
                                case CellType.FORMULA:
                                    rgst.add(cell.getCellFormula())  // o cell.getRawValue()
                                    break
                                case CellType.BOOLEAN:
                                    rgst.add(cell.getBooleanCellValue())
                                    break
                                // default:
                                //     rgst.add('')
                            }
                            }
                        }        
                    }

                                  // 👇 Aquí usarías los datos para la automatización
                        println "reg1: $rgst"
                        // if(rgst[0] != 'Nombre del archivo' && rgst[0] && rgst[1]) {
                        //     println "procesa --> oferta: ${params.ofrt}, $rgst"
                        seleniumService.cargaAnexos(params.ofrt?.toInteger(), rgst)
                        // }
                }


      // -------- procesar hoja  "empresa" --------
                // if (txHoja.toLowerCase() == "empresa") {
                //     println "Procesando la hoja **empresa**"
                //     while (rows.hasNext() && (fila < 100)) {
                //         XSSFRow row = (XSSFRow) rows.next()
                //         fila++

                //         Iterator cells = row.cellIterator()
                //         def rgst = []

                //         while (cells.hasNext()) {
                //             XSSFCell cell = (XSSFCell) cells.next()
                //             switch (cell.getCellType()) {
                //                 case CellType.NUMERIC:
                //                     rgst.add(cell.getNumericCellValue())
                //                     break
                //                 case CellType.STRING:
                //                     rgst.add(cell.getStringCellValue())
                //                     break
                //                 case CellType.FORMULA:
                //                     rgst.add(cell.getCellFormula())  // o cell.getRawValue()
                //                     break
                //                 case CellType.BOOLEAN:
                //                     rgst.add(cell.getBooleanCellValue())
                //                     break
                //                 default:
                //                     rgst.add('')
                //             }
                //         }

                //         println "reg1: $rgst"
                //         if(rgst[0] != 'No.' && rgst[0] && rgst[1]) {
                //             println "procesa --> oferta: ${params.ofrt}, $rgst"
                //             // seleniumService.cargaAnexos(params.oferta, rgst)
                //         }

                //     }
                // }

            }

            workbook.close()
            excelFile.close()

            flash.message = "Archivo '${nombreFinal}' subido y procesado correctamente."
        } catch (Exception e) {
            log.error("Error procesando el Excel", e)
            flash.error = "Error al procesar el Excel: ${e.message}"
        }

        // redirect(action: "archivoSubido")
        redirect(action: "upload")
    }

    def archivoSubido(){

    }

    def error(){
     return [error: params.mensajeError, seccion: params.seccion]    
    }

}
