/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades.organizaciones;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.organizaciones.TblActividadOrganizacionAgenciaBeneficiaria;
import com.api.pgc.core.APIRestPGC.models.actividades.organizaciones.TblActividadOrganizacionSocioDesarrollo;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblOrganizacion;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.organizaciones.ActividadOrganizacionAgenciaBeneficiariaRepository;
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
public class ActividadOrgAgenciaBeneficiariaResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadOrganizacionAgenciaBeneficiariaRepository _actividadOrganizacionAgenciaBeneficiariaRepository;


    @Autowired
    ActividadRepository _actividadRepository;

    @Autowired
    OrganizacionRepository _organizacionRepository;

    /**
     * Metodo que despliega la Lista de todos los datos de agencia beneficiaria de una Actividad de la BD
     *
     * @return Lista de Socios al Desarrollo de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 14/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todas los datos de Agencia Beneficiaria los Proyectos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ORGANIZACIONES_ACT_ENDPOINT_AGENCIA_BENEFICIARIA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllActvidadesAgenciaBeneficiaria() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los datos de agencia beneficiaria registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadOrganizacionAgenciaBeneficiariaRepository.findAll());
            msgExeptions.map.put("countRecords", _actividadOrganizacionAgenciaBeneficiariaRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAllActvidadesAgenciaBeneficiaria


    /**
     * Metodo que despliega datos de agencia beneficiaria a la Actividad de la BD
     *
     * @param idActividad Identificador de la Actividad a Buscar
     * @return agencia beneficiaria de una Atividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 14/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna datos de agencia beneficiaria de la Actividad enviada a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ORGANIZACIONES_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_AGENCIA_BENEFICIARIA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAgenciaBeneficiariaByIdActividad(@ApiParam(value = "Identificador de la Actividad a Buscar", required = true)
                                                                   @PathVariable("idActividad") long idActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad para verificar si existe
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(idActividad);

            if (_actividadOrganizacionAgenciaBeneficiariaRepository.countByIdActividad(_tblActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado datos de agencia beneficiaria del Proyecto consultado";

                msgExeptions.map.put("error", "No data found");

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de los datos de agencia beneficiaria  del Proyecto";
                msgExeptions.map.put("data", _actividadOrganizacionAgenciaBeneficiariaRepository.getCodigoActividadOrganizacion(_tblActividad));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado el Proyecto solicitado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAgencciaBeneficiariaByIdActividad

    /**
     * Metodo que despliega datos de agencia beneficiaria a Proyecto de la BD
     *
     * @param codigoActividad Codigo de agencia beneficiaria  asociado a Proyecto a Buscar
     * @return agencia beneficiaria  de la BD
     * @autor Nahum Martinez | NAM
     * @version 14/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna dato de agencia beneficiaria  a buscar el codigo a la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ORGANIZACIONES_ACT_ENDPOINT_FIND_BY_COD_PROGRAMA_AGENCIA_BENEFICIARIA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAgenciaBeneficiariaByCodigoActividad(@ApiParam(value = "Código de agencia beneficiaria  a un Proyecto a Buscar", required = true)
                                                                       @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadOrganizacionAgenciaBeneficiariaRepository.countByCodigoActividad(codigoActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de agencia beneficiaria  asociados a Proyecto consultado";

                msgExeptions.map.put("data", _actividadOrganizacionAgenciaBeneficiariaRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("find", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Se ha encontrado dato de agencia beneficiaria  asociados a Proyecto consultado";
                msgExeptions.map.put("data", _actividadOrganizacionAgenciaBeneficiariaRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("find", true);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "Se ha encontraddo datos de agencia beneficiaria  asociado a un Proyecto Consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAgenciaBeneficiariaByCodigoActividad


    /**
     * Metodo que Solicita un json con los datos de la Entidad agencia beneficiaria  con Relacion
     * a Actividades
     *
     * @param _actividadAgenciaBeneficiariaJson Obtiene desde el request los datos del agencia beneficiaria  a ingresar
     * @return Mensaje de Confirmacion de Registro de agencia beneficiaria
     * @version 14/05/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean de agencia beneficiaria  de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = ORGANIZACIONES_ACT_ENDPOINT_NEW_AGENCIA_BENEFICIARIA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addActividadAgenciaBeneficiaria(@ApiParam(value = "Json de agencia beneficiaria  del Proyecto a Ingresar", required = true)
                                                               @RequestBody @Valid final TblActividadOrganizacionAgenciaBeneficiaria _actividadAgenciaBeneficiariaJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();
        System.out.println( "************************"  +_actividadAgenciaBeneficiariaJson.getPorcentajePart());

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividad": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(_actividadAgenciaBeneficiariaJson.getIdActividad().getIdActividad());

            try {
                // Busca la Agencia Beneficiaria, desde el Reporsitorio con el Parametro del Json enviado ( "idOrganizacion": {"idOrganizacion": valor })
                TblOrganizacion _tblOrganizacion = _organizacionRepository.findByIdOrganizacion(_actividadAgenciaBeneficiariaJson.getIdOrganizacion().getIdOrganizacion());

                // Busca el Proyecto con el Proposito de validar que no se meta otro Item mas,
                // desde el Reporsitorio de Agencia Beneficiaria con el Parametro del Json enviado ( "idActividad": _tblActividad )

                if (_actividadOrganizacionAgenciaBeneficiariaRepository.countByCodigoActividad(_actividadAgenciaBeneficiariaJson.getCodigoActividad()) > 0) {
                    msgMethod = "Ya Existe un registro con el Código de Socio Desarrollo para este Proyecto !! " + _actividadAgenciaBeneficiariaJson.getCodigoActividad();

                    msgExeptions.map.put("findRecord", true);
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    // Seteo de las Fecha y Hora de Creacion
                    _actividadAgenciaBeneficiariaJson.setFechaCreacion(dateActual);
                    _actividadAgenciaBeneficiariaJson.setHoraCreacion(dateActual);
                    _actividadAgenciaBeneficiariaJson.setPorcentajePart(_actividadAgenciaBeneficiariaJson.getPorcentajePart());

                    // Seteamos la Actividad de Actividad y Agencia Beneficiaria
                    _actividadAgenciaBeneficiariaJson.setIdActividad(_tblActividad);
                    _actividadAgenciaBeneficiariaJson.setIdOrganizacion(_tblOrganizacion);

                    // Realizamos la Persistencia de los Datos
                    _actividadOrganizacionAgenciaBeneficiariaRepository.save(_actividadAgenciaBeneficiariaJson);
                    _actividadOrganizacionAgenciaBeneficiariaRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Socio Desarrollo para este Proyecto, " + _actividadAgenciaBeneficiariaJson.getCodigoActividad() + " se ha Ingresado de forma satisfactoria!!";

                    //Retorno del json
                    msgExeptions.map.put("findRecord", false);
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el agencia beneficiaria del Proyecto, con la informacion indicada !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Proyecto que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | addActividadAgenciaBeneficiaria


    /**
     * Metodo que Solicita un json con los datos de la Entidad Agencia Beneficiaria con Relacion
     * a Actividades
     *
     * @param codigoActividad Identificador de la Agencia Beneficiaria con Proyecto a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion de Agencia Beneficiaria
     * @autor Nahum Martinez | NAM
     * @version 14/05/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el codigo de Agencia Beneficiaria", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = ORGANIZACIONES_ACT_ENDPOINT_DELETE_AGENCIA_BENEFICIARIA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadAgenciaBeneficiaria(@ApiParam(value = "Codigo de Agencia Beneficiaria del Proyecto a Eliminar", required = true)
                                                                   @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Codigo enviado ( codigoActividad )
            TblActividadOrganizacionAgenciaBeneficiaria _tblActividadOrganizacionAgenciaBeneficiaria = _actividadOrganizacionAgenciaBeneficiariaRepository.findByCodigoActividad(codigoActividad);

            try {
                if (_actividadOrganizacionAgenciaBeneficiariaRepository.countByCodigoActividad(codigoActividad) > 0) {
                    // Realizamos la Persistencia de los Datos

                    _actividadOrganizacionAgenciaBeneficiariaRepository.deleletedCodigoActividad(codigoActividad);
                    _actividadOrganizacionAgenciaBeneficiariaRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Socio Desarrollo para este Proyecto, se ha Eliminado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgMethod = "No Existe un registro de agencia beneficiaria para este Proyecto !!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Eliminar el Agencia Beneficiaria del Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Agencia Beneficiaria para este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deletedActividadAgenciaBeneficiaria

}
