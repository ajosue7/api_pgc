/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle;

import com.api.pgc.core.APIRestPGC.models.mantenimiento.actividades.TblMonedaActividad;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_actividades_financiamiento_det_gastos",
        indexes = {@Index(name = "idx_codigo_financ_GASTO", columnList = "CODIGO_FINANC_GASTO")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_FINANC_GASTO"})})
@SequenceGenerator(name = "tbl_actividades_financiamiento_ga_id_actividad_financ_gasto_seq", sequenceName = "tbl_actividades_financiamiento_ga_id_actividad_financ_gasto_seq", initialValue = 1, allocationSize = 1)
public class TblActividadFinanciamientoDetGasto {
    // Propiedades de la tabla
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_actividades_financiamiento_ga_id_actividad_financ_gasto_seq")
    @Column(name = "ID_ACTIVIDAD_FINANC_DET_GASTO", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idActividadFinancDetGasto;

    @Column(name = "CODIGO_FINANC_GASTO", nullable = false, length = 50)
    @ApiModelProperty(notes = "Codigo de la Transacción", required = true)
    private String codigoFinancGasto;

    // Mapeo de la Relacion de la Tabla de Financiamiento Detalle con Actividades
    // Muchos Financiamiento Detalle = 1 Actividad
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ID_ACTIVIDAD_FINANC_DET", referencedColumnName = "ID_ACTIVIDAD_FINANC_DET")
    @ApiModelProperty(notes = "Entidad Detalle del Financiamiento de Proyecto, se envia desde un Json (\"idActividadFinancDet\": { \"idActividadFinancDet\": \"valor\" })", required = true)
    private TblActividadFinanciamientoDet idActividadFinancDet;

    // Mapeo de la Relacion de la Tabla de Tipo de Financiamiento con Actividades
    // Muchos Tipo de Financiamiento = 1 Actividad
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ID_TIPO_TRANSACCION", referencedColumnName = "ID_TIPO_TRANSACCION")
    @ApiModelProperty(notes = "Entidad del tipo de transacción de Proyecto, se envia desde un Json (\"idTipoTransaccion\": { \"idTipoTransaccion\": \"valor\" })", required = true)
    private TblActividadTipoTransaccion idTipoTransaccion;

    // Mapeo de la Relacion de la Tabla de Moneda con Actividades
    // Muchos Moneda de Ayuda = 1 Actividad
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ID_MONEDA_ACTIVIDAD", referencedColumnName = "ID_MONEDA_ACTIVIDAD")
    @ApiModelProperty(notes = "Entidad del Moneda de Transaccion de Proyecto, se envia desde un Json (\"idMonedaActividad\": { \"idMonedaActividad\": \"valor\" })", required = true)
    private TblMonedaActividad idMonedaActividad;

    // Transaccionales
    @Column(name = "MONTO_GASTO")
    @ApiModelProperty(notes = "Monto de la Transacción")
    private double montoGasto;

    @Column(name = "FECHA_TRANSACCION")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha de Transacción")
    private Date fechaTransaccion;

    @Column(name = "CAMBIO_FIJO")
    @ApiModelProperty(notes = "Cambio fijo de Moneda")
    private double cambioFijo;

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

    @Column(name = "FECHA_MODIFICACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha de Modificacion", readOnly = true)
    private Date fechaModificacion;

    @Column(name = "HORA_MODIFICACION")
    @Temporal(TemporalType.TIME)
    @ApiModelProperty(notes = "Hora de Modificacion, formato hh:mm:ss", readOnly = true)
    private Date horaModificacion;


    /**
     * Constructor vacio de la Clase, solo para Jpa
     */
    public TblActividadFinanciamientoDetGasto() {
        // Este lo usa Jpa para realizar los Mapping
    }

    /**
     * Metodos Getters y Setter
     */
    public long getIdActividadFinancDetGasto() {
        return idActividadFinancDetGasto;
    }

    public void setIdActividadFinancDetGasto(long idActividadFinancDetGasto) {
        this.idActividadFinancDetGasto = idActividadFinancDetGasto;
    }

    public String getCodigoFinancGasto() {
        return codigoFinancGasto;
    }

    public void setCodigoFinancGasto(String codigoFinancGasto) {
        this.codigoFinancGasto = codigoFinancGasto;
    }

    public TblActividadFinanciamientoDet getIdActividadFinancDet() {
        return idActividadFinancDet;
    }

    public void setIdActividadFinancDet(TblActividadFinanciamientoDet idActividadFinancDet) {
        this.idActividadFinancDet = idActividadFinancDet;
    }

    public TblActividadTipoTransaccion getIdTipoTransaccion() {
        return idTipoTransaccion;
    }

    public void setIdTipoTransaccion(TblActividadTipoTransaccion idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
    }

    public TblMonedaActividad getIdMonedaActividad() {
        return idMonedaActividad;
    }

    public void setIdMonedaActividad(TblMonedaActividad idMonedaActividad) {
        this.idMonedaActividad = idMonedaActividad;
    }

    public double getMontoGasto() {
        return montoGasto;
    }

    public void setMontoGasto(double montoGasto) {
        this.montoGasto = montoGasto;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public double getCambioFijo() {
        return cambioFijo;
    }

    public void setCambioFijo(double cambioFijo) {
        this.cambioFijo = cambioFijo;
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

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getHoraModificacion() {
        return horaModificacion;
    }

    public void setHoraModificacion(Date horaModificacion) {
        this.horaModificacion = horaModificacion;
    }
}
