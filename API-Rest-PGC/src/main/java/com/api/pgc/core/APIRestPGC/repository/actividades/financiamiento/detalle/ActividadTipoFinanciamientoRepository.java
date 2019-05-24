/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle;

import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadTipoFinanciamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadTipoFinanciamientoRepository extends JpaRepository<TblActividadTipoFinanciamiento, Integer> {

    /**
     * Metodo que despliega la Moneda de la BD
     *
     * @param idTipoFinanciamiento
     * @return Detalle del Tipo Financiamiento de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 23/05/2019/v1.0
     */
    TblActividadTipoFinanciamiento findByIdTipoFinanciamiento(long idTipoFinanciamiento);

}
