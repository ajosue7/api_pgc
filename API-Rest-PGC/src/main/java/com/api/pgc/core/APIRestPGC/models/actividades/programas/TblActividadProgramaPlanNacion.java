/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.actividades.programas;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.programas.TblProgramaPlanNacion;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_actividades_programas_plan_nacion",
        indexes = {@Index(name = "idx_codigo_actividad_plan_nacion", columnList = "CODIGO_ACTIVIDAD")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_ACTIVIDAD"})})
public class TblActividadProgramaPlanNacion {
    // Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ACTIVIDAD_PROGRAMA_PLAN_NACION", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idActividadProgramaPlanNacion;

    @Column(name = "CODIGO_ACTIVIDAD", nullable = false, length = 50)
    @ApiModelProperty(notes = "Codigo de la Actividad", required = true)
    private String codigoActividad;

    // Mapeo de la Relacion de la Tabla de Actividades con Plan de Nacion
    // Muchos Actividad = 1 Plan de Nacion
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ID_PROGRAMA_PLAN_NACION", referencedColumnName = "ID_PROGRAMA")
    @ApiModelProperty(notes = "Entidad del Plan de Nacion, se envia desde un Json (\"idProgramaPlanNacion\": { \"idProgramaPlanNacion\": \"valor\" })", required = true)
    private TblProgramaPlanNacion idProgramaPlanNacion;


    // Mapeo de la Relacion de la Tabla de Id Interna con Actividades
    // Muchos Sector Ocde = 1 Actividad
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID_ACTIVIDAD")
    @ApiModelProperty(notes = "Entidad de la Proyectos, se envia desde un Json (\"idActividad\": { \"idActividad\": \"valor\" })", required = true)
    private TblActividad idActividad;

    @Column(name = "PORCENTAJE_PART")
    @ApiModelProperty(notes = "Porcentaje participacion")
    private double porcentaje_part;


    // Auditoria
    @Column(name = "ACTIVO")
    @ApiModelProperty(notes = "Activo", readOnly = true)
    private boolean activo = true;

    @Column(name = "FECHA_CREACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha de Creacion", readOnly = true)
    private Date fechaCreacion;

    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    @ApiModelProperty(notes = "Hora de Creacion de la Actividad, formato hh:mm:ss", readOnly = true)
    private Date horaCreacion = new Date();


    /**
     * Constructor vacio de la Clase, solo para Jpa
     */
    public TblActividadProgramaPlanNacion() {
        // Este lo usa Jpa para realizar los Mapping
    }


    /**
     * Metodos Getters y Setter
     */
    public long getIdActividadProgramaPlanNacion() {
        return idActividadProgramaPlanNacion;
    }

    public void setIdActividadProgramaPlanNacion(long idActividadProgramaPlanNacion) {
        this.idActividadProgramaPlanNacion = idActividadProgramaPlanNacion;
    }

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public TblProgramaPlanNacion getIdProgramaPlanNacion() {
        return idProgramaPlanNacion;
    }

    public void setIdProgramaPlanNacion(TblProgramaPlanNacion idProgramaPlanNacion) {
        this.idProgramaPlanNacion = idProgramaPlanNacion;
    }

    public TblActividad getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(TblActividad idActividad) {
        this.idActividad = idActividad;
    }

    public double getPorcentaje_part() {
        return porcentaje_part;
    }

    public void setPorcentaje_part(double porcentaje_part) {
        this.porcentaje_part = porcentaje_part;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(Date horaCreacion) {
        this.horaCreacion = horaCreacion;
    }
}
