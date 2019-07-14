/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle;

import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadFinanciamientoDet;
import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadFinanciamientoDetDesembolso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActividadFinanciamientoDetDesembolsoRepository extends JpaRepository<TblActividadFinanciamientoDetDesembolso, Integer> {

    /**
     * Metodo que despliega la Moneda de la BD
     *
     * @param idActividadFinancDesembolso
     * @return Detalle del Financiamiento Desembolso de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 09/07/2019/v1.0
     */
    TblActividadFinanciamientoDetDesembolso findByIdActividadFinancDetDesembolso(long idActividadFinancDesembolso);

    /**
     * Metodo que despliega las Detalle del Financiamiento Desembolso de la BD
     *
     * @param idActividadFinancDesembolso
     * @return Detalle del Financiamiento Desembolso de la BD, por paramtro de Id
     * @autor Nahum Martinez | NAM
     * @version 09/07/2019/v1.0
     */
    long countByIdActividadFinancDetDesembolso(long idActividadFinancDesembolso);


    /**
     * Metodo que despliega el Detalle del Financiamiento Desembolso de la BD
     *
     * @param codigoFinancDesembolso
     * @return Detalle del Financiamiento Desembolso de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 09/07/2019/v1.0
     */
    TblActividadFinanciamientoDetDesembolso findByCodigoFinancDesembolso(String codigoFinancDesembolso);

    /**
     * Metodo que despliega las Detalle del Financiamiento Desembolso de la BD
     *
     * @param codigoFinancDesembolso
     * @return Detalle del Financiamiento Desembolso de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 09/07/2019/v1.0
     */
    long countByCodigoFinancDesembolso(String codigoFinancDesembolso);

    /**
     * Metodo que despliega los Detalle del Financiamiento Desembolso de la BD
     *
     * @param tblActividadFinanciamientoDet
     * @return Detalle de Financiamiento del Proyecto de la BD, por parametro de Código
     * @autor Nahum Martinez | NAM
     * @version 09/07/2019/v1.0
     */
    @Query("SELECT a.idActividadFinancDetDesembolso, a.codigoFinancDesembolso, " +
            "a.montoDesembolso, a.fechaTransaccion, " +
            "b.idActividadFinancDet, " +
            "c.descTipoTransaccion, c.idTipoTransaccion, " +
            "d.nombreMoneda, d.idMonedaActividad " +
            "FROM TblActividadFinanciamientoDetDesembolso as a," +
            "     TblActividadFinanciamientoDet as b, " +
            "     TblActividadTipoTransaccion as c, " +
            "     TblMonedaActividad as d " +
            "WHERE a.idActividadFinancDet = b.idActividadFinancDet " +
            "  AND a.idTipoTransaccion = c.idTipoTransaccion " +
            "  AND a.idMonedaActividad = d.idMonedaActividad " +
            "  AND a.idActividadFinancDet = :idActividadFinancDet " +
            "ORDER BY a.idActividadFinancDetDesembolso")
    List<TblActividadFinanciamientoDetDesembolso> getByIdFinancDet(@Param("idActividadFinancDet") TblActividadFinanciamientoDet tblActividadFinanciamientoDet);


    /**
     * Metodo que despliega las Detalle del Financiamiento Desembolso de la BD
     *
     * @param tblActividadFinanciamientoDet
     * @return Detalle del Financiamiento Desembolso de la BD, por paramtro de Id
     * @autor Nahum Martinez | NAM
     * @version 09/07/2019/v1.0
     */
    long countByIdActividadFinancDet(TblActividadFinanciamientoDet tblActividadFinanciamientoDet);


    /**
     * Metodo que Borra el Sector de la BD con el Id como parametro
     * en la Tabla Detalle del Financiamiento Desembolso Actividades
     *
     * @param codigoFinancDesembolso
     * @return Detalle del Financiamiento Desembolso de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 09/07/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividadFinanciamientoDetDesembolso e WHERE e.codigoFinancDesembolso = :codigoFinancDesembolso")
    void deleletedCodigoFinancDetDesembolso(@Param("codigoFinancDesembolso") String codigoFinancDesembolso);

}
