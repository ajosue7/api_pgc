/*
 * Copyright (c) 2019.  Nahum Martinez
 */

/**
 * @author nahum.martinez
 * @date 24/04/2019
 * @name SectoresOcdeCadResources
 */

package com.api.pgc.core.APIRestPGC.resourses.sectores;


import com.api.pgc.core.APIRestPGC.models.sectores.TblNivelSector;
import com.api.pgc.core.APIRestPGC.models.sectores.TblSectorOds;
import com.api.pgc.core.APIRestPGC.repository.sectores.NivelSectorRepository;
import com.api.pgc.core.APIRestPGC.repository.sectores.SectorOdsRepository;
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
@Api(value = "SectoresOdsApi", description = "Operaciones sobre el Modulo de Sectores", tags = "Sectores")
public class SectoresOdsResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    SectorOdsRepository _sectorOdsRepository;

    @Autowired
    TipoSectorRepository _tipoSectorRepository;

    @Autowired
    NivelSectorRepository _nivelSectorRepository;


    /**
     * Metodo que despliega la Lista de todos los Sectores ODS de la BD
     *
     * @return Lista de las Sectores ODS de la BD
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Sectores ODS de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_ODS_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllSectoresOds() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Sectores ODS registrados en la BD";

        try {
            //Sobreescirbe el Metodo de Mensajes
            if (_sectorOdsRepository.findAll().isEmpty() || _sectorOdsRepository.findAll() == null) {
                msgMethod = "No Existen, Sectores ODS resgitrados en la Base de Datos, Contacta al Administrador";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("data", _sectorOdsRepository.findAll(new Sort(Sort.Direction.DESC, "<idSector>")));
                msgExeptions.map.put("totalRecors", _sectorOdsRepository.count());
            } else {
                msgExeptions.map.put("data", _sectorOdsRepository.findAll());
                msgExeptions.map.put("totalRecors", _sectorOdsRepository.count());
            }
            // Retorno del JSON
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getAllSectoresOds


    /**
     * Metodo que despliega el Sector ODS de la BD
     *
     * @param idSector Identificador del Sector ODS a Buscar
     * @return Sector ODS de la BD
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Sector ODS enviado a buscar de la BD, con el ID como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_ODS_ENDPOINT_FIND_BY_ID_SECTOR, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSectorOdsById(@ApiParam(value = "Identificador del Sector ODS a Buscar", required = true)
                                                    @PathVariable("idSector") long idSector) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_sectorOdsRepository.findByIdSector(idSector) == null) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Sector ODS consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de Sector ODS Consultado";
                msgExeptions.map.put("data", _sectorOdsRepository.findByIdSector(idSector));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato de Sector ODS consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getSectorOdsById


    /**
     * Metodo que despliega el Sector ODS de la BD
     *
     * @param idNivelSector Identificador del Nivel de Sector ODS a Buscar
     * @return Sector ODS de la BD
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Sector ODS enviado a buscar de la BD, con el ID de Nivel como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_ODS_ENDPOINT_FIND_BY_ID_NIVEL_SECTOR, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSectorOdsByIdNivelSector(@ApiParam(value = "Identificador de Nivel del Sector ODS a Buscar", required = true)
                                                               @PathVariable("idNivelSector") long idNivelSector) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Buscamos el Nivel Indicado por el Usuario
        try {
            TblNivelSector tblNivelSector = _nivelSectorRepository.findByIdNivelSector(idNivelSector);

            try {
                if (_sectorOdsRepository.countSectorODSByIdNivelSector(tblNivelSector) == 0) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado datos de Sector ODS consultado, con el Nivel indicado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de Sector ODS Consultado";
                    msgExeptions.map.put("data", _sectorOdsRepository.getSectorODSByIdNivelSector(tblNivelSector));
                    msgExeptions.map.put("totalRecords", _sectorOdsRepository.countSectorODSByIdNivelSector(tblNivelSector));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Sector ODS consultado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato del Nivel de Sector ODS consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getSectorOdsByIdNivelSector


    /**
     * Metodo que despliega el Sector ODS de la BD
     *
     * @param idNivelSector Identificador del Nivel de Sector ODS a Buscar
     * @param sectorPadreId Identificador del ODS Padre a Buscar
     * @return Sector ODS de la BD
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Sector ODS enviado a buscar de la BD, con el ID de Nivel y Sector Padre como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_ODS_ENDPOINT_FIND_BY_ID_TIPO_NIVEL_PADRE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSectorOdsByIdNivelSectorAndSectorPadreId(@ApiParam(value = "Identificador de Nivel del Sector y Sector Padre ODS a Buscar", required = true)
                                                                               @PathVariable("idNivelSector") long idNivelSector, @PathVariable("sectorPadreId") long sectorPadreId) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Buscamos el Nivel Indicado y el Sector Padre
        try {
            TblNivelSector _tblNivelSector = _nivelSectorRepository.findByIdNivelSector(idNivelSector);
            TblSectorOds _tblSectorOds = _sectorOdsRepository.findByIdSector(sectorPadreId);

            try {
                if (_sectorOdsRepository.countSectorODSByIdNivelSectorAndSectorPadreId(_tblNivelSector, _tblSectorOds) == 0) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado datos de Sector ODS consultado, con el Sector Padre y Nivel indicado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de Sector ODS Consultado";
                    msgExeptions.map.put("data", _sectorOdsRepository.getSectorODSByIdNivelSectorAndSectorPadreId(_tblNivelSector, _tblSectorOds));
                    msgExeptions.map.put("totalRecords", _sectorOdsRepository.countSectorODSByIdNivelSectorAndSectorPadreId(_tblNivelSector, _tblSectorOds));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Sector ODS consultado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato del Nivel de Sector ODS consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getSectorOdsByIdNivelSectorAndSectorPadreId
}
