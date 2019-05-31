/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.detalle;

import com.api.pgc.core.APIRestPGC.models.actividades.financiamiento.encabezado.TblActividadFinanciamientoEnc;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblOrganizacion;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_actividades_financiamiento_det",
        indexes = {@Index(name = "idx_codigo_financ_det", columnList = "CODIGO_FINANC_DET")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_FINANC_DET"})})
public class TblActividadFinanciamientoDet {
    // Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ACTIVIDAD_FINANC_DET", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idActividadFinancDet;

    @Column(name = "CODIGO_FINANC_DET", nullable = false, length = 50)
    @ApiModelProperty(notes = "Codigo de la Transacción", required = true)
    private String codigoFinancDet;

    // Mapeo de la Relacion de la Tabla de Financiamiento Encabezado con Actividades
    // Muchos Financiamiento Encabezado = 1 Actividad
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ID_ACTIVIDAD_FINANC_ENC", referencedColumnName = "ID_ACTIVIDAD_FINANC_ENC")
    @ApiModelProperty(notes = "Entidad Encabezado del financiamiento de Proyecto, se envia desde un Json (\"idActividadFinancEnc\": { \"idActividadFinancEnc\": \"valor\" })", required = true)
    private TblActividadFinanciamientoEnc idActividadFinancEnc;

    // Mapeo de la Relacion de la Tabla de Tipo de Financiamiento con Actividades
    // Muchos Tipo de Financiamiento = 1 Actividad
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ID_TIPO_FINANCIAMIENTO", referencedColumnName = "ID_TIPO_FINANCIAMIENTO")
    @ApiModelProperty(notes = "Entidad del tipo de financiamiento de Proyecto, se envia desde un Json (\"idTipoFinanciamiento\": { \"idTipoFinanciamiento\": \"valor\" })", required = true)
    private TblActividadTipoFinanciamiento idTipoFinanciamiento;

    // Mapeo de la Relacion de la Tabla de Modalidad de Ayuda con Actividades
    // Muchos Modalidad e Ayuda = 1 Actividad
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ID_MODALIDAD_AYUDA", referencedColumnName = "ID_MODALIDAD_AYUDA")
    @ApiModelProperty(notes = "Entidad del Modalidad de Ayuda de Proyecto, se envia desde un Json (\"idModalidadAyuda\": { \"idModalidadAyuda\": \"valor\" })", required = true)
    private TblActividadModalidadAyuda idModalidadAyuda;

    @Column(name = "ID_ORGANIZACION_FINANCIERA", nullable = false, length = 30)
    @ApiModelProperty(notes = "Id organizacion financiera", required = true)
    private String idOrganizacionFinanciera;

    // Mapeo de la Relacion de la Tabla de Socio al Desarrollo con Actividades
    // Muchos Socio al Desarrollo = 1 Actividad
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ID_SOCIO_DESARROLLO", referencedColumnName = "ID_ORGANIZACION")
    @ApiModelProperty(notes = "Entidad del Modalidad de Socio al Desarrollo de Proyecto, se envia desde un Json (\"idSocioDesarrollo\": { \"idOrganizacion\": \"valor\" })", required = true)
    private TblOrganizacion idSocioDesarrollo;


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
    public TblActividadFinanciamientoDet() {
        // Este lo usa Jpa para realizar los Mapping
    }

    /**
     * Metodos Getters y Setter
     */
    public long getIdActividadFinancDet() {
        return idActividadFinancDet;
    }

    public void setIdActividadFinancDet(long idActividadFinancDet) {
        this.idActividadFinancDet = idActividadFinancDet;
    }

    public String getCodigoFinancDet() {
        return codigoFinancDet;
    }

    public void setCodigoFinancDet(String codigoFinancDet) {
        this.codigoFinancDet = codigoFinancDet;
    }

    public TblActividadFinanciamientoEnc getIdActividadFinancEnc() {
        return idActividadFinancEnc;
    }

    public void setIdActividadFinancEnc(TblActividadFinanciamientoEnc idActividadFinancEnc) {
        this.idActividadFinancEnc = idActividadFinancEnc;
    }

    public TblActividadTipoFinanciamiento getIdTipoFinanciamiento() {
        return idTipoFinanciamiento;
    }

    public void setIdTipoFinanciamiento(TblActividadTipoFinanciamiento idTipoFinanciamiento) {
        this.idTipoFinanciamiento = idTipoFinanciamiento;
    }

    public TblActividadModalidadAyuda getIdModalidadAyuda() {
        return idModalidadAyuda;
    }

    public void setIdModalidadAyuda(TblActividadModalidadAyuda idModalidadAyuda) {
        this.idModalidadAyuda = idModalidadAyuda;
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

    public String getIdOrganizacionFinanciera() {
        return idOrganizacionFinanciera;
    }

    public void setIdOrganizacionFinanciera(String idOrganizacionFinanciera) {
        this.idOrganizacionFinanciera = idOrganizacionFinanciera;
    }

    public TblOrganizacion getIdSocioDesarrollo() {
        return idSocioDesarrollo;
    }

    public void setIdSocioDesarrollo(TblOrganizacion idSocioDesarrollo) {
        this.idSocioDesarrollo = idSocioDesarrollo;
    }
}
