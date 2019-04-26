/*
 * Copyright (c) 2019.  Nahum Martinez 
 */

package com.api.pgc.core.APIRestPGC.repository.actividades.programas;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.programas.TblActividadProgramaPoliticaPublica;
import com.api.pgc.core.APIRestPGC.models.actividades.programas.TblActividadProgramaPoliticaPublica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActividadProgramaPoliticaPublicaRepository extends JpaRepository<TblActividadProgramaPoliticaPublica, Integer> {
    /**
     * Metodo que despliega Programa Politica Publica de la BD
     *
     * @param codigoActividad
     * @return Programa Politica Publica Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 25/04/2019/v1.0
     */
    TblActividadProgramaPoliticaPublica findByCodigoActividad(String codigoActividad);

    /**
     * Metodo que despliega las Programa Politica Publica de la BD
     *
     * @param codigoActividad
     * @return Programa Politica Publica de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 25/04/2019/v1.0
     */
    long countByCodigoActividad(String codigoActividad);


    /**
     * Metodo que despliega Programas de Programa Politica Publica de la BD
     *
     * @param tblActividad
     * @return Programas de Programa Politica Publica del Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 25/04/2019/v1.0
     */
    @Query("SELECT e FROM TblActividadProgramaPoliticaPublica e WHERE e.idActividad = :idActividad")
    List<TblActividadProgramaPoliticaPublica> getCodigoActividadPrograma(@Param("idActividad") TblActividad tblActividad);

    /**
     * Metodo que despliega las Programa Politica Publica de la BD
     *
     * @param tblActividad
     * @return Programa Politica Publica de la BD, por paramtro de Id Programa Programa Politica Publica
     * @autor Nahum Martinez | NAM
     * @version 25/04/2019/v1.0
     */
    long countByIdActividad(TblActividad tblActividad);


    /**
     * Metodo que Borra el Programa de la BD con el Id como parametro
     * en la Tabla Programa Politica Publica Actividades
     *
     * @param codigoActividad
     * @return Programa Politica Publica de la BD, por paramtro de ID Tipo
     * @autor Nahum Martinez | NAM
     * @version 25/04/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividadProgramaPoliticaPublica e WHERE e.codigoActividad = :codigoActividad")
    void deleletedCodigoActividad(@Param("codigoActividad") String codigoActividad);
}
