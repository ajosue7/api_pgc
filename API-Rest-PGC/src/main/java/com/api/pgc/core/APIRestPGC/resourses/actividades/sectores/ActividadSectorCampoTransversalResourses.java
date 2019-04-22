/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades.sectores;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.sectores.TblActividadSectorCampoTransversal;
import com.api.pgc.core.APIRestPGC.models.actividades.sectores.TblActividadSectorGobierno;
import com.api.pgc.core.APIRestPGC.models.sectores.TblSectorCampoTransversal;
import com.api.pgc.core.APIRestPGC.models.sectores.TblSectorGobierno;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.sectores.ActividadSectorCampoTransversalRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.sectores.ActividadSectorGobiernoRepository;
import com.api.pgc.core.APIRestPGC.repository.sectores.SectorCampoTransversalRepository;
import com.api.pgc.core.APIRestPGC.repository.sectores.SectorGobiernoRepository;
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
@Api(value = "ActividadCampoTransversalAPI", description = "Operaciones sobre el Modulo de Proyectos Sectores", tags = "Sectores de Proyecto")
public class ActividadSectorCampoTransversalResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadSectorCampoTransversalRepository _actividadSectorCampoTransversalRepository;

    @Autowired
    ActividadRepository _actividadRepository;

    @Autowired
    SectorCampoTransversalRepository _sectorCampoTransversalRepository;

    /**
     * Metodo que despliega la Lista de todas los Campos Transversales de una Actividad de la BD
     *
     * @return Lista de Campos Transversales de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 16/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todas los Campos Transversales los Proyectos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_CAMPOS_ACT_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllActvidadesCamposTransversales() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas los Sectores de Campos Transversales registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadSectorCampoTransversalRepository.findAll());
            msgExeptions.map.put("countRecords", _actividadSectorCampoTransversalRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAllActvidadesCamposTransversales


    /**
     * Metodo que despliega los Sectores Campos Transversales a la Actividad de la BD
     *
     * @param idActividad Identificador de la Actividad a Buscar
     * @return Campos Transversales de una Atividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 16/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna los Campos Transversales de la Actividad enviada a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_CAMPOS_ENDPOINT_FIND_BY_ID_ACTIVIDAD, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSectorCTByIdActividad(@ApiParam(value = "Identificador de la Actividad a Buscar", required = true)
                                                             @PathVariable("idActividad") long idActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad para verificar si existe
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(idActividad);

            if (_actividadSectorCampoTransversalRepository.countByIdActividad(_tblActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de los Campos Transversales del Proyecto consultado";

                msgExeptions.map.put("error", "No data found");

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de los Campos Transversales del Proyecto";
                msgExeptions.map.put("data", _actividadSectorCampoTransversalRepository.getCodigoActividadSector(_tblActividad));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado el Proyecto solicitado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getSectorCTByIdActividad

    /**
     * Metodo que despliega el Sector Campo Transversal asociado a Proyecto de la BD
     *
     * @param codigoActividad Codigo de Sector Campo Transversal asociado a Proyecto a Buscar
     * @return Campo Transversal de la BD
     * @autor Nahum Martinez | NAM
     * @version 16/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Sector Campo Transversal a buscar el codigo a la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_CAMPOS_ENDPOINT_FIND_BY_COD_SECTOR, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSectorCTByCodigoActividad(@ApiParam(value = "Código de Sector Campo Transversal asociado a un Proyecto a Buscar", required = true)
                                                                 @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadSectorCampoTransversalRepository.countByCodigoActividad(codigoActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Campo Transversal asociados a Proyecto consultado";

                msgExeptions.map.put("data", _actividadSectorCampoTransversalRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("find", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Se ha encontrado dato de Sectores de Campo Transversal asociados a Proyecto consultado";
                msgExeptions.map.put("data", _actividadSectorCampoTransversalRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("find", true);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "Se ha encontraddo datos de Sector Campo Transversal asociado a un Proyecto Consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getSectorCTByCodigoActividad


    /**
     * Metodo que Solicita un json con los datos de la Entidad Campo Transversal con Relacion
     * a Actividades
     *
     * @param _actividadSectorCTJson Obtiene desde el request los datos del Sector Campo Transversal a ingresar
     * @return Mensaje de Confirmacion de Registro de Sector Campo Transversal
     * @autor Nahum Martinez | NAM
     * @version 16/04/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del Campo Transversal de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = SECTORES_CAMPOS_ENDPOINT_NEW, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addActividadSectorCampoTransversal(@ApiParam(value = "Json de Sector Campo Transversal del Proyecto a Ingresar", required = true)
                                                              @RequestBody @Valid final TblActividadSectorCampoTransversal _actividadSectorCTJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividad": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(_actividadSectorCTJson.getIdActividad().getIdActividad());

            try {
                // Busca la Sector Campo Transversal, desde el Reporsitorio con el Parametro del Json enviado ( "idSector": {"idSector": valor })
                TblSectorCampoTransversal _tblSectorCampoTransversal = _sectorCampoTransversalRepository.findByIdSector(_actividadSectorCTJson.getIdSectorCampo().getIdSector());

                // Busca el Proyecto con el Proposito de validar que no se meta otro Item mas,
                // desde el Reporsitorio de Campo Transversal con el Parametro del Json enviado ( "idActividad": _tblActividad )

                if (_actividadSectorCampoTransversalRepository.countByCodigoActividad(_actividadSectorCTJson.getCodigoActividad()) > 0) {
                    msgMethod = "Ya Existe un registro con el Código de Campo Transversal para este Proyecto !! " + _actividadSectorCTJson.getCodigoActividad();

                    msgExeptions.map.put("findRecord", false);
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    // Seteo de las Fecha y Hora de Creacion
                    _actividadSectorCTJson.setFechaCreacion(dateActual);
                    _actividadSectorCTJson.setHoraCreacion(dateActual);

                    // Seteamos la Actividad de Actividad y Campo Transversal
                    _actividadSectorCTJson.setIdActividad(_tblActividad);
                    _actividadSectorCTJson.setIdSectorCampo(_tblSectorCampoTransversal);

                    // Realizamos la Persistencia de los Datos
                    _actividadSectorCampoTransversalRepository.save(_actividadSectorCTJson);
                    _actividadSectorCampoTransversalRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Campo Transversal para este Proyecto, se ha Ingresado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Campo Transversal del Proyecto, con la informacion indicada !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Proyecto que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | addActividadSectorCampoTransversal


    /**
     * Metodo que Solicita un json con los datos de la Entidad Campo Transversal con Relacion
     * a Actividades
     *
     * @param codigoActividad Identificador de la Campo Transversal con Proyecto a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion de Campo Transversal
     * @autor Nahum Martinez | NAM
     * @version 16/04/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el codigo de Campo Transversal", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = SECTORES_CAMPOS_ENDPOINT_DELETE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadCampoTransversal(@ApiParam(value = "Codigo de Campo Transversal del Proyecto a Eliminar", required = true)
                                                                  @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Codigo enviado ( codigoActividad )
            TblActividadSectorCampoTransversal _tblActividadSectorCampoTransversal = _actividadSectorCampoTransversalRepository.findByCodigoActividad(codigoActividad);

            try {
                if (_actividadSectorCampoTransversalRepository.countByCodigoActividad(codigoActividad) > 0) {
                    // Realizamos la Persistencia de los Datos

                    _actividadSectorCampoTransversalRepository.deleletedCodigoActividad(codigoActividad);
                    _actividadSectorCampoTransversalRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Campo Transversal para este Proyecto, se ha Eliminado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgMethod = "No Existe un registro de Sector de Gobierno para este Proyecto !!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Eliminar el Campo Transversal del Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Campo Transversal para este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deletedActividadCampoTransversal

}
