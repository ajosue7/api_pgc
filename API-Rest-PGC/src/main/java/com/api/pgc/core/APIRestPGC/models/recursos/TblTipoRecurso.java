/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.recursos;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_tipo_recursos",
        indexes = {@Index(name = "idx_cod_tipo_recurso", columnList = "COD_TIPO_RECURSO")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"COD_TIPO_RECURSO"})})
public class TblTipoRecurso {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_RECURSO", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idTipoRecurso;

    @Column(name = "COD_TIPO_RECURSO", nullable = false, length = 10)
    @ApiModelProperty(notes = "Codigo Tipo de Recurso", required = true)
    private String codtiporecurso;

    @Column(name = "DESCRIPCION_TIPO_RECURSO", nullable = false, length = 100)
    @ApiModelProperty(notes = "Descripción de Tipo de Recurso", required = true)
    private String descripcionTipoRecurso;

    @Column(name = "ACTIVO")
    private boolean activo = true;


    //Constructor vacio de la Clase, solo para Jpa
    public TblTipoRecurso() {
        //Este lo usa Jpa para realizar los Mapping
    }


    /**
     * Metodos Getter y Setter
     *
     * @return
     */
    public long getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(long idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }

    public String getCodtiporecurso() {
        return codtiporecurso;
    }

    public void setCodtiporecurso(String codtiporecurso) {
        this.codtiporecurso = codtiporecurso;
    }

    public String getDescripcionTipoRecurso() {
        return descripcionTipoRecurso;
    }

    public void setDescripcionTipoRecurso(String descripcionTipoRecurso) {
        this.descripcionTipoRecurso = descripcionTipoRecurso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
