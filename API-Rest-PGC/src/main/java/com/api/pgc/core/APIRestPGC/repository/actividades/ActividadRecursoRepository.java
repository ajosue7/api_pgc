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
     * Metodo que despliega los Recursos de la BD
     *
     * @param codActividadRecurso
     * @return Recursos de la BD, por parametro de ID
     * @autor Nahum Martinez | NAM
     * @version 12/04/2019/v1.0
     */
    TblActividadRecurso findByCodigoActividadRecurso(String codActividadRecurso);



    /**
     * Metodo que despliega el Espacio de Trabajo de la BD
     * @autor Nahum Martinez | NAM
     * @version  11/10/2018/v1.0
     * @return Espacios de Trabajo de la BD, por paramtro de ID
     * @param idActividadRecurso
     */
    TblActividadRecurso findByIdActividadRecurso(long idActividadRecurso);

    /**
     * Metodo que despliega las Recursos de la BD
     *
     * @param idActividadRecurso
     * @return Recursos de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 12/04/2019/v1.0
     */
    long countByidActividadRecurso(long idActividadRecurso);

    /**
     * Metodo que despliega las Recursos de la BD
     *
     * @param codigoActividadRecurso
     * @return Recursos de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 12/04/2019/v1.0
     */
    long countByCodigoActividadRecurso(String codigoActividadRecurso);

    /**
     * Metodo que despliega los Recursos de la BD
     *
     * @param tblActividad
     * @return Recursos de la BD, por paramtro de Id
     * @autor Nahum Martinez | NAM
     * @version 12/04/2019/v1.0
     */
    long countByIdActividad(TblActividad tblActividad);


    /**
     * Metodo que Borra el Recurso de la BD con el Cod como parametro
     * en la Tabla Recursos de Actividad
     *
     * @param codigoActividadRecurso
     * @return Recurso de la BD, por paramtro de Codigo a borrar
     * @autor Nahum Martinez | NAM
     * @version 12/04/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividadRecurso e WHERE e.codigoActividadRecurso = :codigoActividadRecurso")
    void deleletedCodigoActividadRecurso(@Param("codigoActividadRecurso") String codigoActividadRecurso);
}
