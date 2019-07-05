/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.TblActividadUbicacion;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblUbicacionImplementacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActividadUbicacionRepository extends JpaRepository<TblActividadUbicacion, Integer> {
    /**
     * Metodo que despliega las Ubicaciones de la BD
     * validando la Actividad y la Ubicacion
     *
     * @param codigoActividad
     * @return Id Interna de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 26/02/2019/v1.0
     */
    long countByCodigoActividad(String codigoActividad);


    /**
     * Metodo que despliega las Ubicaciones de la BD
     *
     * @param codigoActividad
     * @return Ubicacion de la BD, por paramtro de Codigo de la Actividad
     * @autor Nahum Martinez | NAM
     * @version 26/02/2019/v1.0
     */
    TblActividadUbicacion findByCodigoActividad(String codigoActividad);

    /**
     * Metodo que despliega las Ubicaciones de la BD
     *
     * @param tblUbicacionImplementacion ubicacion de Implementacion
     * @param tblActividad               Actividad
     * @return Ubicacion de la BD, por paramtro de Id de Actiivdad y Ubicacion
     * @autor Nahum Martinez | NAM
     * @version 28/02/2019/v1.0
     */
    @Query("SELECT COUNT(*) FROM TblActividadUbicacion e WHERE e.idUbicacionImplementacion = :idUbicacionImpl AND e.idActividad = :idActividad")
    long countByIdUbicacionImplementacionAndIdActividad(@Param("idUbicacionImpl") TblUbicacionImplementacion tblUbicacionImplementacion,
                                                        @Param("idActividad") TblActividad tblActividad);


    /**
     * Metodo que despliega el Listado de las Ubicaciones de la BD
     *
     * @param tblUbicacionImplementacion ubicacion de Implementacion
     * @param tblActividad               Actividad
     * @return Ubicacion de la BD, por paramtro de Id de Activivdad y Ubicacion
     * @autor Nahum Martinez | NAM
     * @version 01/03/2019/v1.0
     */
    @Query("SELECT e FROM TblActividadUbicacion e WHERE e.idUbicacionImplementacion = :idUbicacionImpl AND e.idActividad = :idActividad")
    List<TblActividadUbicacion> findByIdUbicacionImplementacionAndIdActividad(@Param("idUbicacionImpl") TblUbicacionImplementacion tblUbicacionImplementacion,
                                                                              @Param("idActividad") TblActividad tblActividad);


    /**
     * Metodo que despliega las Ubicaciones de la BD
     *
     * @param _tblActividad
     * @return Ubicacion de la BD, por paramtro de Id de la Actividad
     * @autor Nahum Martinez | NAM
     * @version 03/07/2019/v1.0
     */
    @Query("SELECT new map( au.idActividadUbicacion as idActividadUbicacion, au.codigoActividad as codigoActividad, " +
            "au.porcentajeUbicacion as porcentajeUbicacion, " +
            "au.fechaCreacion as fechaCreacion, au.horaCreacion as horaCreacion, " +
            "ui.idUbicacionImplementacion.idUbicacionImplementacion as idUbicacionImplementacion, ui.idUbicacionImplementacion.nombreUbicacionImpl as nombreUbicacionImpl," +
            "ui.latitudUbicacion as latitudUbicacion, ui.longitudUbicacion as longitudUbicacion, " +
            "ac.idActividad.idActividad as idActividad, ac.idActividad.codigoActividad as codigoActividad, " +
            "us.idUsuario.idUsuario as idUsuarioCreador) " +
            "FROM TblActividadUbicacion as au " +
            "INNER JOIN au.idUbicacionImplementacion as ui " +
            "INNER JOIN au.idActividad as ac " +
            "INNER JOIN au.idUsuario as us " +
            "WHERE au.idActividad = :idActividad")
    // List<TblActividadUbicacion> getUbicacionByIdActividad(TblActividad _tblActividad);
    List<TblActividadUbicacion> getUbicacionByIdActividad(@Param("idActividad") TblActividad _tblActividad);

    /**
     * Metodo que despliega los Proyectos de la BD
     *
     * @param tblActividad
     * @return Proyecto de la BD, por paramtro de Id
     * @autor Nahum Martinez | NAM
     * @version 26/02/2019/v1.0
     */
    long countByIdActividad(TblActividad tblActividad);


    /**
     * Metodo que despliega los Proyectos de la BD, relacionados con
     * las Ubicaciones
     *
     * @param tblUbicacionImplementacion
     * @return Proyecto de la BD, por paramtro de Id de la Ubicacion
     * @autor Nahum Martinez | NAM
     * @version 28/02/2019/v1.0
     */
    long countByIdUbicacionImplementacion(TblUbicacionImplementacion tblUbicacionImplementacion);


    /**
     * Metodo que despliega los Proyectos de la BD, relacionados con
     * las Ubicaciones
     *
     * @param tblUbicacionImplementacion
     * @return Proyecto de la BD, por paramtro de Id de la Ubicacion
     * @autor Nahum Martinez | NAM
     * @version 28/02/2019/v1.0
     */
    TblActividadUbicacion findByIdUbicacionImplementacion(TblUbicacionImplementacion tblUbicacionImplementacion);


    /**
     * Metodo que Borra la Organizacion de la BD con el Id como parametro
     * en la Tabla Ubicaciones Proyectos
     *
     * @param tblUbicacionImplementacion
     * @param tblActividad
     * @return Ubicacion de la BD, por paramtro de Ubicacion y Actividad
     * @autor Nahum Martinez | NAM
     * @version 28/02/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividadUbicacion e WHERE e.idUbicacionImplementacion = :idUbicacionImpl AND e.idActividad = :idActividad")
    void deleletedUbicacionActividad(@Param("idUbicacionImpl") TblUbicacionImplementacion tblUbicacionImplementacion,
                                     @Param("idActividad") TblActividad tblActividad);

}
