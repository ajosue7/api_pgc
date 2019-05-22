/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.encabezado;

import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.encabezado.TblActividadFinanciamientoEnc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadFinanciamientoEncRepository extends JpaRepository<TblActividadFinanciamientoEnc, Integer> {

    /**
     * Metodo que despliega la Moneda de la BD
     *
     * @param idActividadFinancEnc
     * @return Encabezado del Financiamiento de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 22/05/2019/v1.0
     */
    TblActividadFinanciamientoEnc findByIdActividadFinancEnc(long idActividadFinancEnc);

}
