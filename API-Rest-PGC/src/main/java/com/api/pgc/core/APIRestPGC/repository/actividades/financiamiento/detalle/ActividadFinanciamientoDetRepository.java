/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadFinanciamientoDet;
import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.encabezado.TblActividadFinanciamientoEnc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActividadFinanciamientoDetRepository extends JpaRepository<TblActividadFinanciamientoDet, Integer> {

    /**
     * Metodo que despliega la Moneda de la BD
     *
     * @param idActividadFinancDet
     * @return Detalle del Financiamiento de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.0
     */
    TblActividadFinanciamientoDet findByIdActividadFinancDet(long idActividadFinancDet);

    /**
     * Metodo que despliega las Financiamiento Detalle de la BD
     *
     * @param idActividadFinancDet
     * @return Financiamiento Detalle de la BD, por paramtro de Id Programa Plan de Nacion
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.0
     */
    long countByIdActividadFinancDet(long idActividadFinancDet);


    /**
     * Metodo que despliega el Detalle del Financiamiento de la BD
     *
     * @param codigoFinancDet
     * @return Detalle del Financiamiento de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.0
     */
    TblActividadFinanciamientoDet findByCodigoFinancDet(String codigoFinancDet);

    /**
     * Metodo que despliega las Financiamiento Detalle de la BD
     *
     * @param codigoFinancDet
     * @return Financiamiento Detalle de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.0
     */
    long countByCodigoFinancDet(String codigoFinancDet);


    /**
     * Metodo que despliega los Financiamiento Detalle de la BD
     *
     * @param tblActividadFinanciamientoEnc
     * @return Detalle de Financiamiento del Proyecto de la BD, por parametro de Código
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.0
     */
    @Query("SELECT e FROM TblActividadFinanciamientoDet e WHERE e.idActividadFinancEnc = :idActividadFinancEnc")
    List<TblActividadFinanciamientoDet> getByIdFinancEnc(@Param("idActividadFinancEnc") TblActividadFinanciamientoEnc tblActividadFinanciamientoEnc);


    /**
     * Metodo que despliega las Financiamiento Detalle de la BD
     *
     * @param tblActividadFinanciamientoEnc
     * @return Financiamiento Detalle de la BD, por paramtro de Id Programa Plan de Nacion
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.0
     */
    long countByIdActividadFinancEnc(TblActividadFinanciamientoEnc tblActividadFinanciamientoEnc);


    /**
     * Metodo que Borra el Sector de la BD con el Id como parametro
     * en la Tabla Financiamiento Detalle Actividades
     *
     * @param codigoFinancDet
     * @return Financiamiento Detalle de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividadFinanciamientoDet e WHERE e.codigoFinancDet = :codigoFinancDet")
    void deleletedCodigoFinancDet(@Param("codigoFinancDet") String codigoFinancDet);

}
