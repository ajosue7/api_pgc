/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.TblActividadRecurso;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTipo;
import com.api.pgc.core.APIRestPGC.models.recursos.TblTipoRecurso;
import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRecursoRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.TiposRepository;
import com.api.pgc.core.APIRestPGC.repository.recursos.TiposRecursosRepository;
import com.api.pgc.core.APIRestPGC.repository.seguridad.UsuariosRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "ActividadRecursoAPI", description = "Operaciones sobre el Modulo de Recursos de Proyectos", tags = "Recursos del Proyecto")
public class ActividadRecusoResourses {
    //Propiedades de la Clase
    private String msgMethod = null;

    @Autowired
    ActividadRecursoRepository _actividadRecursoRepository;

    @Autowired
    UsuariosRepository _usuariosRepository;


    @Autowired
    ActividadRepository _actividadRepository;
    @Autowired
    TiposRepository tiposRepository;

    @Autowired
    TiposRecursosRepository _tiposRecursosRepository;

    /**
     * Metodo que despliega la Lista de todos los Recursos de una Actividad de la BD
     *
     * @return Lista de los Recursos de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 12/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Recursos de una Actividad de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = RECURSOS_DOC_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllActvidadesRecursos() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Recursos registradas en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadRecursoRepository.findAll());
            msgExeptions.map.put("countRecords", _actividadRecursoRepository.count());
            // Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getAllActvidadesRecursos


    /**
     * Metodo que despliega el Recurso asociadas a la Actividad de la BD
     *
     * @param ccodActividadRecurso Identificador del Recurso a Buscar
     * @return Recurso de Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 12/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Recurso enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = RECURSOS_DOC_ENDPOINT_FIND_BY_ID_ACTIVIDAD, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getIdInternaByCodigoActividadRecurso(@ApiParam(value = "Recursos de la Actividad a Buscar", required = true)
                                                                        @PathVariable("codActividadRecurso") String ccodActividadRecurso) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadRecursoRepository.findByCodigoActividadRecurso(ccodActividadRecurso) == null) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Recurso consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Recurso consultado";
                msgExeptions.map.put("data", _actividadRecursoRepository.findByCodigoActividadRecurso(ccodActividadRecurso));

                // Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN


    /**
     * Metodo que Solicita un json con los datos de la Recursos de Proyecto
     * @autor Allan Madrid | AMA
     * @version  24/06/2019/v1.0
     * @return Mensaje de Confirmacion de Ingreso de Recurso de Proyecto
     * @param actividadRecursoJson Obtiene desde el request los datos del Recurso de Proyecto a ingresar
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del nuevo Recurso de Proyecto",
            notes = "Se debe incluir en la Estructura del JsonBean el Identificador de Espacio de Trabajo", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = RECURSOS_DOC_ENDPOINT_NEW, produces = "application/json")
    public HashMap<String, Object> addRecursoProyecto(@ApiParam(value="Json del nuevo Recurso a ingresar", required=true)
                                                     @RequestBody final TblActividadRecurso actividadRecursoJson) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();


        try {
            //Busca el Tipo, desde el Repositorio con el Parametro del Json enviado ( "idTipo": { "idTipo": valor })
            TblTipo tipo = tiposRepository.findByIdTipo( actividadRecursoJson.getIdTipo().getIdTipo() );

            //Busca la Actividad, desde el Repositorio con el Parametro del Json enviado ( "idActividad": { "idActividad": valor })
            TblActividad actividad = _actividadRepository.findByIdActividad( actividadRecursoJson.getIdActividad().getIdActividad() );

            //Busca el Usuario, desde el Repositorio con el Parametro del Json enviado ( "idUsuario": { "idUsuario": valor })
            TblUsuarios usuarios = _usuariosRepository.findByIdUsuario( actividadRecursoJson.getIdUsuario().getIdUsuario() );

            //Busca el Tipo de Recurso, desde el Repositorio con el Parametro del Json enviado ( "idTipoRecursos": { "idTipoRecursos": valor })
            TblTipoRecurso recurso = _tiposRecursosRepository.findByIdTipoRecursos( actividadRecursoJson.getIdTipoRecursos().getIdTipoRecursos() );



            //Graba los Datos de Tipos
            try {

                //Setea el valor Buscado de la Entidad Espacios de Trabajo | Tipo
                actividadRecursoJson.setIdTipo(tipo);

                //Setea el valor Buscado de la Entidad Espacios de Trabajo | Estados
                actividadRecursoJson.setIdActividad(actividad);

                //Setea el valor Buscado de la Entidad Espacios de Trabajo | Pais
                actividadRecursoJson.setIdUsuario(usuarios);

                actividadRecursoJson.setIdTipoRecurso(recurso);


                //Realizamos la Persistencia de los Datos
                _actividadRecursoRepository.save(actividadRecursoJson);

                //return _actividadRecursoRepository.findAll();
                msgMethod = "Se ha Ingresado de forma satisfactoria!!";

                //Retorno del json
                return msgExeptions.msgJson( msgMethod, 200 );

            }catch ( Exception ex ){
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Tipo";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        }catch ( Exception ex ){
            msgMethod = "No existe el Tipo de Espacio de Trabajo que buscas, por favor verfica que el Grupo ingresado es correcto.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que Solcita un json con los datos de la Entidad de Recurso de Proyecto
     *
     * @param _actividadRecursoJson Obtiene desde el request los datos del Recurso a ingresar
     * @param idActividadRecurso    Identificador de la tabla
     * @return Mensaje de Confirmacion de Actualización del Recurso de Proyecto
     * @autor Allan Madrid | AMA
     * @version 25/06/2019/v1.0
     */
    @ApiOperation(value = "Actualiza a la BD, la Información enviada por el Bean del Recurso de Proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PutMapping(value = RECURSOS_DOC_ENDPOINT_EDIT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> editRecursoProyecto(@ApiParam(value = "Json de Recurso de Proyecto a Ingresar", required = true)
                                                      @PathVariable("idActividadRecurso") long idActividadRecurso,
                                                      @RequestBody final TblActividadRecurso _actividadRecursoJson) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingreso
        Date dateActual = new Date();

        // Buscamos el Recurso solicitado para la Modificacion
        try {
            // Buacamos la Organizacion segun el Parametro enviado
            TblActividadRecurso _tblActividadRecurso = _actividadRecursoRepository.findByIdActividadRecurso(idActividadRecurso);

            if (_actividadRecursoRepository.countByidActividadRecurso(idActividadRecurso) > 0) {
                // Buscamos el Tipo de Recurso segun el Parametro enviado
                TblTipo _tblTipoE = tiposRepository.findByIdTipo(_actividadRecursoJson.getIdTipo().getIdTipo());

                // Buscamos la actividad  segun el Parametro enviado
                TblActividad _tblActividad = _actividadRepository.findByIdActividad(_actividadRecursoJson.getIdActividad().getIdActividad());

                // Buscamos el Usuario segun el Parametro enviado
                TblUsuarios _tblUsuarios = _usuariosRepository.findByIdUsuario(_actividadRecursoJson.getIdUsuario().getIdUsuario());

                // Buscamos el Recurso a utilizar segun el Parametro enviado
                TblTipoRecurso _tblRecurso = _tiposRecursosRepository.findByIdTipoRecursos(_actividadRecursoJson.getIdTipoRecursos().getIdTipoRecursos());

                try {
                    // Realizamos la Persistencia de los Datos
                    _tblActividadRecurso.setActivo(_actividadRecursoJson.isActivo());
                    _tblActividadRecurso.setCodigoActividadRecurso(_actividadRecursoJson.getCodigoActividadRecurso());
                    _tblActividadRecurso.setTitulo(_actividadRecursoJson.getTitulo());
                    _tblActividadRecurso.setDescripcion(_actividadRecursoJson.getDescripcion());
                    _tblActividadRecurso.setNota(_actividadRecursoJson.getNota());
                    _tblActividadRecurso.setUrlActividadRecursoLink(_actividadRecursoJson.getUrlActividadRecursoLink());
                    _tblActividadRecurso.setUrlActividadRecursoDocumento(_actividadRecursoJson.getUrlActividadRecursoDocumento());


                    _tblActividadRecurso.setIdTipo(_tblTipoE);
                    _tblActividadRecurso.setIdActividad(_tblActividad);
                    _tblActividadRecurso.setIdUsuario(_tblUsuarios);
                    _tblActividadRecurso.setIdTipoRecurso(_tblRecurso);

                    _actividadRecursoRepository.save(_tblActividadRecurso);
                    _actividadRecursoRepository.flush();

                    // return Repository
                    msgMethod = "Se ha Actualizado de forma satisfactoria el Recurso de Proyecto!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } catch (Exception ex) {
                    msgMethod = "No se pudo Actualizar el Recurso del proyecto, verifique no tenga un codigo de Actividad Duplicado ";
                    throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
                }
            } else {
                //Retorno del json
                msgMethod = "No se encuentra un Recurso con el parametro enviado !!";
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            msgMethod = "Hay problemas al momento de Actualizar el Recurso.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | editRecursoProyecto



}