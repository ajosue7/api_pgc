package com.api.pgc.core.APIRestPGC.models;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_estados")
public class TblEstado {
    //Propiedades de la tabla
    @Id
    @GeneratedValue
    @Column(name = "ID_ESTADO")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idEstado;

    @Column(name = "COD_ESTADO")
    @ApiModelProperty(notes = "Codigo Estado", required = true)
    private String codEstado;

    @Column(name = "DESC_ESTADO")
    @ApiModelProperty(notes = "Descripcion Estado", required = true)
    private String descEstado;

    @Column(name = "HABILITADA")
    private boolean habilitada;


    public TblEstado() {
        //Sin Parametros
    }

    public long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(long idEstado) {
        this.idEstado = idEstado;
    }

    public String getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(String codEstado) {
        this.codEstado = codEstado;
    }

    public String getDescEstado() {
        return descEstado;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }

    public boolean isHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }
}
