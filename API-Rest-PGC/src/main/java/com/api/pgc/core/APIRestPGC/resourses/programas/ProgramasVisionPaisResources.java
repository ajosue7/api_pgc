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
import com.api.pgc.core.APIRestPGC.models.programas.TblProgramaVisionPais;
import com.api.pgc.core.APIRestPGC.repository.programas.NivelProgramaRepository;
import com.api.pgc.core.APIRestPGC.repository.programas.ProgramaVisionPaisRepository;
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
@Api(value = "ProgramasVisionPaisApi", description = "Operaciones sobre el Modulo de Vision de Pais", tags = "Programas")
public class ProgramasVisionPaisResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ProgramaVisionPaisRepository _programaVisionPaisRepository;

    @Autowired
    NivelProgramaRepository _nivelProgramaRepository;


    /**
     * Metodo que despliega la Lista de todos Programas de Vision Pais de la BD
     *
     * @return Lista de Vision Pais de la BD
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos Programas de Vision Pais de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_VISION_PAIS_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllVisionPais() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos Programas de Vision Pais registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            if (_programaVisionPaisRepository.findAll().isEmpty() || _programaVisionPaisRepository.findAll() == null) {
                msgMethod = "No Existen, Vision Pais resgitrados en la Base de Datos, Contacta al Administrador";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("data", _programaVisionPaisRepository.findAll(new Sort(Sort.Direction.DESC, "<idPrograma>")));
                msgExeptions.map.put("totalRecors", _programaVisionPaisRepository.count());
            } else {
                msgExeptions.map.put("data", _programaVisionPaisRepository.findAll());
                msgExeptions.map.put("totalRecors", _programaVisionPaisRepository.count());
            }
            // Retorno del JSON
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getAllVisionPais


    /**
     * Metodo que despliega el Vision Pais de la BD
     *
     * @param idPrograma Identificador del Vision Pais a Buscar
     * @return Vision Pais de la BD
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Vision Pais enviado a buscar de la BD, con el ID como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_VISION_PAIS_ENDPOINT_FIND_BY_ID_PROGRAMA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getVisionPaisById(@ApiParam(value = "Identificador del Vision Pais a Buscar", required = true)
                                                    @PathVariable("idPrograma") long idPrograma) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_programaVisionPaisRepository.findByIdPrograma(idPrograma) == null) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Vision Pais consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de Vision Pais Consultado";
                msgExeptions.map.put("data", _programaVisionPaisRepository.findByIdPrograma(idPrograma));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato de Vision Pais consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getVisionPaisById


    /**
     * Metodo que despliega el Vision Pais de la BD
     *
     * @param idNivelPrograma Identificador del Nivel de Vision Pais a Buscar
     * @return Vision Pais de la BD
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Vision Pais enviado a buscar de la BD, con el ID de Nivel como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_VISION_PAIS_ENDPOINT_FIND_BY_ID_NIVEL_PROGRAMA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getVisionPaisByIdNivelPrograma(@ApiParam(value = "Identificador de Nivel del Vision Pais a Buscar", required = true)
                                                                 @PathVariable("idNivelPrograma") long idNivelPrograma) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Buscamos el Nivel Indicado por el Usuario
        try {
            TblNivelPrograma _tblNivelPrograma = _nivelProgramaRepository.findByIdNivelPrograma(idNivelPrograma);

            try {
                if (_programaVisionPaisRepository.countVisionPaisByIdNivelPrograma(_tblNivelPrograma) == 0) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado datos de Vision Pais consultado, con el Nivel indicado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de Vision Pais Consultado";
                    msgExeptions.map.put("data", _programaVisionPaisRepository.getVisionPaisByIdNivelPrograma(_tblNivelPrograma));
                    msgExeptions.map.put("totalRecords", _programaVisionPaisRepository.countVisionPaisByIdNivelPrograma(_tblNivelPrograma));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Vision Pais consultado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato del Nivel de Vision Pais consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getVisionPaisByIdNivelPrograma


    /**
     * Metodo que despliega el Vision Pais de la BD
     *
     * @param idNivelPrograma Identificador del Nivel de Vision Pais a Buscar
     * @param sectorPadreId   Identificador del Vision Pais Padre a Buscar
     * @return Vision Pais de la BD
     * @autor Nahum Martinez | NAM
     * @version 16/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Vision Pais enviado a buscar de la BD, con el ID de Nivel y Sector Padre como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_VISION_PAIS_ENDPOINT_FIND_BY_ID_TIPO_NIVEL_PADRE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getVisionPaisByIdNivelProgramaAndSectorPadreId(@ApiParam(value = "Identificador de Nivel del Programa y Programa Padre Vision Pais a Buscar", required = true)
                                                                                 @PathVariable("idNivelPrograma") long idNivelPrograma, @PathVariable("sectorPadreId") long sectorPadreId) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Buscamos el Nivel Indicado y el Sector Padre
        try {
            TblNivelPrograma _tblNivelPrograma = _nivelProgramaRepository.findByIdNivelPrograma(idNivelPrograma);
            TblProgramaVisionPais _tblProgramaVisionPais = _programaVisionPaisRepository.findByIdPrograma(sectorPadreId);

            try {
                if (_programaVisionPaisRepository.countVisionPaisIdNivelProgramaAndprogramaPadreId(_tblNivelPrograma, _tblProgramaVisionPais) == 0) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado datos de Vision Pais consultado, con el Sector Padre y Nivel indicado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de Vision Pais Consultado";
                    msgExeptions.map.put("data", _programaVisionPaisRepository.getVisionPaisByIdNivelProgramaAndprogramaPadreId(_tblNivelPrograma, _tblProgramaVisionPais));
                    msgExeptions.map.put("totalRecords", _programaVisionPaisRepository.countVisionPaisIdNivelProgramaAndprogramaPadreId(_tblNivelPrograma, _tblProgramaVisionPais));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Vision Pais consultado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato del Nivel de Sector Gobierno consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getVisionPaisByIdNivelProgramaAndSectorPadreId
}
