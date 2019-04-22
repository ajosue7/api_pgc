/*
 * Copyright (c) 2019.  Nahum Martinez
 */

/**
 * @author nahum.martinez
 * @date 22/03/2019
 * @name SectoresOcdeCadResources
 */

package com.api.pgc.core.APIRestPGC.resourses.programas;


import com.api.pgc.core.APIRestPGC.models.programas.TblNivelPrograma;
import com.api.pgc.core.APIRestPGC.models.programas.TblProgramaVidaMejor;
import com.api.pgc.core.APIRestPGC.repository.programas.NivelProgramaRepository;
import com.api.pgc.core.APIRestPGC.repository.programas.ProgramaVidaMejorRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "ProgramasVidaMejorApi", description = "Operaciones sobre el Modulo de Vida Mejor", tags = "Programas")
public class ProgramasVidaMejorResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ProgramaVidaMejorRepository _programaVidaMejorRepository;

    @Autowired
    NivelProgramaRepository _nivelProgramaRepository;


    /**
     * Metodo que despliega la Lista de todos Programas de Vida Mejor de la BD
     *
     * @return Lista de Vida Mejor de la BD
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos Programas de Vida Mejor de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_VIDA_MEJOR_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllVidaMejor() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos Programas de Vida Mejor registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            if (_programaVidaMejorRepository.findAll().isEmpty() || _programaVidaMejorRepository.findAll() == null) {
                msgMethod = "No Existen, Vida Mejor resgitrados en la Base de Datos, Contacta al Administrador";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("data", _programaVidaMejorRepository.findAll(new Sort(Sort.Direction.DESC, "<idPrograma>")));
                msgExeptions.map.put("totalRecors", _programaVidaMejorRepository.count());
            } else {
                msgExeptions.map.put("data", _programaVidaMejorRepository.findAll());
                msgExeptions.map.put("totalRecors", _programaVidaMejorRepository.count());
            }
            // Retorno del JSON
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getAllVidaMejor


    /**
     * Metodo que despliega el Vida Mejor de la BD
     *
     * @param idPrograma Identificador del Vida Mejor a Buscar
     * @return Vida Mejor de la BD
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Vida Mejor enviado a buscar de la BD, con el ID como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_VIDA_MEJOR_ENDPOINT_FIND_BY_ID_PROGRAMA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getVidaMejorById(@ApiParam(value = "Identificador del Vida Mejor a Buscar", required = true)
                                                    @PathVariable("idPrograma") long idPrograma) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_programaVidaMejorRepository.findByIdPrograma(idPrograma) == null) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Vida Mejor consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de Vida Mejor Consultado";
                msgExeptions.map.put("data", _programaVidaMejorRepository.findByIdPrograma(idPrograma));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato de Vida Mejor consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getVidaMejorById


    /**
     * Metodo que despliega el Vida Mejor de la BD
     *
     * @param idNivelPrograma Identificador del Nivel de Vida Mejor a Buscar
     * @return Vida Mejor de la BD
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Vida Mejor enviado a buscar de la BD, con el ID de Nivel como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_VIDA_MEJOR_ENDPOINT_FIND_BY_ID_NIVEL_PROGRAMA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getVidaMejorByIdNivelPrograma(@ApiParam(value = "Identificador de Nivel del Vida Mejor a Buscar", required = true)
                                                                 @PathVariable("idNivelPrograma") long idNivelPrograma) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Buscamos el Nivel Indicado por el Usuario
        try {
            TblNivelPrograma _tblNivelPrograma = _nivelProgramaRepository.findByIdNivelPrograma(idNivelPrograma);

            try {
                if (_programaVidaMejorRepository.countVidaMejorByIdNivelPrograma(_tblNivelPrograma) == 0) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado datos de Vida Mejor consultado, con el Nivel indicado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de Vida Mejor Consultado";
                    msgExeptions.map.put("data", _programaVidaMejorRepository.getVidaMejorByIdNivelPrograma(_tblNivelPrograma));
                    msgExeptions.map.put("totalRecords", _programaVidaMejorRepository.countVidaMejorByIdNivelPrograma(_tblNivelPrograma));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Vida Mejor consultado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato del Nivel de Vida Mejor consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getVidaMejorByIdNivelPrograma


    /**
     * Metodo que despliega el Vida Mejor de la BD
     *
     * @param idNivelPrograma Identificador del Nivel de Vida Mejor a Buscar
     * @param sectorPadreId   Identificador del Vida Mejor Padre a Buscar
     * @return Vida Mejor de la BD
     * @autor Nahum Martinez | NAM
     * @version 16/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Vida Mejor enviado a buscar de la BD, con el ID de Nivel y Sector Padre como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_VIDA_MEJOR_ENDPOINT_FIND_BY_ID_TIPO_NIVEL_PADRE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getVidaMejorByIdNivelProgramaAndSectorPadreId(@ApiParam(value = "Identificador de Nivel del Programa y Programa Padre Vida Mejor a Buscar", required = true)
                                                                                 @PathVariable("idNivelPrograma") long idNivelPrograma, @PathVariable("sectorPadreId") long sectorPadreId) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Buscamos el Nivel Indicado y el Sector Padre
        try {
            TblNivelPrograma _tblNivelPrograma = _nivelProgramaRepository.findByIdNivelPrograma(idNivelPrograma);
            TblProgramaVidaMejor _tblProgramaVidaMejor = _programaVidaMejorRepository.findByIdPrograma(sectorPadreId);

            try {
                if (_programaVidaMejorRepository.countVidaMejorIdNivelProgramaAndprogramaPadreId(_tblNivelPrograma, _tblProgramaVidaMejor) == 0) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado datos de Vida Mejor consultado, con el Sector Padre y Nivel indicado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de Vida Mejor Consultado";
                    msgExeptions.map.put("data", _programaVidaMejorRepository.getVidaMejorByIdNivelProgramaAndprogramaPadreId(_tblNivelPrograma, _tblProgramaVidaMejor));
                    msgExeptions.map.put("totalRecords", _programaVidaMejorRepository.countVidaMejorIdNivelProgramaAndprogramaPadreId(_tblNivelPrograma, _tblProgramaVidaMejor));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Vida Mejor consultado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato del Nivel de Sector Gobierno consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getVidaMejorByIdNivelProgramaAndSectorPadreId
}
