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
import com.api.pgc.core.APIRestPGC.models.programas.TblProgramaPoliticaPublica;
import com.api.pgc.core.APIRestPGC.models.programas.TblTipoPrograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramaPoliticaPublicaRepository extends JpaRepository<TblProgramaPoliticaPublica, Integer> {
    /**
     * Metodo que despliega el Politica Publica de la BD
     *
     * @param idPrograma
     * @return Politica Publica de la BD, por parametro de ID
     * @autor Nahum Martinez | NAM
     * @version 25/04/2019/v1.0
     */
    TblProgramaPoliticaPublica findByIdPrograma(long idPrograma);


    /**
     * Metodo que despliega el Politica Publica de la BD
     *
     * @param codPrograma
     * @return Politica Publica de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 25/04/2019/v1.0
     */
    TblProgramaPoliticaPublica findByCodigoPrograma(String codPrograma);


    /**
     * Metodo que despliega el Politica Publica de la BD con el Tipo de Programa como parametro
     *
     * @param tblTipoPrograma
     * @return Politica Publica de la BD, por parametro de ID Tipo
     * @autor Nahum Martinez | NAM
     * @version 25/04/2019/v1.0
     */
    @Query("SELECT e FROM TblProgramaPoliticaPublica e WHERE e.idTipoPrograma = :idTipoPrograma ")
    List<TblProgramaPoliticaPublica> getPoliticaPublicaByIdTipoPrograma(@Param("idTipoPrograma") TblTipoPrograma tblTipoPrograma);


    /**
     * Metodo que despliega el Politica Publica de la BD con el Nivel de Programa como parametro
     *
     * @param tblNivelPrograma
     * @return Politica Publica de la BD, por parametro de ID Nivel de Programa
     * @autor Nahum Martinez | NAM
     * @version 25/04/2019/v1.0
     */
    @Query("SELECT e FROM TblProgramaPoliticaPublica e WHERE e.idNivelPrograma = :idNivelPrograma Order By e.idNivelPrograma")
    List<TblProgramaPoliticaPublica> getPoliticaPublicaByIdNivelPrograma(@Param("idNivelPrograma") TblNivelPrograma tblNivelPrograma);


    /**
     * Metodo que despliega el Politica Publica de la BD con el Nivel de Programa como parametro
     *
     * @param tblNivelPrograma
     * @return Politica Publica de la BD, por parametro de ID Nivel de Programa
     * @autor Nahum Martinez | NAM
     * @version 25/04/2019/v1.0
     */
    @Query("SELECT COUNT(*) FROM TblProgramaPoliticaPublica e WHERE e.idNivelPrograma = :idNivelPrograma")
    long countPoliticaPublicaByIdNivelPrograma(@Param("idNivelPrograma") TblNivelPrograma tblNivelPrograma);


    /**
     * Metodo que despliega el Politica Publica de la BD con el Nivel de Programa y Sector Padre como parametro
     *
     * @param tblNivelPrograma           Nivel del Sector
     * @param TblProgramaPoliticaPublica Sector Padre
     * @return Politica Publica de la BD, por parametro de ID Nivel de Programa y Sector Padre Id
     * @autor Nahum Martinez | NAM
     * @version 25/04/2019/v1.0
     */
    @Query("SELECT e FROM TblProgramaPoliticaPublica e WHERE e.idNivelPrograma = :idNivelPrograma AND e.programaPadreId = :programaPadreId")
    List<TblProgramaPoliticaPublica> getPoliticaPublicaByIdNivelProgramaAndprogramaPadreId(@Param("idNivelPrograma") TblNivelPrograma tblNivelPrograma,
                                                                                           @Param("programaPadreId") TblProgramaPoliticaPublica TblProgramaPoliticaPublica);


    /**
     * Metodo que despliega el Politica Publica de la BD con el Nivel de Programa como parametro
     *
     * @param tblNivelPrograma
     * @param TblProgramaPoliticaPublica Sector Padre
     * @return Politica Publica de la BD, por parametro de ID Nivel de Programa
     * @autor Nahum Martinez | NAM
     * @version 25/04/2019/v1.0
     */
    @Query("SELECT COUNT(*) FROM TblProgramaPoliticaPublica e WHERE e.idNivelPrograma = :idNivelPrograma AND e.programaPadreId = :programaPadreId")
    long countPoliticaPublicaIdNivelProgramaAndprogramaPadreId(@Param("idNivelPrograma") TblNivelPrograma tblNivelPrograma,
                                                               @Param("programaPadreId") TblProgramaPoliticaPublica TblProgramaPoliticaPublica);
}
