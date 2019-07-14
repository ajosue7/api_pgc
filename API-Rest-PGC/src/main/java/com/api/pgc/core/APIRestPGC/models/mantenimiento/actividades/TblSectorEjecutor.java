package com.api.pgc.core.APIRestPGC.models.mantenimiento.actividades;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;


@Entity
@Table(name = "tbl_sector_ejecutor_actividad",
        indexes = {@Index(name = "idx_codigo_sector_ejeutor", columnList = "CODIGO_SECTOR_EJEUTOR" )},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_SECTOR_EJEUTOR"})})
public class TblSectorEjecutor {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_SECTOR_EJECUTOR", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idSectorEjecutor;

    @Column(name = "CODIGO_SECTOR_EJEUTOR", nullable = false, length=10)
    @ApiModelProperty(notes = "Codigo del Sector Ejecutor de la Actividad", required = true)
    private String codigoSectorEjecutor;

    @Column(name = "DESCRIPION_SECTOR_EJECUTOR", nullable = false, length=150)
    @ApiModelProperty(notes = "Descripcion Sector Ejecutor", required = true)
    private String descripcionSectorEjecutor;

    @Column(name = "HABILITADA")
    private boolean habilitada;


    public TblSectorEjecutor() {
    }

    public long getIdSectorEjecutor() {
        return idSectorEjecutor;
    }

    public void setIdSectorEjecutor(long idSectorEjecutor) {
        this.idSectorEjecutor = idSectorEjecutor;
    }

    public String getCodigoSectorEjecutor() {
        return codigoSectorEjecutor;
    }

    public void setCodigoSectorEjecutor(String codigoSectorEjecutor) {
        this.codigoSectorEjecutor = codigoSectorEjecutor;
    }

    public String getDescripcionSectorEjecutor() {
        return descripcionSectorEjecutor;
    }

    public void setDescripcionSectorEjecutor(String descripcionSectorEjecutor) {
        this.descripcionSectorEjecutor = descripcionSectorEjecutor;
    }

    public boolean isHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }
}
