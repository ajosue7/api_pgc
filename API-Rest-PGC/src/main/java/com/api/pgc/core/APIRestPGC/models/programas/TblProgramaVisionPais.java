/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.programas;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_programas_vision_pais",
        indexes = {@Index(name = "idx_codigo_programa_vision_pais", columnList = "CODIGO_PROGRAMA")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_PROGRAMA"})})
public class TblProgramaVisionPais {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROGRAMA", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idPrograma;

    @Column(name = "CODIGO_PROGRAMA", nullable = false, length = 10)
    @ApiModelProperty(notes = "Codigo Vision de País", required = true)
    private String codigoPrograma;

    @Column(name = "NOMBRE_PROGRAMA", nullable = false, length = 250)
    @ApiModelProperty(notes = "Nombre del Plan de Nacion")
    private String nombrePrograma;

    @Column(name = "DESCRIPCION_PROGRAMA", nullable = false, length = 250)
    @ApiModelProperty(notes = "Descripcion del Plan de Nacion")
    private String descripcionPrograma;

    @Column(name = "IMAGEN_PROGRAMA", length = 300)
    @ApiModelProperty(notes = "Imagen del Plan de Nacion")
    private String imagenPrograma;

    @Column(name = "ACTIVO")
    private boolean activo;

    @Column(name = "FECHA_CREACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha Creacion del Usuario, formato hh:mm:ss", readOnly = true)
    private Date fechaCreacion;

    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    @ApiModelProperty(notes = "Hora de Creacion del Usuario, formato hh:mm:ss", readOnly = true)
    private Date horaCreacion = new Date();

    // Mapeo de la Relacion de la Tabla de Plan de Nacion con Tipo Programa
    // Muchos Plan de Nacion = 1 Tipo Programa
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TIPO_PROGRAMA", referencedColumnName = "ID_TIPO_PROGRAMA")
    @ApiModelProperty(notes = "Entidad del Tipo de Programa, se envia desde un Json (\"idTipoPrograma\": { \"idTipoPrograma\": \"valor\" })",
            required = true)
    private TblTipoPrograma idTipoPrograma;


    // Mapeo de la Relacion de la Tabla de Plan de Nacion con Padre
    // Muchos Plan de Nacion = 1 Plan de Nacion Padre
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PROGRAMA_PADRE_ID", referencedColumnName = "ID_PROGRAMA")
    @ApiModelProperty(notes = "Entidad del Plan de Nacion, se envia desde un Json (\"programaPadreId\": { \"idPrograma\": \"valor\" })",
            required = true)
    private TblProgramaVisionPais programaPadreId;


    // Mapeo de la Relacion de la Tabla de Plan de Nacion con Nivel de Programa
    // Muchos Plan de Nacion = 1 Nivel de Programa
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_NIVEL_PROGRAMA", referencedColumnName = "ID_NIVEL_PROGRAMA")
    @ApiModelProperty(notes = "Entidad del Nivel de Programa, se envia desde un Json (\"idNivelPrograma\": { \"idNivelPrograma\": \"valor\" })",
            required = true)
    private TblNivelPrograma idNivelPrograma;


    /**
     * Constructor de la Clase
     */
    public TblProgramaVisionPais() {
    }


    /**
     * Metodos Getters y Setters
     */
    public long getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(long idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getCodigoPrograma() {
        return codigoPrograma;
    }

    public void setCodigoPrograma(String codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public String getDescripcionPrograma() {
        return descripcionPrograma;
    }

    public void setDescripcionPrograma(String descripcionPrograma) {
        this.descripcionPrograma = descripcionPrograma;
    }

    public String getImagenPrograma() {
        return imagenPrograma;
    }

    public void setImagenPrograma(String imagenPrograma) {
        this.imagenPrograma = imagenPrograma;
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

    public TblTipoPrograma getIdTipoPrograma() {
        return idTipoPrograma;
    }

    public void setIdTipoPrograma(TblTipoPrograma idTipoPrograma) {
        this.idTipoPrograma = idTipoPrograma;
    }

    public TblProgramaVisionPais getProgramaPadreId() {
        return programaPadreId;
    }

    public void setProgramaPadreId(TblProgramaVisionPais programaPadreId) {
        this.programaPadreId = programaPadreId;
    }

    public TblNivelPrograma getIdNivelPrograma() {
        return idNivelPrograma;
    }

    public void setIdNivelPrograma(TblNivelPrograma idNivelPrograma) {
        this.idNivelPrograma = idNivelPrograma;
    }
}
