/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades.sectores;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.sectores.TblActividadSectorOcde;
import com.api.pgc.core.APIRestPGC.models.sectores.TblSectorOcdeCad;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.sectores.ActividadSectorOcdeRepository;
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
@Api(value = "ActividadSectorOcdeAPI", description = "Operaciones sobre el Modulo de Proyectos Sectores", tags = "Sectores de Proyecto")
public class ActividadSectorOcdeResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadSectorOcdeRepository _actividadSectorOcdeRepository;

    @Autowired
    ActividadRepository _actividadRepository;

    @Autowired
    SectorOcdeCadRepository _sectorOcdeCadRepository;

    /**
     * Metodo que despliega la Lista de todas los Sectores Ocde de una Actividad de la BD
     *
     * @return Lista de Sectore Ocde de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 14/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Sectores OCDE relaciones con Proyectos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_OCDE_ACT_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllActvidadesSectoresOcde() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas los Sectores Ocde registrados en la BD";

        try {
            System.out.println("Entro a cargar Sceto Act ++++++++++++++++++++++++++++");
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadSectorOcdeRepository.getAllActividadesSectoresOcde2());
            // msgExeptions.map.put("countRecords", _actividadSectorOcdeRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAllActvidadesSectoresOcde


    /**
     * Metodo que despliega los Sectores Ocde asociados a la Actividad de la BD
     *
     * @param idActividad Identificador de la Actividad a Buscar
     * @return Sectores Ocde de una Atividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 14/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna los Sectores Ocde de la Actividad enviada a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_OCDE_ENDPOINT_FIND_BY_ID_ACTIVIDAD, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSectorOcdeByIdActividad(@ApiParam(value = "Identificador de la Actividad a Buscar", required = true)
                                                              @PathVariable("idActividad") long idActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad para verificar si existe
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(idActividad);

            if (_actividadSectorOcdeRepository.countByIdActividad(_tblActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de los Sectores Ocde del Proyecto consultado";

                msgExeptions.map.put("error", "No data found");

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de los Sectores Ocde del Proyecto";
                msgExeptions.map.put("data", _actividadSectorOcdeRepository.getCodigoActividadSector(_tblActividad));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado el Proyecto solicitado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getSectorOcdeByCod

    /**
     * Metodo que despliega el Sector Ocde asociado a Proyecto de la BD
     *
     * @param codigoActividad Codigo de Sector Ocde asociado a Proyecto a Buscar
     * @return Sector Ocde de la BD
     * @autor Nahum Martinez | NAM
     * @version 14/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Sector Ocde a buscar el codigo a la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_OCDE_ENDPOINT_FIND_BY_COD_SECTOR, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSectorOcdeByCodigoActividad(@ApiParam(value = "Código de Sector Ocde asociado a un Proyecto a Buscar", required = true)
                                                                  @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadSectorOcdeRepository.countByCodigoActividad(codigoActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Sectores Ocde asociados a Proyecto consultado";

                msgExeptions.map.put("data", _actividadSectorOcdeRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("find", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Se ha encontrado dato de Sectores Ocde asociados a Proyecto consultado";
                msgExeptions.map.put("data", _actividadSectorOcdeRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("find", true);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "Se ha encontraddo datos de Sector asociado a un Proyecto Consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getSectorOcdeByCodigoActividad


    /**
     * Metodo que Solicita un json con los datos de la Entidad Sectores Ocde con Relacion
     * a Actividades
     *
     * @param _actividadSectorOcdeJson Obtiene desde el request los datos del Sector Ocde a ingresar
     * @return Mensaje de Confirmacion de Registro de Sector Ocde
     * @autor Nahum Martinez | NAM
     * @version 14/04/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del Sector Ocde de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = SECTORES_OCDE_ENDPOINT_NEW, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addActividadSectorOcde(@ApiParam(value = "Json de Sector Ocde del Proyecto a Ingresar", required = true)
                                                          @RequestBody @Valid final TblActividadSectorOcde _actividadSectorOcdeJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividad": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(_actividadSectorOcdeJson.getIdActividad().getIdActividad());

            try {
                // Busca la Sector Ocde, desde el Reporsitorio con el Parametro del Json enviado ( "idSector": {"idSector": valor })
                TblSectorOcdeCad _tblSectorOcdeCad = _sectorOcdeCadRepository.findByIdSector(_actividadSectorOcdeJson.getIdSectorOcde().getIdSector());

                // Busca el Proyecto con el Proposito de validar que no se meta otro Item mas,
                // desde el Reporsitorio de Sectores Ocde con el Parametro del Json enviado ( "idActividad": _tblActividad )

                if (_actividadSectorOcdeRepository.countByCodigoActividad(_actividadSectorOcdeJson.getCodigoActividad()) > 0) {
                    msgMethod = "Ya Existe un registro con el Código de Sector Ocde para este Proyecto !! " + _actividadSectorOcdeJson.getCodigoActividad();

                    msgExeptions.map.put("findRecord", false);
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    // Seteo de las Fecha y Hora de Creacion
                    _actividadSectorOcdeJson.setFechaCreacion(dateActual);
                    _actividadSectorOcdeJson.setHoraCreacion(dateActual);

                    // Seteamos la Actividad de la Id Interna y Sector Ocde
                    _actividadSectorOcdeJson.setIdActividad(_tblActividad);
                    _actividadSectorOcdeJson.setIdSectorOcde(_tblSectorOcdeCad);

                    // Realizamos la Persistencia de los Datos
                    _actividadSectorOcdeRepository.save(_actividadSectorOcdeJson);
                    _actividadSectorOcdeRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Sector Ocde para este Proyecto, se ha Ingresado de forma satisfactoria!!";

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
     * Metodo que Solicita un json con los datos de la Entidad Sectores Ocde con Relacion
     * a Actividades
     *
     * @param codigoActividad Identificador de la Sector Ocde con Proyecto a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion de Sector Ocde
     * @autor Nahum Martinez | NAM
     * @version 14/04/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el codigo de Sector Ocde", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = SECTORES_OCDE_ENDPOINT_DELETE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadSectorOcde(@ApiParam(value = "Codigo de Sector Ocde del Proyecto a Eliminar", required = true)
                                                              @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Codigo enviado ( codigoActividad )
            TblActividadSectorOcde _tblActividadSectorOcde = _actividadSectorOcdeRepository.findByCodigoActividad(codigoActividad);

            try {
                if (_actividadSectorOcdeRepository.countByCodigoActividad(codigoActividad) > 0) {
                    // Realizamos la Persistencia de los Datos

                    _actividadSectorOcdeRepository.deleletedCodigoActividad(codigoActividad);
                    _actividadSectorOcdeRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Sector Ocde/Cad para este Proyecto, se ha Eliminado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgMethod = "No Existe un registro de Sector Ocde/Cad para este Proyecto !!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Eliminar el sector Ocde/Cad del Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Sector Ocde/Cad para este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deletedActividadSectorOcde

}
