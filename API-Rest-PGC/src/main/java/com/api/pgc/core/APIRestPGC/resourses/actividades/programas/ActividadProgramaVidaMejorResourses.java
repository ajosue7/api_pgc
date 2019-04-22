/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades.programas;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.programas.TblActividadProgramaVidaMejor;
import com.api.pgc.core.APIRestPGC.models.programas.TblProgramaVidaMejor;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.programas.ActividadProgramaVidaMejorRepository;
import com.api.pgc.core.APIRestPGC.repository.programas.ProgramaVidaMejorRepository;
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
@Api(value = "ActividadVidaMejorAPI", description = "Operaciones sobre el Modulo de Proyectos Programas", tags = "Programas de Proyecto")
public class ActividadProgramaVidaMejorResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadProgramaVidaMejorRepository _actividadProgramaVidaMejorRepository;

    @Autowired
    ActividadRepository _actividadRepository;

    @Autowired
    ProgramaVidaMejorRepository _programaVidaMejorRepository;

    /**
     * Metodo que despliega la Lista de todas los Programas de Vida Mejor de una Actividad de la BD
     *
     * @return Lista de Programas de Vida Mejor de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todas los Programas de Vida Mejor los Proyectos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_VIDA_MEJOR_ACT_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllActvidadesVidaMejor() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas los Programas de Vida Mejor registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadProgramaVidaMejorRepository.findAll());
            msgExeptions.map.put("countRecords", _actividadProgramaVidaMejorRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAllActvidadesVidaMejor


    /**
     * Metodo que despliega los Programas de Vida Mejor a la Actividad de la BD
     *
     * @param idActividad Identificador de la Actividad a Buscar
     * @return Programas de Vida Mejor de una Atividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna los Programas de Vida Mejor de la Actividad enviada a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_VIDA_MEJOR_ENDPOINT_FIND_BY_ID_ACTIVIDAD, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getVidaMejorByIdActividad(@ApiParam(value = "Identificador de la Actividad a Buscar", required = true)
                                                             @PathVariable("idActividad") long idActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad para verificar si existe
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(idActividad);

            if (_actividadProgramaVidaMejorRepository.countByIdActividad(_tblActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de los Programas de Vida Mejor del Proyecto consultado";

                msgExeptions.map.put("error", "No data found");

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de los Programas de Vida Mejor del Proyecto";
                msgExeptions.map.put("data", _actividadProgramaVidaMejorRepository.getCodigoActividadPrograma(_tblActividad));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado el Proyecto solicitado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getVidaMejorByIdActividad

    /**
     * Metodo que despliega el Vida Mejor asociado a Proyecto de la BD
     *
     * @param codigoActividad Codigo de Vida Mejor asociado a Proyecto a Buscar
     * @return Vida Mejor de la BD
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Vida Mejor a buscar el codigo a la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_VIDA_MEJOR_ENDPOINT_FIND_BY_COD_PROGRAMA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getVidaMejorByCodigoActividad(@ApiParam(value = "Código de Vida Mejor asociado a un Proyecto a Buscar", required = true)
                                                                 @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadProgramaVidaMejorRepository.countByCodigoActividad(codigoActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Vida Mejor asociados a Proyecto consultado";

                msgExeptions.map.put("data", _actividadProgramaVidaMejorRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("find", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Se ha encontrado dato de Vida Mejor asociados a Proyecto consultado";
                msgExeptions.map.put("data", _actividadProgramaVidaMejorRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("find", true);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "Se ha encontraddo datos de Vida Mejor asociado a un Proyecto Consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getVidaMejorByCodigoActividad


    /**
     * Metodo que Solicita un json con los datos de la Entidad Vida Mejor con Relacion
     * a Actividades
     *
     * @param _actividadVidaMejorJson Obtiene desde el request los datos del Vida Mejor a ingresar
     * @return Mensaje de Confirmacion de Registro de Vida Mejor
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del Vida Mejor de proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = PROGRAMAS_VIDA_MEJOR_ENDPOINT_NEW, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addActividadVidaMejor(@ApiParam(value = "Json de Vida Mejor del Proyecto a Ingresar", required = true)
                                                         @RequestBody @Valid final TblActividadProgramaVidaMejor _actividadVidaMejorJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividad": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(_actividadVidaMejorJson.getIdActividad().getIdActividad());

            try {
                // Busca la Vida Mejor, desde el Reporsitorio con el Parametro del Json enviado ( "idSector": {"idSector": valor })
                TblProgramaVidaMejor _tblProgramaVidaMejor = _programaVidaMejorRepository.findByIdPrograma(_actividadVidaMejorJson.getIdProgramaVidaMejor().getIdPrograma());

                // Busca el Proyecto con el Proposito de validar que no se meta otro Item mas,
                // desde el Reporsitorio de Vida Mejor con el Parametro del Json enviado ( "idActividad": _tblActividad )

                if (_actividadProgramaVidaMejorRepository.countByCodigoActividad(_actividadVidaMejorJson.getCodigoActividad()) > 0) {
                    msgMethod = "Ya Existe un registro con el Código de Vida Mejor para este Proyecto !! " + _actividadVidaMejorJson.getCodigoActividad();

                    msgExeptions.map.put("findRecord", false);
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    // Seteo de las Fecha y Hora de Creacion
                    _actividadVidaMejorJson.setFechaCreacion(dateActual);
                    _actividadVidaMejorJson.setHoraCreacion(dateActual);

                    // Seteamos la Actividad de Actividad y Vida Mejor
                    _actividadVidaMejorJson.setIdActividad(_tblActividad);
                    _actividadVidaMejorJson.setIdProgramaVidaMejor(_tblProgramaVidaMejor);

                    // Realizamos la Persistencia de los Datos
                    _actividadProgramaVidaMejorRepository.save(_actividadVidaMejorJson);
                    _actividadProgramaVidaMejorRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Vida Mejor para este Proyecto, se ha Ingresado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Vida Mejor del Proyecto, con la informacion indicada !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Proyecto que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | addActividadVidaMejor


    /**
     * Metodo que Solicita un json con los datos de la Entidad Vida Mejor con Relacion
     * a Actividades
     *
     * @param codigoActividad Identificador de la Vida Mejor con Proyecto a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion de Vida Mejor
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el codigo de Vida Mejor", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = PROGRAMAS_VIDA_MEJOR_ENDPOINT_DELETE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadVidaMejor(@ApiParam(value = "Codigo de Vida Mejor del Proyecto a Eliminar", required = true)
                                                             @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Codigo enviado ( codigoActividad )
            TblActividadProgramaVidaMejor _tblActividadProgramaVidaMejor = _actividadProgramaVidaMejorRepository.findByCodigoActividad(codigoActividad);

            try {
                if (_actividadProgramaVidaMejorRepository.countByCodigoActividad(codigoActividad) > 0) {
                    // Realizamos la Persistencia de los Datos

                    _actividadProgramaVidaMejorRepository.deleletedCodigoActividad(codigoActividad);
                    _actividadProgramaVidaMejorRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Vida Mejor para este Proyecto, se ha Eliminado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgMethod = "No Existe un registro de Sector de Gobierno para este Proyecto !!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Eliminar el Vida Mejor del Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Vida Mejor para este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deletedActividadVidaMejor

}
