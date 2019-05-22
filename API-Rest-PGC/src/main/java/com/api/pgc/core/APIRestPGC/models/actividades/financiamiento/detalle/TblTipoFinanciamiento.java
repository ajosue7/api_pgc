/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_tipo_financiamiento",
        indexes = {@Index(name = "idx_cod_tipo_financiamiento", columnList = "COD_TIPO_FINANCIAMIENTO")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"COD_TIPO_FINANCIAMIENTO"})})
public class TblTipoFinanciamiento {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_FINANCIAMIENTO", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idTipoFinanciamiento;

    @Column(name = "COD_TIPO_FINANCIAMIENTO", nullable = false, length = 10)
    @ApiModelProperty(notes = "Codigo de Tipo de Financiamiento", required = true)
    private String codTipoFinanciamiento;

    @Column(name = "NOMBRE_TIPO_FINANCIAMIENTO", nullable = false, length = 150)
    @ApiModelProperty(notes = "Nombre del Tipo de Financiamiento", required = true)
    private String nombreTipoFinanciamiento;

    @Column(name = "DESCRIPCION_TIPO_FINANCIAMIENTO", nullable = false, length = 300)
    @ApiModelProperty(notes = "Descripcion del Tipo de Financiamiento", required = true)
    private String descripcionTipoFinanciamiento;

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
    public TblTipoFinanciamiento() {
        //Este lo usa Jpa para realizar los Mapping
    }


    /*
     * Metodos Getters y Setters de la Clase
     * 2019-05-22
     */

    public long getIdTipoFinanciamiento() {
        return idTipoFinanciamiento;
    }

    public void setIdTipoFinanciamiento(long idTipoFinanciamiento) {
        this.idTipoFinanciamiento = idTipoFinanciamiento;
    }

    public String getCodTipoFinanciamiento() {
        return codTipoFinanciamiento;
    }

    public void setCodTipoFinanciamiento(String codTipoFinanciamiento) {
        this.codTipoFinanciamiento = codTipoFinanciamiento;
    }

    public String getNombreTipoFinanciamiento() {
        return nombreTipoFinanciamiento;
    }

    public void setNombreTipoFinanciamiento(String nombreTipoFinanciamiento) {
        this.nombreTipoFinanciamiento = nombreTipoFinanciamiento;
    }

    public String getDescripcionTipoFinanciamiento() {
        return descripcionTipoFinanciamiento;
    }

    public void setDescripcionTipoFinanciamiento(String descripcionTipoFinanciamiento) {
        this.descripcionTipoFinanciamiento = descripcionTipoFinanciamiento;
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
