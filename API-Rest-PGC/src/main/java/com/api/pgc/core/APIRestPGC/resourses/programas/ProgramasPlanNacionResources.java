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
import com.api.pgc.core.APIRestPGC.models.programas.TblProgramaPlanNacion;
import com.api.pgc.core.APIRestPGC.repository.programas.NivelProgramaRepository;
import com.api.pgc.core.APIRestPGC.repository.programas.ProgramaPlanNacionRepository;
import com.api.pgc.core.APIRestPGC.repository.sectores.TipoSectorRepository;
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
@Api(value = "ProgramasPlanNacionApi", description = "Operaciones sobre el Modulo de Plan de Nacion", tags = "Programas")
public class ProgramasPlanNacionResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ProgramaPlanNacionRepository _programaPlanNacionRepository;

    @Autowired
    TipoSectorRepository _tipoSectorRepository;

    @Autowired
    NivelProgramaRepository _nivelProgramaRepository;


    /**
     * Metodo que despliega la Lista de todos los Plan Nacion de la BD
     *
     * @return Lista de las Plan Nacion de la BD
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Plan Nacion de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_PLAN_NACION_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllPlanNacion() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Plan Nacion registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            if (_programaPlanNacionRepository.findAll().isEmpty() || _programaPlanNacionRepository.findAll() == null) {
                msgMethod = "No Existen, Plan Nacion resgitrados en la Base de Datos, Contacta al Administrador";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("data", _programaPlanNacionRepository.findAll(new Sort(Sort.Direction.DESC, "<idPrograma>")));
                msgExeptions.map.put("totalRecors", _programaPlanNacionRepository.count());
            } else {
                msgExeptions.map.put("data", _programaPlanNacionRepository.findAll());
                msgExeptions.map.put("totalRecors", _programaPlanNacionRepository.count());
            }
            // Retorno del JSON
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getAllPlanNacion


    /**
     * Metodo que despliega el Plan de Nacion de la BD
     *
     * @param idPrograma Identificador del Plan de Nacion a Buscar
     * @return Plan Nacion de la BD
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Plan de Nacion enviado a buscar de la BD, con el ID como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_PLAN_NACION_ENDPOINT_FIND_BY_ID_PROGRAMA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getPlanNacionById(@ApiParam(value = "Identificador del Plan de Nacion a Buscar", required = true)
                                                     @PathVariable("idPrograma") long idPrograma) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_programaPlanNacionRepository.findByIdPrograma(idPrograma) == null) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Plan de Nacion consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de Plan de Nacion Consultado";
                msgExeptions.map.put("data", _programaPlanNacionRepository.findByIdPrograma(idPrograma));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato de Plan de Nacion consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getPlanNacionById


    /**
     * Metodo que despliega el Plan de Nacion de la BD
     *
     * @param idNivelPrograma Identificador del Nivel de Plan de Nacion a Buscar
     * @return Plan de Nacion de la BD
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Plan de Nacion enviado a buscar de la BD, con el ID de Nivel como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_PLAN_NACION_ENDPOINT_FIND_BY_ID_NIVEL_PROGRAMA, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getPlanNacionByidNivelPrograma(@ApiParam(value = "Identificador de Nivel del Plan de Nacion a Buscar", required = true)
                                                                  @PathVariable("idNivelPrograma") long idNivelPrograma) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Buscamos el Nivel Indicado por el Usuario
        try {
            TblNivelPrograma _tblNivelPrograma = _nivelProgramaRepository.findByIdNivelPrograma(idNivelPrograma);

            try {
                if (_programaPlanNacionRepository.countPlanNacionByIdNivelPrograma(_tblNivelPrograma) == 0) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado datos de Plan de Nacion consultado, con el Nivel indicado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de Plan de Nacion Consultado";
                    msgExeptions.map.put("data", _programaPlanNacionRepository.getPlanNacionByIdNivelPrograma(_tblNivelPrograma));
                    msgExeptions.map.put("totalRecords", _programaPlanNacionRepository.countPlanNacionByIdNivelPrograma(_tblNivelPrograma));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Plan de Nacion consultado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato del Nivel de Plan de Nacion consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getPlanNacionByidNivelPrograma


    /**
     * Metodo que despliega el Plan de Nacion de la BD
     *
     * @param idNivelPrograma Identificador del Nivel de Plan de Nacion a Buscar
     * @param sectorPadreId   Identificador del Plan de Nacion Padre a Buscar
     * @return Plan de Nacion de la BD
     * @autor Nahum Martinez | NAM
     * @version 16/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Plan de Nacion enviado a buscar de la BD, con el ID de Nivel y Sector Padre como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = PROGRAMAS_PLAN_NACION_ENDPOINT_FIND_BY_ID_TIPO_NIVEL_PADRE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getPlanNacionByidNivelProgramaAndSectorPadreId(@ApiParam(value = "Identificador de Nivel del Programa y Programa Padre Plan de Nacion a Buscar", required = true)
                                                                                  @PathVariable("idNivelPrograma") long idNivelPrograma, @PathVariable("sectorPadreId") long sectorPadreId) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Buscamos el Nivel Indicado y el Sector Padre
        try {
            TblNivelPrograma _tblNivelPrograma = _nivelProgramaRepository.findByIdNivelPrograma(idNivelPrograma);
            TblProgramaPlanNacion _tblProgramaPlanNacion = _programaPlanNacionRepository.findByIdPrograma(sectorPadreId);

            try {
                if (_programaPlanNacionRepository.countPlanNacionIdNivelProgramaAndprogramaPadreId(_tblNivelPrograma, _tblProgramaPlanNacion) == 0) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado datos de Plan de Nacion consultado, con el Sector Padre y Nivel indicado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de Plan de Nacion Consultado";
                    msgExeptions.map.put("data", _programaPlanNacionRepository.getPlanNacionByIdNivelProgramaAndprogramaPadreId(_tblNivelPrograma, _tblProgramaPlanNacion));
                    msgExeptions.map.put("totalRecords", _programaPlanNacionRepository.countPlanNacionIdNivelProgramaAndprogramaPadreId(_tblNivelPrograma, _tblProgramaPlanNacion));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Plan de Nacion consultado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato del Nivel de Sector Gobierno consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getPlanNacionByidNivelProgramaAndSectorPadreId
}
