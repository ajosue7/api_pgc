/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.programas;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_nivel_programa",
        indexes = {@Index(name = "idx_codigo_nivel_programa", columnList = "CODIGO_NIVEL_PROGRAMA")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_NIVEL_PROGRAMA"})})
public class TblNivelPrograma {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NIVEL_PROGRAMA", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idNivelPrograma;

    @Column(name = "CODIGO_NIVEL_PROGRAMA", nullable = false, length = 10)
    @ApiModelProperty(notes = "Codigo Tipo de Programa", required = true)
    private String codigoNivelPrograma;

    @Column(name = "NOMBRE_NIVEL_PROGRAMA", nullable = false, length = 250)
    @ApiModelProperty(notes = "Nombre del Nivel de Programa")
    private String nombreNivelPrograma;

    @Column(name = "DESCRIPCION_NIVEL_PROGRAMA", nullable = false, length = 250)
    @ApiModelProperty(notes = "Descripcion del Nivel de Programa")
    private String descripcionNivelPrograma;

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
    public TblNivelPrograma() {
        // JPA construye la informacion
    }


    /**
     * Metodos Getters y Setters
     */
    public long getIdNivelPrograma() {
        return idNivelPrograma;
    }

    public void setIdNivelPrograma(long idNivelPrograma) {
        this.idNivelPrograma = idNivelPrograma;
    }

    public String getCodigoNivelPrograma() {
        return codigoNivelPrograma;
    }

    public void setCodigoNivelPrograma(String codigoNivelPrograma) {
        this.codigoNivelPrograma = codigoNivelPrograma;
    }

    public String getNombreNivelPrograma() {
        return nombreNivelPrograma;
    }

    public void setNombreNivelPrograma(String nombreNivelPrograma) {
        this.nombreNivelPrograma = nombreNivelPrograma;
    }

    public String getDescripcionNivelPrograma() {
        return descripcionNivelPrograma;
    }

    public void setDescripcionNivelPrograma(String descripcionNivelPrograma) {
        this.descripcionNivelPrograma = descripcionNivelPrograma;
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
