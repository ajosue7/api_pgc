/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades.sectores;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.sectores.TblActividadSectorOds;
import com.api.pgc.core.APIRestPGC.models.sectores.TblSectorOds;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.sectores.ActividadSectorOdsRepository;
import com.api.pgc.core.APIRestPGC.repository.sectores.SectorOdsRepository;
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
@Api(value = "ActividadSectorOdsAPI", description = "Operaciones sobre el Modulo de Proyectos Sectores", tags = "Sectores de Proyecto")
public class ActividadSectorOdsResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadSectorOdsRepository _actividadSectorOdsRepository;

    @Autowired
    ActividadRepository _actividadRepository;

    @Autowired
    SectorOdsRepository _sectorOdsRepository;

    /**
     * Metodo que despliega la Lista de todas los Sectores Ods de una Actividad de la BD
     *
     * @return Lista de Sectore Ods de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 19/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todas las Id Internas de una Actividad de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_ODS_ACT_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllActvidadesSectoresOds() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas los Sectores Ods registrados en la BD";

        try {
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadSectorOdsRepository.findAll());
            msgExeptions.map.put("countRecords", _actividadSectorOdsRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAllActvidadesSectoresOds


    /**
     * Metodo que despliega los Sectores Ods asociados a la Actividad de la BD
     *
     * @param idActividad Identificador de la Actividad a Buscar
     * @return Sectores Ods de una Atividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 19/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna los Sectores Ods de la Actividad enviada a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_ODS_ENDPOINT_FIND_BY_ID_ACTIVIDAD, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSectorOdsByIdActividad(@ApiParam(value = "Identificador de la Actividad a Buscar", required = true)
                                                             @PathVariable("idActividad") long idActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad para verificar si existe
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(idActividad);

            if (_actividadSectorOdsRepository.countByIdActividad(_tblActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de los Sectores Ods del Proyecto consultado";

                msgExeptions.map.put("error", "No data found");

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de los Sectores Ods del Proyecto";
                msgExeptions.map.put("data", _actividadSectorOdsRepository.getCodigoActividadSector(_tblActividad));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado el Proyecto solicitado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getSectorOdsByCod

    /**
     * Metodo que despliega el Sector Ods asociado a Proyecto de la BD
     *
     * @param codigoActividad Codigo de Sector Ods asociado a Proyecto a Buscar
     * @return Sector Ods de la BD
     * @autor Nahum Martinez | NAM
     * @version 19/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Sector Ods a buscar el codigo a la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_ODS_ENDPOINT_FIND_BY_COD_SECTOR, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSectorOdsByCodigoActividad(@ApiParam(value = "Código de Sector Ods asociado a un Proyecto a Buscar", required = true)
                                                                 @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadSectorOdsRepository.countByCodigoActividad(codigoActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Sectores Ods asociados a Proyecto consultado";

                msgExeptions.map.put("data", _actividadSectorOdsRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("find", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Se ha encontrado dato de Sectores Ods asociados a Proyecto consultado";
                msgExeptions.map.put("data", _actividadSectorOdsRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("find", true);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "Se ha encontraddo datos de Sector asociado a un Proyecto Consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getSectorOdsByCodigoActividad


    /**
     * Metodo que Solicita un json con los datos de la Entidad Sectores Ods con Relacion
     * a Actividades
     *
     * @param _actividadSectorOdsJson Obtiene desde el request los datos del Sector Ods a ingresar
     * @return Mensaje de Confirmacion de Registro de Sector Ods
     * @autor Nahum Martinez | NAM
     * @version 19/04/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del Sector Ods de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = SECTORES_ODS_ENDPOINT_NEW, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addActividadSectorOds(@ApiParam(value = "Json de Sector Ods del Proyecto a Ingresar", required = true)
                                                         @RequestBody @Valid final TblActividadSectorOds _actividadSectorOdsJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividad": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(_actividadSectorOdsJson.getIdActividad().getIdActividad());

            try {
                // Busca la Sector Ods, desde el Reporsitorio con el Parametro del Json enviado ( "idSector": {"idSector": valor })
                TblSectorOds _tblSectorOds = _sectorOdsRepository.findByIdSector(_actividadSectorOdsJson.getIdSectorOds().getIdSector());

                // Busca el Proyecto con el Proposito de validar que no se meta otro Item mas,
                // desde el Reporsitorio de Sectores Ods con el Parametro del Json enviado ( "idActividad": _tblActividad )

                if (_actividadSectorOdsRepository.countByCodigoActividad(_actividadSectorOdsJson.getCodigoActividad()) > 0) {
                    msgMethod = "Ya Existe un registro con el Código de Sector Ods para este Proyecto !! " + _actividadSectorOdsJson.getCodigoActividad();

                    msgExeptions.map.put("findRecord", false);
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    // Seteo de las Fecha y Hora de Creacion
                    _actividadSectorOdsJson.setFechaCreacion(dateActual);
                    _actividadSectorOdsJson.setHoraCreacion(dateActual);

                    // Seteamos la Actividad de la Id Interna y Sector Ods
                    _actividadSectorOdsJson.setIdActividad(_tblActividad);
                    _actividadSectorOdsJson.setIdSectorOds(_tblSectorOds);

                    // Realizamos la Persistencia de los Datos
                    _actividadSectorOdsRepository.save(_actividadSectorOdsJson);
                    _actividadSectorOdsRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Sector Ods para este Proyecto, se ha Ingresado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar la Id Interna del Proyecto, con la Organización Indicada !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Proyecto que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN


    /**
     * Metodo que Solicita un json con los datos de la Entidad Sectores Ods con Relacion
     * a Actividades
     *
     * @param codigoActividad Identificador de la Sector Ods con Proyecto a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion de Sector Ods
     * @autor Nahum Martinez | NAM
     * @version 19/04/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el codigo de Sector Ods", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = SECTORES_ODS_ENDPOINT_DELETE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadSectorOds(@ApiParam(value = "Codigo de Sector Ods del Proyecto a Eliminar", required = true)
                                                             @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Codigo enviado ( codigoActividad )
            TblActividadSectorOds _tblActividadSectorOds = _actividadSectorOdsRepository.findByCodigoActividad(codigoActividad);

            try {
                if (_actividadSectorOdsRepository.countByCodigoActividad(codigoActividad) > 0) {
                    // Realizamos la Persistencia de los Datos

                    _actividadSectorOdsRepository.deleletedCodigoActividad(codigoActividad);
                    _actividadSectorOdsRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Sector Ods/Cad para este Proyecto, se ha Eliminado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgMethod = "No Existe un registro de Sector Ods/Cad para este Proyecto !!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Eliminar el sector Ods/Cad del Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Sector Ods/Cad para este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deletedActividadSectorOds

}
