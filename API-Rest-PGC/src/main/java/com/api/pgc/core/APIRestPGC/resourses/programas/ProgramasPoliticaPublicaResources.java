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
import com.api.pgc.core.APIRestPGC.models.programas.TblProgramaPoliticaPublica;
import com.api.pgc.core.APIRestPGC.repository.programas.NivelProgramaRepository;
import com.api.pgc.core.APIRestPGC.repository.programas.ProgramaPoliticaPublicaRepository;
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
@Api(value = "ProgramasPoliticaPublicaApi", description = "Operaciones sobre el Modulo de Politica Publicas", tags = "Programas")
public class ProgramasPoliticaPublicaResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ProgramaPoliticaPublicaRepository _politicaPublicaRepository;

    @Autowired
    NivelProgramaRepository _nivelProgramaRepository;


    /**
     * Metodo que despliega la Lista de todos Programas de Politicas Publicas de la BD
     *
     * @return Lista de Politicas Publicas de la BD
     * @autor Nahum Martinez | NAM
     * @version 25/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos Programas de Politicas Publicas de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_POLITICA_PUBLICA_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllPoliticaPublica() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos Programas de Politicas Publicas registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            if (_politicaPublicaRepository.findAll().isEmpty() || _politicaPublicaRepository.findAll() == null) {
                msgMethod = "No Existen, Politicas Publicas resgitrados en la Base de Datos, Contacta al Administrador";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("data", _politicaPublicaRepository.findAll(new Sort(Sort.Direction.DESC, "<idPrograma>")));
                msgExeptions.map.put("totalRecors", _politicaPublicaRepository.count());
            } else {
                msgExeptions.map.put("data", _politicaPublicaRepository.findAll());
                msgExeptions.map.put("totalRecors", _politicaPublicaRepository.count());
            }
            // Retorno del JSON
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getAllPoliticaPublica


    /**
     * Metodo que despliega el Politicas Publicas de la BD
     *
     * @param idPrograma Identificador del Politicas Publicas a Buscar
     * @return Politicas Publicas de la BD
     * @autor Nahum Martinez | NAM
     * @version 25/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Politicas Publicas enviado a buscar de la BD, con el ID como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_POLITICA_PUBLICA_ENDPOINT_FIND_BY_ID_PROGRAMA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getPoliticaPublicaById(@ApiParam(value = "Identificador del Politicas Publicas a Buscar", required = true)
                                                          @PathVariable("idPrograma") long idPrograma) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_politicaPublicaRepository.findByIdPrograma(idPrograma) == null) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Politicas Publicas consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de Politicas Publicas Consultado";
                msgExeptions.map.put("data", _politicaPublicaRepository.findByIdPrograma(idPrograma));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato de Politicas Publicas consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getPoliticaPublicaById


    /**
     * Metodo que despliega el Politicas Publicas de la BD
     *
     * @param idNivelPrograma Identificador del Nivel de Politicas Publicas a Buscar
     * @return Politicas Publicas de la BD
     * @autor Nahum Martinez | NAM
     * @version 25/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Politicas Publicas enviado a buscar de la BD, con el ID de Nivel como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_POLITICA_PUBLICA_ENDPOINT_FIND_BY_ID_NIVEL_PROGRAMA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getPoliticaPublicaByIdNivelPrograma(@ApiParam(value = "Identificador de Nivel del Politicas Publicas a Buscar", required = true)
                                                                       @PathVariable("idNivelPrograma") long idNivelPrograma) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Buscamos el Nivel Indicado por el Usuario
        try {
            TblNivelPrograma _tblNivelPrograma = _nivelProgramaRepository.findByIdNivelPrograma(idNivelPrograma);

            try {
                if (_politicaPublicaRepository.countPoliticaPublicaByIdNivelPrograma(_tblNivelPrograma) == 0) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado datos de Politicas Publicas consultado, con el Nivel indicado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de Politicas Publicas Consultado";
                    msgExeptions.map.put("data", _politicaPublicaRepository.getPoliticaPublicaByIdNivelPrograma(_tblNivelPrograma));
                    msgExeptions.map.put("totalRecords", _politicaPublicaRepository.countPoliticaPublicaByIdNivelPrograma(_tblNivelPrograma));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Politicas Publicas consultado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato del Nivel de Politicas Publicas consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getPoliticaPublicaByIdNivelPrograma


    /**
     * Metodo que despliega el Politicas Publicas de la BD
     *
     * @param idNivelPrograma Identificador del Nivel de Politicas Publicas a Buscar
     * @param sectorPadreId   Identificador del Politicas Publicas Padre a Buscar
     * @return Politicas Publicas de la BD
     * @autor Nahum Martinez | NAM
     * @version 16/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Politicas Publicas enviado a buscar de la BD, con el ID de Nivel y Sector Padre como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_POLITICA_PUBLICA_ENDPOINT_FIND_BY_ID_TIPO_NIVEL_PADRE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getPoliticaPublicaByIdNivelProgramaAndSectorPadreId(@ApiParam(value = "Identificador de Nivel del Programa y Programa Padre Politicas Publicas a Buscar", required = true)
                                                                                       @PathVariable("idNivelPrograma") long idNivelPrograma, @PathVariable("sectorPadreId") long sectorPadreId) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Buscamos el Nivel Indicado y el Sector Padre
        try {
            TblNivelPrograma _tblNivelPrograma = _nivelProgramaRepository.findByIdNivelPrograma(idNivelPrograma);
            TblProgramaPoliticaPublica _tblProgramaPoliticaPublica = _politicaPublicaRepository.findByIdPrograma(sectorPadreId);

            try {
                if (_politicaPublicaRepository.countPoliticaPublicaIdNivelProgramaAndprogramaPadreId(_tblNivelPrograma, _tblProgramaPoliticaPublica) == 0) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado datos de Politicas Publicas consultado, con el Sector Padre y Nivel indicado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de Politicas Publicas Consultado";
                    msgExeptions.map.put("data", _politicaPublicaRepository.getPoliticaPublicaByIdNivelProgramaAndprogramaPadreId(_tblNivelPrograma, _tblProgramaPoliticaPublica));
                    msgExeptions.map.put("totalRecords", _politicaPublicaRepository.countPoliticaPublicaIdNivelProgramaAndprogramaPadreId(_tblNivelPrograma, _tblProgramaPoliticaPublica));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Politicas Publicas consultado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato del Nivel de Sector Gobierno consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getPoliticaPublicaByIdNivelProgramaAndSectorPadreId
}
