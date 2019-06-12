/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle;

import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadFinanciamientoDet;
import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadFinanciamientoDetCompromiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActividadFinanciamientoDetCompromisoRepository extends JpaRepository<TblActividadFinanciamientoDetCompromiso, Integer> {

    /**
     * Metodo que despliega la Moneda de la BD
     *
     * @param idActividadFinancCompromiso
     * @return Detalle del Financiamiento Compromiso de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 04/06/2019/v1.0
     */
    TblActividadFinanciamientoDetCompromiso findByidActividadFinancDetCompromiso(long idActividadFinancCompromiso);

    /**
     * Metodo que despliega las Detalle del Financiamiento Compromiso de la BD
     *
     * @param idActividadFinancCompromiso
     * @return Detalle del Financiamiento Compromiso de la BD, por paramtro de Id
     * @autor Nahum Martinez | NAM
     * @version 04/06/2019/v1.0
     */
    long countByidActividadFinancDetCompromiso(long idActividadFinancCompromiso);


    /**
     * Metodo que despliega el Detalle del Financiamiento Compromiso de la BD
     *
     * @param codigoFinancCompromiso
     * @return Detalle del Financiamiento Compromiso de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 04/06/2019/v1.0
     */
    TblActividadFinanciamientoDetCompromiso findByCodigoFinancCompromiso(String codigoFinancCompromiso);

    /**
     * Metodo que despliega las Detalle del Financiamiento Compromiso de la BD
     *
     * @param codigoFinancCompromiso
     * @return Detalle del Financiamiento Compromiso de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 04/06/2019/v1.0
     */
    long countByCodigoFinancCompromiso(String codigoFinancCompromiso);

    /**
     * Metodo que despliega los Detalle del Financiamiento Compromiso de la BD
     *
     * @param tblActividadFinanciamientoDet
     * @return Detalle de Financiamiento del Proyecto de la BD, por parametro de Código
     * @autor Nahum Martinez | NAM
     * @version 04/06/2019/v1.0
     */
    // @Query("SELECT e FROM TblActividadFinanciamientoDetCompromiso e WHERE e.idActividadFinancDet = :idActividadFinancDet")
    @Query("SELECT a.idActividadFinancDetCompromiso, a.codigoFinancCompromiso, " +
            "a.montoCompromiso, a.fechaTransaccion, " +
            "b.idActividadFinancDet, " +
            "c.descTipoTransaccion, c.idTipoTransaccion, " +
            "d.nombreMoneda, d.idMonedaActividad " +
            "FROM TblActividadFinanciamientoDetCompromiso as a," +
            "     TblActividadFinanciamientoDet as b, " +
            "     TblActividadTipoTransaccion as c, " +
            "     TblMonedaActividad as d " +
            "WHERE a.idActividadFinancDet = b.idActividadFinancDet " +
            "  AND a.idTipoTransaccion = c.idTipoTransaccion " +
            "  AND a.idMonedaActividad = d.idMonedaActividad " +
            "  AND a.idActividadFinancDet = :idActividadFinancDet")
    List<TblActividadFinanciamientoDetCompromiso> getByIdFinancDet(@Param("idActividadFinancDet") TblActividadFinanciamientoDet tblActividadFinanciamientoDet);



    /**
     * Metodo que despliega las Detalle del Financiamiento Compromiso de la BD
     *
     * @param tblActividadFinanciamientoDet
     * @return Detalle del Financiamiento Compromiso de la BD, por paramtro de Id
     * @autor Nahum Martinez | NAM
     * @version 04/06/2019/v1.0
     */
    long countByIdActividadFinancDet(TblActividadFinanciamientoDet tblActividadFinanciamientoDet);


    /**
     * Metodo que Borra el Sector de la BD con el Id como parametro
     * en la Tabla Detalle del Financiamiento Compromiso Actividades
     *
     * @param codigoFinancCompromiso
     * @return Detalle del Financiamiento Compromiso de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 04/06/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividadFinanciamientoDetCompromiso e WHERE e.codigoFinancCompromiso = :codigoFinancCompromiso")
    void deleletedCodigoFinancDetCompromiso(@Param("codigoFinancCompromiso") String codigoFinancCompromiso);

}
