/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle;

import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadFinanciamientoDet;
import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadFinanciamientoDetGasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActividadFinanciamientoDetGastoRepository extends JpaRepository<TblActividadFinanciamientoDetGasto, Integer> {

    /**
     * Metodo que despliega la Moneda de la BD
     *
     * @param idActividadFinancGasto
     * @return Detalle del Financiamiento Gasto de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 10/07/2019/v1.0
     */
    TblActividadFinanciamientoDetGasto findByIdActividadFinancDetGasto(long idActividadFinancGasto);

    /**
     * Metodo que despliega las Detalle del Financiamiento Gasto de la BD
     *
     * @param idActividadFinancGasto
     * @return Detalle del Financiamiento Gasto de la BD, por paramtro de Id
     * @autor Nahum Martinez | NAM
     * @version 10/07/2019/v1.0
     */
    long countByIdActividadFinancDetGasto(long idActividadFinancGasto);


    /**
     * Metodo que despliega el Detalle del Financiamiento Gasto de la BD
     *
     * @param codigoFinancGasto
     * @return Detalle del Financiamiento Gasto de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 10/07/2019/v1.0
     */
    TblActividadFinanciamientoDetGasto findByCodigoFinancGasto(String codigoFinancGasto);

    /**
     * Metodo que despliega las Detalle del Financiamiento Gasto de la BD
     *
     * @param codigoFinancGasto
     * @return Detalle del Financiamiento Gasto de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 10/07/2019/v1.0
     */
    long countByCodigoFinancGasto(String codigoFinancGasto);

    /**
     * Metodo que despliega los Detalle del Financiamiento Gasto de la BD
     *
     * @param tblActividadFinanciamientoDet
     * @return Detalle de Financiamiento del Proyecto de la BD, por parametro de Código
     * @autor Nahum Martinez | NAM
     * @version 10/07/2019/v1.0
     */
    @Query("SELECT a.idActividadFinancDetGasto, a.codigoFinancGasto, " +
            "a.montoGasto, a.fechaTransaccion, " +
            "b.idActividadFinancDet, " +
            "c.descTipoTransaccion, c.idTipoTransaccion, " +
            "d.nombreMoneda, d.idMonedaActividad " +
            "FROM TblActividadFinanciamientoDetGasto as a," +
            "     TblActividadFinanciamientoDet as b, " +
            "     TblActividadTipoTransaccion as c, " +
            "     TblMonedaActividad as d " +
            "WHERE a.idActividadFinancDet = b.idActividadFinancDet " +
            "  AND a.idTipoTransaccion = c.idTipoTransaccion " +
            "  AND a.idMonedaActividad = d.idMonedaActividad " +
            "  AND a.idActividadFinancDet = :idActividadFinancDet " +
            "ORDER BY a.idActividadFinancDetGasto")
    List<TblActividadFinanciamientoDetGasto> getByIdFinancDet(@Param("idActividadFinancDet") TblActividadFinanciamientoDet tblActividadFinanciamientoDet);


    /**
     * Metodo que despliega las Detalle del Financiamiento Gasto de la BD
     *
     * @param tblActividadFinanciamientoDet
     * @return Detalle del Financiamiento Gasto de la BD, por paramtro de Id
     * @autor Nahum Martinez | NAM
     * @version 10/07/2019/v1.0
     */
    long countByIdActividadFinancDet(TblActividadFinanciamientoDet tblActividadFinanciamientoDet);


    /**
     * Metodo que Borra el Sector de la BD con el Id como parametro
     * en la Tabla Detalle del Financiamiento Gasto Actividades
     *
     * @param codigoFinancGasto
     * @return Detalle del Financiamiento Gasto de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 10/07/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividadFinanciamientoDetGasto e WHERE e.codigoFinancGasto = :codigoFinancGasto")
    void deleletedCodigoFinancDetGasto(@Param("codigoFinancGasto") String codigoFinancGasto);

}
