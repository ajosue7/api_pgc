/*
 * Copyright (c) 2019.  Nahum Martinez 
 */

/**
 * @author nahum.martinez
 * @date 22/03/2019
 * @name SectorOcdeCadRepository
 */

package com.api.pgc.core.APIRestPGC.repository.programas;

import com.api.pgc.core.APIRestPGC.models.programas.TblNivelPrograma;
import com.api.pgc.core.APIRestPGC.models.programas.TblProgramaVisionPais;
import com.api.pgc.core.APIRestPGC.models.programas.TblProgramaVisionPais;
import com.api.pgc.core.APIRestPGC.models.programas.TblTipoPrograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramaVisionPaisRepository extends JpaRepository<TblProgramaVisionPais, Integer> {
    /**
     * Metodo que despliega el Vision de Pais de la BD
     *
     * @param idPrograma
     * @return Vision de Pais de la BD, por parametro de ID
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    TblProgramaVisionPais findByIdPrograma(long idPrograma);


    /**
     * Metodo que despliega el Vision de Pais de la BD
     *
     * @param codPrograma
     * @return Vision de Pais de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    TblProgramaVisionPais findByCodigoPrograma(String codPrograma);


    /**
     * Metodo que despliega el Vision de Pais de la BD con el Tipo de Programa como parametro
     *
     * @param tblTipoPrograma
     * @return Vision de Pais de la BD, por parametro de ID Tipo
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    @Query("SELECT e FROM TblProgramaVisionPais e WHERE e.idTipoPrograma = :idTipoPrograma ")
    List<TblProgramaVisionPais> getVisionPaisByIdTipoPrograma(@Param("idTipoPrograma") TblTipoPrograma tblTipoPrograma);


    /**
     * Metodo que despliega el Vision de Pais de la BD con el Nivel de Programa como parametro
     *
     * @param tblNivelPrograma
     * @return Vision de Pais de la BD, por parametro de ID Nivel de Programa
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    @Query("SELECT e FROM TblProgramaVisionPais e WHERE e.idNivelPrograma = :idNivelPrograma Order By e.idNivelPrograma")
    List<TblProgramaVisionPais> getVisionPaisByIdNivelPrograma(@Param("idNivelPrograma") TblNivelPrograma tblNivelPrograma);


    /**
     * Metodo que despliega el Vision de Pais de la BD con el Nivel de Programa como parametro
     *
     * @param tblNivelPrograma
     * @return Vision de Pais de la BD, por parametro de ID Nivel de Programa
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    @Query("SELECT COUNT(*) FROM TblProgramaVisionPais e WHERE e.idNivelPrograma = :idNivelPrograma")
    long countVisionPaisByIdNivelPrograma(@Param("idNivelPrograma") TblNivelPrograma tblNivelPrograma);


    /**
     * Metodo que despliega el Vision de Pais de la BD con el Nivel de Programa y Sector Padre como parametro
     *
     * @param tblNivelPrograma     Nivel del Sector
     * @param TblProgramaVisionPais Sector Padre
     * @return Vision de Pais de la BD, por parametro de ID Nivel de Programa y Sector Padre Id
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    @Query("SELECT e FROM TblProgramaVisionPais e WHERE e.idNivelPrograma = :idNivelPrograma AND e.programaPadreId = :programaPadreId")
    List<TblProgramaVisionPais> getVisionPaisByIdNivelProgramaAndprogramaPadreId(@Param("idNivelPrograma") TblNivelPrograma tblNivelPrograma,
                                                                               @Param("programaPadreId") TblProgramaVisionPais TblProgramaVisionPais);


    /**
     * Metodo que despliega el Vision de Pais de la BD con el Nivel de Programa como parametro
     *
     * @param tblNivelPrograma
     * @param TblProgramaVisionPais Sector Padre
     * @return Vision de Pais de la BD, por parametro de ID Nivel de Programa
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    @Query("SELECT COUNT(*) FROM TblProgramaVisionPais e WHERE e.idNivelPrograma = :idNivelPrograma AND e.programaPadreId = :programaPadreId")
    long countVisionPaisIdNivelProgramaAndprogramaPadreId(@Param("idNivelPrograma") TblNivelPrograma tblNivelPrograma,
                                                         @Param("programaPadreId") TblProgramaVisionPais TblProgramaVisionPais);
}
