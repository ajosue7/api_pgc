/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades.organizaciones;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadFinanciamientoDet;
import com.api.pgc.core.APIRestPGC.models.actividades.organizaciones.TblActividadOrganizacionSocioDesarrollo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActividadOrganizacionSocioDesarrolloRepository extends JpaRepository<TblActividadOrganizacionSocioDesarrollo, Integer> {
    /**
     * Metodo que despliega los Socio al Desarrollon de la BD
     *
     * @param codigoActividad
     * @return Socio al Desarrollo Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 02/05/2019/v1.0
     */
    TblActividadOrganizacionSocioDesarrollo findByCodigoActividad(String codigoActividad);

    /**
     * Metodo que despliega las Socio al Desarrollo de la BD
     *
     * @param codigoActividad
     * @return Socio al Desarrollo de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 02/05/2019/v1.0
     */
    long countByCodigoActividad(String codigoActividad);

    /**
     * Metodo que despliega los Socio al Desarrollo de la BD
     *
     * @param tblActividad
     * @return Campos Transversales del Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    @Query("SELECT e FROM TblActividadOrganizacionSocioDesarrollo e WHERE e.idActividad = :idActividad")
    List<TblActividadOrganizacionSocioDesarrollo> getCodigoActividadOrganizacion(@Param("idActividad") TblActividad tblActividad);

    /**
     * Metodo que despliega las Socio al Desarrollo de la BD
     *
     * @param tblActividad
     * @return Socio al Desarrollo de la BD, por paramtro de Id Programa Plan de Nacion
     * @autor Nahum Martinez | NAM
     * @version 02/05/2019/v1.0
     */
    long countByIdActividad(TblActividad tblActividad);


    /**
     * Metodo que Borra el Sector de la BD con el Id como parametro
     * en la Tabla Socio al Desarrollo Actividades
     *
     * @param codigoActividad
     * @return Socio al Desarrollo de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 02/05/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividadOrganizacionSocioDesarrollo e WHERE e.codigoActividad = :codigoActividad")
    void deleletedCodigoActividad(@Param("codigoActividad") String codigoActividad);
}
