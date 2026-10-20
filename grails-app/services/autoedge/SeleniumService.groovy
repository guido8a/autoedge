package autoedge

import java.time.Duration

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.edge.EdgeDriverService
import org.openqa.selenium.edge.EdgeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait

class SeleniumService {

    WebDriver crearDriver() {
        System.setProperty("SE_DRIVER_MIRROR_URL", "https://msedgedriver.microsoft.com");

        EdgeOptions options = new EdgeOptions()
        options.addArguments(
            "--disable-gpu",
            "--no-sandbox",
            "--disable-dev-shm-usage",
            "--start-maximized"
        );
        // Selenium Manager (4.28+) descarga automáticamente el driver
        // compatible con la versión de Edge instalada en la máquina
        return new EdgeDriver(options);
    }

    Map probarNavegacion() {
        WebDriver driver = null
        try {
            driver = crearDriver()
            driver.get("https://www.google.com")
            return [
                exito: true,
                titulo: driver.title,
                url: driver.currentUrl
            ]
        } catch (Exception e) {
            return [
                exito: false,
                error: e.message,
                causa: e.cause?.message
            ]
        } finally {
            if (driver != null) {
                driver.quit()
            }
        }
    }

   /* desde agenteMfc */
   void ejecutarAutomatizacion(int oferta) {
        println "=== Iniciando automatización compromiso participacion con Edge ==="

        WebDriver driver = crearDriver()

        try {
            // 3. Inicializar el navegador

            WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(10))
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15))
            WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(20))
            WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(5))
            driver.get("http://localhost:6012/mfc-oa/web/app.php")


            def ingreso = wait2.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("btn_inicio_abajo"))
            )
            ingreso.click()

            // OFERTAS

            def abrirOfertas = wait2.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath('//*[@title="Ofertas"]'))
            )
            abrirOfertas.click()

            //OFERTA SELECCIONADA

            if (!verificarSiUrlExiste("http://localhost:6012/mfc-oa/web/app.php/ofertas/edicion/${oferta}")) {
                log.error("El proceso se detuvo: La URL no existe o no responde.")
                driver.get("http://localhost:6012/mfc-oa/web/app.php")
            } else {
                driver.get("http://localhost:6012/mfc-oa/web/app.php/ofertas/edicion/${oferta}")

                //COMPROMISO DE PARTICIPACION

                def ingresoCompromiso = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("btnFormularioCompromisoParticipacion"))
                )
                ingresoCompromiso.click()

                //Agregar

                def agregarPersonal = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("agregarPersonalTecnico"))
                )
                agregarPersonal.click()

                //Hoja de vida

                //tipo de documento
                def comboUno = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("comproPartici_tipoDocumentoCp"))
                )
                Select seleccionarTipoDocumento = new Select(comboUno)
                seleccionarTipoDocumento.selectByVisibleText("CÉDULA")

                //numero documento

                def campoNumeroDocumento = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("comproPartici_numeroDocumentoCp"))
                )
                campoNumeroDocumento.clear()
                campoNumeroDocumento.sendKeys("1716473325")

                //nombre completo

                def campoNombreCompleto = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("comproPartici_nombresCp"))
                )
                campoNombreCompleto.clear()
                campoNombreCompleto.sendKeys("Pedro Perez")

                //Lugar Nacimiento

                def campoLugarNacimiento = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("comproPartici_lugarNacimientoCp"))
                )
                campoLugarNacimiento.clear()
                campoLugarNacimiento.sendKeys("Quito")

                //fecha de nacimiento

                WebElement inputFecha = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.id("comproPartici_fechaNacimientoCp"))
                )

                JavascriptExecutor js = (JavascriptExecutor) driver

                js.executeScript("arguments[0].removeAttribute('readonly');", inputFecha)

                inputFecha.clear()
                inputFecha.sendKeys("1980/08/13")

                js.executeScript("arguments[0].dispatchEvent(new Event('change'));", inputFecha)
                js.executeScript("arguments[0].dispatchEvent(new Event('blur'));", inputFecha)

                //nacionalidad

                def campoNacionalidad = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("comproPartici_nacionalidadCp"))
                )
                campoNacionalidad.clear()
                campoNacionalidad.sendKeys("Ecuatoriano")

                //nivel de estudio

                def comboNE = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("comproPartici_nivelEstudioCodCp"))
                )
                Select seleccionarNivelEstudio = new Select(comboNE)
                seleccionarNivelEstudio.selectByVisibleText("CUARTO NIVEL")

                //titulacion

                def campoTitulacion = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("comproPartici_tituloProfesionalCp"))
                )
                campoTitulacion.clear()
                campoTitulacion.sendKeys("Ingeniero en computación")

                //fecha de graduacion

                WebElement inputFechaGraduacion = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.id("comproPartici_fechaGraduacionCp"))
                )

                JavascriptExecutor js2 = (JavascriptExecutor) driver

                js2.executeScript("arguments[0].removeAttribute('readonly');", inputFechaGraduacion)

                inputFechaGraduacion.clear()
                inputFechaGraduacion.sendKeys("2010/05/20")

                js2.executeScript("arguments[0].dispatchEvent(new Event('change'));", inputFechaGraduacion)
                js2.executeScript("arguments[0].dispatchEvent(new Event('blur'));", inputFechaGraduacion)

                //titulo IV nivel

                def campoTitulo = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("comproPartici_tituloCuartoNivCp"))
                )
                campoTitulo.clear()
                campoTitulo.sendKeys("Ingeniero")

                //tiempo de participacion

                def campoTiempoParticipacion = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("comproPartici_tiempoPartiCp"))
                )
                campoTiempoParticipacion.clear()
                campoTiempoParticipacion.sendKeys("6")

                //tiempo de participacion seleccion

                def comboTiempoParticipacion = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("comproPartici_tiempoPartiCpMedida"))
                )
                Select seleccionarTiempoParticipacion= new Select(comboTiempoParticipacion)
                seleccionarTiempoParticipacion.selectByVisibleText("MESES")

                //funcion

                def campoFuncion= wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("comproPartici_cargoConsCp"))
                )
                campoFuncion.clear()
                campoFuncion.sendKeys("Desarrollador Web")

                //observaciones

                def campoObservacion= wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("comproPartici_observacionesCp"))
                )
                campoObservacion.clear()
                campoObservacion.sendKeys("NINGUNA")

                //boton guardar

                def guardarPersonal = wait.until(
                        ExpectedConditions.elementToBeClickable(By.id("guardarPersonalTecnico")) )
                guardarPersonal.click()

                def guardarPersonal2 = wait.until(
                        ExpectedConditions.elementToBeClickable(By.id("guardarPersonalTecnico")) )
                guardarPersonal2.click()

                WebElement botonGuardar = driver.findElement(By.id("guardarPersonalTecnico"))

                JavascriptExecutor js3 = (JavascriptExecutor) driver
                js3.executeScript("arguments.focus();", botonGuardar)
                js3.executeScript("arguments.click();", botonGuardar)

            }

        } catch (Exception e ) {
            println "Ocurrió un error en Selenium: ${e.message}"
            e.printStackTrace()
        } finally {
            // 5. Asegurar el cierre del proceso de Firefox
            if (driver != null) {
                driver.quit()
                println "=== Terminado correctamente ==="
            }
        }
    }

    void ejecutarAutomatizacionExperienciaLaboral(int oferta, int personal) {
        println "=== Iniciando ExperienciaLaboral ==="

        // WebDriver driver = new EdgeDriver()
        WebDriver driver = crearDriver()

        try {

            WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(10))
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15))
            WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(20))
            WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(5))

            driver.get("http://localhost:6012/mfc-oa/web/app.php")


            def ingreso = wait2.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("btn_inicio_abajo"))
            )
            ingreso.click()

            // OFERTAS
//
            def abrirOfertas = wait2.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath('//*[@title="Ofertas"]'))
            )
            abrirOfertas.click()

            //OFERTA SELECCIONADA

            if (!verificarSiUrlExiste("http://localhost:6012/mfc-oa/web/app.php/ofertas/edicion/${oferta}")) {
                log.error("El proceso se detuvo: La URL no existe o no responde.")
                driver.get("http://localhost:6012/mfc-oa/web/app.php")
            } else {
                driver.get("http://localhost:6012/mfc-oa/web/app.php/ofertas/edicion/${oferta}")

                //ingreso compromiso participacion

                def ingresoCompromiso = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("btnFormularioCompromisoParticipacion"))
                )
                ingresoCompromiso.click()

                //identificar los td de la tabla

                String xpathSegundoBoton = "//td[text()='${personal}']/parent::tr/td[last()]//button[2]"
                WebElement segundoBoton = driver.findElement(By.xpath(xpathSegundoBoton))
                String idDelBoton = segundoBoton.getAttribute("idpersonal")

                println "El ID del segundo botón es: " + idDelBoton

                //EXPERIENCIA LABORAL

                String selectorCss = ".btnExpPro[idpersonal='${idDelBoton}']"
                WebElement ingresarExperiencia = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selectorCss)))
                ingresarExperiencia.click()

                def agregarExperienciaProfesional = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("agregarExperienciaProfesional"))
                )
                agregarExperienciaProfesional.click()

                println "agregando experiencia profesional"

                //empresa

                def campoEmpresa = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("expProfe_empresaEp"))
                )
                campoEmpresa.clear()
                campoEmpresa.sendKeys("Tedein")
//
                //contratante

                def campoContratante = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("expProfe_contratanteEp"))
                )
                campoContratante.clear()
                campoContratante.sendKeys("Luis Lopez")
//
                //proyecto

                def campoProyecto = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("expProfe_proyectoEp"))
                )
                campoProyecto.clear()
                campoProyecto.sendKeys("XXXXXX")

                //monto

                def campoMonto = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("expProfe_montoProyecyoEp"))
                )
                campoMonto.clear()
                campoMonto.sendKeys("50000")

                //funcion

                def campoFuncion = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("expProfe_cargoEp"))
                )
                campoFuncion.clear()
                campoFuncion.sendKeys("Desarrollador web")

                //actividades relevantes

                def campoActividades= wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("expProfe_activiRelevEp"))
                )
                campoActividades.clear()
                campoActividades.sendKeys("Actividades...")

                //tiempo de participacion

                def campoTiempoParticipacion = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("expProfe_tiempoPartiConsCp"))
                )
                campoTiempoParticipacion.clear()
                campoTiempoParticipacion.sendKeys("10")

                //tiempo de participacion seleccion

                def comboTiempoParticipacion = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("expProfe_tiempoPartiConsCpMedida"))
                )
                Select seleccionarTiempoParticipacion= new Select(comboTiempoParticipacion)
                seleccionarTiempoParticipacion.selectByVisibleText("AÑOS")


                def guardarPersonal = wait.until(
                        ExpectedConditions.elementToBeClickable(By.id("guardarExperienciaProfesional")) )
                guardarPersonal.click()

//                def guardarPersonal2 = wait.until(
//                        ExpectedConditions.elementToBeClickable(By.id("guardarExperienciaProfesional")) )
//                guardarPersonal2.click()

            }

        } catch (Exception e ) {
            println "Ocurrió un error en Selenium: ${e.message}"
            e.printStackTrace()
        } finally {
            if (driver != null) {
                driver.quit()
                println "=== Terminado correctamente ==="
            }
        }
    }

    void ejecutarAutomatizacionCargaAnexos(int oferta) {
        println "=== Iniciando automatización carga anexos con Edge ==="

        // WebDriver driver = new EdgeDriver()
        WebDriver driver = crearDriver()

        try {
            WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(10))
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15))
            WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(20))
            WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(5))

            driver.get("http://localhost:6012/mfc-oa/web/app.php")
            def ingreso = wait2.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("btn_inicio_abajo"))
            )
            ingreso.click()

            // OFERTAS

            def abrirOfertas = wait2.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath('//*[@title="Ofertas"]'))
            )
            abrirOfertas.click()

            //OFERTA SELECCIONADA

            if (!verificarSiUrlExiste("http://localhost:6012/mfc-oa/web/app.php/ofertas/edicion/${oferta}")) {
                log.error("El proceso se detuvo: La URL no existe o no responde.")
                driver.get("http://localhost:6012/mfc-oa/web/app.php")
            } else {
                driver.get("http://localhost:6012/mfc-oa/web/app.php/ofertas/edicion/${oferta}")

                //ANEXOS

                def ingresoAnexos = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("btnArchivos"))
                )
                ingresoAnexos.click()

                driver.get("http://localhost:6012/mfc-oa/web/app.php/ofertas/archivos/subir")

                //Descripcion

                def campoDescripcion = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("form_descripcion"))
                )
                campoDescripcion.clear()
                campoDescripcion.sendKeys("Anexo numero uno")

                //clic antes de cargar

                def campoArchivo = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.className("input-file"))
                )
                campoArchivo.click()

                //cargar archivo

                String rutaAbsoluta = "C:/Users/Guido/anexo.pdf"
//                String rutaAbsoluta = "C:/Users/USER/anexo.pdf"

                WebElement inputArchivo = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.id("form_file"))
                )

                inputArchivo.sendKeys(rutaAbsoluta)

                //boton guardar

                def guardarAnexo = wait.until(
                        ExpectedConditions.elementToBeClickable(By.id("submit")) )
                guardarAnexo.click()

            }

        } catch (Exception e ) {
            println "Ocurrió un error en Selenium: ${e.message}"
            e.printStackTrace()
        } finally {
            if (driver != null) {
                driver.quit()
                println "=== Terminado carga de anexo correctamente ==="
            }
        }
    }

    void cargaAnexos(int oferta, List rgst) {
        println "=== Inicia carga anexos con $rgst ==="
        WebDriver driver = crearDriver()

        try {
            WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(10))
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15))
            WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(20))
            WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(5))

            driver.get("http://localhost:6012/mfc-oa/web/app.php")
            def ingreso = wait2.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("btn_inicio_abajo"))
            )
            ingreso.click()

            // OFERTAS
            def abrirOfertas = wait2.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath('//*[@title="Ofertas"]'))
            )
            abrirOfertas.click()

            //OFERTA SELECCIONADA
            if (!verificarSiUrlExiste("http://localhost:6012/mfc-oa/web/app.php/ofertas/edicion/${oferta}")) {
                log.error("El proceso se detuvo: La URL no existe o no responde.")
                driver.get("http://localhost:6012/mfc-oa/web/app.php")
            } else {
                driver.get("http://localhost:6012/mfc-oa/web/app.php/ofertas/edicion/${oferta}")

                //ANEXOS
                def ingresoAnexos = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("btnArchivos"))
                )
                ingresoAnexos.click()

                rgst.collate(2).each{ fila ->
                    println("--> " + fila[0])  

                driver.get("http://localhost:6012/mfc-oa/web/app.php/ofertas/archivos/subir")

                //Descripcion
                def campoDescripcion = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("form_descripcion"))
                )
                campoDescripcion.clear()
                campoDescripcion.sendKeys(fila[1]?.toString())  //rgst[1] es la descripción

                //clic antes de cargar
                def campoArchivo = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.className("input-file"))
                )
                campoArchivo.click()

                //cargar archivo
                String rutaAbsoluta = "C:/agente/anexos/" + fila[0] + ".pdf"

                WebElement inputArchivo = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.id("form_file"))
                )

                inputArchivo.sendKeys(rutaAbsoluta)

                //boton guardar
                def guardarAnexo = wait.until(
                        ExpectedConditions.elementToBeClickable(By.id("submit")) )
                guardarAnexo.click()

                }                             
            }

        } catch (Exception e ) {
            println "Ocurrió un error en Selenium: ${e.message}"
            e.printStackTrace()
           redirect(controller:'selenium', action:'error', params: [error: e.message])
        } finally {
            if (driver != null) {
                driver.get("http://localhost:6012/mfc-oa/web/app.php/ofertas/archivos/subir")
                driver.quit()
                println "=== Terminado carga de anexo correctamente ==="
            }
        }
    }

    void ejecutarAutomatizacionCargaExperienciaOferente(int oferta) {
        println "=== Iniciando automatización carga experiencia oferente con Edge ==="

        // WebDriver driver = new EdgeDriver()
        WebDriver driver = crearDriver()

        try {

            WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(10))
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15))
            WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(20))
            WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(5))

            driver.get("http://localhost:6012/mfc-oa/web/app.php")

            def ingreso = wait2.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("btn_inicio_abajo"))
            )
            ingreso.click()

            // OFERTAS
//
            def abrirOfertas = wait2.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath('//*[@title="Ofertas"]'))
            )
            abrirOfertas.click()

            //OFERTA SELECCIONADA

            if (!verificarSiUrlExiste("http://localhost:6012/mfc-oa/web/app.php/ofertas/edicion/${oferta}")) {
                log.error("El proceso se detuvo: La URL no existe o no responde.")
                driver.get("http://localhost:6012/mfc-oa/web/app.php")
            } else {
                driver.get("http://localhost:6012/mfc-oa/web/app.php/ofertas/edicion/${oferta}")

                //formulario de oferta

                def ingresoFormularioOferta = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("btnFormularioOferta"))
                )
                ingresoFormularioOferta.click()

                //seleccion de acordeon 4

                def abrirAcordeonCuatro = wait.until(
                        ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'EXPERIENCIA OFERENTE')]"))
                )
                abrirAcordeonCuatro.click()

                //clic boton agregar

                WebElement botonJS = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.cssSelector("[href='#modExpeOfer']"))
                )

                JavascriptExecutor js = (JavascriptExecutor) driver
                js.executeScript("arguments[0].click();", botonJS)

                //Tipo

                def comboTipo = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("incop_mfc_ofertasbundle_tmepformoferexpeofer_tipo"))
                )
                Select seleccionarTipo= new Select(comboTipo)
                seleccionarTipo.selectByVisibleText("GENERAL")

                //contratante

                def campoDescripcion = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("incop_mfc_ofertasbundle_tmepformoferexpeofer_contratante"))
                )
                campoDescripcion.clear()
                campoDescripcion.sendKeys("Empresa XX")

                //fecha de recepcion

                WebElement inputFechaRecepcion = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.id("incop_mfc_ofertasbundle_tmepformoferexpeofer_fechaRecepcion"))
                )

                JavascriptExecutor js2 = (JavascriptExecutor) driver

                js2.executeScript("arguments[0].removeAttribute('readonly');", inputFechaRecepcion)

                inputFechaRecepcion.clear()
                inputFechaRecepcion.sendKeys("2010/05/20")

                js2.executeScript("arguments[0].dispatchEvent(new Event('change'));", inputFechaRecepcion)
                js2.executeScript("arguments[0].dispatchEvent(new Event('blur'));", inputFechaRecepcion)

                //objeto

                def campoObjeto= wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("incop_mfc_ofertasbundle_tmepformoferexpeofer_objetoContrato"))
                )
                campoObjeto.clear()
                campoObjeto.sendKeys("Objeto XX")

                // //valor del contrato

                // def campoValor= wait.until(
                //         ExpectedConditions.visibilityOfElementLocated(By.id("incop_mfc_ofertasbundle_tmepformoferexpeofer_valorContrato"))
                // )
                // campoValor.click()
                // campoValor.clear()
                // campoValor.sendKeys("100000")

                //seleccion de carga de archivos

                JavascriptExecutor js3 = (JavascriptExecutor) driver   

def botonArchivos = wait3.until(
    ExpectedConditions.elementToBeClickable(
        By.xpath("//div[@id='modExpeOfer']" +
                 "//select[@id='incop_mfc_ofertasbundle_tmepformoferexpeofer_archivos']" +
                 "/following-sibling::div[@class='btn-group']" +
                 "/button[contains(@class,'multiselect')]")
    )
)
js3.executeScript("arguments[0].click();", botonArchivos)


// Localizar todos los labels (sin filtrar por li disabled)
def labels = js3.findElements(
    By.xpath("//div[@id='modExpeOfer']" +
             "//select[@id='incop_mfc_ofertasbundle_tmepformoferexpeofer_archivos']" +
             "/following-sibling::div[contains(@class,'btn-group')]" +
             "//ul[contains(@class,'multiselect-container')]" +
             "//label")
)

println "Total labels: ${labels.size()}"
labels.eachWithIndex { l, i ->
    println "[${i}] getText()='${l.getText()}' | textContent='${l.getAttribute('textContent')}'"
}

// Buscar 'anexo' usando textContent (robusto ante elementos ocultos)
def labelAnexo = labels.find { l ->
    def texto = (l.getAttribute('textContent') ?: l.getText() ?: '').toString()
    texto.trim().toLowerCase().contains('anexo')
}

if (labelAnexo == null) {
    def disponibles = labels.collect {
        "'${(it.getAttribute('textContent') ?: it.getText() ?: '').toString().trim()}'"
    }.join(', ')
    throw new RuntimeException("No se encontró 'anexo'. Disponibles: [${disponibles}]")
}

// Clic
println "Seleccionando: '${labelAnexo.getAttribute('textContent')}'"
wait3.until(ExpectedConditions.elementToBeClickable(labelAnexo))
js3.executeScript("arguments[0].scrollIntoView({block:'center'});", labelAnexo)
js3.executeScript("arguments[0].click();", labelAnexo)


                //valor del contrato

                def campoValor= wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("incop_mfc_ofertasbundle_tmepformoferexpeofer_valorContrato"))
                )
                campoValor.click()
                campoValor.clear()
                campoValor.sendKeys("100000")


         //boton guardar


def botonesGuardar = js3.findElements(
    By.xpath("//div[@id='modExpeOfer']" +
             "//div[contains(@id,'formOferExpeOfer')]" +
             "/button[@type='submit' and @name='accion']")
)

def btnGuardar = botonesGuardar.find { it.isDisplayed() }

if (btnGuardar == null) {
    throw new RuntimeException("No hay botón Guardar visible en el modal #modExpeOfer")
}

js3.executeScript("arguments[0].scrollIntoView({block:'center'});", btnGuardar)
js3.executeScript("arguments[0].click();", btnGuardar)


            }

        } catch (Exception e ) {
            println "Ocurrió un error en Selenium: ${e.message}"
            e.printStackTrace()
        } finally {
            if (driver != null) {
                driver.quit()
                println "=== Terminado correctamente ==="
            }
        }
    }


    boolean verificarSiUrlExiste(String urlString) {
        try {
            URL url = new URL(urlString)
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection()
            conexion.requestMethod = "HEAD"
            conexion.connectTimeout = 5000
            conexion.readTimeout = 5000
            int codigoEstado = conexion.responseCode
            return (codigoEstado >= 200 && codigoEstado < 400)
        } catch (Exception e) {
            log.error("Error de conexión URL ${urlString}: ${e.message}")
            return false
        }
    }

}
