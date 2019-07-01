/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades.financiamiento.detalle;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadFinanciamientoDet;
import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadModalidadAyuda;
import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadTipoFinanciamiento;
import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.encabezado.TblActividadFinanciamientoEnc;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblOrganizacion;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle.ActividadFinanciamientoDetRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle.ActividadModalidadAyudaRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle.ActividadTipoFinanciamientoRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.encabezado.ActividadFinanciamientoEncRepository;
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
@Api(value = "ActividadFinancDetAPI", description = "Operaciones sobre el Modulo de Proyectos Financiamiento", tags = "Financiamiento de Proyecto")
public class ActividadFinanciamientoDetResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadFinanciamientoDetRepository _actividadFinanciamientoDetRepository;

    @Autowired
    ActividadFinanciamientoEncRepository _actividadFinanciamientoEncRepository;

    @Autowired
    ActividadRepository _actividadRepository;

    @Autowired
    ActividadTipoFinanciamientoRepository actividadTipoFinanciamientoRepository;

    @Autowired
    ActividadModalidadAyudaRepository _actividadModalidadAyudaRepository;

    @Autowired
    OrganizacionRepository _organizacionRepository;

    /**
     * Metodo que despliega la Lista de todos los Detalles de Financiamiento de una Actividad de la BD
     *
     * @return Lista de Detalles de Financiamiento de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 24/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de todos los Detalles de Financiamiento los Proyectos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_FINANC_DET, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllActvidadesFinancDet() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas los Detalles de Financiamiento registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadFinanciamientoDetRepository.findAll());
            msgExeptions.map.put("countRecords", _actividadFinanciamientoDetRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAllActvidadesFinancDet


    /**
     * Metodo que despliega los Detalles de Financiamiento a la Actividad de la BD
     *
     * @param idActividadFinancEnc Identificador del Detalle de Financiamiento a Buscar
     * @return Detalles de Financiamiento de una Atividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 24/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna los Detalles de Financiamiento de la Actividad enviada a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_FINANC_DET, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getActvidadFinancDetByIdFinancEnc(@ApiParam(value = "Identificador del Detalle de la Actividad a Buscar", required = true)
                                                                     @PathVariable("idActividadFinancEnc") long idActividadFinancEnc) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad para verificar si existe
            TblActividadFinanciamientoEnc _tblActividadFinanciamientoEnc = _actividadFinanciamientoEncRepository.findByIdActividadFinancEnc(idActividadFinancEnc);

            if (_actividadFinanciamientoDetRepository.countByIdActividadFinancEnc(_tblActividadFinanciamientoEnc) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de los Detalles de Financiamiento del Proyecto consultado";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("findRecord", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de los Detalles de Financiamiento del Proyecto";
                msgExeptions.map.put("findRecord", true);

                msgExeptions.map.put("data", _actividadFinanciamientoDetRepository.getByIdFinancEnc(_tblActividadFinanciamientoEnc));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado el Proyecto solicitado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getActvidadFinancDetByIdFinancEnc


    /**
     * Metodo que despliega los Detalles de Financiamiento a la Actividad de la BD
     *
     * @param idActividadFinancEnc Identificador del Detalle de Financiamiento a Buscar
     * @param idSocioDesarrollo    Identificador del Socio al Desarrollo a Buscar
     * @return Detalles de Financiamiento de una Atividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 15/06/2019/v1.0
     */
    @ApiOperation(value = "Retorna los Detalles de Financiamiento de la Actividad con el Socio al Desarrollo enviada a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_ENC_AND_ID_SOCIO_DESARROLLO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getActvidadFinancDetByIdActividadFinancEncAndIdSocioDesarrollo(@ApiParam(value = "Identificador del Detalle de la Actividad con Socio al Desarrollo a Buscar", required = true)
                                                                                                  @PathVariable("idActividadFinancEnc") long idActividadFinancEnc,
                                                                                                  @PathVariable("idSocioDesarrollo") long idSocioDesarrollo) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad para verificar si existe
            TblActividadFinanciamientoEnc _tblActividadFinanciamientoEnc = _actividadFinanciamientoEncRepository.findByIdActividadFinancEnc(idActividadFinancEnc);

            try {
                // Busca el Socio al Desarrollo
                TblOrganizacion _tblOrganizacion = _organizacionRepository.findByIdOrganizacion(idSocioDesarrollo);

                if (_actividadFinanciamientoDetRepository.countByIdActividadFinancEncAndIdSocioDesarrollo(_tblActividadFinanciamientoEnc, _tblOrganizacion) == 0) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado dato de los Detalles de Financiamiento del Proyecto consultado";

                    msgExeptions.map.put("error", "No data found");
                    msgExeptions.map.put("findRecord", false);

                    // Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de los Detalles de Financiamiento del Proyecto";
                    msgExeptions.map.put("findRecord", true);

                    msgExeptions.map.put("data", _actividadFinanciamientoDetRepository.getByIdActividadFinancEncAndIdSocioDesarrollo(_tblActividadFinanciamientoEnc, _tblOrganizacion));

                    // Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado el Socio al Desarrollo solicitado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado el Proyecto solicitado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getActvidadFinancDetByIdFinancEnc


    /**
     * Metodo que despliega el Financiamiento Detalle asociado a Proyecto de la BD
     *
     * @param codigoFinancDet Codigo de Financiamiento Detalle asociado a Proyecto a Buscar
     * @return Financiamiento Detalle de la BD
     * @autor Nahum Martinez | NAM
     * @version 24/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Financiamiento Detalle a buscar el codigo a la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_COD_FINANC_DET, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getActvidadFinancDetByCodigoFinancDet(@ApiParam(value = "Código de transacción de financiamiento Detalle asociado a un Proyecto a Buscar", required = true)
                                                                         @PathVariable("codigoFinancDet") String codigoFinancDet) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadFinanciamientoDetRepository.countByCodigoFinancDet(codigoFinancDet) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Financiamiento Detalle asociados a Proyecto consultado";

                msgExeptions.map.put("data", _actividadFinanciamientoDetRepository.findByCodigoFinancDet(codigoFinancDet));
                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("find", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Se ha encontrado dato de Financiamiento Detalle asociados a Proyecto consultado";
                msgExeptions.map.put("data", _actividadFinanciamientoDetRepository.findByCodigoFinancDet(codigoFinancDet));
                msgExeptions.map.put("find", true);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "Se ha encontraddo datos de Financiamiento Detalle asociado a un Proyecto Consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getActvidadFinancDetByCodigoFinancDet


    /**
     * Metodo que Solicita un json con los datos de la Entidad Financiamiento Detalle con Relacion
     * a Actividades
     *
     * @param _actividadFinancDetJson Obtiene desde el request los datos del Financiamiento Detalle a ingresar
     * @return Mensaje de Confirmacion de Registro de Financiamiento Detalle
     * @autor Nahum Martinez | NAM
     * @version 24/05/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del Financiamiento Detalle de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_NEW_FINANC_DET, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addActividadActvidadFinancDet(@ApiParam(value = "Json de Financiamiento Detalle del Proyecto a Ingresar", required = true)
                                                                 @RequestBody @Valid final TblActividadFinanciamientoDet _actividadFinancDetJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividadFinancEnc": {"idActividadFinancEnc": valor })
            TblActividadFinanciamientoEnc _tblActividadFinanciamientoEnc = _actividadFinanciamientoEncRepository.findByIdActividadFinancEnc(_actividadFinancDetJson.getIdActividadFinancEnc().getIdActividadFinancEnc());

            try {
                // Busca el Tipo de Financiamiento, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoFinanciamiento": {"idTipoFinanciamiento": valor })
                TblActividadTipoFinanciamiento _tblActividadTipoFinanciamiento = actividadTipoFinanciamientoRepository.findByIdTipoFinanciamiento(_actividadFinancDetJson.getIdTipoFinanciamiento().getIdTipoFinanciamiento());

                try {
                    // Busca la Modalidad de Ayuda, desde el Reporsitorio con el Parametro del Json enviado ( "idModalidadAyuda": {"idModalidadAyuda": valor })
                    TblActividadModalidadAyuda _tblActividadModalidadAyuda = _actividadModalidadAyudaRepository.findByIdModalidadAyuda(_actividadFinancDetJson.getIdModalidadAyuda().getIdModalidadAyuda());


                    try {
                        // Busca la Organizacion Socio al Desarrollo, desde el Reporsitorio con el Parametro del Json enviado ( "idSocioDesarrollo": {"idOrganizacion": valor })
                        TblOrganizacion _tblOrganizacionSocioDesarrollo = _organizacionRepository.findByIdOrganizacion(_actividadFinancDetJson.getIdSocioDesarrollo().getIdOrganizacion());


                        if (_actividadFinanciamientoDetRepository.countByCodigoFinancDet(_actividadFinancDetJson.getCodigoFinancDet()) > 0) {
                            msgMethod = "Ya Existe un registro con el código de transacción Financiamiento Detalle para este Proyecto !! " + _actividadFinancDetJson.getCodigoFinancDet();

                            msgExeptions.map.put("findRecord", true);
                            return msgExeptions.msgJson(msgMethod, 200);
                        } else {
                            // Seteo de las Fecha y Hora de Creacion
                            _actividadFinancDetJson.setFechaCreacion(dateActual);
                            _actividadFinancDetJson.setHoraCreacion(dateActual);

                            _actividadFinancDetJson.setCodigoFinancDet(_actividadFinancDetJson.getCodigoFinancDet());
                            _actividadFinancDetJson.setIdOrganizacionFinanciera(_actividadFinancDetJson.getIdOrganizacionFinanciera());

                            // Seteamos la Actividad de Actividad y Financiamiento Detalle
                            _actividadFinancDetJson.setIdActividadFinancEnc(_tblActividadFinanciamientoEnc);
                            _actividadFinancDetJson.setIdTipoFinanciamiento(_tblActividadTipoFinanciamiento);
                            _actividadFinancDetJson.setIdModalidadAyuda(_tblActividadModalidadAyuda);
                            _actividadFinancDetJson.setIdSocioDesarrollo(_tblOrganizacionSocioDesarrollo);

                            // Realizamos la Persistencia de los Datos
                            _actividadFinanciamientoDetRepository.save(_actividadFinancDetJson);
                            _actividadFinanciamientoDetRepository.flush();

                            // Retorno de la Funcion
                            msgMethod = "El Financiamiento Detalle para este Proyecto, " + _actividadFinancDetJson.getCodigoFinancDet() + " se ha Ingresado de forma satisfactoria!!";

                            // Retorno del json
                            msgExeptions.map.put("data", _actividadFinanciamientoDetRepository.findByCodigoFinancDet(_actividadFinancDetJson.getCodigoFinancDet()));
                            msgExeptions.map.put("findRecord", false);

                            return msgExeptions.msgJson(msgMethod, 200);
                        }
                    } catch (Exception ex) {
                        msgMethod = "Ha Ocurrido un error al Intentar Grabar el Financiamiento Detalle del Proyecto, con la informacion de Socio al Desarrollo erronea!!";
                        throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
                    }
                } catch (Exception ex) {
                    msgMethod = "Ha Ocurrido un error al Intentar Grabar el Financiamiento Detalle del Proyecto, con la informacion de Modalidad de Ayuda erronea!!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Financiamiento Detalle del Proyecto, con la informacion de Tipo de Financiamiento erronea !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Financiamiento Encabezado que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | addActividadActvidadFinancDet


    /**
     * Metodo que Solicita un json con los datos de la Entidad Financiamiento Detalle con Relacion
     * a Actividades
     *
     * @param codigoFinancDet Identificador de la Financiamiento Detalle con Proyecto a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion de Financiamiento Detalle
     * @autor Nahum Martinez | NAM
     * @version 24/05/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el codigo de Financiamiento Detalle", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_DELETE_FINANC_DET, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadActvidadFinancDet(@ApiParam(value = "Codigo de Financiamiento Detalle del Proyecto a Eliminar", required = true)
                                                                     @PathVariable("codigoFinancDet") String codigoFinancDet) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Codigo enviado ( codigoFinancDet )
            // TblActividadFinanciamientoEnc _tblActividadFinanciamientoEnc = _actividadFinanciamientoDetRepository.findByCodigoFinancDet(codigoFinancDet);

            try {
                if (_actividadFinanciamientoDetRepository.countByCodigoFinancDet(codigoFinancDet) > 0) {
                    // Realizamos la Persistencia de los Datos
                    msgExeptions.map.put("findRecord", true);

                    _actividadFinanciamientoDetRepository.deleletedCodigoFinancDet(codigoFinancDet);
                    _actividadFinanciamientoDetRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Financiamiento Detalle para este Proyecto, se ha Eliminado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgExeptions.map.put("findRecord", true);
                    msgMethod = "No Existe un registro de Detalle de Financiamiento para este Proyecto !!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Eliminar el Financiamiento Detalle del Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Financiamiento Detalle para este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deletedActividadActvidadFinancDet


    /**
     * Metodo que Solicita un json con los datos de la Entidad Financiamiento Detalle con Relacion
     * a Actividades
     *
     * @param _actividadFinancDetJson Obtiene desde el request los datos del Financiamiento Detalle a ingresar
     * @param idActividadFinancDet    Obtiene identificador de Financiamiento de Encabezado
     * @return Mensaje de Confirmacion de Actualizacion de Financiamiento Detalle
     * @autor Nahum Martinez | NAM
     * @version 14/06/2019/v1.0
     */
    @ApiOperation(value = "Actualiza a la BD, la Información enviada por el Bean del Financiamiento Detalle de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PutMapping(value = FINANCIAMIENTO_ACT_ENDPOINT_EDIT_FINANC_DET, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> editActividadActvidadFinancDet(@ApiParam(value = "Json de Financiamiento Detalle del Proyecto a Actualizar", required = true)
                                                                  @PathVariable("idActividadFinancDet") long idActividadFinancDet,
                                                                  @RequestBody @Valid final TblActividadFinanciamientoDet _actividadFinancDetJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Actualziacion
        Date dateActual = new Date();

        try {
            // Busca el Detalle de Financiamiento, desde el Reporsitorio con el Parametro del Json enviado ( "idActividadFinancDet": {"idActividadFinancDet": valor })
            TblActividadFinanciamientoDet _tblActividadFinanciamientoDet = _actividadFinanciamientoDetRepository.findByIdActividadFinancDet(idActividadFinancDet);

            try {
                // Busca el Tipo de Financiamiento, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoFinanciamiento": {"idTipoFinanciamiento": valor })
                TblActividadTipoFinanciamiento _tblActividadTipoFinanciamiento = actividadTipoFinanciamientoRepository.findByIdTipoFinanciamiento(_actividadFinancDetJson.getIdTipoFinanciamiento().getIdTipoFinanciamiento());

                try {
                    // Busca la Modalidad de Ayuda, desde el Reporsitorio con el Parametro del Json enviado ( "idModalidadAyuda": {"idModalidadAyuda": valor })
                    TblActividadModalidadAyuda _tblActividadModalidadAyuda = _actividadModalidadAyudaRepository.findByIdModalidadAyuda(_actividadFinancDetJson.getIdModalidadAyuda().getIdModalidadAyuda());

                    if (_actividadFinanciamientoDetRepository.countByCodigoFinancDet(_actividadFinancDetJson.getCodigoFinancDet()) > 0) {
                        // Seteo de las Fecha y Hora de Creacion
                        _tblActividadFinanciamientoDet.setFechaModificacion(dateActual);
                        _tblActividadFinanciamientoDet.setHoraModificacion(dateActual);
                        _tblActividadFinanciamientoDet.setIdOrganizacionFinanciera(_actividadFinancDetJson.getIdOrganizacionFinanciera());

                        // Seteamos la Actividad de Actividad y Financiamiento Detalle
                        _tblActividadFinanciamientoDet.setIdTipoFinanciamiento(_tblActividadTipoFinanciamiento);
                        _tblActividadFinanciamientoDet.setIdModalidadAyuda(_tblActividadModalidadAyuda);

                        // Realizamos la Persistencia de los Datos
                        _actividadFinanciamientoDetRepository.save(_tblActividadFinanciamientoDet);
                        _actividadFinanciamientoDetRepository.flush();

                        // Retorno de la Funcion
                        msgMethod = "El Financiamiento Detalle para este Proyecto, " + _actividadFinancDetJson.getCodigoFinancDet() + " se ha Actualizado de forma satisfactoria!!";

                        // Retorno del json
                        msgExeptions.map.put("data", _actividadFinanciamientoDetRepository.findByCodigoFinancDet(_actividadFinancDetJson.getCodigoFinancDet()));
                        msgExeptions.map.put("findRecord", true);

                        return msgExeptions.msgJson(msgMethod, 200);
                    } else {
                        msgMethod = "Ya Existe un registro con el código de transacción Financiamiento Detalle para este Proyecto !! " + _actividadFinancDetJson.getCodigoFinancDet();

                        msgExeptions.map.put("findRecord", false);
                        return msgExeptions.msgJson(msgMethod, 200);
                    }
                } catch (Exception ex) {
                    msgMethod = "Ha Ocurrido un error al Intentar Grabar el Financiamiento Detalle del Proyecto, con la informacion de Modalidad de Ayuda erronea!!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Financiamiento Detalle del Proyecto, con la informacion de Tipo de Financiamiento erronea !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Financiamiento Detalle que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | editActividadActvidadFinancDet
}
