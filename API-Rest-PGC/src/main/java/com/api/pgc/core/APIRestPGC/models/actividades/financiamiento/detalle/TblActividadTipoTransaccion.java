/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_tipo_transaccion",
        indexes = {@Index(name = "idx_codigo_tipo_transaccion", columnList = "CODIGO_TIPO_TRANSACCION")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_TIPO_TRANSACCION"})})
public class TblActividadTipoTransaccion {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_TRANSACCION", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idTipoTransaccion;

    @Column(name = "CODIGO_TIPO_TRANSACCION", nullable = false, length = 30)
    @ApiModelProperty(notes = "Codigo de Tipo de Transacción", required = true)
    private String codigoTipoTransaccion;

    @Column(name = "DESC_TIPO_TRANSACCION", nullable = false, length = 100)
    @ApiModelProperty(notes = "Descripcion del Tipo de Transacción", required = true)
    private String descTipoTransaccion;

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
    public TblActividadTipoTransaccion() {
        //Este lo usa Jpa para realizar los Mapping
    }


    /*
     * Metodos Getters y Setters de la Clase
     * 2019-05-22
     */

    public long getIdTipoTransaccion() {
        return idTipoTransaccion;
    }

    public void setIdTipoTransaccion(long idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
    }

    public String getCodigoTipoTransaccion() {
        return codigoTipoTransaccion;
    }

    public void setCodigoTipoTransaccion(String codigoTipoTransaccion) {
        this.codigoTipoTransaccion = codigoTipoTransaccion;
    }

    public String getDescTipoTransaccion() {
        return descTipoTransaccion;
    }

    public void setDescTipoTransaccion(String descTipoTransaccion) {
        this.descTipoTransaccion = descTipoTransaccion;
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
