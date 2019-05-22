/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades.programas;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.programas.TblActividadProgramaPoliticaPublica;
import com.api.pgc.core.APIRestPGC.models.programas.TblProgramaPoliticaPublica;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.programas.ActividadProgramaPoliticaPublicaRepository;
import com.api.pgc.core.APIRestPGC.repository.programas.ProgramaPoliticaPublicaRepository;
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
@Api(value = "ActividadPoliticaPublicaAPI", description = "Operaciones sobre el Modulo de Proyectos Programas", tags = "Programas de Proyecto")
public class ActividadProgramaPoliticaPublicaResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadProgramaPoliticaPublicaRepository _actividadProgramaPoliticaPublicaRepository;

    @Autowired
    ActividadRepository _actividadRepository;

    @Autowired
    ProgramaPoliticaPublicaRepository _politicaPublicaRepository;

    /**
     * Metodo que despliega la Lista de todas los Programas de Politicas Publicas una Actividad de la BD
     *
     * @return Lista de Programas de Politicas Publicas una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 25/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todas los Programas de Politicas Publicas los Proyectos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_POLITICA_PUBLICA_ACT_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllActvidadesPoliticaPublica() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas los Programas de Politicas Publicas registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadProgramaPoliticaPublicaRepository.findAll());
            msgExeptions.map.put("countRecords", _actividadProgramaPoliticaPublicaRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getAllActvidadesPoliticaPublica


    /**
     * Metodo que despliega los Programas de Politicas Publicas a la Actividad de la BD
     *
     * @param idActividad Identificador de la Actividad a Buscar
     * @return Programas de Politicas Publicas una Atividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 25/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna los Programas de Politicas Publicas la Actividad enviada a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_POLITICA_PUBLICA_ENDPOINT_FIND_BY_ID_ACTIVIDAD, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getPoliticaPublicaByIdActividad(@ApiParam(value = "Identificador de la Actividad a Buscar", required = true)
                                                                   @PathVariable("idActividad") long idActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad para verificar si existe
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(idActividad);

            if (_actividadProgramaPoliticaPublicaRepository.countByIdActividad(_tblActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de los Programas de Politicas Publicas del Proyecto consultado";

                msgExeptions.map.put("error", "No data found");

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de los Programas de Politicas Publicas del Proyecto";
                msgExeptions.map.put("data", _actividadProgramaPoliticaPublicaRepository.getCodigoActividadPrograma(_tblActividad));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado el Proyecto solicitado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getPoliticaPublicaByIdActividad

    /**
     * Metodo que despliega el Politicas Publicasasociado a Proyecto de la BD
     *
     * @param codigoActividad Codigo de Politicas Publicasasociado a Proyecto a Buscar
     * @return Politicas Publicas la BD
     * @autor Nahum Martinez | NAM
     * @version 25/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Politicas Publicas buscar el codigo a la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_POLITICA_PUBLICA_ENDPOINT_FIND_BY_COD_PROGRAMA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getPoliticaPublicaByCodigoActividad(@ApiParam(value = "Código de Politicas Publicasasociado a un Proyecto a Buscar", required = true)
                                                                       @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadProgramaPoliticaPublicaRepository.countByCodigoActividad(codigoActividad) == 0) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Politicas Publicasasociados a Proyecto consultado";

                msgExeptions.map.put("data", _actividadProgramaPoliticaPublicaRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("find", false);

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Se ha encontrado dato de Politicas Publicasasociados a Proyecto consultado";
                msgExeptions.map.put("data", _actividadProgramaPoliticaPublicaRepository.findByCodigoActividad(codigoActividad));
                msgExeptions.map.put("find", true);

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "Se ha encontraddo datos de Politicas Publicasasociado a un Proyecto Consultada";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getPoliticaPublicaByCodigoActividad


    /**
     * Metodo que Solicita un json con los datos de la Entidad Politicas Publicascon Relacion
     * a Actividades
     *
     * @param _actividadPoliticaPublicaJson Obtiene desde el request los datos del Politicas Publicasa ingresar
     * @return Mensaje de Confirmacion de Registro de Programa Politica Publica
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.1
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del Politicas Publicas proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = PROGRAMAS_POLITICA_PUBLICA_ENDPOINT_NEW, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addActividadPoliticaPublica(@ApiParam(value = "Json de Politicas Publicasl Proyecto a Ingresar", required = true)
                                                               @RequestBody @Valid final TblActividadProgramaPoliticaPublica _actividadPoliticaPublicaJson) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividad": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad(_actividadPoliticaPublicaJson.getIdActividad().getIdActividad());

            try {
                // Busca la Programa Politica Publica, desde el Reporsitorio con el Parametro del Json enviado ( "idSector": {"idSector": valor })
                TblProgramaPoliticaPublica _tblProgramaPoliticaPublica = _politicaPublicaRepository.findByIdPrograma(_actividadPoliticaPublicaJson.getIdProgramaPoliticaPublica().getIdPrograma());

                // Busca el Proyecto con el Proposito de validar que no se meta otro Item mas,
                // desde el Reporsitorio de Politicas Publicascon el Parametro del Json enviado ( "idActividad": _tblActividad )

                if (_actividadProgramaPoliticaPublicaRepository.countByCodigoActividad(_actividadPoliticaPublicaJson.getCodigoActividad()) > 0) {
                    msgMethod = "Ya Existe un registro con el Código de Politicas Publicaspara este Proyecto !! " + _actividadPoliticaPublicaJson.getCodigoActividad();

                    msgExeptions.map.put("findRecord", false);
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    // Seteo de las Fecha y Hora de Creacion
                    _actividadPoliticaPublicaJson.setFechaCreacion(dateActual);
                    _actividadPoliticaPublicaJson.setHoraCreacion(dateActual);

                    // Seteamos la Actividad de Actividad y Programa Politica Publica
                    _actividadPoliticaPublicaJson.setIdActividad(_tblActividad);
                    _actividadPoliticaPublicaJson.setIdProgramaPoliticaPublica(_tblProgramaPoliticaPublica);

                    // Realizamos la Persistencia de los Datos
                    _actividadProgramaPoliticaPublicaRepository.save(_actividadPoliticaPublicaJson);
                    _actividadProgramaPoliticaPublicaRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Politicas Publicaspara este Proyecto, se ha Ingresado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Politicas Publicasl Proyecto, con la informacion indicada !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Proyecto que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | addActividadPoliticaPublica


    /**
     * Metodo que Solicita un json con los datos de la Entidad Politicas Publicascon Relacion
     * a Actividades
     *
     * @param codigoActividad Identificador de la Politicas Publicascon Proyecto a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion de Programa Politica Publica
     * @autor Nahum Martinez | NAM
     * @version 25/04/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el codigo de Programa Politica Publica", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = PROGRAMAS_POLITICA_PUBLICA_ENDPOINT_DELETE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadPoliticaPublica(@ApiParam(value = "Codigo de Politicas Publicasl Proyecto a Eliminar", required = true)
                                                                   @PathVariable("codigoActividad") String codigoActividad) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Codigo enviado ( codigoActividad )
            TblActividadProgramaPoliticaPublica _tblActividadProgramaPoliticaPublica = _actividadProgramaPoliticaPublicaRepository.findByCodigoActividad(codigoActividad);

            try {
                if (_actividadProgramaPoliticaPublicaRepository.countByCodigoActividad(codigoActividad) > 0) {
                    // Realizamos la Persistencia de los Datos

                    _actividadProgramaPoliticaPublicaRepository.deleletedCodigoActividad(codigoActividad);
                    _actividadProgramaPoliticaPublicaRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Politicas Publicaspara este Proyecto, se ha Eliminado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgMethod = "No Existe un registro de Programa de Politicas Publicas para este Proyecto !!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Eliminar el Politicas Publicas del Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Politicas Publicas para este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deletedActividadPoliticaPublica

}
