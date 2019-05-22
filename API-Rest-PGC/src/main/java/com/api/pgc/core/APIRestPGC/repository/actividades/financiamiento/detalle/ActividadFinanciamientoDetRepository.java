/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle;

import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadFinanciamientoDet;
import org.springframework.data.jpa.repository.JpaRepository;

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

}
