/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.programas;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_tipo_programa",
        indexes = {@Index(name = "idx_codigo_tipo_programa", columnList = "CODIGO_TIPO_PROGRAMA")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_TIPO_PROGRAMA"})})
public class TblTipoPrograma {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_PROGRAMA", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idTipoPrograma;

    @Column(name = "CODIGO_TIPO_PROGRAMA", nullable = false, length = 10)
    @ApiModelProperty(notes = "Codigo Tipo de Programa", required = true)
    private String codTipoPrograma;

    @Column(name = "NOMBRE_TIPO_PROGRAMA", nullable = false, length = 250)
    @ApiModelProperty(notes = "Nombre del Tipo de Programa")
    private String nombreTipoPrograma;

    @Column(name = "ACTIVO")
    @ApiModelProperty(notes = "Activo", readOnly = true)
    private boolean activo = true;

    @Column(name = "FECHA_CREACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha Creacion del Usuario, formato hh:mm:ss", readOnly = true)
    private Date fechaCreacion;

    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    @ApiModelProperty(notes = "Hora de Creacion del Usuario, formato hh:mm:ss", readOnly = true)
    private Date horaCreacion = new Date();


    /**
     * Constructor de la Clase
     */
    public TblTipoPrograma() {
    }


    /**
     * Metodos Getters y Setters
     */
    public long getIdTipoPrograma() {
        return idTipoPrograma;
    }

    public void setIdTipoPrograma(long idTipoPrograma) {
        this.idTipoPrograma = idTipoPrograma;
    }

    public String getCodTipoPrograma() {
        return codTipoPrograma;
    }

    public void setCodTipoPrograma(String codTipoPrograma) {
        this.codTipoPrograma = codTipoPrograma;
    }

    public String getNombreTipoPrograma() {
        return nombreTipoPrograma;
    }

    public void setNombreTipoPrograma(String nombreTipoPrograma) {
        this.nombreTipoPrograma = nombreTipoPrograma;
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
