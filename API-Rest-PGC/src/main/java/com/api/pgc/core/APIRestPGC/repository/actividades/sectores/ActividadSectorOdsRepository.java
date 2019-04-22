/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades.sectores;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.sectores.TblActividadSectorOds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActividadSectorOdsRepository extends JpaRepository<TblActividadSectorOds, Integer> {
    /**
     * Metodo que despliega los Recursos de la BD
     *
     * @param codigoActividad
     * @return Sectores ODS del Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 19/04/2019/v1.0
     */
    TblActividadSectorOds findByCodigoActividad(String codigoActividad);

    /**
     * Metodo que despliega las IODS de la BD
     *
     * @param codigoActividad
     * @return Sectores ODS de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 09/01/2019/v1.0
     */
    long countByCodigoActividad(String codigoActividad);


    /**
     * Metodo que despliega los Recursos de la BD
     *
     * @param tblActividad
     * @return Sectores ODS del Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 13/04/2019/v1.0
     */
    @Query("SELECT e FROM TblActividadSectorOds e WHERE e.idActividad = :idActividad")
    List<TblActividadSectorOds> getCodigoActividadSector(@Param("idActividad") TblActividad tblActividad);

    /**
     * Metodo que despliega las ODS de la BD
     *
     * @param tblActividad
     * @return Sectores ODS de la BD, por paramtro de Id de la Actividad
     * @autor Nahum Martinez | NAM
     * @version 19/04/2019/v1.0
     */
    long countByIdActividad(TblActividad tblActividad);


    /**
     * Metodo que Borra el Sector de la BD con el Id como parametro
     * en la Tabla Sectores ODS
     *
     * @param codigoActividad
     * @return Sector ODS/Cad de la BD, por paramtro de ID Tipo
     * @autor Nahum Martinez | NAM
     * @version 19/04/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividadSectorOds e WHERE e.codigoActividad = :codigoActividad")
    void deleletedCodigoActividad(@Param("codigoActividad") String codigoActividad);
}
