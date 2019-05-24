/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_modalidad_ayuda",
        indexes = {@Index(name = "idx_codigo_modalidad_ayuda", columnList = "CODIGO_MODALIDAD_AYUDA")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_MODALIDAD_AYUDA"})})
public class TblActividadModalidadAyuda {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MODALIDAD_AYUDA", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idModalidadAyuda;

    @Column(name = "CODIGO_MODALIDAD_AYUDA", nullable = false, length = 10)
    @ApiModelProperty(notes = "Codigo de modalidad de Ayuda", required = true)
    private String codigoModalidadAyuda;

    @Column(name = "NOMBRE_MODALIDAD_AYUDA", nullable = false, length = 100)
    @ApiModelProperty(notes = "Nombre de la modalidad de Ayuda", required = true)
    private String nombreModalidadAyuda;

    @Column(name = "DESCRIPCION_MODALIDAD_AYUDA", nullable = false, length = 300)
    @ApiModelProperty(notes = "Descripcion de la modalidad de Ayuda", required = true)
    private String descripcionModalidadAyuda;

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
    @ApiModelProperty(notes = "Hora de Creacion, formato hh:mm:ss", readOnly = true)
    private Date horaCreacion = new Date();


    /*
     * Constructor vacio de la Clase, solo para Jpa
     */
    public TblActividadModalidadAyuda() {
        //Este lo usa Jpa para realizar los Mapping
    }


    /*
     * Metodos Getters y Setters de la Clase
     * 2019-05-23
     */

    public long getIdModalidadAyuda() {
        return idModalidadAyuda;
    }

    public void setIdModalidadAyuda(long idModalidadAyuda) {
        this.idModalidadAyuda = idModalidadAyuda;
    }

    public String getCodigoModalidadAyuda() {
        return codigoModalidadAyuda;
    }

    public void setCodigoModalidadAyuda(String codigoModalidadAyuda) {
        this.codigoModalidadAyuda = codigoModalidadAyuda;
    }

    public String getNombreModalidadAyuda() {
        return nombreModalidadAyuda;
    }

    public void setNombreModalidadAyuda(String nombreModalidadAyuda) {
        this.nombreModalidadAyuda = nombreModalidadAyuda;
    }

    public String getDescripcionModalidadAyuda() {
        return descripcionModalidadAyuda;
    }

    public void setDescripcionModalidadAyuda(String descripcionModalidadAyuda) {
        this.descripcionModalidadAyuda = descripcionModalidadAyuda;
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
