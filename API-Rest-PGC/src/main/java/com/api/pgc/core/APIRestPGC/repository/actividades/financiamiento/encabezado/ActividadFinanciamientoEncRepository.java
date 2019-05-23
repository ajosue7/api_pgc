/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.encabezado;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.encabezado.TblActividadFinanciamientoEnc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActividadFinanciamientoEncRepository extends JpaRepository<TblActividadFinanciamientoEnc, Integer> {
    /**
     * Metodo que despliega el Encabezado del Financiamiento de la BD
     *
     * @param idActividadFinancEnc
     * @return Encabezado del Financiamiento de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.0
     */
    TblActividadFinanciamientoEnc findByIdActividadFinancEnc(long idActividadFinancEnc);


    /**
     * Metodo que despliega el Encabezado del Financiamiento de la BD
     *
     * @param codigoActividadFinancEnc
     * @return Encabezado del Financiamiento de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.0
     */
    TblActividadFinanciamientoEnc findByCodigoFinancEnc(String codigoActividadFinancEnc);

    /**
     * Metodo que despliega las Financiamiento Encabezado de la BD
     *
     * @param codigoActividadEnc
     * @return Financiamiento Encabezado de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.0
     */
    long countByCodigoFinancEnc(String codigoActividadEnc);


    /**
     * Metodo que despliega los Financiamiento Encabezado de la BD
     *
     * @param tblActividad
     * @return Encabezado de Financiamiento del Proyecto de la BD, por parametro de Código
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.0
     */
    @Query("SELECT e FROM TblActividadFinanciamientoEnc e WHERE e.idActividad = :idActividad")
    List<TblActividadFinanciamientoEnc> getListFinancEnc(@Param("idActividad") TblActividad tblActividad);

    /**
     * Metodo que despliega las Financiamiento Encabezado de la BD
     *
     * @param tblActividad
     * @return Financiamiento Encabezado de la BD, por paramtro de Id Programa Plan de Nacion
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.0
     */
    long countByIdActividad(TblActividad tblActividad);


    /**
     * Metodo que Borra el Sector de la BD con el Id como parametro
     * en la Tabla Financiamiento Encabezado Actividades
     *
     * @param codigoFinancEnc
     * @return Financiamiento Encabezado de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividadFinanciamientoEnc e WHERE e.codigoFinancEnc = :codigoFinancEnc")
    void deleletedCodigoFinancEnc(@Param("codigoFinancEnc") String codigoFinancEnc);
}
