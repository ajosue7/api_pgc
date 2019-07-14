/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle;

import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle.TblActividadModalidadAyuda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadModalidadAyudaRepository extends JpaRepository<TblActividadModalidadAyuda, Integer> {
    /**
     * Metodo que despliega la Moneda de la BD
     *
     * @param idModalidadAyuda
     * @return Detalle del Modalidad de Ayuda de la BD, por parametro de ID
     * @autor Nahum Martinez | NAM
     * @version 23/05/2019/v1.0
     */
    TblActividadModalidadAyuda findByIdModalidadAyuda(long idModalidadAyuda);


    /**
     * Metodo que despliega la Moneda de la BD
     *
     * @param idModalidadAyuda
     * @return Detalle del Modalidad de Ayuda de la BD, por parametro de ID
     * @autor Nahum Martinez | NAM
     * @version 23/05/2019/v1.0
     */
    long countByIdModalidadAyuda(long idModalidadAyuda);

}
