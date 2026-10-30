<div class="container-fluid">
    <div class="row">
        <div class="alert alert-warning py-2">
            <strong> Lineamientos generales para la creación del archivo excel: </strong>
            <ul>
                <li>
                    Debe contener las hojas en el orden mostrado a continuación
                </li>
                <li>
                    Los datos bajo cada una de las columnas deben coincidir con su respectiva cabecera
                </li>
                <li>
                    Debe contener TODAS las columnas mostradas en el ejemplo
                </li>
            </ul>
        </div>

        <g:if test="${tipo == '1'}">
            <div class="col-md-12 card h-100 text-decoration-none shadow-sm border-1 hover-card hover-card-warning"
                style="margin-top: 10px;">
                <h4>Hoja n°1: <strong class="text-success"> Anexos </strong></h4>
                <div class="alert alert-warning py-1">
                    <ul>
                        <li>
                            Los archivos deben ser de formato .PDF
                        </li>
                        <li>
                            Deben tener el mismo nombre que en la columna "Nombre del archivo"
                        </li>
                        <li>
                            Deben estar almacenados en "C:\agente\anexos"
                        </li>
                    </ul>
                </div>
                <div class="table-responsive">
                    <table class="table table-bordered border-success-subtle text-nowrap" style="font-size: 11px;">
                        <thead class="table-success text-warning-emphasis">
                            <tr style="text-align: center; font-size: 14px;">
                                <th>A</th>
                                <th>B</th>
                            </tr>
                            <tr>
                                <th>Nombre del archivo</th>
                                <th>Descripción del archivo máx 50ca</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>01 acta contrato 1</td>
                                <td>Contrato de desarrollo</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </g:if>

        <g:if test="${tipo == '2'}">
            <div class="col-md-12 card h-100 text-decoration-none shadow-sm border-1 hover-card hover-card-info"
                style="margin-top: 10px;">
                <h4>Hoja n°2: <strong class="text-success"> Experiencia oferente </strong></h4>
                <div class="table-responsive">
                    <table class="table table-bordered border-success-subtle text-nowrap" style="font-size: 11px;">
                        <thead class="table-success text-warning-emphasis">
                            <tr style="text-align: center; font-size: 14px;">
                                <th>A</th>
                                <th>B</th>
                                <th>C</th>
                                <th>D</th>
                                <th>E</th>
                                <th>F</th>
                                <th>G</th>
                            </tr>
                            <tr>
                                <th>No.</th>
                                <th>Tipo</th>
                                <th>Contratante</th>
                                <th>Objeto del Contrato</th>
                                <th>Valor del Contrato</th>
                                <th>Fecha de recepción</th>
                                <th>Archivo</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>GENERAL</td>
                                <td>GADPP</td>
                                <td>Servicio de mantenimiento y soporte técnico</td>
                                <td>6703.2</td>
                                <td>2017/10/10</td>
                                <td>Contrato de desarrollo</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </g:if>
        <g:if test="${tipo == '3'}">
            <div class="col-md-12 card h-100 text-decoration-none shadow-sm border-1 hover-card hover-card-info"
                style="margin-top: 10px;">
                <h4>Hoja n°3: <strong class="text-success"> Compromiso participación </strong></h4>
                <div class="alert alert-warning py-1">
                    <ul>
                        <li>
                            La columna "Título 4to nivel" es la única que puede quedar vacía de ser necesario
                        </li>
                    </ul>
                </div>
                <div class="table-responsive">
                    <table class="table table-bordered border-success-subtle text-nowrap" style="font-size: 11px;">
                        <thead class="table-success text-warning-emphasis">
                            <tr style="text-align: center; font-size: 14px;">
                                <th>A</th>
                                <th>B</th>
                                <th>C</th>
                                <th>D</th>
                                <th>E</th>
                                <th>F</th>
                                <th>G</th>
                                <th>H</th>
                                <th>I</th>
                                <th>J</th>
                                <th>K</th>
                                <th>L</th>
                                <th>M</th>
                                <th>N</th>
                                <th>O</th>
                            </tr>
                            <tr>
                                <th>Persona No.</th>
                                <th>Tipo de documento</th>
                                <th>Número documento</th>
                                <th>Nombre completo</th>
                                <th>Lugar Nacimiento</th>
                                <th>Fecha Nacimiento</th>
                                <th>Nacionalidad</th>
                                <th>Nivel de estudio</th>
                                <th>Titulación Académica</th>
                                <th>Fecha graduación</th>
                                <th>Título 4to nivel</th>
                                <th>Tiempo Participación (NÚMERO)</th>
                                <th>Tiempo participación (UNIDAD)</th>
                                <th>Función</th>
                                <th>Observaciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>CÉDULA</td>
                                <td>1716472235</td>
                                <td>PEDRO PEREZ</td>
                                <td>Quito</td>
                                <td>1980/08/13</td>
                                <td>Ecuatoriano</td>
                                <td>TERCER NIVEL CON TÍTULO</td>
                                <td>Ingeniero</td>
                                <td>2017/03/06</td>
                                <td>Phd</td>
                                <td>16</td>
                                <td>AÑOS</td>
                                <td>Desarrollador</td>
                                <td>Observaciones</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </g:if>
        <g:if test="${tipo == '4'}">
            <div class="col-md-12 card h-100 text-decoration-none shadow-sm border-1 hover-card hover-card-info"
                style="margin-top: 10px;">
                <h4>Hoja n°4: <strong class="text-success"> Experiencia Laboral </strong></h4>
                <div class="table-responsive">
                    <table class="table table-bordered border-success-subtle text-nowrap" style="font-size: 11px;">
                        <thead class="table-success text-warning-emphasis">
                            <tr style="text-align: center; font-size: 14px;">
                                <th>A</th>
                                <th>B</th>
                                <th>C</th>
                                <th>D</th>
                                <th>E</th>
                                <th>F</th>
                                <th>G</th>
                                <th>H</th>
                                <th>I</th>
                            </tr>
                            <tr>
                                <th>Persona No.</th>
                                <th>Empresa</th>
                                <th>Contratante</th>
                                <th>Proyecto</th>
                                <th>Monto del proyecto</th>
                                <th>Función</th>
                                <th>Tiempo de participación (número)</th>
                                <th>Tiempo de participación (unidad)</th>
                                <th>Actividad</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>EMPRESA S. A.</td>
                                <td>EMPRESA S. A.</td>
                                <td>Sistema de administración documental</td>
                                <td>10000</td>
                                <td>Desarrollador web</td>
                                <td>6</td>
                                <td>MESES</td>
                                <td>Desarrollo, pruebas e implantación.</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </g:if>

    </div>
</div>