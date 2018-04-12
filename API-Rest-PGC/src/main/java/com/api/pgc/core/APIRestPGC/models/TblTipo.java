package com.api.pgc.core.APIRestPGC.models;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_tipos")
//@SequenceGenerator( name = "id_tipo_sequence", sequenceName = "id_tipo_sequence", initialValue = 1, allocationSize = 1)
public class TblTipo {
    //Propiedades de la tabla
    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_tipo_sequence")
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID_TIPO", columnDefinition = "serial")
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

    //Mapeo de la Relacion de la Tabla de Tipos con Grupos
    // Muchos Tipos = 1 Grupo
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO")
    private TblGrupo idGrupo;

    /*@ManyToOne
    @JoinColumn( name = "ID_GRUPO")
    private TblGrupo idGrupo;*/


    //Constructor vacio de la Clase, solo para Jpa
    public TblTipo() {
        //Este lo usa Jpa para realizar los Mapping
    }


    public TblGrupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(TblGrupo idGrupo) {
        this.idGrupo = idGrupo;
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


}
