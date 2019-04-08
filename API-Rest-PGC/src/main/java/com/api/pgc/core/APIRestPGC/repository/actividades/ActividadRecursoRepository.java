/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.TblActividadRecurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ActividadRecursoRepository extends JpaRepository<TblActividadRecurso, Integer> {
    /**
     * Metodo que despliega los Documentos del Proyecto de la BD
     * @autor Nahum Martinez | NAM
     * @version  06/04/2019/v1.0
     * @return Recursos de la BD, por paramtro de Codigo
     * @param codActividadRecurso
     */
    TblActividadRecurso findByCodActividadRecurso(String codActividadRecurso);

    /**
     * Metodo que despliega los Documentos del Proyecto de la BD
     *
     * @param codActividadRecurso
     * @return Recursos del Proyecto de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 06/04/2019/v1.0
     */
    long countByCodActividadRecurso(String codActividadRecurso);

    /**
     * Metodo que despliega las Id Internas de la BD
     *
     * @param tblActividad
     * @return Documentos del Proyecto de la BD, por paramtro de Id Actividad
     * @autor Nahum Martinez | NAM
     * @version 06/04/2019/v1.0
     */
    long countByIdActividad(TblActividad tblActividad);


    /**
     * Metodo que Borra el Documento de la BD con el Id como parametro
     * en la Tabla Actividad Documentos
     * @param codActividadRecurso
     * @return Recurso Actividad de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 06/04/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividadRecurso e WHERE e.codActividadRecurso = :codActividadRecurso")
    void  deleletedCodActividadRecurso(@Param("codActividadRecurso") String codActividadRecurso);
}
