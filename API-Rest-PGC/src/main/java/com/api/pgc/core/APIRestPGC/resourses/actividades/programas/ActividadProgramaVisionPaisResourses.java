/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades.programas;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.programas.TblActividadProgramaVisionPais;
import com.api.pgc.core.APIRestPGC.models.programas.TblProgramaVisionPais;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.programas.ActividadProgramaVisionPaisRepository;
import com.api.pgc.core.APIRestPGC.repository.programas.ProgramaVisionPaisRepository;
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
@Api(value = "ActividadVisionPaisAPI", description = "Operaciones sobre el Modulo de Proyectos Programas", tags = "Programas de Proyecto")
public class ActividadProgramaVisionPaisResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadProgramaVisionPaisRepository _actividadProgramaVisionPaisRepository;

    @Autowired
    ActividadRepository _actividadRepository;

    @Autowired
    ProgramaVisionPaisRepository _visionPaisRepository;

    /**
     * Metodo que despliega la Lista de todas los Programas de Vision de Paisde una Actividad de la BD
     *
     * @return Lista de Programas de Vision de Paisde una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todas los Programas de Vision de Pais los Proyectos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_VISION_PAIS_ACT_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllActvidadesVisionPais() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas los Programas de Vision de Pais registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadProgramaVisionPaisRepository.findAll());
            msgExeptions.map.put("countRecords", _actividadProgramaVisionPaisRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAllActvidadesVisionPais


    /**
     * Metodo que despliega los Programas de Vision de Pais a la Actividad de la BD
     *
     * @param idActividad Identificador de la Actividad a Buscar
     * @return Programas de Vision de Paisde una Atividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna los Programas de Vision de Paisde la Actividad enviada a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_VISION_PAIS_ENDPOINT_FIND_BY_ID_ACTIVIDAD, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getVisionPaisByIdActividad(@ApiParam(value = "Identificador de la Actividad a Buscar", required = true)
                                                              @PathVariable("idActividad") long idActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad para verificar si existe
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(idActividad);

            if (_actividadProgramaVisionPaisRepository.countByIdActividad(_tblActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de los Programas de Vision de Pais del Proyecto consultado";

                msgExeptions.map.put("error", "No data found");

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de los Programas de Vision de Pais del Proyecto";
                msgExeptions.map.put("data", _actividadProgramaVisionPaisRepository.getCodigoActividadPrograma(_tblActividad));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado el Proyecto solicitado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getVisionPaisByIdActividad

    /**
     * Metodo que despliega el Vision de Paisasociado a Proyecto de la BD
     *
     * @param codigoActividad Codigo de Vision de Paisasociado a Proyecto a Buscar
     * @return Vision de Paisde la BD
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Vision de Paisa buscar el codigo a la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_VISION_PAIS_ENDPOINT_FIND_BY_COD_PROGRAMA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getVisionPaisByCodigoActividad(@ApiParam(value = "Código de Vision de Paisasociado a un Proyecto a Buscar", required = true)
                                                                  @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadProgramaVisionPaisRepository.countByCodigoActividad(codigoActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Vision de Paisasociados a Proyecto consultado";

                msgExeptions.map.put("data", _actividadProgramaVisionPaisRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("find", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Se ha encontrado dato de Vision de Paisasociados a Proyecto consultado";
                msgExeptions.map.put("data", _actividadProgramaVisionPaisRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("find", true);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "Se ha encontraddo datos de Vision de Paisasociado a un Proyecto Consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getVisionPaisByCodigoActividad


    /**
     * Metodo que Solicita un json con los datos de la Entidad Vision de Paiscon Relacion
     * a Actividades
     *
     * @param _actividadVisionPaisJson Obtiene desde el request los datos del Vision de Paisa ingresar
     * @return Mensaje de Confirmacion de Registro de Vida Mejor
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del Vision de Paisde proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = PROGRAMAS_VISION_PAIS_ENDPOINT_NEW, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addActividadVisionPais(@ApiParam(value = "Json de Vision de Paisdel Proyecto a Ingresar", required = true)
                                                          @RequestBody @Valid final TblActividadProgramaVisionPais _actividadVisionPaisJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividad": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(_actividadVisionPaisJson.getIdActividad().getIdActividad());

            try {
                // Busca la Vida Mejor, desde el Reporsitorio con el Parametro del Json enviado ( "idSector": {"idSector": valor })
                TblProgramaVisionPais _tblProgramaVisionPais = _visionPaisRepository.findByIdPrograma(_actividadVisionPaisJson.getIdProgramaVisionPais().getIdPrograma());

                // Busca el Proyecto con el Proposito de validar que no se meta otro Item mas,
                // desde el Reporsitorio de Vision de Paiscon el Parametro del Json enviado ( "idActividad": _tblActividad )

                if (_actividadProgramaVisionPaisRepository.countByCodigoActividad(_actividadVisionPaisJson.getCodigoActividad()) > 0) {
                    msgMethod = "Ya Existe un registro con el Código de Vision de Paispara este Proyecto !! " + _actividadVisionPaisJson.getCodigoActividad();

                    msgExeptions.map.put("findRecord", false);
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    // Seteo de las Fecha y Hora de Creacion
                    _actividadVisionPaisJson.setFechaCreacion(dateActual);
                    _actividadVisionPaisJson.setHoraCreacion(dateActual);

                    // Seteamos la Actividad de Actividad y Vida Mejor
                    _actividadVisionPaisJson.setIdActividad(_tblActividad);
                    _actividadVisionPaisJson.setIdProgramaVisionPais(_tblProgramaVisionPais);

                    // Realizamos la Persistencia de los Datos
                    _actividadProgramaVisionPaisRepository.save(_actividadVisionPaisJson);
                    _actividadProgramaVisionPaisRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Vision de Paispara este Proyecto, se ha Ingresado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Vision de Paisdel Proyecto, con la informacion indicada !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Proyecto que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | addActividadVisionPais


    /**
     * Metodo que Solicita un json con los datos de la Entidad Vision de Paiscon Relacion
     * a Actividades
     *
     * @param codigoActividad Identificador de la Vision de Paiscon Proyecto a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion de Vida Mejor
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el codigo de Vida Mejor", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = PROGRAMAS_VISION_PAIS_ENDPOINT_DELETE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadVisionPais(@ApiParam(value = "Codigo de Vision de Paisdel Proyecto a Eliminar", required = true)
                                                              @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Codigo enviado ( codigoActividad )
            TblActividadProgramaVisionPais _tblActividadProgramaVisionPais = _actividadProgramaVisionPaisRepository.findByCodigoActividad(codigoActividad);

            try {
                if (_actividadProgramaVisionPaisRepository.countByCodigoActividad(codigoActividad) > 0) {
                    // Realizamos la Persistencia de los Datos

                    _actividadProgramaVisionPaisRepository.deleletedCodigoActividad(codigoActividad);
                    _actividadProgramaVisionPaisRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Vision de Paispara este Proyecto, se ha Eliminado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgMethod = "No Existe un registro de Programa de Vision de Pais para este Proyecto !!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Eliminar el Vision de Paisdel Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Vision de Paispara este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deletedActividadVisionPais

}
