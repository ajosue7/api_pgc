/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades.sectores;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.sectores.TblActividadSectorGobierno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActividadSectorGobiernoRepository extends JpaRepository<TblActividadSectorGobierno, Integer> {
    /**
     * Metodo que despliega los Sectores de Gobierno de la BD
     *
     * @param codigoActividad
     * @return Sectores Gobierno del Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 15/04/2019/v1.0
     */
    TblActividadSectorGobierno findByCodigoActividad(String codigoActividad);

    /**
     * Metodo que despliega las Sectores de Gobierno de la BD
     *
     * @param codigoActividad
     * @return Sectores Gobierno de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 15/04/2019/v1.0
     */
    long countByCodigoActividad(String codigoActividad);


    /**
     * Metodo que despliega los Sectores Gobierno de la BD
     *
     * @param tblActividad
     * @return Sectores Gobierno del Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 15/04/2019/v1.0
     */
    @Query("SELECT e FROM TblActividadSectorGobierno e WHERE e.idActividad = :idActividad")
    List<TblActividadSectorGobierno> getCodigoActividadSector(@Param("idActividad") TblActividad tblActividad);

    /**
     * Metodo que despliega las Sectores de Gobierno de la BD
     *
     * @param tblActividad
     * @return Sectores Gobierno de la BD, por paramtro de Id Sector de Gobierno
     * @autor Nahum Martinez | NAM
     * @version 15/04/2019/v1.0
     */
    long countByIdActividad(TblActividad tblActividad);


    /**
     * Metodo que Borra el Sector de la BD con el Id como parametro
     * en la Tabla Sectores Gobierno
     *
     * @param codigoActividad
     * @return Sector Gobierno de la BD, por paramtro de ID Tipo
     * @autor Nahum Martinez | NAM
     * @version 15/04/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividadSectorGobierno e WHERE e.codigoActividad = :codigoActividad")
    void deleletedCodigoActividad(@Param("codigoActividad") String codigoActividad);
}
