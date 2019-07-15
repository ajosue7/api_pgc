/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades.sectores;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.sectores.TblActividadSectorOcde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActividadSectorOcdeRepository extends JpaRepository<TblActividadSectorOcde, Integer> {
    /**
     * Metodo que despliega los Recursos de la BD
     *
     * @param codigoActividad
     * @return Sectores Ocde del Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 13/04/2019/v1.0
     */
    TblActividadSectorOcde findByCodigoActividad(String codigoActividad);

    /**
     * Metodo que despliega las Id Internas de la BD
     *
     * @param codigoActividad
     * @return Sectores Ocde de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 09/01/2019/v1.0
     */
    long countByCodigoActividad(String codigoActividad);


    /**
     * Metodo que despliega los Recursos de la BD
     *
     * @param tblActividad
     * @return Sectores Ocde del Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 13/04/2019/v1.0
     */
    @Query("SELECT e FROM TblActividadSectorOcde e WHERE e.idActividad = :idActividad")
    List<TblActividadSectorOcde> getCodigoActividadSector(@Param("idActividad") TblActividad tblActividad);

    /**
     * Metodo que despliega las Id Internas de la BD
     *
     * @param tblActividad
     * @return Sectores Ocde de la BD, por paramtro de Id de la Actividad
     * @autor Nahum Martinez | NAM
     * @version 13/04/2019/v1.0
     */
    long countByIdActividad(TblActividad tblActividad);


    /**
     * Metodo que Borra el Sector de la BD con el Id como parametro
     * en la Tabla Sectores Ocde
     *
     * @param codigoActividad
     * @return Sector Ocde/Cad de la BD, por paramtro de ID Tipo
     * @autor Nahum Martinez | NAM
     * @version 13/04/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividadSectorOcde e WHERE e.codigoActividad = :codigoActividad")
    void deleletedCodigoActividad(@Param("codigoActividad") String codigoActividad);


    /**
     * Metodo que despliega los Sectores OCDE/CAD de la BD
     *
     * @return SECTORES OCDE/CAD de la BD
     * @autor Nahum Martinez | NAM
     * @version 02/07/2019/v1.0
     */
    @Query("SELECT se.idActividadSectorOcde, se.codigoActividad," +
            "se.idSectorOcde, se.idActividad, se.porcentajePart," +
            "se.activo, se.fechaCreacion, se.horaCreacion " +
            "FROM TblActividadSectorOcde se ")
    List<TblActividadSectorOcde> getAllActividadesSectoresOcde2();
}
