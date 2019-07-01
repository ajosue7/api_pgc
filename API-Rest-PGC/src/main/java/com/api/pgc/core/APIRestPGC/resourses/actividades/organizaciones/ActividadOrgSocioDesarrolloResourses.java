/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades.organizaciones;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadFinanciamientoDet;
import com.api.pgc.core.APIRestPGC.models.actividades.organizaciones.TblActividadOrganizacionSocioDesarrollo;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblOrganizacion;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle.ActividadFinanciamientoDetRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.organizaciones.ActividadOrganizacionSocioDesarrolloRepository;
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
public class ActividadOrgSocioDesarrolloResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadOrganizacionSocioDesarrolloRepository _actividadOrganizacionSocioDesarrolloRepository;

    @Autowired
    ActividadRepository _actividadRepository;

    @Autowired
    OrganizacionRepository _organizacionRepository;

    @Autowired
    ActividadFinanciamientoDetRepository _actividadFinanciamientoDetRepository;

    /**
     * Metodo que despliega la Lista de todas los Socios al Desarrollo de una Actividad de la BD
     *
     * @return Lista de Socios al Desarrollo de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 14/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todas los Socios al Desarrollo los Proyectos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ORGANIZACIONES_ACT_ENDPOINT_SOCIO_DESARROLLO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllActvidadesSocioDesarrollo() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas los Socios al Desarrollo registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadOrganizacionSocioDesarrolloRepository.findAll());
            msgExeptions.map.put("countRecords", _actividadOrganizacionSocioDesarrolloRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAllActvidadesSocioDesarrollo


    /**
     * Metodo que despliega los Socios al Desarrollo a la Actividad de la BD
     *
     * @param idActividad Identificador de la Actividad a Buscar
     * @return Socios al Desarrollo de una Atividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 14/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna los Socios al Desarrollo de la Actividad enviada a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ORGANIZACIONES_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_SOCIO_DESARROLLO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSocioDesarrolloByIdActividad(@ApiParam(value = "Identificador de la Actividad a Buscar", required = true)
                                                                   @PathVariable("idActividad") long idActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad para verificar si existe
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(idActividad);

            if (_actividadOrganizacionSocioDesarrolloRepository.countByIdActividad(_tblActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de los Socios al Desarrollo del Proyecto consultado";

                msgExeptions.map.put("error", "No data found");

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de los Socios al Desarrollo del Proyecto";
                msgExeptions.map.put("data", _actividadOrganizacionSocioDesarrolloRepository.getCodigoActividadOrganizacion(_tblActividad));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado el Proyecto solicitado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getSocioDesarrolloByIdActividad

    /**
     * Metodo que despliega el Socio Desarrollo asociado a Proyecto de la BD
     *
     * @param codigoActividad Codigo de Socio Desarrollo asociado a Proyecto a Buscar
     * @return Socio Desarrollo de la BD
     * @autor Nahum Martinez | NAM
     * @version 14/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Socio Desarrollo a buscar el codigo a la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ORGANIZACIONES_ACT_ENDPOINT_FIND_BY_COD_PROGRAMA_SOCIO_DESARROLLO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSocioDesarrolloByCodigoActividad(@ApiParam(value = "Código de Socio Desarrollo asociado a un Proyecto a Buscar", required = true)
                                                                       @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadOrganizacionSocioDesarrolloRepository.countByCodigoActividad(codigoActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Socio Desarrollo asociados a Proyecto consultado";

                msgExeptions.map.put("data", _actividadOrganizacionSocioDesarrolloRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("find", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Se ha encontrado dato de Socio Desarrollo asociados a Proyecto consultado";
                msgExeptions.map.put("data", _actividadOrganizacionSocioDesarrolloRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("find", true);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "Se ha encontraddo datos de Socio Desarrollo asociado a un Proyecto Consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getSocioDesarrolloByCodigoActividad


    /**
     * Metodo que Solicita un json con los datos de la Entidad Socio Desarrollo con Relacion
     * a Actividades
     *
     * @param _actividadSocioDesarrolloJson Obtiene desde el request los datos del Socio Desarrollo a ingresar
     * @return Mensaje de Confirmacion de Registro de Socio Desarrollo
     * @autor Nahum Martinez | NAM
     * @version 14/05/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del Socio Desarrollo de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = ORGANIZACIONES_ACT_ENDPOINT_NEW_SOCIO_DESARROLLO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addActividadSocioDesarrollo(@ApiParam(value = "Json de Socio Desarrollo del Proyecto a Ingresar", required = true)
                                                               @RequestBody @Valid final TblActividadOrganizacionSocioDesarrollo _actividadSocioDesarrolloJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();
        System.out.println("************************" + _actividadSocioDesarrolloJson.getPorcentajePart());

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividad": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(_actividadSocioDesarrolloJson.getIdActividad().getIdActividad());

            try {
                // Busca la Socio Desarrollo, desde el Reporsitorio con el Parametro del Json enviado ( "idOrganizacion": {"idOrganizacion": valor })
                TblOrganizacion _tblOrganizacion = _organizacionRepository.findByIdOrganizacion(_actividadSocioDesarrolloJson.getIdOrganizacion().getIdOrganizacion());

                // Busca el Proyecto con el Proposito de validar que no se meta otro Item mas,
                // desde el Reporsitorio de Socio Desarrollo con el Parametro del Json enviado ( "idActividad": _tblActividad )

                if (_actividadOrganizacionSocioDesarrolloRepository.countByCodigoActividad(_actividadSocioDesarrolloJson.getCodigoActividad()) > 0) {
                    msgMethod = "Ya Existe un registro con el Código de Socio Desarrollo para este Proyecto !! " + _actividadSocioDesarrolloJson.getCodigoActividad();

                    msgExeptions.map.put("findRecord", true);
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    // Seteo de las Fecha y Hora de Creacion
                    _actividadSocioDesarrolloJson.setFechaCreacion(dateActual);
                    _actividadSocioDesarrolloJson.setHoraCreacion(dateActual);
                    _actividadSocioDesarrolloJson.setPorcentajePart(_actividadSocioDesarrolloJson.getPorcentajePart());

                    // Seteamos la Actividad de Actividad y Socio Desarrollo
                    _actividadSocioDesarrolloJson.setIdActividad(_tblActividad);
                    _actividadSocioDesarrolloJson.setIdOrganizacion(_tblOrganizacion);

                    // Realizamos la Persistencia de los Datos
                    _actividadOrganizacionSocioDesarrolloRepository.save(_actividadSocioDesarrolloJson);
                    _actividadOrganizacionSocioDesarrolloRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Socio Desarrollo para este Proyecto, " + _actividadSocioDesarrolloJson.getCodigoActividad() + " se ha Ingresado de forma satisfactoria!!";

                    //Retorno del json
                    msgExeptions.map.put("findRecord", false);
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Socio Desarrollo del Proyecto, con la informacion indicada !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Proyecto que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | addActividadSocioDesarrollo


    /**
     * Metodo que Solicita un json con los datos de la Entidad Socio Desarrollo con Relacion
     * a Actividades
     *
     * @param codigoActividad Identificador de la Socio Desarrollo con Proyecto a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion de Socio Desarrollo
     * @autor Nahum Martinez | NAM
     * @version 14/05/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el codigo de Socio Desarrollo", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = ORGANIZACIONES_ACT_ENDPOINT_DELETE_SOCIO_DESARROLLO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadSocioDesarrollo(@ApiParam(value = "Codigo de Socio Desarrollo del Proyecto a Eliminar", required = true)
                                                                   @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Codigo enviado ( codigoActividad )
            TblActividadOrganizacionSocioDesarrollo _tblActividadOrganizacionSocioDesarrollo = _actividadOrganizacionSocioDesarrolloRepository.findByCodigoActividad(codigoActividad);

            try {
                // Busca los Items de Financiamiento Detalle, desde el Reporsitorio con el Parametro del Socio al Desarrollo ( idSocioDesarrollo )
                // TblActividadFinanciamientoDet _tblActividadFinanciamientoDet = _actividadFinanciamientoDetRepository.count

                if (_actividadOrganizacionSocioDesarrolloRepository.countByCodigoActividad(codigoActividad) > 0) {
                    // Realizamos la Persistencia de los Datos

                    msgExeptions.map.put("findRecord", true);
                    _actividadOrganizacionSocioDesarrolloRepository.deleletedCodigoActividad(codigoActividad);
                    _actividadOrganizacionSocioDesarrolloRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Socio Desarrollo para este Proyecto, se ha Eliminado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgExeptions.map.put("findRecord", false);
                    msgMethod = "No Existe un registro de Socio al Desarrollo para este Proyecto !!";
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Eliminar el Socio Desarrollo del Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Socio Desarrollo para este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deletedActividadSocioDesarrollo

}
