/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades.programas;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.programas.TblActividadProgramaPlanNacion;
import com.api.pgc.core.APIRestPGC.models.programas.TblProgramaPlanNacion;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.programas.ActividadProgramaPlanNacionRepository;
import com.api.pgc.core.APIRestPGC.repository.programas.ProgramaPlanNacionRepository;
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
@Api(value = "ActividadPlanNacionAPI", description = "Operaciones sobre el Modulo de Proyectos Programas", tags = "Programas de Proyecto")
public class ActividadProgramaPlanNacionResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadProgramaPlanNacionRepository _actividadProgramaPlanNacionRepository;

    @Autowired
    ActividadRepository _actividadRepository;

    @Autowired
    ProgramaPlanNacionRepository _programaPlanNacionRepository;

    /**
     * Metodo que despliega la Lista de todas los Plan de Nacion de una Actividad de la BD
     *
     * @return Lista de Plan de Nacion de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todas los Plan de Nacion los Proyectos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_PLAN_NACION_ACT_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllActvidadesPlanNacion() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas los Sectores de Plan de Nacion registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadProgramaPlanNacionRepository.findAll());
            msgExeptions.map.put("countRecords", _actividadProgramaPlanNacionRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAllActvidadesPlanNacion


    /**
     * Metodo que despliega los Plan de Nacion a la Actividad de la BD
     *
     * @param idActividad Identificador de la Actividad a Buscar
     * @return Plan de Nacion de una Atividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna los Plan de Nacion de la Actividad enviada a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_PLAN_NACION_ENDPOINT_FIND_BY_ID_ACTIVIDAD, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getPlanNacionByIdActividad(@ApiParam(value = "Identificador de la Actividad a Buscar", required = true)
                                                              @PathVariable("idActividad") long idActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad para verificar si existe
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(idActividad);

            if (_actividadProgramaPlanNacionRepository.countByIdActividad(_tblActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de los Plan de Nacion del Proyecto consultado";

                msgExeptions.map.put("error", "No data found");

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de los Plan de Nacion del Proyecto";
                msgExeptions.map.put("data", _actividadProgramaPlanNacionRepository.getCodigoActividadPrograma(_tblActividad));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado el Proyecto solicitado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getPlanNacionByIdActividad

    /**
     * Metodo que despliega el Plan Nacion asociado a Proyecto de la BD
     *
     * @param codigoActividad Codigo de Plan Nacion asociado a Proyecto a Buscar
     * @return Plan Nacion de la BD
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Plan Nacion a buscar el codigo a la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_PLAN_NACION_ENDPOINT_FIND_BY_COD_PROGRAMA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getPlanNacionByCodigoActividad(@ApiParam(value = "Código de Plan Nacion asociado a un Proyecto a Buscar", required = true)
                                                                  @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadProgramaPlanNacionRepository.countByCodigoActividad(codigoActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Plan Nacion asociados a Proyecto consultado";

                msgExeptions.map.put("data", _actividadProgramaPlanNacionRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("find", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Se ha encontrado dato de Plan Nacion asociados a Proyecto consultado";
                msgExeptions.map.put("data", _actividadProgramaPlanNacionRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("find", true);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "Se ha encontraddo datos de Plan Nacion asociado a un Proyecto Consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getPlanNacionByCodigoActividad


    /**
     * Metodo que Solicita un json con los datos de la Entidad Plan Nacion con Relacion
     * a Actividades
     *
     * @param _actividadPlanNacionJson Obtiene desde el request los datos del Plan Nacion a ingresar
     * @return Mensaje de Confirmacion de Registro de Plan Nacion
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del Plan Nacion de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = PROGRAMAS_PLAN_NACION_ENDPOINT_NEW, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addActividadPlanNacion(@ApiParam(value = "Json de Plan Nacion del Proyecto a Ingresar", required = true)
                                                          @RequestBody @Valid final TblActividadProgramaPlanNacion _actividadPlanNacionJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividad": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(_actividadPlanNacionJson.getIdActividad().getIdActividad());

            try {
                // Busca la Plan Nacion, desde el Reporsitorio con el Parametro del Json enviado ( "idSector": {"idSector": valor })
                TblProgramaPlanNacion _tblProgramaPlanNacion = _programaPlanNacionRepository.findByIdPrograma(_actividadPlanNacionJson.getIdProgramaPlanNacion().getIdPrograma());

                // Busca el Proyecto con el Proposito de validar que no se meta otro Item mas,
                // desde el Reporsitorio de Plan Nacion con el Parametro del Json enviado ( "idActividad": _tblActividad )

                if (_actividadProgramaPlanNacionRepository.countByCodigoActividad(_actividadPlanNacionJson.getCodigoActividad()) > 0) {
                    msgMethod = "Ya Existe un registro con el Código de Plan Nacion para este Proyecto !! " + _actividadPlanNacionJson.getCodigoActividad();

                    msgExeptions.map.put("findRecord", false);
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    // Seteo de las Fecha y Hora de Creacion
                    _actividadPlanNacionJson.setFechaCreacion(dateActual);
                    _actividadPlanNacionJson.setHoraCreacion(dateActual);

                    // Seteamos la Actividad de Actividad y Plan Nacion
                    _actividadPlanNacionJson.setIdActividad(_tblActividad);
                    _actividadPlanNacionJson.setIdProgramaPlanNacion(_tblProgramaPlanNacion);

                    // Realizamos la Persistencia de los Datos
                    _actividadProgramaPlanNacionRepository.save(_actividadPlanNacionJson);
                    _actividadProgramaPlanNacionRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Plan Nacion para este Proyecto, se ha Ingresado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Plan Nacion del Proyecto, con la informacion indicada !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Proyecto que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | addActividadPlanNacion


    /**
     * Metodo que Solicita un json con los datos de la Entidad Plan Nacion con Relacion
     * a Actividades
     *
     * @param codigoActividad Identificador de la Plan Nacion con Proyecto a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion de Plan Nacion
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el codigo de Plan Nacion", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = PROGRAMAS_PLAN_NACION_ENDPOINT_DELETE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadPlanNacion(@ApiParam(value = "Codigo de Plan Nacion del Proyecto a Eliminar", required = true)
                                                              @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Codigo enviado ( codigoActividad )
            TblActividadProgramaPlanNacion _tblActividadProgramaPlanNacion = _actividadProgramaPlanNacionRepository.findByCodigoActividad(codigoActividad);

            try {
                if (_actividadProgramaPlanNacionRepository.countByCodigoActividad(codigoActividad) > 0) {
                    // Realizamos la Persistencia de los Datos

                    _actividadProgramaPlanNacionRepository.deleletedCodigoActividad(codigoActividad);
                    _actividadProgramaPlanNacionRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Plan Nacion para este Proyecto, se ha Eliminado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgMethod = "No Existe un registro de programas de plan de nacion  !!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Eliminar el Plan Nacion del Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Plan Nacion para este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deletedActividadPlanNacion

}
