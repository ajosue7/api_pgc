/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.encabezado;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.actividades.TblMonedaActividad;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_actividades_financiamiento_enc",
        indexes = {@Index(name = "idx_codigo_financ_enc", columnList = "CODIGO_FINANC_ENC")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_FINANC_ENC"})})
public class TblActividadFinanciamientoEnc {
    // Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ACTIVIDAD_FINANC_ENC", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idActividadFinancEnc;

    @Column(name = "CODIGO_FINANC_ENC", nullable = false, length = 50)
    @ApiModelProperty(notes = "Codigo de la Transacción", required = true)
    private String codigoFinancEnc;

    // Mapeo de la Relacion de la Tabla de Moneda con Actividades
    // Muchos Moneda = 1 Actividad
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ID_MONEDA_ACTIVIDAD", referencedColumnName = "ID_MONEDA_ACTIVIDAD")
    @ApiModelProperty(notes = "Entidad Menoda de Proyecto, se envia desde un Json (\"idMonedaActividad\": { \"idMonedaActividad\": \"valor\" })", required = true)
    private TblMonedaActividad idMonedaActividad;

    // Mapeo de la Relacion de la Tabla de Actividades con Organizaciones
    // Muchos Sector Ocde = 1 Actividad
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID_ACTIVIDAD")
    @ApiModelProperty(notes = "Entidad de la Proyectos, se envia desde un Json (\"idActividad\": { \"idActividad\": \"valor\" })", required = true)
    private TblActividad idActividad;

    @Column(name = "MONTO_ACTIVIDAD")
    @ApiModelProperty(notes = "Costo del Proyecto")
    private double montoActividad;

    @Column(name = "FECHA_TRANSACCION")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha de Transacción")
    private Date fechaTransaccion;

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
    public TblActividadFinanciamientoEnc() {
        // Este lo usa Jpa para realizar los Mapping
    }

    /**
     * Metodos Getters y Setter
     */
    public long getIdActividadFinancEnc() {
        return idActividadFinancEnc;
    }

    public void setIdActividadFinancEnc(long idActividadFinancEnc) {
        this.idActividadFinancEnc = idActividadFinancEnc;
    }

    public String getCodigoFinancEnc() {
        return codigoFinancEnc;
    }

    public void setCodigoFinancEnc(String codigoFinancEnc) {
        this.codigoFinancEnc = codigoFinancEnc;
    }

    public TblMonedaActividad getIdMonedaActividad() {
        return idMonedaActividad;
    }

    public void setIdMonedaActividad(TblMonedaActividad idMonedaActividad) {
        this.idMonedaActividad = idMonedaActividad;
    }

    public TblActividad getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(TblActividad idActividad) {
        this.idActividad = idActividad;
    }

    public double getMontoActividad() {
        return montoActividad;
    }

    public void setMontoActividad(double montoActividad) {
        this.montoActividad = montoActividad;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
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
