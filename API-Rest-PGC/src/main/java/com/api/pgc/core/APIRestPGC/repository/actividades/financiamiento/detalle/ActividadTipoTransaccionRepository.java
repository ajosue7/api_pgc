/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle;

import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadTipoTransaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadTipoTransaccionRepository extends JpaRepository<TblActividadTipoTransaccion, Integer> {

    /**
     * Metodo que despliega la Moneda de la BD
     *
     * @param idTipoTransaccion
     * @return Detalle del Tipo Transaccion de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 04/05/2019/v1.0
     */
    TblActividadTipoTransaccion findByIdTipoTransaccion(long idTipoTransaccion);

}
