/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades.organizaciones;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.organizaciones.TblActividadOrganizacionAdmonFinanciero;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblOrganizacion;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.organizaciones.ActividadOrganizacionAdmonFinancieroRepository;
import com.api.pgc.core.APIRestPGC.repository.organizaciones.OrganizacionRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "ActividadOrganizacionesAPI", description = "Operaciones sobre el Modulo de Proyectos Organizaciones", tags = "Organizaciones de Proyecto")
public class ActividadOrgAdmonFinancieroResourses {

    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadOrganizacionAdmonFinancieroRepository _actividadOrganizacionAdmonFinancieroRepository;

    @Autowired
    ActividadRepository _actividadRepository;

    @Autowired
    OrganizacionRepository _organizacionRepository;

    /**
     * Metodo que despliega la Lista de todas los Socios al Desarrollo de una Actividad de la BD
     *
     * @return Lista de Socios al Desarrollo de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 14/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todas los Socios al Desarrollo los Proyectos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ORGANIZACIONES_ACT_ENDPOINT_ADMON_FINANCIERO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllActvidadesAdmonFinanciero() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas los Socios al Desarrollo registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadOrganizacionAdmonFinancieroRepository.findAll());
            msgExeptions.map.put("countRecords", _actividadOrganizacionAdmonFinancieroRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAllActvidadesSocio


    /**
     * Metodo que despliega los Socios al Desarrollo a la Actividad de la BD
     *
     * @param idActividad Identificador de la Actividad a Buscar
     * @return Socios al Desarrollo de una Atividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 14/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna los Socios al Desarrollo de la Actividad enviada a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ORGANIZACIONES_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_ADMON_FINANCIERO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAdmonFinancieroByIdActividad(@ApiParam(value = "Identificador de la Actividad a Buscar", required = true)
                                                                   @PathVariable("idActividad") long idActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad para verificar si existe
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(idActividad);

            if (_actividadOrganizacionAdmonFinancieroRepository.countByIdActividad(_tblActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de la Admon Financiero  del Proyecto consultado";

                msgExeptions.map.put("error", "No data found");

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de los Admon financiero del Proyecto";
                msgExeptions.map.put("data", _actividadOrganizacionAdmonFinancieroRepository.getCodigoActividadOrganizacion(_tblActividad));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado el Proyecto solicitado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAdmonFinancieroByIdActividad

    /**
     * Metodo que despliega el Admon Financiero asociado a Proyecto de la BD
     *
     * @param codigoActividad Codigo de Admon Financiero asociado a Proyecto a Buscar
     * @return Admon Financiero de la BD
     * @autor Nahum Martinez | NAM
     * @version 14/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Admon Financiera a buscar el codigo a la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ORGANIZACIONES_ACT_ENDPOINT_FIND_BY_COD_PROGRAMA_ADMON_FINANCIERO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAdmonFinancieroByCodigoActividad(@ApiParam(value = "Código de Admon Financiero asociado a un Proyecto a Buscar", required = true)
                                                                       @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadOrganizacionAdmonFinancieroRepository.countByCodigoActividad(codigoActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Admon Financiero asociados a Proyecto consultado";

                msgExeptions.map.put("data", _actividadOrganizacionAdmonFinancieroRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("find", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Se ha encontrado dato de Admon Financiero asociados a Proyecto consultado";
                msgExeptions.map.put("data", _actividadOrganizacionAdmonFinancieroRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("find", true);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "Se ha encontraddo datos de Admon Financiero asociado a un Proyecto Consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAdmonFinancieroByCodigoActividad


    /**
     * Metodo que Solicita un json con los datos de la Entidad Admon Financiero con Relacion
     * a Actividades
     *
     * @param _actividadAdmonFinancieroJson Obtiene desde el request los datos del Socio Desarrollo a ingresar
     * @return Mensaje de Confirmacion de Registro de Admon Financiero
     * @autor Nahum Martinez | NAM
     * @version 14/05/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del Admon Financiero de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = ORGANIZACIONES_ACT_ENDPOINT_NEW_ADMON_FINANCIERO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addActividadAdmonFinanciero(@ApiParam(value = "Json de Admon Financiero del Proyecto a Ingresar", required = true)
                                                               @RequestBody @Valid final TblActividadOrganizacionAdmonFinanciero _actividadAdmonFinancieroJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingreso
        Date dateActual = new Date();
        System.out.println("************************" + _actividadAdmonFinancieroJson.getPorcentajePart());

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividad": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(_actividadAdmonFinancieroJson.getIdActividad().getIdActividad());

            try {
                // Busca la Admon Financiero, desde el Reporsitorio con el Parametro del Json enviado ( "idOrganizacion": {"idOrganizacion": valor })
                TblOrganizacion _tblOrganizacion = _organizacionRepository.findByIdOrganizacion(_actividadAdmonFinancieroJson.getIdOrganizacion().getIdOrganizacion());

                // Busca el Proyecto con el Proposito de validar que no se meta otro Item mas,
                // desde el Reporsitorio de Admon Financiero con el Parametro del Json enviado ( "idActividad": _tblActividad )

                if (_actividadOrganizacionAdmonFinancieroRepository.countByCodigoActividad(_actividadAdmonFinancieroJson.getCodigoActividad()) > 0) {
                    msgMethod = "Ya Existe un registro con el Código de Admon Financiero para este Proyecto !! " + _actividadAdmonFinancieroJson.getCodigoActividad();

                    msgExeptions.map.put("findRecord", true);
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    // Seteo de las Fecha y Hora de Creacion
                    _actividadAdmonFinancieroJson.setFechaCreacion(dateActual);
                    _actividadAdmonFinancieroJson.setHoraCreacion(dateActual);
                    _actividadAdmonFinancieroJson.setPorcentajePart(_actividadAdmonFinancieroJson.getPorcentajePart());

                    // Seteamos la Actividad de Actividad y Socio Desarrollo
                    _actividadAdmonFinancieroJson.setIdActividad(_tblActividad);
                    _actividadAdmonFinancieroJson.setIdOrganizacion(_tblOrganizacion);

                    // Realizamos la Persistencia de los Datos
                    _actividadOrganizacionAdmonFinancieroRepository.save(_actividadAdmonFinancieroJson);
                    _actividadOrganizacionAdmonFinancieroRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Admon Financiero para este Proyecto, " + _actividadAdmonFinancieroJson.getCodigoActividad() + " se ha Ingresado de forma satisfactoria!!";

                    //Retorno del json
                    msgExeptions.map.put("findRecord", false);
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Admon Financierodel Proyecto, con la informacion indicada !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Proyecto que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | addActividadSocioDesarrollo


    /**
     * Metodo que Solicita un json con los datos de la Entidad Admon Financierocon Relacion
     * a Actividades
     *
     * @param codigoActividad Identificador de la Socio Desarrollo con Proyecto a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion de Admon Financiero
     * @autor Nahum Martinez | NAM
     * @version 14/05/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el codigo de Admon Financiero", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = ORGANIZACIONES_ACT_ENDPOINT_DELETE_ADMON_FINANCIERO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadAmonFinanciero(@ApiParam(value = "Codigo de Admon Financiero del Proyecto a Eliminar", required = true)
                                                                  @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Codigo enviado ( codigoActividad )
            TblActividadOrganizacionAdmonFinanciero _tblActividadAdmonFinaciero = _actividadOrganizacionAdmonFinancieroRepository.findByCodigoActividad(codigoActividad);

            try {
                if (_actividadOrganizacionAdmonFinancieroRepository.countByCodigoActividad(codigoActividad) > 0) {
                    // Realizamos la Persistencia de los Datos

                    _actividadOrganizacionAdmonFinancieroRepository.deleletedCodigoActividad(codigoActividad);
                    _actividadOrganizacionAdmonFinancieroRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Admon Financiero para este Proyecto, se ha Eliminado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgMethod = "No Existe un registro de Admon Financiero para este Proyecto !!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Eliminar el Admon Financiero del Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Admon Financieropara este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deletedActividadAdmonFinanciero

}
