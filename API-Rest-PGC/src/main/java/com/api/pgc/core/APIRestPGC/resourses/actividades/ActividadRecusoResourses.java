/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades;


/*
 * Definicion de las Librerias a importar de la Clase
 */

import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRecursoRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.recursos.TiposRecursosRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    ActividadRepository _actividadRepository;

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



}
