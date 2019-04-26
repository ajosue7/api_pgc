/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades.programas;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.programas.TblActividadProgramaVisionPais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActividadProgramaVisionPaisRepository extends JpaRepository<TblActividadProgramaVisionPais, Integer> {
    /**
     * Metodo que despliega Vision de Pais de la BD
     *
     * @param codigoActividad
     * @return Vision de Pais Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    TblActividadProgramaVisionPais findByCodigoActividad(String codigoActividad);

    /**
     * Metodo que despliega las Vision de Pais de la BD
     *
     * @param codigoActividad
     * @return Vision de Pais de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    long countByCodigoActividad(String codigoActividad);


    /**
     * Metodo que despliega Programas de Vision de Pais de la BD
     *
     * @param tblActividad
     * @return Programas de Vision de Pais del Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    @Query("SELECT e FROM TblActividadProgramaVisionPais e WHERE e.idActividad = :idActividad")
    List<TblActividadProgramaVisionPais> getCodigoActividadPrograma(@Param("idActividad") TblActividad tblActividad);

    /**
     * Metodo que despliega las Vision de Pais de la BD
     *
     * @param tblActividad
     * @return Vision de Pais de la BD, por paramtro de Id Programa Vision de Pais
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    long countByIdActividad(TblActividad tblActividad);


    /**
     * Metodo que Borra el Programa de la BD con el Id como parametro
     * en la Tabla Vision de Pais Actividades
     *
     * @param codigoActividad
     * @return Vision de Pais de la BD, por paramtro de ID Tipo
     * @autor Nahum Martinez | NAM
     * @version 24/04/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividadProgramaVisionPais e WHERE e.codigoActividad = :codigoActividad")
    void deleletedCodigoActividad(@Param("codigoActividad") String codigoActividad);
}
