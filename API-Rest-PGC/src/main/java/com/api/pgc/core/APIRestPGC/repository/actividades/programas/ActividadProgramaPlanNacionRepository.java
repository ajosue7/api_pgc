/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades.programas;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.programas.TblActividadProgramaPlanNacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActividadProgramaPlanNacionRepository extends JpaRepository<TblActividadProgramaPlanNacion, Integer> {
    /**
     * Metodo que despliega los Planes de Nacion de la BD
     *
     * @param codigoActividad
     * @return Plan de Nacion Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    TblActividadProgramaPlanNacion findByCodigoActividad(String codigoActividad);

    /**
     * Metodo que despliega las Plan de Nacion de la BD
     *
     * @param codigoActividad
     * @return Plan de Nacion de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    long countByCodigoActividad(String codigoActividad);


    /**
     * Metodo que despliega los Campos Transversales de la BD
     *
     * @param tblActividad
     * @return Campos Transversales del Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @Query("SELECT e FROM TblActividadProgramaPlanNacion e WHERE e.idActividad = :idActividad")
    List<TblActividadProgramaPlanNacion> getCodigoActividadPrograma(@Param("idActividad") TblActividad tblActividad);

    /**
     * Metodo que despliega las Plan de Nacion de la BD
     *
     * @param tblActividad
     * @return Plan de Nacion de la BD, por paramtro de Id Programa Plan de Nacion
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    long countByIdActividad(TblActividad tblActividad);


    /**
     * Metodo que Borra el Sector de la BD con el Id como parametro
     * en la Tabla Plan de Nacion Actividades
     *
     * @param codigoActividad
     * @return Plan de Nacion de la BD, por paramtro de ID Tipo
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividadProgramaPlanNacion e WHERE e.codigoActividad = :codigoActividad")
    void deleletedCodigoActividad(@Param("codigoActividad") String codigoActividad);
}
