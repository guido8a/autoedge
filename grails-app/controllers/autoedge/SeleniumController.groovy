package autoedge

import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook


class SeleniumController {

    SeleniumService seleniumService

    def index() {
       render(view: "index")
    }

     def prueba() {
    }

    //SECCION DE PRUEBAS
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
              flash.error = "Automatización correcta de ingreso de experiencia laboral en la oferta ${numeroOferta}"
            redirect(action: "prueba")
        } catch (Exception e) {
             redirect(action: "error", params: [mensajeError: e.message, seccion: "experiencia laboral"])
        }
    }

    def iniciarCargaAnexos() {
        println "=== Petición iniciarCargaAnexos ==="

        String numeroOferta = params.id

        try {
            seleniumService.ejecutarAutomatizacionCargaAnexos(Integer.parseInt(numeroOferta))
            flash.error = "Automatización correcta de carga de anexos en la oferta ${numeroOferta}"
            redirect(action: "prueba")
        } catch (Exception e) {
             redirect(action: "error", params: [mensajeError: e.message, seccion: "carga de anexos"])
        }
    }

    def iniciarCargaExperienciaOferente() {
        println "=== Petición iniciar cargaExperienciaOferente==="

        String numeroOferta = params.id

        try {
            seleniumService.ejecutarAutomatizacionCargaExperienciaOferente(Integer.parseInt(numeroOferta))

            flash.error = "Automatización correcta de ingreso de experiencia del oferente en la oferta ${numeroOferta}"
            redirect(action: "prueba")
        } catch (Exception e) {
             redirect(action: "error", params: [mensajeError: e.message, seccion: "experiencia del oferente"])
        }
    }



    //SECCION DE CARGADO DE DATOS CON EXCEL
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

    def procesaExcelParticipacion() {
        println "params participacion: $params"
        def f = request.getFile('archivoParticipacion') 

        // 1. Validar que el archivo llegó y no está vacío
        if (!f || f.empty) {
            flash.error = "Debes seleccionar un archivo Excel."
            redirect(action: "upload")
            return
        } else {
            println "archivo con contenido"
        }

        def os = System.getProperty("os.name").toLowerCase()
        def path = "" 

        if (os.contains("win")) {
            println "Estás en Windows"
            path = "C:\\agente\\oferta_${params.ofrtParticipacion}" 
        } else if (os.contains("linux")) {
            println "Estás en Linux"
            path = "/var/agente/oferta_${params.ofrtParticipacion}" 
        } else if (os.contains("mac")) {
            println "Estás en macOS"
            path = "/var/agente/oferta_${params.ofrtParticipacion}" 
        }
        new File(path).mkdirs()

        // 2. Obtener nombre original y extensión
        def fileName = f.getOriginalFilename()
        def parts = fileName.split("\\.")
        def ext = parts.length > 1 ? parts[parts.length - 1] : ""

        // 3. Validar extensión
        if (ext != "xlsx") {
            flash.error = "El archivo debe tener extensión .xlsx (recibido: .${ext})."
            redirect(action: "upload")
            return
        }
        println "archivo xlsx"

        // 4. Construir el nombre final: "oferta_<obra>.xlsx"
        def nombreFinal = "excelCompromisoParticipacion_" + params.ofrtParticipacion + "." + ext

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

                XSSFSheet sheet = workbook.getSheetAt(2)
                txHoja = sheet.getSheetName()

                println "Procesando hoja: 3 --> $txHoja"

                Iterator rows = sheet.rowIterator()
                def fila = 0

                // -------- procesar solo la hoja "Personal" --------
                if (txHoja.toLowerCase() == "personal") {
                    println "Procesando la hoja **personal**"

                    def rgst = []
                    def dataFormatter = new DataFormatter()

                    // Empezamos el bucle en 1 para saltarnos la primera fila (índice 0)
                    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                        XSSFRow row = sheet.getRow(i)
                        
                        // Si la fila está completamente vacía en el Excel, la saltamos para evitar NullPointerException
                        if (row == null) continue 
                    
                        // 1. Verificación previa: ¿La fila tiene contenido real?
                        boolean filaTieneDatos = false
                        for (int colCheck = 1; colCheck <= 14; colCheck++) {
                            XSSFCell cellCheck = row.getCell(colCheck)
                            if (cellCheck != null && cellCheck.getCellType() != CellType.BLANK) {
                                filaTieneDatos = true
                                break // Encontramos al menos un dato, la fila es válida
                            }
                        }
                    
                        // Si la fila está completamente vacía (fantasma), la ignoramos por completo
                        if (!filaTieneDatos) {
                            continue
                        }
                        fila++
                    
                        // Empezamos el bucle en 1 para saltarnos la primera columna (índice 0)
                        for (int j = 1; j <= 14; j++) {
                            XSSFCell cell = row.getCell(j)
                    
                            // Si la celda es nula o está en blanco, añadimos un valor vacío para mantener la estructura
                            if (cell == null || cell.getCellType() == CellType.BLANK) {
                                rgst.add("")
                                continue
                            }
                    
                            switch (cell.getCellType()) {
                                         case CellType.NUMERIC:
                                    if (j == 2 || j == 11) {
                                        rgst.add(dataFormatter.formatCellValue(cell).trim())
                                    } else {
                                        rgst.add(cell.getNumericCellValue())
                                    }
                                    break
                                case CellType.STRING:
                                    rgst.add(cell.getStringCellValue().trim())
                                    break
                                case CellType.FORMULA:
                                    try {
                                        switch (cell.getCachedFormulaResultType()) {
                                            case CellType.STRING: rgst.add(cell.getStringCellValue().trim()); break
                                            case CellType.NUMERIC: rgst.add(cell.getNumericCellValue()); break
                                            default: rgst.add(cell.getCellFormula().trim())
                                        }
                                    } catch (Exception e) {
                                        rgst.add(cell.getCellFormula().trim())
                                    }
                                    break
                                case CellType.BOOLEAN:
                                    rgst.add(cell.getBooleanCellValue())
                                    break
                            }
                        }        
                    }
                    println "reg1: $rgst"
                  def listaOriginal = seleniumService.ejecutarCargaCompromiso(params.ofrtParticipacion?.toInteger(), rgst)
                 flash.correctos = listaOriginal ? listaOriginal.collate(2) : []
                    workbook.close()
                    excelFile.close()
                    redirect(action: "archivoSubido", params: [seccion: " compromiso de participacion EXCEL"])
                }           
        } catch (Exception e) {
            redirect(action: "error", params: [mensajeError: e.message, seccion: " compromiso participacion EXCEL"])

        }        
    }

    //EXPERIENCIA LABORAL

      def procesaExcelExperienciaLaboral() {
        println "params experiencia laboral: $params"
        def f = request.getFile('archivoExperiencia') 

        if (!f || f.empty) {
            flash.error = "Debes seleccionar un archivo Excel."
            redirect(action: "upload")
            return
        } else {
            println "archivo con contenido"
        }

        def os = System.getProperty("os.name").toLowerCase()
        def path = "" 

        if (os.contains("win")) {
            println "Estás en Windows"
            path = "C:\\agente\\oferta_${params.ofrtExperiencia}" 
        } else if (os.contains("linux")) {
            println "Estás en Linux"
            path = "/var/agente/oferta_${params.ofrtExperiencia}" 
        } else if (os.contains("mac")) {
            println "Estás en macOS"
            path = "/var/agente/oferta_${params.ofrtExperiencia}" 
        }
        new File(path).mkdirs()

        // 2. Obtener nombre original y extensión
        def fileName = f.getOriginalFilename()
        def parts = fileName.split("\\.")
        def ext = parts.length > 1 ? parts[parts.length - 1] : ""

        // 3. Validar extensión
        if (ext != "xlsx") {
            flash.error = "El archivo debe tener extensión .xlsx (recibido: .${ext})."
            redirect(action: "upload")
            return
        }
        println "archivo xlsx"

        // 4. Construir el nombre final: "oferta_<obra>.xlsx"
        def nombreFinal = "excelExperienciaLaboral_" + params.ofrtExperiencia + "." + ext

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

                XSSFSheet sheet = workbook.getSheetAt(3)
                txHoja = sheet.getSheetName()

                println "Procesando hoja: 4 --> $txHoja"

                Iterator rows = sheet.rowIterator()
                def fila = 0

                // -------- procesar solo la hoja "experiencia laboral" --------
                if (txHoja.toLowerCase() == "experiencia") {
                    println "Procesando la hoja **experiencia**"

                    def rgst = []
                    def dataFormatter = new DataFormatter()

                    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                        XSSFRow row = sheet.getRow(i)
                        
                        // Si la fila está completamente vacía en el Excel, la saltamos para evitar NullPointerException
                        if (row == null) continue 
                    
                        // 1. Verificación previa: ¿La fila tiene contenido real?
                        boolean filaTieneDatos = false
                        for (int colCheck = 0; colCheck <= 8; colCheck++) {
                            XSSFCell cellCheck = row.getCell(colCheck)
                            if (cellCheck != null && cellCheck.getCellType() != CellType.BLANK) {
                                filaTieneDatos = true
                                break // Encontramos al menos un dato, la fila es válida
                            }
                        }
                    
                        // Si la fila está completamente vacía (fantasma), la ignoramos por completo
                        if (!filaTieneDatos) {
                            continue
                        }
                        fila++
                    
                        for (int j = 0; j <= 8; j++) {
                            XSSFCell cell = row.getCell(j)
                    
                            // Si la celda es nula o está en blanco, añadimos un valor vacío para mantener la estructura
                            if (cell == null || cell.getCellType() == CellType.BLANK) {
                                rgst.add("")
                                continue
                            }
                    
                            switch (cell.getCellType()) {
                                         case CellType.NUMERIC:
                                    // if (j == 2 || j == 11) {
                                        rgst.add(dataFormatter.formatCellValue(cell).trim())
                                    // } else {
                                    //     rgst.add(cell.getNumericCellValue())
                                    // }
                                    break
                                case CellType.STRING:
                                    rgst.add(cell.getStringCellValue().trim())
                                    break
                                case CellType.FORMULA:
                                    try {
                                        switch (cell.getCachedFormulaResultType()) {
                                            case CellType.STRING: rgst.add(cell.getStringCellValue().trim()); break
                                            case CellType.NUMERIC: rgst.add(cell.getNumericCellValue()); break
                                            default: rgst.add(cell.getCellFormula().trim())
                                        }
                                    } catch (Exception e) {
                                        rgst.add(cell.getCellFormula().trim())
                                    }
                                    break
                                case CellType.BOOLEAN:
                                    rgst.add(cell.getBooleanCellValue())
                                    break
                            }
                        }        
                    }
                    println "reg1: $rgst"
                  def listaOriginal = seleniumService.ejecutarCargaExperienciaLaboral(params.ofrtExperiencia?.toInteger(), rgst)
                 flash.correctos = listaOriginal ? listaOriginal.collate(2) : []
                    workbook.close()
                    excelFile.close()
                    redirect(action: "archivoSubido", params: [seccion: " experiencia laboral EXCEL"])
                }           
        } catch (Exception e) {
            redirect(action: "error", params: [mensajeError: e.message, seccion: " experiencia laboral EXCEL"])

        }        
    }


      def procesaExcelExperienciaOferente() {
        println "params: $params"
        def f = request.getFile('archivoParticipacion') 

        // 1. Validar que el archivo llegó y no está vacío
        if (!f || f.empty) {
            flash.error = "Debes seleccionar un archivo Excel."
            redirect(action: "upload")
            return
        } else {
            println "archivo con contenido"
        }

        def os = System.getProperty("os.name").toLowerCase()
        def path = "" 

        if (os.contains("win")) {
            println "Estás en Windows"
            path = "C:\\agente\\oferta_${params.ofrtParticipacion}" 
        } else if (os.contains("linux")) {
            println "Estás en Linux"
            path = "/var/agente/oferta_${params.ofrtParticipacion}" 
        } else if (os.contains("mac")) {
            println "Estás en macOS"
            path = "/var/agente/oferta_${params.ofrtParticipacion}" 
        }
        new File(path).mkdirs()

        // 2. Obtener nombre original y extensión
        def fileName = f.getOriginalFilename()
        def parts = fileName.split("\\.")
        def ext = parts.length > 1 ? parts[parts.length - 1] : ""

        // 3. Validar extensión
        if (ext != "xlsx") {
            flash.error = "El archivo debe tener extensión .xlsx (recibido: .${ext})."
            redirect(action: "upload")
            return
        }
        println "archivo xlsx"

        // 4. Construir el nombre final: "oferta_<obra>.xlsx"
        def nombreFinal = "oferta_" + params.ofrtParticipacion + "." + ext

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
                            }
                            }
                        }        
                    }
                        println "reg1: $rgst"
                        seleniumService.cargaAnexos(params.ofrt?.toInteger(), rgst)
                }
            }

            workbook.close()
            excelFile.close()

            flash.message = "Archivo '${nombreFinal}' subido y procesado correctamente."
        } catch (Exception e) {
            log.error("Error procesando el Excel", e)
            flash.error = "Error al procesar el Excel: ${e.message}"
        }
        redirect(action: "upload")
    }

    def archivoSubido(){
     return [error: params.mensajeError, seccion: params.seccion, correctos: params.correctos]   
    }

    def error(){
     return [error: params.mensajeError, seccion: params.seccion]    
    }

}
