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
import com.api.pgc.core.APIRestPGC.models.sectores.TblSectorOds;
import com.api.pgc.core.APIRestPGC.models.sectores.TblTipoSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectorOdsRepository extends JpaRepository<TblSectorOds, Integer> {
    /**
     * Metodo que despliega el Sector ODS de la BD
     *
     * @param idSector
     * @return Sector ODS de la BD, por parametro de ID
     * @autor Nahum Martinez | NAM
     * @version 19/04/2019/v1.0
     */
    TblSectorOds findByIdSector(long idSector);


    /**
     * Metodo que despliega el Sector ODS de la BD
     *
     * @param codSector
     * @return Sector ODS de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 19/04/2019/v1.0
     */
    TblSectorOds findByCodigoSector(String codSector);


    /**
     * Metodo que despliega el Sector ODS de la BD con el Tipo de Sector como parametro
     *
     * @param tblTipoSector
     * @return SECTOR ODS de la BD, por parametro de ID Tipo
     * @autor Nahum Martinez | NAM
     * @version 19/04/2019/v1.0
     */
    @Query("SELECT e FROM TblSectorOds e WHERE e.idTipoSector = :idTipoSector ")
    List<TblSectorOds> getSectorODSByIdTipoSector(@Param("idTipoSector") TblTipoSector tblTipoSector);


    /**
     * Metodo que despliega el Sector ODS de la BD con el Nivel de Sector como parametro
     *
     * @param tblNivelSector
     * @return SECTOR ODS de la BD, por parametro de ID Nivel de Sector
     * @autor Nahum Martinez | NAM
     * @version 19/04/2019/v1.0
     */
    @Query("SELECT e FROM TblSectorOds e WHERE e.idNivelSector = :idNivelSector Order By e.idSector")
    List<TblSectorOds> getSectorODSByIdNivelSector(@Param("idNivelSector") TblNivelSector tblNivelSector);


    /**
     * Metodo que despliega el Sector ODS de la BD con el Nivel de Sector como parametro
     *
     * @param tblNivelSector
     * @return SECTOR ODS de la BD, por parametro de ID Nivel de Sector
     * @autor Nahum Martinez | NAM
     * @version 19/04/2019/v1.0
     */
    @Query("SELECT COUNT(*) FROM TblSectorOds e WHERE e.idNivelSector = :idNivelSector")
    long countSectorODSByIdNivelSector(@Param("idNivelSector") TblNivelSector tblNivelSector);


    /**
     * Metodo que despliega el Sector ODS de la BD con el Nivel de Sector y Sector Padre como parametro
     *
     * @param tblNivelSector Nivel del Sector
     * @param TblSectorOds   Sector Padre
     * @return SECTOR ODS de la BD, por parametro de ID Nivel de Sector y Sector Padre Id
     * @autor Nahum Martinez | NAM
     * @version 19/04/2019/v1.0
     */
    @Query("SELECT e.idSector as idSector, e.nombreSector as nombreSector FROM TblSectorOds e WHERE e.idNivelSector = :idNivelSector AND e.sectorPadreId = :sectorPadreId")
    List<TblSectorOds> getSectorODSByIdNivelSectorAndSectorPadreId(@Param("idNivelSector") TblNivelSector tblNivelSector,
                                                                  @Param("sectorPadreId") TblSectorOds TblSectorOds);


    /**
     * Metodo que despliega el Sector ODS de la BD con el Nivel de Sector como parametro
     *
     * @param tblNivelSector
     * @param TblSectorOds   Sector Padre
     * @return SECTOR ODS de la BD, por parametro de ID Nivel de Sector
     * @autor Nahum Martinez | NAM
     * @version 19/04/2019/v1.0
     */
    @Query("SELECT COUNT(*) FROM TblSectorOds e WHERE e.idNivelSector = :idNivelSector AND e.sectorPadreId = :sectorPadreId")
    long countSectorODSByIdNivelSectorAndSectorPadreId(@Param("idNivelSector") TblNivelSector tblNivelSector,
                                                      @Param("sectorPadreId") TblSectorOds TblSectorOds);

}
