/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.actividades;

import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTipo;
import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_actividades_recursos",
        indexes = {@Index(name = "idx_codigo_actividad_recurso", columnList = "CODIGO_ACTIVIDAD_RECURSO" )},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_ACTIVIDAD_RECURSO"})})
public class TblActividadRecurso {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_ACTIVIDAD_RECURSO", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idActividadrecurso;

    @Column(name = "CODIGO_ACTIVIDAD_RECURSO", nullable = false, length=50)
    @ApiModelProperty(notes = "Codigo del Recurso de la Actividad", required = true)
    private String codActividadRecurso;

    @Column(name = "URL_ACTIVIDAD_RECURSO", nullable = false, length=300)
    @ApiModelProperty(notes = "Url del Recurso de la Actividad")
    private String urlActividadRecurso;

    // Mapeo de la Relacion de la Tabla de Actividades con Actividades Documentos
    // Muchos Actividad = 1 Actividades Documento
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID_ACTIVIDAD")
    @ApiModelProperty(notes = "Entidad de Actividad, se envia desde un Json (\"idActividad\": { \"idActividad\": \"valor\" })", required = true)
    private TblActividad idActividad;


    // Mapeo de la Relacion de la Tabla de Tipos con Actividades Documentos
    // Muchos Tipo = 1 Actividad Documento
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "ID_TIPO_DOCUMENTO", referencedColumnName = "ID_TIPO")
    @ApiModelProperty(notes = "Entidad de Tipo de Documento, se envia desde un Json (\"idTipoDocumento\": { \"idTipoDocumento\": \"valor\" })", required = true)
    private TblTipo idTipoDocumento;

    // Mapeo de la Relacion de la Tabla de Usuarios con Actividades Documentos
    // Muchos Usuario = 1 Actividad Documento
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ApiModelProperty(notes = "Entidad de Usuario de Documento, se envia desde un Json (\"idUsuario\": { \"idUsuario\": \"valor\" })", required = true)
    private TblUsuarios idUsuario;

    @Column(name = "FECHA_CREACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha de Creacion", readOnly = true)
    private Date fechaCreacion;

    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    @ApiModelProperty(notes = "Hora de Creacion de la Actividad, formato hh:mm:ss", readOnly = true)
    private Date horaCreacion = new Date();

    @Column(name = "ACTIVO")
    @ApiModelProperty(notes = "Activo")
    private Boolean activo;


    // Constructor vacio de la Clase, solo para Jpa
    public TblActividadRecurso() {
        // Este lo usa Jpa para realizar los Mapping
    }

    // Metodos Getters y Setters
    public long getIdActividadrecurso() {
        return idActividadrecurso;
    }

    public void setIdActividadrecurso(long idActividadrecurso) {
        this.idActividadrecurso = idActividadrecurso;
    }

    public String getCodigoActividadRecurso() {
        return codActividadRecurso;
    }

    public void setCodigoActividadRecurso(String codigoActividadRecurso) {
        this.codActividadRecurso = codigoActividadRecurso;
    }

    public String getUrlActividadRecurso() {
        return urlActividadRecurso;
    }

    public void setUrlActividadRecurso(String urlActividadRecurso) {
        this.urlActividadRecurso = urlActividadRecurso;
    }

    public TblActividad getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(TblActividad idActividad) {
        this.idActividad = idActividad;
    }

    public TblTipo getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(TblTipo idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public TblUsuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(TblUsuarios idUsuario) {
        this.idUsuario = idUsuario;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
