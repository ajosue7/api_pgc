/*
 * Copyright (c) 2019.  Nahum Martinez
 */

/**
 * @author nahum.martinez
 * @date 22/03/2019
 * @name SectoresOcdeCadResources
 */

package com.api.pgc.core.APIRestPGC.resourses.sectores;


import com.api.pgc.core.APIRestPGC.models.sectores.TblNivelSector;
import com.api.pgc.core.APIRestPGC.models.sectores.TblSectorCampoTransversal;
import com.api.pgc.core.APIRestPGC.models.sectores.TblSectorGobierno;
import com.api.pgc.core.APIRestPGC.repository.sectores.NivelSectorRepository;
import com.api.pgc.core.APIRestPGC.repository.sectores.SectorCampoTransversalRepository;
import com.api.pgc.core.APIRestPGC.repository.sectores.SectorGobiernoRepository;
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
@Api(value = "SectoresCampoTransversalApi", description = "Operaciones sobre el Modulo de Campos Transversales", tags = "Sectores")
public class SectoresCamposTransversalesResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    SectorCampoTransversalRepository _sectorCampoTransversalRepository;

    @Autowired
    TipoSectorRepository _tipoSectorRepository;

    @Autowired
    NivelSectorRepository _nivelSectorRepository;


    /**
     * Metodo que despliega la Lista de todos los Campos Transversales de la BD
     *
     * @return Lista de las Campos Transversales de la BD
     * @autor Nahum Martinez | NAM
     * @version 16/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Campos Transversales de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_CAMPOS_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllSectoresCamposTransversales() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Campos Transversales registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            if ( _sectorCampoTransversalRepository.findAll().isEmpty() || _sectorCampoTransversalRepository.findAll() == null ) {
                msgMethod = "No Existen, Campos Transversales resgitrados en la Base de Datos, Contacta al Administrador";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("data", _sectorCampoTransversalRepository.findAll( new Sort(Sort.Direction.DESC, "<idSector>" )));
                msgExeptions.map.put("totalRecors", _sectorCampoTransversalRepository.count());
            } else {
                msgExeptions.map.put("data", _sectorCampoTransversalRepository.findAll());
                msgExeptions.map.put("totalRecors", _sectorCampoTransversalRepository.count());
            }
            // Retorno del JSON
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getAllSectoresCamposTransversales


    /**
     * Metodo que despliega el Campo Transversal de la BD
     *
     * @param idSector Identificador del Campo Transversal a Buscar
     * @return Campos Transversales de la BD
     * @autor Nahum Martinez | NAM
     * @version 16/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Campo Transversal enviado a buscar de la BD, con el ID como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_CAMPOS_ENDPOINT_FIND_BY_ID_SECTOR, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getCampoTransversalById(@ApiParam(value = "Identificador del Campo Transversal a Buscar", required = true)
                                                       @PathVariable("idSector") long idSector) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if ( _sectorCampoTransversalRepository.findByIdSector(idSector) == null) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Campo Transversal consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de Campo Transversal Consultado";
                msgExeptions.map.put("data", _sectorCampoTransversalRepository.findByIdSector(idSector));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato de Campo Transversal consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getCampoTransversalById



    /**
     * Metodo que despliega el Campo Transversal de la BD
     *
     * @param idNivelSector Identificador del Nivel de Campo Transversal a Buscar
     * @return Campo Transversal de la BD
     * @autor Nahum Martinez | NAM
     * @version 16/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Campo Transversal enviado a buscar de la BD, con el ID de Nivel como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_CAMPOS_ENDPOINT_FIND_BY_ID_NIVEL_SECTOR, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getCampoTransversalByIdNivelSector(@ApiParam(value = "Identificador de Nivel del Campo Transversal a Buscar", required = true)
                                                        @PathVariable("idNivelSector") long idNivelSector) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Buscamos el Nivel Indicado por el Usuario
        try {
            TblNivelSector tblNivelSector = _nivelSectorRepository.findByIdNivelSector(idNivelSector);

            try {
                if (_sectorCampoTransversalRepository.countSectorGobByIdNivelSector(tblNivelSector) == 0 ) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado datos de Campo Transversal consultado, con el Nivel indicado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de Campo Transversal Consultado";
                    msgExeptions.map.put("data", _sectorCampoTransversalRepository.getSectorGobByIdNivelSector(tblNivelSector));
                    msgExeptions.map.put("totalRecords", _sectorCampoTransversalRepository.countSectorGobByIdNivelSector(tblNivelSector));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Campo Transversal consultado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato del Nivel de Campo Transversal consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getCampoTransversalByIdNivelSector


    /**
     * Metodo que despliega el Campo Transversal de la BD
     *
     * @param idNivelSector Identificador del Nivel de Campo Transversal a Buscar
     * @param sectorPadreId Identificador del Campo Transversal Padre a Buscar
     * @return Campo Transversal de la BD
     * @autor Nahum Martinez | NAM
     * @version 16/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Campo Transversal enviado a buscar de la BD, con el ID de Nivel y Sector Padre como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_CAMPOS_ENDPOINT_FIND_BY_ID_TIPO_NIVEL_PADRE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getCampoTransversalByIdNivelSectorAndSectorPadreId(@ApiParam(value = "Identificador de Nivel del Sector y Sector Padre Campo Transversal a Buscar", required = true)
                                                                   @PathVariable("idNivelSector") long idNivelSector,  @PathVariable("sectorPadreId") long sectorPadreId) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Buscamos el Nivel Indicado y el Sector Padre
        try {
            TblNivelSector _tblNivelSector = _nivelSectorRepository.findByIdNivelSector(idNivelSector);
            TblSectorCampoTransversal _tblSectorCampoTransversal = _sectorCampoTransversalRepository.findByIdSector(sectorPadreId);

            try {
                if (_sectorCampoTransversalRepository.countSectorCTByIdNivelSectorAndSectorPadreId(_tblNivelSector, _tblSectorCampoTransversal) == 0 ) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado datos de Campo Transversal consultado, con el Sector Padre y Nivel indicado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de Campo Transversal Consultado";
                    msgExeptions.map.put("data", _sectorCampoTransversalRepository.getSectorCTByIdNivelSectorAndSectorPadreId(_tblNivelSector, _tblSectorCampoTransversal));
                    msgExeptions.map.put("totalRecords", _sectorCampoTransversalRepository.countSectorCTByIdNivelSectorAndSectorPadreId(_tblNivelSector, _tblSectorCampoTransversal));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Campo Transversal consultado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato del Nivel de Sector Gobierno consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | getCampoTransversalByIdNivelSectorAndSectorPadreId
}
