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
import com.api.pgc.core.APIRestPGC.models.sectores.TblSectorGobierno;
import com.api.pgc.core.APIRestPGC.models.sectores.TblSectorOcdeCad;
import com.api.pgc.core.APIRestPGC.repository.sectores.NivelSectorRepository;
import com.api.pgc.core.APIRestPGC.repository.sectores.SectorGobiernoRepository;
import com.api.pgc.core.APIRestPGC.repository.sectores.SectorOcdeCadRepository;
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
@Api(value = "SectoresGobiernoApi", description = "Operaciones sobre el Modulo de Sectores de Gobierno", tags = "Sectores")
public class SectoresGobiernoResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    SectorGobiernoRepository _sectorGobiernoRepository;

    @Autowired
    TipoSectorRepository _tipoSectorRepository;

    @Autowired
    NivelSectorRepository _nivelSectorRepository;


    /**
     * Metodo que despliega la Lista de todos los Sectores Gobierno de la BD
     *
     * @return Lista de las Sectores Gobierno de la BD
     * @autor Nahum Martinez | NAM
     * @version 15/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Sectores Gobierno de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_GOBIERNO_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllSectoresGobierno() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Sectores Gobierno registrados en la BD";

        try {
            // Sobreescirbe el Metodo de Mensajes
            if ( _sectorGobiernoRepository.findAll().isEmpty() || _sectorGobiernoRepository.findAll() == null ) {
                msgMethod = "No Existen, Sectores Gobierno resgitrados en la Base de Datos, Contacta al Administrador";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("data", _sectorGobiernoRepository.findAll( new Sort(Sort.Direction.DESC, "<idSector>" )));
                msgExeptions.map.put("totalRecors", _sectorGobiernoRepository.count());
            } else {
                msgExeptions.map.put("data", _sectorGobiernoRepository.findAll());
                msgExeptions.map.put("totalRecors", _sectorGobiernoRepository.count());
            }
            // Retorno del JSON
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getAllSectoresGobierno


    /**
     * Metodo que despliega el Sector Gobierno de la BD
     *
     * @param idSector Identificador del Sector Gobierno a Buscar
     * @return Sector Gobierno de la BD
     * @autor Nahum Martinez | NAM
     * @version 15/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Sector Gobierno enviado a buscar de la BD, con el ID como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_GOBIERNO_ENDPOINT_FIND_BY_ID_SECTOR, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSectorGobiernoById(@ApiParam(value = "Identificador del Sector Gobierno a Buscar", required = true)
                                                       @PathVariable("idSector") long idSector) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if ( _sectorGobiernoRepository.findByIdSector(idSector) == null) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Sector Gobierno consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de Sector Gobierno Consultado";
                msgExeptions.map.put("data", _sectorGobiernoRepository.findByIdSector(idSector));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato de Sector Gobierno consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getSectorGobiernoById



    /**
     * Metodo que despliega el Sector Gobierno de la BD
     *
     * @param idNivelSector Identificador del Nivel de Sector Gobierno a Buscar
     * @return Sector Gobierno de la BD
     * @autor Nahum Martinez | NAM
     * @version 15/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Sector Gobierno enviado a buscar de la BD, con el ID de Nivel como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_GOBIERNO_ENDPOINT_FIND_BY_ID_NIVEL_SECTOR, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSectorGobiernoByIdNivelSector(@ApiParam(value = "Identificador de Nivel del Sector Gobierno a Buscar", required = true)
                                                        @PathVariable("idNivelSector") long idNivelSector) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Buscamos el Nivel Indicado por el Usuario
        try {
            TblNivelSector tblNivelSector = _nivelSectorRepository.findByIdNivelSector(idNivelSector);

            try {
                if (_sectorGobiernoRepository.countSectorGobByIdNivelSector(tblNivelSector) == 0 ) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado datos de Sector Gobierno consultado, con el Nivel indicado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de Sector Gobierno Consultado";
                    msgExeptions.map.put("data", _sectorGobiernoRepository.getSectorGobByIdNivelSector(tblNivelSector));
                    msgExeptions.map.put("totalRecords", _sectorGobiernoRepository.countSectorGobByIdNivelSector(tblNivelSector));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Sector Gobierno consultado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato del Nivel de Sector Gobierno consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getSectorGobiernoByIdNivelSector


    /**
     * Metodo que despliega el Sector Gobierno de la BD
     *
     * @param idNivelSector Identificador del Nivel de Sector Gobierno a Buscar
     * @param sectorPadreId Identificador del Gobierno Padre a Buscar
     * @return Sector Gobierno de la BD
     * @autor Nahum Martinez | NAM
     * @version 15/04/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Sector Gobierno enviado a buscar de la BD, con el ID de Nivel y Sector Padre como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_GOBIERNO_ENDPOINT_FIND_BY_ID_TIPO_NIVEL_PADRE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSectorGobiernoByIdNivelSectorAndSectorPadreId(@ApiParam(value = "Identificador de Nivel del Sector y Sector Padre Gobierno a Buscar", required = true)
                                                                   @PathVariable("idNivelSector") long idNivelSector,  @PathVariable("sectorPadreId") long sectorPadreId) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Buscamos el Nivel Indicado y el Sector Padre
        try {
            TblNivelSector _tblNivelSector = _nivelSectorRepository.findByIdNivelSector(idNivelSector);
            TblSectorGobierno _tblSectorGobierno = _sectorGobiernoRepository.findByIdSector(sectorPadreId);

            try {
                if (_sectorGobiernoRepository.countSectorGobByIdNivelSectorAndSectorPadreId(_tblNivelSector, _tblSectorGobierno) == 0 ) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado datos de Sector Gobierno consultado, con el Sector Padre y Nivel indicado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de Sector Gobierno Consultado";
                    msgExeptions.map.put("data", _sectorGobiernoRepository.getSectorGobByIdNivelSectorAndSectorPadreId(_tblNivelSector, _tblSectorGobierno));
                    msgExeptions.map.put("totalRecords", _sectorGobiernoRepository.countSectorGobByIdNivelSectorAndSectorPadreId(_tblNivelSector, _tblSectorGobierno));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Sector Gobierno consultado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato del Nivel de Sector Gobierno consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getSectorGobByIdNivelSectorAndSectorPadreId
}
