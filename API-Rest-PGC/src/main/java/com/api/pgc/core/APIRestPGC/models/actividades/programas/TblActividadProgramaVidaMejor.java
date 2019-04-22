/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.actividades.programas;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.programas.TblProgramaVidaMejor;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_actividades_programas_vida_mejor",
        indexes = {@Index(name = "idx_codigo_actividad_vida_mejor", columnList = "CODIGO_ACTIVIDAD")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_ACTIVIDAD"})})
public class TblActividadProgramaVidaMejor {
    // Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ACTIVIDAD_PROGRAMA_VIDA_MEJOR", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idActividadProgramaVidaMejor;

    @Column(name = "CODIGO_ACTIVIDAD", nullable = false, length = 50)
    @ApiModelProperty(notes = "Codigo de la Actividad", required = true)
    private String codigoActividad;

    // Mapeo de la Relacion de la Tabla de Actividades con Vida Mejor
    // Muchos Actividad = 1 Vida Mejor
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ID_PROGRAMA_VIDA_MEJOR", referencedColumnName = "ID_PROGRAMA")
    @ApiModelProperty(notes = "Entidad de Vida Mejor, se envia desde un Json (\"idProgramaVidaMejor\": { \"idProgramaVidaMejor\": \"valor\" })", required = true)
    private TblProgramaVidaMejor idProgramaVidaMejor;


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
    public TblActividadProgramaVidaMejor() {
        // Este lo usa Jpa para realizar los Mapping
    }


    /**
     * Metodos Getters y Setter
     */
    public long getIdActividadProgramaVidaMejor() {
        return idActividadProgramaVidaMejor;
    }

    public void setIdActividadProgramaVidaMejor(long idActividadProgramaVidaMejor) {
        this.idActividadProgramaVidaMejor = idActividadProgramaVidaMejor;
    }

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public TblProgramaVidaMejor getIdProgramaVidaMejor() {
        return idProgramaVidaMejor;
    }

    public void setIdProgramaVidaMejor(TblProgramaVidaMejor idProgramaVidaMejor) {
        this.idProgramaVidaMejor = idProgramaVidaMejor;
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
