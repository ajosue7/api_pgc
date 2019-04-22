/*
 * Copyright (c) 2019.  Nahum Martinez
 */

/**
 * @author nahum.martinez
 * @date 22/03/2019
 * @name SectorOcdeCadRepository
 */

package com.api.pgc.core.APIRestPGC.repository.sectores;

import com.api.pgc.core.APIRestPGC.models.sectores.TblNivelSector;
import com.api.pgc.core.APIRestPGC.models.sectores.TblSectorCampoTransversal;
import com.api.pgc.core.APIRestPGC.models.sectores.TblSectorGobierno;
import com.api.pgc.core.APIRestPGC.models.sectores.TblTipoSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectorCampoTransversalRepository extends JpaRepository<TblSectorCampoTransversal, Integer> {
    /**
     * Metodo que despliega el Sector Gobierno de la BD
     *
     * @param idSector
     * @return Campo Transversal de la BD, por parametro de ID
     * @autor Nahum Martinez | NAM
     * @version 16/04/2019/v1.0
     */
    TblSectorCampoTransversal findByIdSector(long idSector);


    /**
     * Metodo que despliega el Sector Gobierno de la BD
     *
     * @param codSector
     * @return Campo Transversal de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 115/04/2019/v1.0
     */
    TblSectorCampoTransversal findByCodigoSector(String codSector);


    /**
     * Metodo que despliega el Sector Gobierno de la BD con el Tipo de Sector como parametro
     *
     * @param tblTipoSector
     * @return Campo Transversal de la BD, por parametro de ID Tipo
     * @autor Nahum Martinez | NAM
     * @version 16/04/2019/v1.0
     */
    @Query("SELECT e FROM TblSectorCampoTransversal e WHERE e.idTipoSector = :idTipoSector ")
    List<TblSectorCampoTransversal> getSectorGobByIdTipoSector(@Param("idTipoSector") TblTipoSector tblTipoSector);


    /**
     * Metodo que despliega el Sector Gobierno de la BD con el Nivel de Sector como parametro
     *
     * @param tblNivelSector
     * @return Campo Transversal de la BD, por parametro de ID Nivel de Sector
     * @autor Nahum Martinez | NAM
     * @version 16/04/2019/v1.0
     */
    @Query("SELECT e FROM TblSectorCampoTransversal e WHERE e.idNivelSector = :idNivelSector Order By e.idSector")
    List<TblSectorCampoTransversal> getSectorGobByIdNivelSector(@Param("idNivelSector") TblNivelSector tblNivelSector);


    /**
     * Metodo que despliega el Sector Gobierno de la BD con el Nivel de Sector como parametro
     *
     * @param tblNivelSector
     * @return Campo Transversal de la BD, por parametro de ID Nivel de Sector
     * @autor Nahum Martinez | NAM
     * @version 16/04/2019/v1.0
     */
    @Query("SELECT COUNT(*) FROM TblSectorCampoTransversal e WHERE e.idNivelSector = :idNivelSector")
    long countSectorGobByIdNivelSector(@Param("idNivelSector") TblNivelSector tblNivelSector);


    /**
     * Metodo que despliega el Sector Gobierno de la BD con el Nivel de Sector y Sector Padre como parametro
     *
     * @param tblNivelSector    Nivel del Sector
     * @param tblSectorCampoTransversal Sector Padre
     * @return Campo Transversal de la BD, por parametro de ID Nivel de Sector y Sector Padre Id
     * @autor Nahum Martinez | NAM
     * @version 16/04/2019/v1.0
     */
    @Query("SELECT e FROM TblSectorCampoTransversal e WHERE e.idNivelSector = :idNivelSector AND e.sectorPadreId = :sectorPadreId")
    List<TblSectorCampoTransversal> getSectorCTByIdNivelSectorAndSectorPadreId(@Param("idNivelSector") TblNivelSector tblNivelSector,
                                                                                @Param("sectorPadreId") TblSectorCampoTransversal tblSectorCampoTransversal);


    /**
     * Metodo que despliega el Sector Gobierno de la BD con el Nivel de Sector como parametro
     *
     * @param tblNivelSector
     * @param tblSectorCampoTransversal Sector Padre
     * @return Campo Transversal de la BD, por parametro de ID Nivel de Sector
     * @autor Nahum Martinez | NAM
     * @version 16/04/2019/v1.0
     */
    @Query("SELECT COUNT(*) FROM TblSectorCampoTransversal e WHERE e.idNivelSector = :idNivelSector AND e.sectorPadreId = :sectorPadreId")
    long countSectorCTByIdNivelSectorAndSectorPadreId(@Param("idNivelSector") TblNivelSector tblNivelSector,
                                                       @Param("sectorPadreId") TblSectorCampoTransversal tblSectorCampoTransversal);
}
