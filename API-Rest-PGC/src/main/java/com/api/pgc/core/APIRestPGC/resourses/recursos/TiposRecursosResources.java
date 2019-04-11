/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.recursos;

//Imports de la Clase

import com.api.pgc.core.APIRestPGC.repository.recursos.TiposRecursosRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "tipoRecursoApi", description = "Operaciones sobre el Modulo de Tipos de Recursos", tags = "Recursos del Proyecto")
public class TiposRecursosResources {

    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    TiposRecursosRepository _tiposRecursosRepository;

    /**
     * Metodo que despliega la Lista de todos los Tipos de Recursos de la BD
     *
     * @return Lista de Tipos de Recursos de la BD
     * @autor Nahum Martinez | NAM
     * @version 11/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Tipos de Recursos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = RECURSOS_TIPO_ENDPOINT, produces = "application/json")
    public HashMap<String, Object> getAllTipoRecurso() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Tipos de Recursos registrados en la BD";

        try {
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _tiposRecursosRepository.findAll());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getAllTipoRecurso


    /**
     * Metodo que despliega el Tipo de Recurso de la BD
     *
     * @param idTipoRecurso Identificador del Tipo de Recurso a Buscar
     * @return Tipo Recurso de la BD
     * @autor Nahum Martinez | NAM
     * @version 11/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Tipo de Recurso enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro Encontrado"),
            @ApiResponse(code = 401, message = "No estas Autenticado"),
            @ApiResponse(code = 403, message = "No estas Autorizado para usar el Servicio"),
            @ApiResponse(code = 404, message = "Recurso no encontrado")})
    @GetMapping(value = RECURSOS_TIPO_ENDPOINT_FIND_BY_ID, produces = "application/json")
    public HashMap<String, Object> getTipoRecurso(@ApiParam(value = "Identificador del Tipo de Recurso a Buscar", required = true)
                                                  @PathVariable("idTipoRecurso") long idTipoRecurso) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_tiposRecursosRepository.findByIdTipoRecurso(idTipoRecurso) == null) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Tipo de Recurso consultado";
                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Tipo de Recurso Consultado";
                msgExeptions.map.put("data", _tiposRecursosRepository.findByIdTipoRecurso(idTipoRecurso));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getTipoRecurso

}
