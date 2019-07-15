/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActividadRepository extends JpaRepository<TblActividad, Integer> {

    /**
     * Metodo que despliega la Actividad de la BD
     *
     * @param idActividad
     * @return Actividad de la BD, por paramtro de ID_ACTIVIDAD
     * @autor Nahum Martinez | NAM
     * @version 11/01/2019/v1.0
     */
    TblActividad findByIdActividad(long idActividad);


    /**
     * Metodo que despliega la Actividad de la BD
     *
     * @param codActividad
     * @return Actividad de la BD, por paramtro de COD_ACTIVIDAD
     * @autor Nahum Martinez | NAM
     * @version 28/01/2019/v1.0
     */
    TblActividad findByCodigoActividad(String codActividad);


    /**
     * Metodo que despliega la Actividad de la BD
     *
     * @param codActividad
     * @return Actividad de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 11/01/2019/v1.0
     */
    long countByCodigoActividad(String codActividad);


    /**
     * Metodo que despliega la Actividad de la BD
     *
     * @param nombreActividad
     * @return Actividad de la BD, por parametro de Nombre de Proyecto
     * @autor Nahum Martinez | NAM
     * @version 08/02/2019/v1.0
     */
    long countByNombreActividad(String nombreActividad);


    /**
     * Metodo que despliega la Actividad de la BD
     *
     * @param idProyecto
     * @return Actividad de la BD, por parametro de Id
     * @autor Nahum Martinez | NAM
     * @version 11/01/2019/v1.0
     */
    long countByIdActividad(long idProyecto);

    /**
     * Metodo que despliega los Detalle de los Proyectos de la BD
     *
     * @return Todos los Proyectos de la BD
     * @autor Nahum Martinez | NAM
     * @version 13/07/2019/v1.0
     */
    @Query("SELECT new map( a.idActividad as idActividad, a.codigoActividad as codigoActividad, " +
            "b.idEstado as idEstado, b.descEstado as descEstado," +
            "a.explicacionEstado as explicacionEstado, a.antecedentesActividad as antecedentesActividad, " +
            "a.objetivoActividad as objetivoActividad, a.descripcionActividad as descripcionActividad, " +
            "a.condicionesActividad as condicionesActividad, a.codigoSIAFIBIP as codigoSIAFIBIP, " +
            "c.idEstrategia as idEstrategia, c.descEstrategia as descEstrategia, " +
            "d.idPresupuesto as idPresupuesto, d.descPresupuesto as descPresupuesto,  " +
            "e.idSectorEjecutor as idSectorEjecutor, e.descripcionSectorEjecutor as descripcionSectorEjecutor,  " +
            "a.resultadosEsperados as resultadosEsperados, a.resultadosAlaFecha as resultadosAlaFecha,  " +
            "a.justificacionActividad as justificacionActividad,  " +
            "a.costoActividad as costoActividad,  " +
            "f.idMonedaActividad as idMonedaActividad, f.nombreMoneda as nombreMoneda, " +
            "a.fechaFinanciamiento as fechaFinanciamiento, a.nombreActividad as nombreActividad, " +
            "a.productosEsperados as productosEsperados, a.horaCreacion as horaCreacion, " +
            "g.idUsuario as idUsuarioCreador, g.nombre1Usuario as nombre1Usuario, g.apellido1Usuario as apellido1Usuario, " +
            "h.idEspacioTrabajo as idEspacioTrabajo, h.nombreEspacioTrabajo as nombreEspacioTrabajo, " +
            "a.activo as activo, " +
            "i.idEstado as idEstadoValid, i.descEstado as descEstado, " +
            "a.fechaCreacion as fechaCreacion, " +
            "j.idTipoIniciativa as idTipoIniciativaCssAct, j.descTipoiniciativa as descTipoiniciativa, " +
            "a.actividadesCss as actividadesCss, " +
            "a.fechaModificacion as fechaModificacion, a.horaModificacion as horaModificacion, " +
            "k.idUsuario as idUsuarioMod, k.nombre1Usuario as nombre1Usuario, k.apellido1Usuario as apellido1Usuario ) " +
            "FROM TblActividad as a " +
            "     LEFT OUTER JOIN a.idEstadoActivity as b " +
            "     LEFT OUTER JOIN a.idEstrategiaActivity as c " +
            "     LEFT OUTER JOIN a.idPresupuestoActivity as d " +
            "     LEFT OUTER JOIN a.idSectorEjecutorActivity as e " +
            "     LEFT OUTER JOIN a.idMonedaActividadActivity as f " +
            "     LEFT OUTER JOIN a.idUsuarioCreador as g " +
            "     LEFT OUTER JOIN a.idEspacioTrabajoActivity as h " +
            "     LEFT OUTER JOIN a.idEstadoValid as i " +
            "     LEFT OUTER JOIN a.idTipoIniciativaCssAct as j " +
            "     LEFT OUTER JOIN a.idUsuarioMod as k " +
            "ORDER BY a.idActividad")
    List<TblActividad> getAllActividades();


    /**
     * Metodo que despliega los Detalle de los Proyectos de la BD
     *
     * @return Todos los Proyectos de la BD
     * @autor Nahum Martinez | NAM
     * @version 13/07/2019/v1.0
     */
    @Query("SELECT COUNT(*) " +
            "FROM TblActividad as a " +
            "     LEFT OUTER JOIN a.idEstadoActivity as b " +
            "     LEFT OUTER JOIN a.idEstrategiaActivity as c " +
            "     LEFT OUTER JOIN a.idPresupuestoActivity as d " +
            "     LEFT OUTER JOIN a.idSectorEjecutorActivity as e " +
            "     LEFT OUTER JOIN a.idMonedaActividadActivity as f " +
            "     LEFT OUTER JOIN a.idUsuarioCreador as g " +
            "     LEFT OUTER JOIN a.idEspacioTrabajoActivity as h " +
            "     LEFT OUTER JOIN a.idEstadoValid as i " +
            "     LEFT OUTER JOIN a.idTipoIniciativaCssAct as j " +
            "     LEFT OUTER JOIN a.idUsuarioMod as k ")
    long countGetAllActividades();


    /**
     * Metodo que despliega los Detalle del Proyecto de la BD
     *
     * @return Obtiene el Proyecto de la BD
     * @autor Nahum Martinez | NAM
     * @version 14/07/2019/v1.0
     */
    @Query("SELECT new map( a.idActividad as idActividad, a.codigoActividad as codigoActividad, " +
            "b.idEstado as idEstado, b.descEstado as descEstado," +
            "a.explicacionEstado as explicacionEstado, a.antecedentesActividad as antecedentesActividad, " +
            "a.objetivoActividad as objetivoActividad, a.descripcionActividad as descripcionActividad, " +
            "a.condicionesActividad as condicionesActividad, a.codigoSIAFIBIP as codigoSIAFIBIP, " +
            "c.idEstrategia as idEstrategia, c.descEstrategia as descEstrategia, " +
            "d.idPresupuesto as idPresupuesto, d.descPresupuesto as descPresupuesto,  " +
            "e.idSectorEjecutor as idSectorEjecutor, e.descripcionSectorEjecutor as descripcionSectorEjecutor,  " +
            "a.resultadosEsperados as resultadosEsperados, a.resultadosAlaFecha as resultadosAlaFecha,  " +
            "a.justificacionActividad as justificacionActividad,  " +
            "a.costoActividad as costoActividad,  " +
            "f.idMonedaActividad as idMonedaActividad, f.nombreMoneda as nombreMoneda, " +
            "a.fechaFinanciamiento as fechaFinanciamiento, a.nombreActividad as nombreActividad, " +
            "a.productosEsperados as productosEsperados, a.horaCreacion as horaCreacion, " +
            "g.idUsuario as idUsuarioCreador, g.nombre1Usuario as nombre1Usuario, g.apellido1Usuario as apellido1Usuario, " +
            "h.idEspacioTrabajo as idEspacioTrabajo, h.nombreEspacioTrabajo as nombreEspacioTrabajo, " +
            "a.activo as activo, " +
            "i.idEstado as idEstadoValid, i.descEstado as descEstado, " +
            "a.fechaCreacion as fechaCreacion, " +
            "j.idTipoIniciativa as idTipoIniciativaCssAct, j.descTipoiniciativa as descTipoiniciativa, " +
            "a.actividadesCss as actividadesCss, " +
            "a.fechaModificacion as fechaModificacion, a.horaModificacion as horaModificacion, " +
            "k.idUsuario as idUsuarioMod, k.nombre1Usuario as nombre1Usuario, k.apellido1Usuario as apellido1Usuario ) " +
            "FROM TblActividad as a " +
            "     LEFT OUTER JOIN a.idEstadoActivity as b " +
            "     LEFT OUTER JOIN a.idEstrategiaActivity as c " +
            "     LEFT OUTER JOIN a.idPresupuestoActivity as d " +
            "     LEFT OUTER JOIN a.idSectorEjecutorActivity as e " +
            "     LEFT OUTER JOIN a.idMonedaActividadActivity as f " +
            "     LEFT OUTER JOIN a.idUsuarioCreador as g " +
            "     LEFT OUTER JOIN a.idEspacioTrabajoActivity as h " +
            "     LEFT OUTER JOIN a.idEstadoValid as i " +
            "     LEFT OUTER JOIN a.idTipoIniciativaCssAct as j " +
            "     LEFT OUTER JOIN a.idUsuarioMod as k " +
            "WHERE a.idActividad = :idActividad " +
            "ORDER BY a.idActividad")
    List<TblActividad> getByIdActividad(@Param("idActividad") long idActividad);


    /**
     * Metodo que Borra los Datos Generales de la BD con el Id como parametro
     * en la Tabla Actividades
     *
     * @param idActividad
     * @return Detalle del Proyecto de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 14/07/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividad e WHERE e.idActividad = :idActivity")
    void deleletedByIdActividad(@Param("idActivity") long idActividad);
}
