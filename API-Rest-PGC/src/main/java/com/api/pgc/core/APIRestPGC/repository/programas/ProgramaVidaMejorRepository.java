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
import com.api.pgc.core.APIRestPGC.models.programas.TblProgramaVidaMejor;
import com.api.pgc.core.APIRestPGC.models.programas.TblTipoPrograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramaVidaMejorRepository extends JpaRepository<TblProgramaVidaMejor, Integer> {
    /**
     * Metodo que despliega el Vida Mejor de la BD
     *
     * @param idPrograma
     * @return Vida Mejor de la BD, por parametro de ID
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    TblProgramaVidaMejor findByIdPrograma(long idPrograma);


    /**
     * Metodo que despliega el Vida Mejor de la BD
     *
     * @param codPrograma
     * @return Vida Mejor de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    TblProgramaVidaMejor findByCodigoPrograma(String codPrograma);


    /**
     * Metodo que despliega el Vida Mejor de la BD con el Tipo de Programa como parametro
     *
     * @param tblTipoPrograma
     * @return Vida Mejor de la BD, por parametro de ID Tipo
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @Query("SELECT e FROM TblProgramaVidaMejor e WHERE e.idTipoPrograma = :idTipoPrograma ")
    List<TblProgramaVidaMejor> getVidaMejorByIdTipoPrograma(@Param("idTipoPrograma") TblTipoPrograma tblTipoPrograma);


    /**
     * Metodo que despliega el Vida Mejor de la BD con el Nivel de Programa como parametro
     *
     * @param tblNivelPrograma
     * @return Vida Mejor de la BD, por parametro de ID Nivel de Programa
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @Query("SELECT e FROM TblProgramaVidaMejor e WHERE e.idNivelPrograma = :idNivelPrograma Order By e.idNivelPrograma")
    List<TblProgramaVidaMejor> getVidaMejorByIdNivelPrograma(@Param("idNivelPrograma") TblNivelPrograma tblNivelPrograma);


    /**
     * Metodo que despliega el Vida Mejor de la BD con el Nivel de Programa como parametro
     *
     * @param tblNivelPrograma
     * @return Vida Mejor de la BD, por parametro de ID Nivel de Programa
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @Query("SELECT COUNT(*) FROM TblProgramaVidaMejor e WHERE e.idNivelPrograma = :idNivelPrograma")
    long countVidaMejorByIdNivelPrograma(@Param("idNivelPrograma") TblNivelPrograma tblNivelPrograma);


    /**
     * Metodo que despliega el Vida Mejor de la BD con el Nivel de Programa y Sector Padre como parametro
     *
     * @param tblNivelPrograma     Nivel del Sector
     * @param TblProgramaVidaMejor Sector Padre
     * @return Vida Mejor de la BD, por parametro de ID Nivel de Programa y Sector Padre Id
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @Query("SELECT e FROM TblProgramaVidaMejor e WHERE e.idNivelPrograma = :idNivelPrograma AND e.programaPadreId = :programaPadreId")
    List<TblProgramaVidaMejor> getVidaMejorByIdNivelProgramaAndprogramaPadreId(@Param("idNivelPrograma") TblNivelPrograma tblNivelPrograma,
                                                                               @Param("programaPadreId") TblProgramaVidaMejor TblProgramaVidaMejor);


    /**
     * Metodo que despliega el Vida Mejor de la BD con el Nivel de Programa como parametro
     *
     * @param tblNivelPrograma
     * @param TblProgramaVidaMejor Sector Padre
     * @return Vida Mejor de la BD, por parametro de ID Nivel de Programa
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @Query("SELECT COUNT(*) FROM TblProgramaVidaMejor e WHERE e.idNivelPrograma = :idNivelPrograma AND e.programaPadreId = :programaPadreId")
    long countVidaMejorIdNivelProgramaAndprogramaPadreId(@Param("idNivelPrograma") TblNivelPrograma tblNivelPrograma,
                                                         @Param("programaPadreId") TblProgramaVidaMejor TblProgramaVidaMejor);
}
