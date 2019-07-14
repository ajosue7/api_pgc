/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades.financiamiento.detalle;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadFinanciamientoDet;
import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadFinanciamientoDetGasto;
import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadTipoTransaccion;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.actividades.TblMonedaActividad;
import com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle.ActividadFinanciamientoDetGastoRepository;
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
@Api(value = "ActividadFinancDetGastoAPI", description = "Operaciones sobre el Modulo de Proyectos Financiamiento", tags = "Financiamiento de Proyecto")
public class ActividadFinanciamientoDetGastoResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadFinanciamientoDetGastoRepository _actividadFinanciamientoDetGastoRepository;

    @Autowired
    ActividadFinanciamientoDetRepository _actividadFinanciamientoDetRepository;

    @Autowired
    ActividadTipoTransaccionRepository _actividadTipoTransaccionRepository;

    @Autowired
    MonedaActividadRepository _monedaActividadRepository;

    /**
     * Metodo que despliega la Lista de todos los Detalles de Financiamiento Gasto de una Actividad de la BD
     *
     * @return Lista de Detalles de Financiamiento Gasto de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 10/07/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de todos los Detalles de Financiamiento Gasto los Proyectos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_FINANC_DET_GASTO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllActvidadesFinancDetGasto() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas los Detalles de Financiamiento Gasto registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadFinanciamientoDetGastoRepository.findAll());
            msgExeptions.map.put("countRecords", _actividadFinanciamientoDetGastoRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAllActvidadesFinancDetGasto


    /**
     * Metodo que despliega los Detalles de Financiamiento Gasto a la Actividad de la BD
     *
     * @param idActividadDet Identificador del Detalle de Financiamiento a Buscar
     * @return Detalles de Financiamiento Gasto de una Atividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 10/07/2019/v1.0
     */
    @ApiOperation(value = "Retorna los Detalles de Financiamiento Gasto de la Actividad enviada a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_FINANC_DET_GASTO_2, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getIdActividadFinancDetGasto(@ApiParam(value = "Identificador del Detalle de la Actividad a Buscar", required = true)
                                                                @PathVariable("idActividadDet") long idActividadDet) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad para verificar si existe
            TblActividadFinanciamientoDet _tblActividadFinanciamientoDet = _actividadFinanciamientoDetRepository.findByIdActividadFinancDet(idActividadDet);

            if (_actividadFinanciamientoDetGastoRepository.countByIdActividadFinancDet(_tblActividadFinanciamientoDet) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de los Detalles de Gasto del Proyecto consultado";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("findRecord", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de los Gastos del Proyecto";
                msgExeptions.map.put("findRecord", true);

                msgExeptions.map.put("data", _actividadFinanciamientoDetGastoRepository.getByIdFinancDet(_tblActividadFinanciamientoDet));
                msgExeptions.map.put("countRecords", _actividadFinanciamientoDetGastoRepository.countByIdActividadFinancDet(_tblActividadFinanciamientoDet));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado el detalle del financiamiento del Proyecto solicitado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getActvidadFinancDetGastoByIdFinancEnc


    /**
     * Metodo que despliega el Financiamiento Detalle asociado a Proyecto de la BD
     *
     * @param codigoFinancDetGasto Codigo de Financiamiento Detalle asociado a Proyecto a Buscar
     * @return Financiamiento Detalle de la BD
     * @autor Nahum Martinez | NAM
     * @version 10/07/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Financiamiento Detalle a buscar el codigo a la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_COD_FINANC_DET_GASTO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getActvidadFinancDetGastoByCodigoFinancDetGasto(@ApiParam(value = "Código de transacción de financiamiento Detalle asociado a un Proyecto a Buscar", required = true)
                                                                                   @PathVariable("codigoFinancDetGasto") String codigoFinancDetGasto) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadFinanciamientoDetGastoRepository.countByCodigoFinancGasto(codigoFinancDetGasto) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Gastos asociados a Proyecto consultado";

                msgExeptions.map.put("data", _actividadFinanciamientoDetGastoRepository.countByCodigoFinancGasto(codigoFinancDetGasto));
                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("findRecord", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Se ha encontrado dato de Gastos asociados a Proyecto consultado";
                msgExeptions.map.put("data", _actividadFinanciamientoDetGastoRepository.countByCodigoFinancGasto(codigoFinancDetGasto));
                msgExeptions.map.put("findRecord", true);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "Se ha encontraddo datos de Financiamiento Detalle asociado a un Proyecto Consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getActvidadFinancDetGastoByCodigoFinancDetGasto


    /**
     * Metodo que Solicita un json con los datos de la Entidad Financiamiento Detalle Gasto con Relacion
     * a Actividades
     *
     * @param _actividadFinancDetGastoJson Obtiene desde el request los datos del Financiamiento Detalle a ingresar
     * @return Mensaje de Confirmacion de Registro de Financiamiento Detalle
     * @autor Nahum Martinez | NAM
     * @version 10/07/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del Financiamiento Detalle de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_NEW_FINANC_DET_GASTO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addActividadFinancDetGasto(@ApiParam(value = "Json de Financiamiento Detalle Gasto del Proyecto a Ingresar", required = true)
                                                              @RequestBody @Valid final TblActividadFinanciamientoDetGasto _actividadFinancDetGastoJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca la Detalla de Financiamiento de la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividadFinancDet": {"idActividadFinancDet": valor })
            TblActividadFinanciamientoDet _tblActividadFinanciamientoDet = _actividadFinanciamientoDetRepository.findByIdActividadFinancDet((_actividadFinancDetGastoJson.getIdActividadFinancDet().getIdActividadFinancDet()));

            try {
                // Busca el Tipo de Transacción, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoTransaccion": {"idTipoTransaccion": valor })
                TblActividadTipoTransaccion _tblActividadTipoTransaccion = _actividadTipoTransaccionRepository.findByIdTipoTransaccion(_actividadFinancDetGastoJson.getIdTipoTransaccion().getIdTipoTransaccion());

                try {
                    // Busca la Moneda de Transaccion, desde el Reporsitorio con el Parametro del Json enviado ( "idMonedaActividad": {"idMonedaActividad": valor })
                    TblMonedaActividad _tblMonedaActividad = _monedaActividadRepository.findByIdMonedaActividad(_actividadFinancDetGastoJson.getIdMonedaActividad().getIdMonedaActividad());

                    if (_actividadFinanciamientoDetGastoRepository.countByCodigoFinancGasto(_actividadFinancDetGastoJson.getCodigoFinancGasto()) > 0) {
                        msgMethod = "Ya Existe un registro con el código de transacción Gasto para este Proyecto !! " + _actividadFinancDetGastoJson.getCodigoFinancGasto();

                        msgExeptions.map.put("findRecord", true);
                        return msgExeptions.msgJson(msgMethod, 200);
                    } else {
                        // Seteo de las Fecha y Hora de Creacion
                        _actividadFinancDetGastoJson.setFechaCreacion(dateActual);
                        _actividadFinancDetGastoJson.setHoraCreacion(dateActual);

                        System.out.println("+++++++++++++++++++++++++++++ " + _actividadFinancDetGastoJson.getCodigoFinancGasto());
                        _actividadFinancDetGastoJson.setCodigoFinancGasto(_actividadFinancDetGastoJson.getCodigoFinancGasto());
                        _actividadFinancDetGastoJson.setMontoGasto(_actividadFinancDetGastoJson.getMontoGasto());
                        _actividadFinancDetGastoJson.setFechaTransaccion(_actividadFinancDetGastoJson.getFechaTransaccion());

                        // Seteamos Detalle de Financ. de Actividad y Monenda y Tipo de Transaccion
                        _actividadFinancDetGastoJson.setIdActividadFinancDet(_tblActividadFinanciamientoDet);
                        _actividadFinancDetGastoJson.setIdTipoTransaccion(_tblActividadTipoTransaccion);
                        _actividadFinancDetGastoJson.setIdMonedaActividad(_tblMonedaActividad);

                        // Realizamos la Persistencia de los Datos
                        _actividadFinanciamientoDetGastoRepository.save(_actividadFinancDetGastoJson);
                        _actividadFinanciamientoDetGastoRepository.flush();

                        // Retorno de la Funcion
                        msgMethod = "El Gasto para este Proyecto, " + _actividadFinancDetGastoJson.getCodigoFinancGasto() + " se ha Ingresado de forma satisfactoria!!";

                        // Retorno del json
                        msgExeptions.map.put("data", _actividadFinanciamientoDetGastoRepository.findByCodigoFinancGasto(_actividadFinancDetGastoJson.getCodigoFinancGasto()));
                        msgExeptions.map.put("findRecord", false);

                        return msgExeptions.msgJson(msgMethod, 200);
                    }
                } catch (Exception ex) {
                    msgMethod = "Ha Ocurrido un error al Intentar Grabar el Gasto para el Proyecto, con la informacion de Moneda seleccionada de Ayuda erronea!!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Gasto del Proyecto, con la informacion de Tipo de Transacción erronea !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Financiamiento Detalle que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | addActividadFinancDetGasto


    /**
     * Metodo que Solicita un json con los datos de la Entidad Gasto con Relacion
     * a Actividades
     *
     * @param codigoFinancGasto Identificador del Gasto con Proyecto a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion de Gasto
     * @autor Nahum Martinez | NAM
     * @version 10/07/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el codigo de Financiamiento Detalle Gasto", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_DELETE_FINANC_DET_GASTO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadFinancDetGasto(@ApiParam(value = "Codigo de Financiamiento Detalle Gasto del Proyecto a Eliminar", required = true)
                                                                  @PathVariable("codigoFinancGasto") String codigoFinancGasto) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Codigo enviado ( codigoFinancDetGasto )
            // TblActividadFinanciamientoDet _tblActividadFinanciamientoDet = _actividadFinanciamientoDetRepository.findByCodigoFinancGasto(codigoFinancGasto);

            try {
                if (_actividadFinanciamientoDetGastoRepository.countByCodigoFinancGasto(codigoFinancGasto) > 0) {
                    // Realizamos la Persistencia de los Datos

                    _actividadFinanciamientoDetGastoRepository.deleletedCodigoFinancDetGasto(codigoFinancGasto);
                    _actividadFinanciamientoDetGastoRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Gasto para este Proyecto, se ha Eliminado de forma satisfactoria!!";
                    msgExeptions.map.put("findRecord", true);

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgMethod = "No Existe un registro de Gasto para este Proyecto !!";
                    msgExeptions.map.put("findRecord", false);

                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Eliminar el Gasto del Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Gasto para este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deletedActividadActvidadFinancDetGasto


    /**
     * Metodo que Solicita un json con los datos de la Entidad Gasto con Relacion
     * a Actividades
     *
     * @param _actividadFinancDetGastoJson Obtiene desde el request los datos del Gasto a Eliminar
     * @param idActividadFinancDetGasto    Identificador de la tabla
     * @return Mensaje de Confirmacion de Registro de Financiamiento Detalle
     * @autor Nahum Martinez | NAM
     * @version 10/07/2019/v1.0
     */
    @ApiOperation(value = "Edita a la BD, la Información enviada por el Bean del Gasto de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PutMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_EDIT_FINANC_DET_GASTO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> editActividadFinancDetGasto(@ApiParam(value = "Json de Gasto del Proyecto a Editar", required = true)
                                                               @PathVariable("idActividadFinancDetGasto") long idActividadFinancDetGasto,
                                                               @RequestBody @Valid final TblActividadFinanciamientoDetGasto _actividadFinancDetGastoJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca Gasto de la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividadFinancDetGasto": {"idActividadFinancDetGasto": valor })
            TblActividadFinanciamientoDetGasto _tblActividadFinanciamientoDetGasto = _actividadFinanciamientoDetGastoRepository.findByIdActividadFinancDetGasto(idActividadFinancDetGasto);

            try {
                // Busca el Tipo de Transacción, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoTransaccion": {"idTipoTransaccion": valor })
                TblActividadTipoTransaccion _tblActividadTipoTransaccion = _actividadTipoTransaccionRepository.findByIdTipoTransaccion(_actividadFinancDetGastoJson.getIdTipoTransaccion().getIdTipoTransaccion());

                try {
                    // Busca la Moneda de Transaccion, desde el Reporsitorio con el Parametro del Json enviado ( "idMonedaActividad": {"idMonedaActividad": valor })
                    TblMonedaActividad _tblMonedaActividad = _monedaActividadRepository.findByIdMonedaActividad(_actividadFinancDetGastoJson.getIdMonedaActividad().getIdMonedaActividad());

                    if (_actividadFinanciamientoDetGastoRepository.countByCodigoFinancGasto(_actividadFinancDetGastoJson.getCodigoFinancGasto()) > 0) {
                        // Seteo de las Fecha y Hora de Creacion
                        _tblActividadFinanciamientoDetGasto.setFechaModificacion(dateActual);
                        _tblActividadFinanciamientoDetGasto.setHoraModificacion(dateActual);

                        // Campos propios
                        _tblActividadFinanciamientoDetGasto.setMontoGasto(_actividadFinancDetGastoJson.getMontoGasto());
                        _tblActividadFinanciamientoDetGasto.setFechaTransaccion(_actividadFinancDetGastoJson.getFechaTransaccion());

                        // Seteamos Detalle de Financ. de Actividad y Monenda y Tipo de Transaccion
                        _tblActividadFinanciamientoDetGasto.setIdTipoTransaccion(_tblActividadTipoTransaccion);
                        _tblActividadFinanciamientoDetGasto.setIdMonedaActividad(_tblMonedaActividad);

                        // Realizamos la Persistencia de los Datos
                        _actividadFinanciamientoDetGastoRepository.save(_tblActividadFinanciamientoDetGasto);
                        _actividadFinanciamientoDetGastoRepository.flush();

                        // Retorno de la Funcion
                        msgMethod = "El Gasto para este Proyecto, " + _actividadFinancDetGastoJson.getCodigoFinancGasto() + " se ha Actualizado de forma satisfactoria!!";

                        // Retorno del json
                        msgExeptions.map.put("data", _actividadFinanciamientoDetGastoRepository.findByCodigoFinancGasto(_actividadFinancDetGastoJson.getCodigoFinancGasto()));
                        msgExeptions.map.put("findRecord", true);

                        return msgExeptions.msgJson(msgMethod, 200);
                    } else {
                        msgMethod = "No Existe un registro con el código de transacción Gasto para este Proyecto !! " + _actividadFinancDetGastoJson.getCodigoFinancGasto();

                        msgExeptions.map.put("findRecord", false);
                        return msgExeptions.msgJson(msgMethod, 200);
                    }
                } catch (Exception ex) {
                    msgMethod = "Ha Ocurrido un error al Intentar Grabar el Gasto para el Proyecto, con la informacion de Moneda seleccionada de Ayuda erronea!!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Gasto del Proyecto, con la informacion de Tipo de Transacción erronea !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Gasto NAM que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | addActividadFinancDetGasto
}
