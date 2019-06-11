/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades.financiamiento.detalle;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadFinanciamientoDet;
import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadFinanciamientoDetCompromiso;
import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadTipoTransaccion;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.actividades.TblMonedaActividad;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle.ActividadFinanciamientoDetCompromisoRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle.ActividadFinanciamientoDetRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle.ActividadTipoFinanciamientoRepository;
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
@Api(value = "ActividadFinancDetComprimisoCompromisoAPI", description = "Operaciones sobre el Modulo de Proyectos Financiamiento", tags = "Financiamiento de Proyecto")
public class ActividadFinanciamientoDetComprimisoResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadFinanciamientoDetCompromisoRepository _actividadFinanciamientoDetCompromisoRepository;

    @Autowired
    ActividadFinanciamientoDetRepository _actividadFinanciamientoDetRepository;

    @Autowired
    ActividadRepository _actividadRepository;

    @Autowired
    ActividadTipoFinanciamientoRepository actividadTipoFinanciamientoRepository;

    @Autowired
    ActividadTipoTransaccionRepository _actividadTipoTransaccionRepository;

    @Autowired
    MonedaActividadRepository _monedaActividadRepository;

    /**
     * Metodo que despliega la Lista de todos los Detalles de Financiamiento Compromiso de una Actividad de la BD
     *
     * @return Lista de Detalles de Financiamiento Compromiso de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 06/06/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de todos los Detalles de Financiamiento Compromiso los Proyectos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_FINANC_DET_COMPROMISO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllActvidadesFinancDetComprimiso() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas los Detalles de Financiamiento Compromiso registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadFinanciamientoDetCompromisoRepository.findAll());
            msgExeptions.map.put("countRecords", _actividadFinanciamientoDetCompromisoRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAllActvidadesFinancDetComprimiso


    /**
     * Metodo que despliega los Detalles de Financiamiento Compromiso a la Actividad de la BD
     *
     * @param idActividadDet Identificador del Detalle de Financiamiento a Buscar
     * @return Detalles de Financiamiento Compromiso de una Atividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 06/06/2019/v1.0
     */
    @ApiOperation(value = "Retorna los Detalles de Financiamiento Compromiso de la Actividad enviada a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_FINANC_DET_2, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getIdActividadFinancDetCompromiso(@ApiParam(value = "Identificador del Detalle de la Actividad a Buscar", required = true)
                                                                     @PathVariable("idActividadDet") long idActividadDet) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad para verificar si existe
            TblActividadFinanciamientoDet _tblActividadFinanciamientoDet = _actividadFinanciamientoDetRepository.findByIdActividadFinancDet(idActividadDet);

            if (_actividadFinanciamientoDetCompromisoRepository.countByIdActividadFinancDet(_tblActividadFinanciamientoDet) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de los Detalles de Compromiso del Proyecto consultado";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("findRecord", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de los Compromisos del Proyecto";
                msgExeptions.map.put("findRecord", true);

                msgExeptions.map.put("data", _actividadFinanciamientoDetCompromisoRepository.getByIdFinancDet(_tblActividadFinanciamientoDet));
                msgExeptions.map.put("countRecords", _actividadFinanciamientoDetCompromisoRepository.countByIdActividadFinancDet(_tblActividadFinanciamientoDet));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado el detalle del financiamiento del Proyecto solicitado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getActvidadFinancDetComprimisoByIdFinancEnc


    /**
     * Metodo que despliega el Financiamiento Detalle asociado a Proyecto de la BD
     *
     * @param codigoFinancDetCompromiso Codigo de Financiamiento Detalle asociado a Proyecto a Buscar
     * @return Financiamiento Detalle de la BD
     * @autor Nahum Martinez | NAM
     * @version 06/06/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Financiamiento Detalle a buscar el codigo a la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_COD_FINANC_DET_COMPROMISO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getActvidadFinancDetCompromisoByCodigoFinancDetCompromiso(@ApiParam(value = "Código de transacción de financiamiento Detalle asociado a un Proyecto a Buscar", required = true)
                                                                                             @PathVariable("codigoFinancDetCompromiso") String codigoFinancDetCompromiso) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadFinanciamientoDetCompromisoRepository.countByCodigoFinancCompromiso(codigoFinancDetCompromiso) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de compromisos asociados a Proyecto consultado";

                msgExeptions.map.put("data", _actividadFinanciamientoDetCompromisoRepository.countByCodigoFinancCompromiso(codigoFinancDetCompromiso));
                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("find", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Se ha encontrado dato de compromisos asociados a Proyecto consultado";
                msgExeptions.map.put("data", _actividadFinanciamientoDetCompromisoRepository.countByCodigoFinancCompromiso(codigoFinancDetCompromiso));
                msgExeptions.map.put("find", true);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "Se ha encontraddo datos de Financiamiento Detalle asociado a un Proyecto Consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getActvidadFinancDetCompromisoByCodigoFinancDetCompromiso


    /**
     * Metodo que Solicita un json con los datos de la Entidad Financiamiento Detalle Compromiso con Relacion
     * a Actividades
     *
     * @param _actividadFinancDetCompromisoJson Obtiene desde el request los datos del Financiamiento Detalle a ingresar
     * @return Mensaje de Confirmacion de Registro de Financiamiento Detalle
     * @autor Nahum Martinez | NAM
     * @version 06/06/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del Financiamiento Detalle de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_NEW_FINANC_DET_COMPROMISO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addActividadFinancDetCompromiso(@ApiParam(value = "Json de Financiamiento Detalle Compromiso del Proyecto a Ingresar", required = true)
                                                                   @RequestBody @Valid final TblActividadFinanciamientoDetCompromiso _actividadFinancDetCompromisoJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca la Detalla de Financiamiento de la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividadFinancDet": {"idActividadFinancDet": valor })
            TblActividadFinanciamientoDet _tblActividadFinanciamientoDet = _actividadFinanciamientoDetRepository.findByIdActividadFinancDet((_actividadFinancDetCompromisoJson.getIdActividadFinancDet().getIdActividadFinancDet()));

            try {
                // Busca el Tipo de Transacción, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoTransaccion": {"idTipoTransaccion": valor })
                TblActividadTipoTransaccion _tblActividadTipoTransaccion = _actividadTipoTransaccionRepository.findByIdTipoTransaccion(_actividadFinancDetCompromisoJson.getIdTipoTransaccion().getIdTipoTransaccion());

                try {
                    // Busca la Moneda de Transaccion, desde el Reporsitorio con el Parametro del Json enviado ( "idMonedaActividad": {"idMonedaActividad": valor })
                    TblMonedaActividad _tblMonedaActividad = _monedaActividadRepository.findByIdMonedaActividad(_actividadFinancDetCompromisoJson.getIdMonedaActividad().getIdMonedaActividad());

                    if (_actividadFinanciamientoDetCompromisoRepository.countByCodigoFinancCompromiso(_actividadFinancDetCompromisoJson.getCodigoFinancCompromiso()) > 0) {
                        msgMethod = "Ya Existe un registro con el código de transacción Compromiso para este Proyecto !! " + _actividadFinancDetCompromisoJson.getCodigoFinancCompromiso();

                        msgExeptions.map.put("findRecord", true);
                        return msgExeptions.msgJson(msgMethod, 200);
                    } else {
                        // Seteo de las Fecha y Hora de Creacion
                        _actividadFinancDetCompromisoJson.setFechaCreacion(dateActual);
                        _actividadFinancDetCompromisoJson.setHoraCreacion(dateActual);

                        _actividadFinancDetCompromisoJson.setCodigoFinancCompromiso(_actividadFinancDetCompromisoJson.getCodigoFinancCompromiso());
                        _actividadFinancDetCompromisoJson.setMontoCompromiso(_actividadFinancDetCompromisoJson.getMontoCompromiso());
                        _actividadFinancDetCompromisoJson.setFechaTransaccion(_actividadFinancDetCompromisoJson.getFechaTransaccion());

                        // Seteamos Detalle de Financ. de Actividad y Monenda y Tipo de Transaccion
                        _actividadFinancDetCompromisoJson.setIdActividadFinancDet(_tblActividadFinanciamientoDet);
                        _actividadFinancDetCompromisoJson.setIdTipoTransaccion(_tblActividadTipoTransaccion);
                        _actividadFinancDetCompromisoJson.setIdMonedaActividad(_tblMonedaActividad);

                        // Realizamos la Persistencia de los Datos
                        _actividadFinanciamientoDetCompromisoRepository.save(_actividadFinancDetCompromisoJson);
                        _actividadFinanciamientoDetCompromisoRepository.flush();

                        // Retorno de la Funcion
                        msgMethod = "El Compromiso para este Proyecto, " + _actividadFinancDetCompromisoJson.getCodigoFinancCompromiso() + " se ha Ingresado de forma satisfactoria!!";

                        // Retorno del json
                        msgExeptions.map.put("data", _actividadFinanciamientoDetCompromisoRepository.findByCodigoFinancCompromiso(_actividadFinancDetCompromisoJson.getCodigoFinancCompromiso()));
                        msgExeptions.map.put("findRecord", false);

                        return msgExeptions.msgJson(msgMethod, 200);
                    }
                } catch (Exception ex) {
                    msgMethod = "Ha Ocurrido un error al Intentar Grabar el Compromiso para el Proyecto, con la informacion de Moneda seleccionada de Ayuda erronea!!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Compromiso del Proyecto, con la informacion de Tipo de Transacción erronea !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Financiamiento Detalle que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | addActividadFinancDetCompromiso


    /**
     * Metodo que Solicita un json con los datos de la Entidad Compromiso con Relacion
     * a Actividades
     *
     * @param codigoFinancCompromiso Identificador del Compromiso con Proyecto a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion de Compromiso
     * @autor Nahum Martinez | NAM
     * @version 06/06/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el codigo de Financiamiento Detalle Compromiso", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_DELETE_FINANC_DET_COMPROMISO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadFinancDetCompromiso(@ApiParam(value = "Codigo de Financiamiento Detalle Compromiso del Proyecto a Eliminar", required = true)
                                                                       @PathVariable("codigoFinancCompromiso") String codigoFinancCompromiso) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Codigo enviado ( codigoFinancDetComprimiso )
            // TblActividadFinanciamientoDet _tblActividadFinanciamientoDet = _actividadFinanciamientoDetRepository.findByCodigoFinancComprimiso(codigoFinancComprimiso);

            try {
                if (_actividadFinanciamientoDetCompromisoRepository.countByCodigoFinancCompromiso(codigoFinancCompromiso) > 0) {
                    // Realizamos la Persistencia de los Datos

                    _actividadFinanciamientoDetCompromisoRepository.deleletedCodigoFinancDetCompromiso(codigoFinancCompromiso);
                    _actividadFinanciamientoDetCompromisoRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Compromiso para este Proyecto, se ha Eliminado de forma satisfactoria!!";
                    msgExeptions.map.put("findRecord", true);

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgMethod = "No Existe un registro de Compromiso para este Proyecto !!";
                    msgExeptions.map.put("findRecord", false);

                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Eliminar el Compromiso del Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Compromiso para este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deletedActividadActvidadFinancDetComprimiso

}
