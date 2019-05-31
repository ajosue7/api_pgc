/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades.organizaciones;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.organizaciones.TblActividadOrganizacionUnidadEjecutora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActividadOrganizacionUnidadEjecutoraRepository extends JpaRepository<TblActividadOrganizacionUnidadEjecutora, Integer> {
    /**
     * Metodo que despliega los Unidad Ejecutora de la BD
     *
     * @param codigoActividad
     * @return Unidad Ejecutora Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 02/05/2019/v1.0
     */
    TblActividadOrganizacionUnidadEjecutora findByCodigoActividad(String codigoActividad);
    /**
     * Metodo que despliega los Unidad Ejecutora de la BD
     *
     * @param idActividadUnidadEjecutora
     * @return Unidad Ejecutora Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 02/05/2019/v1.0
     */
    TblActividadOrganizacionUnidadEjecutora findByIdActividadUnidadEjecutora(long idActividadUnidadEjecutora );
    /**
     * Metodo que despliega los Unidad Ejecutora de la BD
     *
     * @param idActividadUnidadEjecutora
     * @return Unidad Ejecutora Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 02/05/2019/v1.0
     */
    long  countByIdActividadUnidadEjecutora(long idActividadUnidadEjecutora );

    /**
     * Metodo que despliega las Unidad Ejecutora de la BD
     *
     * @param codigoActividad
     * @return Unidad Ejecutora de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 02/05/2019/v1.0
     */
    long countByCodigoActividad(String codigoActividad);




    /**
     * Metodo que despliega los Unidad Ejecutora de la BD
     *
     * @param codigoActividad
     * @return Unidad Ejecutora Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 02/05/2019/v1.0
     */
    TblActividadOrganizacionUnidadEjecutora findByCodigoActividad(long  codigoActividad);


    /**
     * Metodo que despliega los Unidad Ejecutora de la BD
     *
     * @param tblActividad
     * @return Unidad Ejecutora del Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 02/05/2019/v1.0
     */
    @Query("SELECT e FROM TblActividadOrganizacionUnidadEjecutora e WHERE e.idActividad = :idActividad")
    List<TblActividadOrganizacionUnidadEjecutora> getCodigoActividadOrganizacion(@Param("idActividad") TblActividad tblActividad);

    /**
     * Metodo que despliega las Unidad Ejecutora de la BD
     *
     * @param tblActividad
     * @return Unidad Ejecutora de la BD, por paramtro de Id Programa Plan de Nacion
     * @autor Nahum Martinez | NAM
     * @version 02/05/2019/v1.0
     */
    long countByIdActividad(TblActividad tblActividad);


    /**
     * Metodo que Borra el Sector de la BD con el Id como parametro
     * en la Tabla Unidad Ejecutora Actividades
     *
     * @param codigoActividad
     * @return Unidad Ejecutora de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 02/05/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividadOrganizacionUnidadEjecutora e WHERE e.codigoActividad = :codigoActividad")
    void deleletedCodigoActividad(@Param("codigoActividad") String codigoActividad);
}
