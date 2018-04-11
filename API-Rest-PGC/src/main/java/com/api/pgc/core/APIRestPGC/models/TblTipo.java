package com.api.pgc.core.APIRestPGC.models;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_tipos")
public class TblTipo {
    //Propiedades de la tabla
    @Id
    @GeneratedValue
    @Column(name = "ID_TIPO")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idTipo;

    @Column(name = "COD_TIPO")
    @ApiModelProperty(notes = "Codigo Tipo", required = true)
    private String codTipo;

    @Column(name = "DESC_TIPO")
    @ApiModelProperty(notes = "Descripción de Tipo", required = true)
    private String descTipo;

    @Column(name = "ACTIVADA")
    private boolean activada;

    //Join de la Columna Grupo
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO")
    @ApiModelProperty(notes = "Relación con el Grupo que pertenece de Tipo", required = true)
    private TblGrupo mGrupos;


    //Constructor vacio de la Clase, solo para Jpa
    public TblTipo() {
        //Este lo usa Jpa para realizar los Mapping
    }


    //Metodos Getters y Setters de la Clase
    public long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(long idTipo) {
        this.idTipo = idTipo;
    }

    public String getCodTipo() {
        return codTipo;
    }

    public void setCodTipo(String codTipo) {
        this.codTipo = codTipo;
    }

    public String getDescTipo() {
        return descTipo;
    }

    public void setDescTipo(String descTipo) {
        this.descTipo = descTipo;
    }

    public boolean isActivada() {
        return activada;
    }

    public void setActivada(boolean activada) {
        this.activada = activada;
    }

    public TblGrupo getmGrupos() {
        return mGrupos;
    }

    public void setmGrupos(TblGrupo mGrupos) {
        this.mGrupos = mGrupos;
    }
}
