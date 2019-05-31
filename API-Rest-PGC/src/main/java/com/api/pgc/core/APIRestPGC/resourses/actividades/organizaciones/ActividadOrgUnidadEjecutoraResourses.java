/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades.organizaciones;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.organizaciones.TblActividadOrganizacionSocioDesarrollo;
import com.api.pgc.core.APIRestPGC.models.actividades.organizaciones.TblActividadOrganizacionUnidadEjecutora;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblOrganizacion;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.organizaciones.ActividadOrganizacionSocioDesarrolloRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.organizaciones.ActividadOrganizacionUnidadEjecutoraRepository;
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
public class ActividadOrgUnidadEjecutoraResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadOrganizacionUnidadEjecutoraRepository _actividadOrganizacionUnidadEjecutoraRepository;

    @Autowired
    ActividadRepository _actividadRepository;

    @Autowired
    OrganizacionRepository _organizacionRepository;

    /**
     * Metodo que despliega la Lista todas las Unidades Ejecutoras de una Actividad de la BD
     *
     * @return Lista de Unidad Ejecutora de una Actividad de la BD
     * @autor Allan Madrid | AMA
     * @version 27/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todas las Unidades Ejecutoras de los Proyectos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ORGANIZACIONES_ACT_ENDPOINT_UNIDAD_EJECUTORA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllActvidadesUnidadEjecutora() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas las Unidades Ejecutoras registrados en la BD";

        try {
            // Sobreescribe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadOrganizacionUnidadEjecutoraRepository.findAll());
            msgExeptions.map.put("countRecords", _actividadOrganizacionUnidadEjecutoraRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAllActividadesUnidadEjecutora


    /**
     * Metodo que despliega las Unidades Ejecutoras a la Actividad de la BD
     *
     * @param idActividad Identificador de la Actividad a Buscar
     * @return Unidad Ejecutora de una Atividad de la BD
     * @autor Allan Madrid | AMA
     * @version 27/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna la Unidad Ejecutora de la Actividad enviada a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ORGANIZACIONES_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_UNIDAD_EJECUTORA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getUnidadEjecutoraByIdActividad(@ApiParam(value = "Identificador de la Actividad a Buscar", required = true)
                                                                   @PathVariable("idActividad") long idActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad para verificar si existe
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(idActividad);

            if (_actividadOrganizacionUnidadEjecutoraRepository.countByIdActividad(_tblActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de la Unidad Ejecutora del Proyecto consultado";

                msgExeptions.map.put("error", "No data found");

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de la Unidad Ejecutora del Proyecto";
                msgExeptions.map.put("data", _actividadOrganizacionUnidadEjecutoraRepository.getCodigoActividadOrganizacion(_tblActividad));

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
     * Metodo que despliega la Unidad Ejecutora asociado a Proyecto de la BD
     *
     * @param codigoActividad Codigo de  asociado a Proyecto a Buscar
     * @return Socio Desarrollo de la BD
     * @autor Nahum Martinez | NAM
     * @version 14/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Socio Desarrollo a buscar el codigo a la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ORGANIZACIONES_ACT_ENDPOINT_FIND_BY_COD_PROGRAMA_UNIDAD_EJECUTORA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getUnidadEjecutoraByCodigoActividad(@ApiParam(value = "Código de Unidad Ejecutora asociado a un Proyecto a Buscar", required = true)
                                                                       @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadOrganizacionUnidadEjecutoraRepository.countByCodigoActividad(codigoActividad) == 0) {
                // Sobreescribe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Unidad Ejecutora asociados a Proyecto consultado";

                msgExeptions.map.put("data", _actividadOrganizacionUnidadEjecutoraRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("find", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Se ha encontrado dato de Unidad Ejecutora asociado a Proyecto consultado";
                msgExeptions.map.put("data", _actividadOrganizacionUnidadEjecutoraRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("find", true);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "Se ha encontrado datos de Unidad Ejecutora asociado a un Proyecto Consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getUnidadEjecutoraByCodigoActividad


    /**
     * Metodo que Solicita un json con los datos de la Entidad Unidad Ejecutora con Relacion
     * a Actividades
     *
     * @param _actividadUnidadEjecutoraJson Obtiene desde el request los datos del Socio Desarrollo a ingresar
     * @return Mensaje de Confirmacion de Registro de Socio Desarrollo
     * @autor Allan Madrid| AMA
     * @version 27/05/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean de la Unidad Ejecutora de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = ORGANIZACIONES_ACT_ENDPOINT_NEW_UNIDAD_EJECUTORA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addActividadSocioDesarrollo(@ApiParam(value = "Json de Unidad Ejecutora del Proyecto a Ingresar", required = true)
                                                               @RequestBody @Valid final TblActividadOrganizacionUnidadEjecutora _actividadUnidadEjecutoraJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingreso
        Date dateActual = new Date();
        System.out.println( "************************"  +_actividadUnidadEjecutoraJson.getPorcentajePart());

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividad": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(_actividadUnidadEjecutoraJson.getIdActividad().getIdActividad());

            try {
                // Busca la Unidad Ejecutora, desde el Repositorio con el Parametro del Json enviado ( "idOrganizacion": {"idOrganizacion": valor })
                TblOrganizacion _tblOrganizacion = _organizacionRepository.findByIdOrganizacion(_actividadUnidadEjecutoraJson.getIdOrganizacion().getIdOrganizacion());

                // Busca el Proyecto con el Proposito de validar que no se meta otro Item mas,
                // desde el Repositorio de Unidad Ejecutora con el Parametro del Json enviado ( "idActividad": _tblActividad )

                if (_actividadOrganizacionUnidadEjecutoraRepository.countByCodigoActividad(_actividadUnidadEjecutoraJson.getCodigoActividad()) > 0) {
                    msgMethod = "Ya Existe un registro con el Código de Unidad Ejecutora para este Proyecto !! " + _actividadUnidadEjecutoraJson.getCodigoActividad();

                    msgExeptions.map.put("findRecord", true);
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    // Seteo de las Fecha y Hora de Creacion
                    _actividadUnidadEjecutoraJson.setFechaCreacion(dateActual);
                    _actividadUnidadEjecutoraJson.setHoraCreacion(dateActual);
                    _actividadUnidadEjecutoraJson.setPorcentajePart(_actividadUnidadEjecutoraJson.getPorcentajePart());


                    // Seteamos la Actividad de Actividad y Unidad Ejecutora
                    _actividadUnidadEjecutoraJson.setIdActividad(_tblActividad);
                    _actividadUnidadEjecutoraJson.setIdOrganizacion(_tblOrganizacion);

                    // Realizamos la Persistencia de los Datos
                    _actividadOrganizacionUnidadEjecutoraRepository.save(_actividadUnidadEjecutoraJson);
                    _actividadOrganizacionUnidadEjecutoraRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "La Unidad Ejecutora para este Proyecto, " + _actividadUnidadEjecutoraJson.getCodigoActividad() + " se ha Ingresado de forma satisfactoria!!";

                    //Retorno del json
                    msgExeptions.map.put("findRecord", false);
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar la Unidad Ejecutora del Proyecto, con la informacion indicada !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Proyecto que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | addActividadUnidadEjecutora


    /**
     * Metodo que Solicita un json con los datos de la Entidad Unidad Ejecutora con Relacion
     * a Actividades
     *
     * @param codigoActividad Identificador de la Unidad Ejecutora con Proyecto a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion de Unidad Ejecutora
     * @autor Allan Madrid| AMA
     * @version 27/05/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el codigo de Unidad Ejecutora", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = ORGANIZACIONES_ACT_ENDPOINT_DELETE_UNIDAD_EJECUTORA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadUnidadEjecutora(@ApiParam(value = "Codigo de la Unidad Ejectutora del Proyecto a Eliminar", required = true)
                                                                   @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Codigo enviado ( codigoActividad )
            TblActividadOrganizacionUnidadEjecutora _tblActividadOrganizacionUnidadEjecutora = _actividadOrganizacionUnidadEjecutoraRepository.findByCodigoActividad(codigoActividad);

            try {
                if (_actividadOrganizacionUnidadEjecutoraRepository.countByCodigoActividad(codigoActividad) > 0) {
                    // Realizamos la Persistencia de los Datos

                    _actividadOrganizacionUnidadEjecutoraRepository.deleletedCodigoActividad(codigoActividad);
                    _actividadOrganizacionUnidadEjecutoraRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "la Unidad Ejecutora para este Proyecto, se ha Eliminado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgMethod = "No Existe un registro de Unidad Ejecutora para este Proyecto !!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Eliminar la Unidad Ejecutora del Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Unidad Ejecutora para este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deletedActividadUnidadEjecutora

    /**
     * Metodo que Solicita un json con los datos de la Entidad Unidad Ejecutora con Relacion
     * a Actividades
     *
     * @param _actividadUnidadEjecutoraJson Obtiene desde el request los datos de Unidad Ejecutora a Modificar
     * @return Mensaje de Confirmacion de Registro de Unidad Ejecutora
     * @autor Allan Madrid| AMA
     * @version 27/05/2019/v1.0
     */
    @ApiOperation(value = "Actualiza a la BD, la Información enviada por el Bean de la Unidad Ejecutora de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PutMapping (value = ORGANIZACIONES_ACT_ENDPOINT_EDIT_UNIDAD_EJECUTORA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> editActividadUnidadEjecutora(@ApiParam(value = "Json de Unidad Ejecutora del Proyecto a Ingresar", required = true)
                                                                    @PathVariable("idActividadUnidadEjecutora") long idActividadUnidadEjecutora,
                                                                    @RequestBody  final TblActividadOrganizacionUnidadEjecutora _actividadUnidadEjecutoraJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingreso
        Date dateActual = new Date();
        //System.out.println( "************************"  +_actividadUnidadEjecutoraJson.getPorcentajePart());

        try {
            // Busca la Actividad, desde el Repositorio con el Parametro del Json enviado ( "idActividad": {"idActividad": valor })
            TblActividadOrganizacionUnidadEjecutora _tblActividadOrganizacionUnidadEjecutora = _actividadOrganizacionUnidadEjecutoraRepository.findByIdActividadUnidadEjecutora(idActividadUnidadEjecutora);


            if(_actividadOrganizacionUnidadEjecutoraRepository.countByIdActividadUnidadEjecutora(idActividadUnidadEjecutora) >0){

                //Buscamos el Tipo de ACT.Ejecutora segun el parametro enviado

            // Busca el Proyecto con el Proposito de validar que no se meta otro Item mas,
                // desde el Repositorio de Unidad Ejecutora con el Parametro del Json enviado ( "idActividad": _tblActividad )
                try  {
                    // Seteo de las Fecha y Hora de Creacion
                    _tblActividadOrganizacionUnidadEjecutora.setFechaCreacion(dateActual);
                    _tblActividadOrganizacionUnidadEjecutora.setHoraCreacion(dateActual);
                    _tblActividadOrganizacionUnidadEjecutora.setPorcentajePart(_actividadUnidadEjecutoraJson.getPorcentajePart());
;


                    // Realizamos la Persistencia de los Datos

                    _actividadOrganizacionUnidadEjecutoraRepository.save(_tblActividadOrganizacionUnidadEjecutora);
                    _actividadOrganizacionUnidadEjecutoraRepository.flush();

                    // return Repository
                    msgMethod = "La Unidad Ejecutora para este Proyecto, " + _actividadUnidadEjecutoraJson.getCodigoActividad() + " se ha actualizado de forma satisfactoria!!";

                    //Retorno del json
                    msgExeptions.map.put("findRecord", false);
                    return msgExeptions.msgJson(msgMethod, 200);
                } catch (Exception ex) {
                    msgMethod = "Hay problemas al momento de Actualizar la Organizacion.";
                    throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
                }
        } else {
            //Retorno del json
            msgMethod = "No se encuentra una Organizacion con el parametro enviado !!";
            return msgExeptions.msgJson(msgMethod, 200);
        }
    } catch (Exception ex) {
        msgMethod = "Hay problemas al momento de Actualizar la Organizacion.";
        throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
    }
} // FIN | editActividadEjecutora


}
