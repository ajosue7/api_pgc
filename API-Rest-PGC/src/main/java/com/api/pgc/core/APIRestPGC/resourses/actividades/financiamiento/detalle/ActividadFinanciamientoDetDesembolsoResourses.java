/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades.financiamiento.detalle;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadFinanciamientoDet;
import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadFinanciamientoDetDesembolso;
import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadTipoTransaccion;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.actividades.TblMonedaActividad;
import com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle.ActividadFinanciamientoDetDesembolsoRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle.ActividadFinanciamientoDetRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle.ActividadTipoTransaccionRepository;
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
@Api(value = "ActividadFinancDetDesembolsoAPI", description = "Operaciones sobre el Modulo de Proyectos Financiamiento", tags = "Financiamiento de Proyecto")
public class ActividadFinanciamientoDetDesembolsoResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadFinanciamientoDetDesembolsoRepository _actividadFinanciamientoDetDesembolsoRepository;

    @Autowired
    ActividadFinanciamientoDetRepository _actividadFinanciamientoDetRepository;

    @Autowired
    ActividadTipoTransaccionRepository _actividadTipoTransaccionRepository;

    @Autowired
    MonedaActividadRepository _monedaActividadRepository;

    /**
     * Metodo que despliega la Lista de todos los Detalles de Financiamiento Desembolso de una Actividad de la BD
     *
     * @return Lista de Detalles de Financiamiento Desembolso de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 09/07/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de todos los Detalles de Financiamiento Desembolso los Proyectos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_FINANC_DET_DESEMBOLSO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllActvidadesFinancDetDesembolso() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas los Detalles de Financiamiento Desembolso registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadFinanciamientoDetDesembolsoRepository.findAll());
            msgExeptions.map.put("countRecords", _actividadFinanciamientoDetDesembolsoRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAllActvidadesFinancDetDesembolso


    /**
     * Metodo que despliega los Detalles de Financiamiento Desembolso a la Actividad de la BD
     *
     * @param idActividadDet Identificador del Detalle de Financiamiento a Buscar
     * @return Detalles de Financiamiento Desembolso de una Atividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 09/07/2019/v1.0
     */
    @ApiOperation(value = "Retorna los Detalles de Financiamiento Desembolso de la Actividad enviada a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_FINANC_DET_DESEMBOLSO_2, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getIdActividadFinancDetDesembolso(@ApiParam(value = "Identificador del Detalle de la Actividad a Buscar", required = true)
                                                                     @PathVariable("idActividadDet") long idActividadDet) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad para verificar si existe
            TblActividadFinanciamientoDet _tblActividadFinanciamientoDet = _actividadFinanciamientoDetRepository.findByIdActividadFinancDet(idActividadDet);

            if (_actividadFinanciamientoDetDesembolsoRepository.countByIdActividadFinancDet(_tblActividadFinanciamientoDet) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de los Detalles de Desembolso del Proyecto consultado";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("findRecord", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de los Desembolsos del Proyecto";
                msgExeptions.map.put("findRecord", true);

                msgExeptions.map.put("data", _actividadFinanciamientoDetDesembolsoRepository.getByIdFinancDet(_tblActividadFinanciamientoDet));
                msgExeptions.map.put("countRecords", _actividadFinanciamientoDetDesembolsoRepository.countByIdActividadFinancDet(_tblActividadFinanciamientoDet));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado el detalle del financiamiento del Proyecto solicitado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getActvidadFinancDetDesembolsoByIdFinancEnc


    /**
     * Metodo que despliega el Financiamiento Detalle asociado a Proyecto de la BD
     *
     * @param codigoFinancDetDesembolso Codigo de Financiamiento Detalle asociado a Proyecto a Buscar
     * @return Financiamiento Detalle de la BD
     * @autor Nahum Martinez | NAM
     * @version 09/07/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Financiamiento Detalle a buscar el codigo a la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_COD_FINANC_DET_DESEMBOLSO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getActvidadFinancDetDesembolsoByCodigoFinancDetDesembolso(@ApiParam(value = "Código de transacción de financiamiento Detalle asociado a un Proyecto a Buscar", required = true)
                                                                                             @PathVariable("codigoFinancDetDesembolso") String codigoFinancDetDesembolso) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadFinanciamientoDetDesembolsoRepository.countByCodigoFinancDesembolso(codigoFinancDetDesembolso) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Desembolsos asociados a Proyecto consultado";

                msgExeptions.map.put("data", _actividadFinanciamientoDetDesembolsoRepository.countByCodigoFinancDesembolso(codigoFinancDetDesembolso));
                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("findRecord", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Se ha encontrado dato de Desembolsos asociados a Proyecto consultado";
                msgExeptions.map.put("data", _actividadFinanciamientoDetDesembolsoRepository.countByCodigoFinancDesembolso(codigoFinancDetDesembolso));
                msgExeptions.map.put("findRecord", true);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "Se ha encontraddo datos de Financiamiento Detalle asociado a un Proyecto Consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getActvidadFinancDetDesembolsoByCodigoFinancDetDesembolso


    /**
     * Metodo que Solicita un json con los datos de la Entidad Financiamiento Detalle Desembolso con Relacion
     * a Actividades
     *
     * @param _actividadFinancDetDesembolsoJson Obtiene desde el request los datos del Financiamiento Detalle a ingresar
     * @return Mensaje de Confirmacion de Registro de Financiamiento Detalle
     * @autor Nahum Martinez | NAM
     * @version 09/07/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del Financiamiento Detalle de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_NEW_FINANC_DET_DESEMBOLSO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addActividadFinancDetDesembolso(@ApiParam(value = "Json de Financiamiento Detalle Desembolso del Proyecto a Ingresar", required = true)
                                                                   @RequestBody @Valid final TblActividadFinanciamientoDetDesembolso _actividadFinancDetDesembolsoJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca la Detalla de Financiamiento de la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividadFinancDet": {"idActividadFinancDet": valor })
            TblActividadFinanciamientoDet _tblActividadFinanciamientoDet = _actividadFinanciamientoDetRepository.findByIdActividadFinancDet((_actividadFinancDetDesembolsoJson.getIdActividadFinancDet().getIdActividadFinancDet()));

            try {
                // Busca el Tipo de Transacción, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoTransaccion": {"idTipoTransaccion": valor })
                TblActividadTipoTransaccion _tblActividadTipoTransaccion = _actividadTipoTransaccionRepository.findByIdTipoTransaccion(_actividadFinancDetDesembolsoJson.getIdTipoTransaccion().getIdTipoTransaccion());

                try {
                    // Busca la Moneda de Transaccion, desde el Reporsitorio con el Parametro del Json enviado ( "idMonedaActividad": {"idMonedaActividad": valor })
                    TblMonedaActividad _tblMonedaActividad = _monedaActividadRepository.findByIdMonedaActividad(_actividadFinancDetDesembolsoJson.getIdMonedaActividad().getIdMonedaActividad());

                    if (_actividadFinanciamientoDetDesembolsoRepository.countByCodigoFinancDesembolso(_actividadFinancDetDesembolsoJson.getCodigoFinancDesembolso()) > 0) {
                        msgMethod = "Ya Existe un registro con el código de transacción Desembolso para este Proyecto !! " + _actividadFinancDetDesembolsoJson.getCodigoFinancDesembolso();

                        msgExeptions.map.put("findRecord", true);
                        return msgExeptions.msgJson(msgMethod, 200);
                    } else {
                        // Seteo de las Fecha y Hora de Creacion
                        _actividadFinancDetDesembolsoJson.setFechaCreacion(dateActual);
                        _actividadFinancDetDesembolsoJson.setHoraCreacion(dateActual);

                        System.out.println("+++++++++++++++++++++++++++++ " + _actividadFinancDetDesembolsoJson.getCodigoFinancDesembolso());
                        _actividadFinancDetDesembolsoJson.setCodigoFinancDesembolso(_actividadFinancDetDesembolsoJson.getCodigoFinancDesembolso());
                        _actividadFinancDetDesembolsoJson.setMontoDesembolso(_actividadFinancDetDesembolsoJson.getMontoDesembolso());
                        _actividadFinancDetDesembolsoJson.setFechaTransaccion(_actividadFinancDetDesembolsoJson.getFechaTransaccion());

                        // Seteamos Detalle de Financ. de Actividad y Monenda y Tipo de Transaccion
                        _actividadFinancDetDesembolsoJson.setIdActividadFinancDet(_tblActividadFinanciamientoDet);
                        _actividadFinancDetDesembolsoJson.setIdTipoTransaccion(_tblActividadTipoTransaccion);
                        _actividadFinancDetDesembolsoJson.setIdMonedaActividad(_tblMonedaActividad);

                        // Realizamos la Persistencia de los Datos
                        _actividadFinanciamientoDetDesembolsoRepository.save(_actividadFinancDetDesembolsoJson);
                        _actividadFinanciamientoDetDesembolsoRepository.flush();

                        // Retorno de la Funcion
                        msgMethod = "El Desembolso para este Proyecto, " + _actividadFinancDetDesembolsoJson.getCodigoFinancDesembolso() + " se ha Ingresado de forma satisfactoria!!";

                        // Retorno del json
                        msgExeptions.map.put("data", _actividadFinanciamientoDetDesembolsoRepository.findByCodigoFinancDesembolso(_actividadFinancDetDesembolsoJson.getCodigoFinancDesembolso()));
                        msgExeptions.map.put("findRecord", false);

                        return msgExeptions.msgJson(msgMethod, 200);
                    }
                } catch (Exception ex) {
                    msgMethod = "Ha Ocurrido un error al Intentar Grabar el Desembolso para el Proyecto, con la informacion de Moneda seleccionada de Ayuda erronea!!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Desembolso del Proyecto, con la informacion de Tipo de Transacción erronea !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Financiamiento Detalle que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | addActividadFinancDetDesembolso


    /**
     * Metodo que Solicita un json con los datos de la Entidad Desembolso con Relacion
     * a Actividades
     *
     * @param codigoFinancDesembolso Identificador del Desembolso con Proyecto a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion de Desembolso
     * @autor Nahum Martinez | NAM
     * @version 09/07/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el codigo de Financiamiento Detalle Desembolso", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_DELETE_FINANC_DET_DESEMBOLSO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadFinancDetDesembolso(@ApiParam(value = "Codigo de Financiamiento Detalle Desembolso del Proyecto a Eliminar", required = true)
                                                                       @PathVariable("codigoFinancDesembolso") String codigoFinancDesembolso) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Codigo enviado ( codigoFinancDetDesembolso )
            // TblActividadFinanciamientoDet _tblActividadFinanciamientoDet = _actividadFinanciamientoDetRepository.findByCodigoFinancDesembolso(codigoFinancDesembolso);

            try {
                if (_actividadFinanciamientoDetDesembolsoRepository.countByCodigoFinancDesembolso(codigoFinancDesembolso) > 0) {
                    // Realizamos la Persistencia de los Datos

                    _actividadFinanciamientoDetDesembolsoRepository.deleletedCodigoFinancDetDesembolso(codigoFinancDesembolso);
                    _actividadFinanciamientoDetDesembolsoRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Desembolso para este Proyecto, se ha Eliminado de forma satisfactoria!!";
                    msgExeptions.map.put("findRecord", true);

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgMethod = "No Existe un registro de Desembolso para este Proyecto !!";
                    msgExeptions.map.put("findRecord", false);

                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Eliminar el Desembolso del Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Desembolso para este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deletedActividadActvidadFinancDetDesembolso


    /**
     * Metodo que Solicita un json con los datos de la Entidad Desembolso con Relacion
     * a Actividades
     *
     * @param _actividadFinancDetDesembolsoJson Obtiene desde el request los datos del Desembolso a Eliminar
     * @param idActividadFinancDetDesembolso    Identificador de la tabla
     * @return Mensaje de Confirmacion de Registro de Financiamiento Detalle
     * @autor Nahum Martinez | NAM
     * @version 09/07/2019/v1.0
     */
    @ApiOperation(value = "Edita a la BD, la Información enviada por el Bean del Desembolso de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PutMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_EDIT_FINANC_DET_DESEMBOLSO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> editActividadFinancDetDesembolso(@ApiParam(value = "Json de Desembolso del Proyecto a Editar", required = true)
                                                                    @PathVariable("idActividadFinancDetDesembolso") long idActividadFinancDetDesembolso,
                                                                    @RequestBody @Valid final TblActividadFinanciamientoDetDesembolso _actividadFinancDetDesembolsoJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca Desembolso de la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividadFinancDetDesembolso": {"idActividadFinancDetDesembolso": valor })
            TblActividadFinanciamientoDetDesembolso _tblActividadFinanciamientoDetDesembolso = _actividadFinanciamientoDetDesembolsoRepository.findByIdActividadFinancDetDesembolso(idActividadFinancDetDesembolso);

            try {
                // Busca el Tipo de Transacción, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoTransaccion": {"idTipoTransaccion": valor })
                TblActividadTipoTransaccion _tblActividadTipoTransaccion = _actividadTipoTransaccionRepository.findByIdTipoTransaccion(_actividadFinancDetDesembolsoJson.getIdTipoTransaccion().getIdTipoTransaccion());

                try {
                    // Busca la Moneda de Transaccion, desde el Reporsitorio con el Parametro del Json enviado ( "idMonedaActividad": {"idMonedaActividad": valor })
                    TblMonedaActividad _tblMonedaActividad = _monedaActividadRepository.findByIdMonedaActividad(_actividadFinancDetDesembolsoJson.getIdMonedaActividad().getIdMonedaActividad());

                    if (_actividadFinanciamientoDetDesembolsoRepository.countByCodigoFinancDesembolso(_actividadFinancDetDesembolsoJson.getCodigoFinancDesembolso()) > 0) {
                        // Seteo de las Fecha y Hora de Creacion
                        _tblActividadFinanciamientoDetDesembolso.setFechaModificacion(dateActual);
                        _tblActividadFinanciamientoDetDesembolso.setHoraModificacion(dateActual);

                        // Campos propios
                        _tblActividadFinanciamientoDetDesembolso.setMontoDesembolso(_actividadFinancDetDesembolsoJson.getMontoDesembolso());
                        _tblActividadFinanciamientoDetDesembolso.setFechaTransaccion(_actividadFinancDetDesembolsoJson.getFechaTransaccion());

                        // Seteamos Detalle de Financ. de Actividad y Monenda y Tipo de Transaccion
                        _tblActividadFinanciamientoDetDesembolso.setIdTipoTransaccion(_tblActividadTipoTransaccion);
                        _tblActividadFinanciamientoDetDesembolso.setIdMonedaActividad(_tblMonedaActividad);

                        // Realizamos la Persistencia de los Datos
                        _actividadFinanciamientoDetDesembolsoRepository.save(_tblActividadFinanciamientoDetDesembolso);
                        _actividadFinanciamientoDetDesembolsoRepository.flush();

                        // Retorno de la Funcion
                        msgMethod = "El Desembolso para este Proyecto, " + _actividadFinancDetDesembolsoJson.getCodigoFinancDesembolso() + " se ha Actualizado de forma satisfactoria!!";

                        // Retorno del json
                        msgExeptions.map.put("data", _actividadFinanciamientoDetDesembolsoRepository.findByCodigoFinancDesembolso(_actividadFinancDetDesembolsoJson.getCodigoFinancDesembolso()));
                        msgExeptions.map.put("findRecord", true);

                        return msgExeptions.msgJson(msgMethod, 200);
                    } else {
                        msgMethod = "No Existe un registro con el código de transacción Desembolso para este Proyecto !! " + _actividadFinancDetDesembolsoJson.getCodigoFinancDesembolso();

                        msgExeptions.map.put("findRecord", false);
                        return msgExeptions.msgJson(msgMethod, 200);
                    }
                } catch (Exception ex) {
                    msgMethod = "Ha Ocurrido un error al Intentar Grabar el Desembolso para el Proyecto, con la informacion de Moneda seleccionada de Ayuda erronea!!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Desembolso del Proyecto, con la informacion de Tipo de Transacción erronea !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Desembolso NAM que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | addActividadFinancDetDesembolso
}
