package com.api.pgc.core.APIRestPGC.models;


import javax.persistence.*;

@Entity
@Table(name = "tbl_grupos")
public class TblGrupo {

    @Id
    @GeneratedValue
    @Column(name = "ID_GRUPO")
    private long idGrupo;

    @Column(name = "COD_GRUPO")
    private String codGrupo;

    @Column(name = "DESC_GRUPO")
    private String descGrupo;

    @Column(name = "HABILITADA")
    private boolean habilitada;


    //Constructor de la Clase Modelo
    public TblGrupo() {
    }


    //Metodos Getters y Setters
    public long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(String codGrupo) {
        this.codGrupo = codGrupo;
    }

    public String getDescGrupo() {
        return descGrupo;
    }

    public void setDescGrupo(String descGrupo) {
        this.descGrupo = descGrupo;
    }

    public boolean isHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }
}
