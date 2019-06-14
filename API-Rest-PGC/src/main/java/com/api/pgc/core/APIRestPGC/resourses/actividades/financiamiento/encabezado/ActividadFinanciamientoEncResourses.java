/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades.financiamiento.encabezado;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.encabezado.TblActividadFinanciamientoEnc;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.actividades.TblMonedaActividad;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.encabezado.ActividadFinanciamientoEncRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.actividades.MonedaActividadRepository;
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
@Api(value = "ActividadFinancEncAPI", description = "Operaciones sobre el Modulo de Proyectos Financiamiento", tags = "Financiamiento de Proyecto")
public class ActividadFinanciamientoEncResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadFinanciamientoEncRepository _actividadFinanciamientoEncRepository;

    @Autowired
    ActividadRepository _actividadRepository;

    @Autowired
    MonedaActividadRepository _monedaActividadRepository;

    /**
     * Metodo que despliega la Lista de todos los Encabezados de Financiamiento de una Actividad de la BD
     *
     * @return Lista de Encabezados de Financiamiento de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de todos los Encabezados de Financiamiento los Proyectos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_FINANC_ENC, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllActvidadesFinancEnc() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas los Encabezados de Financiamiento registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadFinanciamientoEncRepository.findAll());
            msgExeptions.map.put("countRecords", _actividadFinanciamientoEncRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAllActvidadesFinancEnc


    /**
     * Metodo que despliega los Encabezados de Financiamiento a la Actividad de la BD
     *
     * @param idActividad Identificador de la Actividad a Buscar
     * @return Encabezados de Financiamiento de una Atividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna los Encabezados de Financiamiento de la Actividad enviada a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_FINANC_ENC, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getActvidadFinancEncByIdActividad(@ApiParam(value = "Identificador de la Actividad a Buscar", required = true)
                                                                     @PathVariable("idActividad") long idActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad para verificar si existe
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(idActividad);

            if (_actividadFinanciamientoEncRepository.countByIdActividad(_tblActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de los Encabezados de Financiamiento del Proyecto consultado";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("findRecord", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de los Encabezados de Financiamiento del Proyecto";
                msgExeptions.map.put("findRecord", true);

                msgExeptions.map.put("data", _actividadFinanciamientoEncRepository.getListFinancEnc(_tblActividad));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado el Proyecto solicitado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getActvidadFinancEncByIdActividad


    /**
     * Metodo que despliega el Financiamiento Encabezado asociado a Proyecto de la BD
     *
     * @param codigoFinancEnc Codigo de Financiamiento Encabezado asociado a Proyecto a Buscar
     * @return Financiamiento Encabezado de la BD
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Financiamiento Encabezado a buscar el codigo a la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_COD_FINANC_ENC, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getActvidadFinancEncByCodigoFinancEnc(@ApiParam(value = "Código de transacción de financiamiento encabezado asociado a un Proyecto a Buscar", required = true)
                                                                         @PathVariable("codigoFinancEnc") String codigoFinancEnc) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadFinanciamientoEncRepository.countByCodigoFinancEnc(codigoFinancEnc) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Financiamiento Encabezado asociados a Proyecto consultado";

                msgExeptions.map.put("data", _actividadFinanciamientoEncRepository.findByCodigoFinancEnc(codigoFinancEnc));
                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("find", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Se ha encontrado dato de Financiamiento Encabezado asociados a Proyecto consultado";
                msgExeptions.map.put("data", _actividadFinanciamientoEncRepository.findByCodigoFinancEnc(codigoFinancEnc));
                msgExeptions.map.put("find", true);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "Se ha encontraddo datos de Financiamiento Encabezado asociado a un Proyecto Consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getActvidadFinancEncByCodigoFinancEnc


    /**
     * Metodo que Solicita un json con los datos de la Entidad Financiamiento Encabezado con Relacion
     * a Actividades
     *
     * @param _actividadFinancEncJson Obtiene desde el request los datos del Financiamiento Encabezado a ingresar
     * @return Mensaje de Confirmacion de Registro de Financiamiento Encabezado
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del Financiamiento Encabezado de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_NEW_FINANC_ENC, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addActividadActvidadFinancEnc(@ApiParam(value = "Json de Financiamiento Encabezado del Proyecto a Ingresar", required = true)
                                                                 @RequestBody @Valid final TblActividadFinanciamientoEnc _actividadFinancEncJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividad": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(_actividadFinancEncJson.getIdActividad().getIdActividad());

            try {
                // Busca la Financiamiento Encabezado, desde el Reporsitorio con el Parametro del Json enviado ( "idMonedaActividad": {"idMonedaActividad": valor })
                TblMonedaActividad _tblMonedaActividad = _monedaActividadRepository.findByIdMonedaActividad(_actividadFinancEncJson.getIdMonedaActividad().getIdMonedaActividad());

                // Busca el Proyecto con el Proposito de validar que no se meta otro Item mas,
                // desde el Reporsitorio de Financiamiento Encabezado con el Parametro del Json enviado ( "idActividad": _tblActividad )

                if (_actividadFinanciamientoEncRepository.countByCodigoFinancEnc(_actividadFinancEncJson.getCodigoFinancEnc()) > 0) {
                    msgMethod = "Ya Existe un registro con el código de transacción Financiamiento Encabezado para este Proyecto !! " + _actividadFinancEncJson.getCodigoFinancEnc();

                    msgExeptions.map.put("findRecord", true);
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    // Seteo de las Fecha y Hora de Creacion
                    _actividadFinancEncJson.setFechaCreacion(dateActual);
                    _actividadFinancEncJson.setHoraCreacion(dateActual);

                    _actividadFinancEncJson.setMontoActividad(_actividadFinancEncJson.getMontoActividad());
                    _actividadFinancEncJson.setFechaTransaccion(_actividadFinancEncJson.getFechaTransaccion());

                    // Seteamos la Actividad de Actividad y Financiamiento Encabezado
                    _actividadFinancEncJson.setIdActividad(_tblActividad);
                    _actividadFinancEncJson.setIdMonedaActividad(_tblMonedaActividad);

                    // Realizamos la Persistencia de los Datos
                    _actividadFinanciamientoEncRepository.save(_actividadFinancEncJson);
                    _actividadFinanciamientoEncRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Financiamiento Encabezado para este Proyecto, " + _actividadFinancEncJson.getCodigoFinancEnc() + " se ha Ingresado de forma satisfactoria!!";
                    msgExeptions.map.put("data", _actividadFinanciamientoEncRepository.findByCodigoFinancEnc(_actividadFinancEncJson.getCodigoFinancEnc()));

                    //Retorno del json
                    msgExeptions.map.put("findRecord", false);
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Financiamiento Encabezado del Proyecto, con la informacion indicada !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Proyecto que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | addActividadActvidadFinancEnc


    /**
     * Metodo que Solicita un json con los datos de la Entidad Financiamiento Encabezado con Relacion
     * a Actividades
     *
     * @param codigoFinancEnc Identificador de la Financiamiento Encabezado con Proyecto a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion de Financiamiento Encabezado
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el codigo de Financiamiento Encabezado", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_DELETE_FINANC_ENC, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadActvidadFinancEnc(@ApiParam(value = "Codigo de Financiamiento Encabezado del Proyecto a Eliminar", required = true)
                                                                     @PathVariable("codigoFinancEnc") String codigoFinancEnc) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Codigo enviado ( codigoFinancEnc )
            TblActividadFinanciamientoEnc _tblActividadFinanciamientoEnc = _actividadFinanciamientoEncRepository.findByCodigoFinancEnc(codigoFinancEnc);

            try {
                if (_actividadFinanciamientoEncRepository.countByCodigoFinancEnc(codigoFinancEnc) > 0) {
                    // Realizamos la Persistencia de los Datos

                    _actividadFinanciamientoEncRepository.deleletedCodigoFinancEnc(codigoFinancEnc);
                    _actividadFinanciamientoEncRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Financiamiento Encabezado para este Proyecto, se ha Eliminado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgMethod = "No Existe un registro de Encabezado de Financiamiento para este Proyecto !!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Eliminar el Financiamiento Encabezado del Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Financiamiento Encabezado para este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deletedActividadActvidadFinancEnc


    /**
     * Metodo que Solicita un json con los datos de la Entidad Financiamiento Encabezado con Relacion
     * a Actividades
     *
     * @param _actividadFinancEncJson Obtiene desde el request los datos del Financiamiento Encabezado a Actualizar
     * @param idActividadFinancEnc    Identificador del item de Encabezado a Actualizar
     * @return Mensaje de Confirmacion de Actualizacion de Financiamiento Encabezado
     * @autor Nahum Martinez | NAM
     * @version 14/06/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del Financiamiento Encabezado de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PutMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_EDIT_FINANC_ENC, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> editActividadActvidadFinancEnc(@ApiParam(value = "Json de Financiamiento Encabezado del Proyecto a Actualizar", required = true)
                                                                  @PathVariable("idActividadFinancEnc") long idActividadFinancEnc,
                                                                  @RequestBody @Valid final TblActividadFinanciamientoEnc _actividadFinancEncJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca la el Financiamiento Encabezado de la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividadFinancEnc": {"idActividadFinancEnc": valor })
            TblActividadFinanciamientoEnc _tblActividadFinanciamientoEnc = _actividadFinanciamientoEncRepository.findByIdActividadFinancEnc(idActividadFinancEnc);

            try {
                // Busca la Financiamiento Encabezado, desde el Reporsitorio con el Parametro del Json enviado ( "idMonedaActividad": {"idMonedaActividad": valor })
                TblMonedaActividad _tblMonedaActividad = _monedaActividadRepository.findByIdMonedaActividad(_actividadFinancEncJson.getIdMonedaActividad().getIdMonedaActividad());

                // Busca el Proyecto con el Proposito de validar que no se meta otro Item mas,
                // desde el Reporsitorio de Financiamiento Encabezado con el Parametro del Json enviado ( "idActividadFinancEnc": _tblActividadFinanciamientoEnc )

                if (_actividadFinanciamientoEncRepository.countByCodigoFinancEnc(_actividadFinancEncJson.getCodigoFinancEnc()) > 0) {
                    // Seteo de las Fecha y Hora de Creacion
                    _tblActividadFinanciamientoEnc.setFechaModificacion(dateActual);
                    _tblActividadFinanciamientoEnc.setHoraModificacion(dateActual);

                    _tblActividadFinanciamientoEnc.setMontoActividad(_actividadFinancEncJson.getMontoActividad());
                    _tblActividadFinanciamientoEnc.setFechaTransaccion(_actividadFinancEncJson.getFechaTransaccion());

                    // Seteamos la Moneda de Financiamiento
                    _tblActividadFinanciamientoEnc.setIdMonedaActividad(_tblMonedaActividad);

                    // Realizamos la Persistencia de los Datos
                    _actividadFinanciamientoEncRepository.save(_tblActividadFinanciamientoEnc);
                    _actividadFinanciamientoEncRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Financiamiento Encabezado para este Proyecto, " + _actividadFinancEncJson.getCodigoFinancEnc() + " se ha Actualizado de forma satisfactoria!!";
                    msgExeptions.map.put("data", _actividadFinanciamientoEncRepository.findByCodigoFinancEnc(_actividadFinancEncJson.getCodigoFinancEnc()));

                    //Retorno del json
                    msgExeptions.map.put("findRecord", true);
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgMethod = "No Existe un registro con el código de transacción Financiamiento Encabezado para este Proyecto !! " + _actividadFinancEncJson.getCodigoFinancEnc();

                    msgExeptions.map.put("findRecord", false);
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al intentar Actualizar el Financiamiento Encabezado del Proyecto, con la informacion indicada !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Proyecto que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | editActividadActvidadFinancEnc

}
