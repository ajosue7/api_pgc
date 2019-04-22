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
import com.api.pgc.core.APIRestPGC.models.programas.TblProgramaPlanNacion;
import com.api.pgc.core.APIRestPGC.models.programas.TblTipoPrograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramaPlanNacionRepository extends JpaRepository<TblProgramaPlanNacion, Integer> {
    /**
     * Metodo que despliega el Plan Nacion de la BD
     *
     * @param idPrograma
     * @return Plan Nacion de la BD, por parametro de ID
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    TblProgramaPlanNacion findByIdPrograma(long idPrograma);


    /**
     * Metodo que despliega el Plan Nacion de la BD
     *
     * @param codPrograma
     * @return Plan Nacion de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    TblProgramaPlanNacion findByCodigoPrograma(String codPrograma);


    /**
     * Metodo que despliega el Plan Nacion de la BD con el Tipo de Sector como parametro
     *
     * @param tblTipoPrograma
     * @return Plan Nacion de la BD, por parametro de ID Tipo
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @Query("SELECT e FROM TblProgramaPlanNacion e WHERE e.idTipoPrograma = :idTipoPrograma ")
    List<TblProgramaPlanNacion> getPlanNacionByIdTipoPrograma(@Param("idTipoPrograma") TblTipoPrograma tblTipoPrograma);


    /**
     * Metodo que despliega el Plan Nacion de la BD con el Nivel de Programa como parametro
     *
     * @param tblNivelPrograma
     * @return Plan Nacion de la BD, por parametro de ID Nivel de Programa
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @Query("SELECT e FROM TblProgramaPlanNacion e WHERE e.idNivelPrograma = :idNivelPrograma Order By e.idNivelPrograma")
    List<TblProgramaPlanNacion> getPlanNacionByIdNivelPrograma(@Param("idNivelPrograma") TblNivelPrograma tblNivelPrograma);


    /**
     * Metodo que despliega el Plan Nacion de la BD con el Nivel de Programa como parametro
     *
     * @param tblNivelPrograma
     * @return Plan Nacion de la BD, por parametro de ID Nivel de Programa
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @Query("SELECT COUNT(*) FROM TblProgramaPlanNacion e WHERE e.idNivelPrograma = :idNivelPrograma")
    long countPlanNacionByIdNivelPrograma(@Param("idNivelPrograma") TblNivelPrograma tblNivelPrograma);


    /**
     * Metodo que despliega el Plan Nacion de la BD con el Nivel de Programa y Sector Padre como parametro
     *
     * @param tblNivelPrograma      Nivel del Sector
     * @param tblProgramaPlanNacion Sector Padre
     * @return Plan Nacion de la BD, por parametro de ID Nivel de Programa y Sector Padre Id
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @Query("SELECT e FROM TblProgramaPlanNacion e WHERE e.idNivelPrograma = :idNivelPrograma AND e.programaPadreId = :programaPadreId")
    List<TblProgramaPlanNacion> getPlanNacionByIdNivelProgramaAndprogramaPadreId(@Param("idNivelPrograma") TblNivelPrograma tblNivelPrograma,
                                                                               @Param("programaPadreId") TblProgramaPlanNacion tblProgramaPlanNacion);


    /**
     * Metodo que despliega el Plan Nacion de la BD con el Nivel de Programa como parametro
     *
     * @param tblNivelPrograma
     * @param tblProgramaPlanNacion Sector Padre
     * @return Plan Nacion de la BD, por parametro de ID Nivel de Programa
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @Query("SELECT COUNT(*) FROM TblProgramaPlanNacion e WHERE e.idNivelPrograma = :idNivelPrograma AND e.programaPadreId = :programaPadreId")
    long countPlanNacionIdNivelProgramaAndprogramaPadreId(@Param("idNivelPrograma") TblNivelPrograma tblNivelPrograma,
                                                        @Param("programaPadreId") TblProgramaPlanNacion tblProgramaPlanNacion);
}
