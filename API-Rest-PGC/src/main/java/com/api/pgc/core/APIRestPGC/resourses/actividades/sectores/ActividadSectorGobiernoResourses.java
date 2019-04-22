/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades.sectores;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.sectores.TblActividadSectorGobierno;
import com.api.pgc.core.APIRestPGC.models.actividades.sectores.TblActividadSectorOcde;
import com.api.pgc.core.APIRestPGC.models.sectores.TblSectorGobierno;
import com.api.pgc.core.APIRestPGC.models.sectores.TblSectorOcdeCad;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.sectores.ActividadSectorGobiernoRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.sectores.ActividadSectorOcdeRepository;
import com.api.pgc.core.APIRestPGC.repository.sectores.SectorGobiernoRepository;
import com.api.pgc.core.APIRestPGC.repository.sectores.SectorOcdeCadRepository;
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
@Api(value = "ActividadSectorGobiernoAPI", description = "Operaciones sobre el Modulo de Proyectos Sectores", tags = "Sectores de Proyecto")
public class ActividadSectorGobiernoResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadSectorGobiernoRepository _actividadSectorGobiernoRepository;

    @Autowired
    ActividadRepository _actividadRepository;

    @Autowired
    SectorGobiernoRepository _sectorGobiernoRepository;

    /**
     * Metodo que despliega la Lista de todas los Sectores Gobierno de una Actividad de la BD
     *
     * @return Lista de Sectore Gobierno de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 15/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todas los Secores Gobierno los Proyectos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_GOBIERNO_ACT_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllActvidadesSectoresGobierno() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas los Sectores de Gobierno registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadSectorGobiernoRepository.findAll());
            msgExeptions.map.put("countRecords", _actividadSectorGobiernoRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAllActvidadesSectoresGobierno


    /**
     * Metodo que despliega los Sectores Gobierno asociados a la Actividad de la BD
     *
     * @param idActividad Identificador de la Actividad a Buscar
     * @return Sectores Gobierno de una Atividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 15/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna los Sectores Gobierno de la Actividad enviada a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_GOBIERNO_ENDPOINT_FIND_BY_ID_ACTIVIDAD, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSectorGobByIdActividad(@ApiParam(value = "Identificador de la Actividad a Buscar", required = true)
                                                              @PathVariable("idActividad") long idActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad para verificar si existe
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(idActividad);

            if (_actividadSectorGobiernoRepository.countByIdActividad(_tblActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de los Sectores Gobierno del Proyecto consultado";

                msgExeptions.map.put("error", "No data found");

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de los Sectores Gobierno del Proyecto";
                msgExeptions.map.put("data", _actividadSectorGobiernoRepository.getCodigoActividadSector(_tblActividad));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado el Proyecto solicitado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getSectorGobByIdActividad

    /**
     * Metodo que despliega el Sector Gobierno asociado a Proyecto de la BD
     *
     * @param codigoActividad Codigo de Sector Gobierno asociado a Proyecto a Buscar
     * @return Sector Gobierno de la BD
     * @autor Nahum Martinez | NAM
     * @version 15/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Sector Gobierno a buscar el codigo a la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_GOBIERNO_ENDPOINT_FIND_BY_COD_SECTOR, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSectorGobByCodigoActividad(@ApiParam(value = "Código de Sector Gobierno asociado a un Proyecto a Buscar", required = true)
                                                                  @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (-_actividadSectorGobiernoRepository.countByCodigoActividad(codigoActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Sectores Gobierno asociados a Proyecto consultado";

                msgExeptions.map.put("data", _actividadSectorGobiernoRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("find", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Se ha encontrado dato de Sectores de Gobierno asociados a Proyecto consultado";
                msgExeptions.map.put("data", _actividadSectorGobiernoRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("find", true);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "Se ha encontraddo datos de Sector asociado a un Proyecto Consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getSectorGobByCodigoActividad


    /**
     * Metodo que Solicita un json con los datos de la Entidad Sectores Gobierno con Relacion
     * a Actividades
     *
     * @param _actividadSectorGobJson Obtiene desde el request los datos del Sector Gobierno a ingresar
     * @return Mensaje de Confirmacion de Registro de Sector Gobierno
     * @autor Nahum Martinez | NAM
     * @version 14/04/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del Sector Gobierno de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = SECTORES_GOBIERNO_ENDPOINT_NEW, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addActividadSectorGobierno(@ApiParam(value = "Json de Sector Gobierno del Proyecto a Ingresar", required = true)
                                                          @RequestBody @Valid final TblActividadSectorGobierno _actividadSectorGobJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividad": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(_actividadSectorGobJson.getIdActividad().getIdActividad());
            System.out.println(" ******************************** " + _actividadSectorGobJson.getIdActividad().getIdActividad());
            try {
                // Busca la Sector Gobierno, desde el Reporsitorio con el Parametro del Json enviado ( "idSector": {"idSector": valor })
                TblSectorGobierno _tblSectorGobierno = _sectorGobiernoRepository.findByIdSector(_actividadSectorGobJson.getIdSectorGobierno().getIdSector());

                // Busca el Proyecto con el Proposito de validar que no se meta otro Item mas,
                // desde el Reporsitorio de Sectores Gobierno con el Parametro del Json enviado ( "idActividad": _tblActividad )

                if (_actividadSectorGobiernoRepository.countByCodigoActividad(_actividadSectorGobJson.getCodigoActividad()) > 0) {
                    msgMethod = "Ya Existe un registro con el Código de Sector de Gobierno para este Proyecto !! " + _actividadSectorGobJson.getCodigoActividad();

                    msgExeptions.map.put("findRecord", false);
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    // Seteo de las Fecha y Hora de Creacion
                    _actividadSectorGobJson.setFechaCreacion(dateActual);
                    _actividadSectorGobJson.setHoraCreacion(dateActual);

                    // Seteamos la Actividad de Actividad y Sector Gobierno
                    _actividadSectorGobJson.setIdActividad(_tblActividad);
                    _actividadSectorGobJson.setIdSectorGobierno(_tblSectorGobierno);

                    // Realizamos la Persistencia de los Datos
                    _actividadSectorGobiernoRepository.save(_actividadSectorGobJson);
                    _actividadSectorGobiernoRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Sector de Gobierno para este Proyecto, se ha Ingresado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Sector de Gobierno del Proyecto, con la informacion indicada !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Proyecto que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | addActividadSectorGobierno


    /**
     * Metodo que Solicita un json con los datos de la Entidad Sectores de Gobierno con Relacion
     * a Actividades
     *
     * @param codigoActividad Identificador de la Sector Ocde con Proyecto a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion de Sector Gobierno
     * @autor Nahum Martinez | NAM
     * @version 15/04/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el codigo de Sector de Gobierno", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = SECTORES_GOBIERNO_ENDPOINT_DELETE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadSectorGobierno(@ApiParam(value = "Codigo de Sector de Gobierno del Proyecto a Eliminar", required = true)
                                                             @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Codigo enviado ( codigoActividad )
            TblActividadSectorGobierno _tblActividadSectorGobierno = _actividadSectorGobiernoRepository.findByCodigoActividad(codigoActividad);

            try {
                if (_actividadSectorGobiernoRepository.countByCodigoActividad(codigoActividad) > 0) {
                    // Realizamos la Persistencia de los Datos

                    _actividadSectorGobiernoRepository.deleletedCodigoActividad(codigoActividad);
                    _actividadSectorGobiernoRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Sector de Gobierno para este Proyecto, se ha Eliminado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgMethod = "No Existe un registro de Sector de Gobierno para este Proyecto !!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Eliminar el sector Ode Gobierno sdel Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Sector de Gobierno para este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deletedActividadSectorGobierno

}
