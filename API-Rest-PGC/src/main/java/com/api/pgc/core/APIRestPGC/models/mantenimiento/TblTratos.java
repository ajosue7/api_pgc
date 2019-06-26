package com.api.pgc.core.APIRestPGC.models.mantenimiento;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_tratos",
        indexes = {@Index(name = "idx_codigo_trato", columnList = "CODIGO_TRATO")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_TRATO"})})
//@SequenceGenerator( name = "id_tipo_sequence", sequenceName = "id_tipo_sequence", initialValue = 1, allocationSize = 1)
public class TblTratos {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRATO", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idTrato;

    @Column(name = "CODIGO_TRATO", nullable = false, length = 15)
    @ApiModelProperty(notes = "Codigo Trato", required = true)
    private String codigoTrato;

    @Column(name = "DESC_TRATO", nullable = false, length = 100)
    @ApiModelProperty(notes = "Descripción del Trato", required = true)
    private String desc_Trato;

    @Column(name = "ACTIVADA")
    private boolean activada;

    //Constructor vacio de la Clase, solo para Jpa
    public TblTratos() {
        //Este lo usa Jpa para realizar los Mapping
    }

    public long getIdTrato() {
        return idTrato;
    }

    public void setIdTrato(long idTrato) {
        this.idTrato = idTrato;
    }

    public String getCodigoTrato() {
        return codigoTrato;
    }

    public void setCodigoTrato(String codigoTrato) {
        this.codigoTrato = codigoTrato;
    }

    public String getDesc_Trato() {
        return desc_Trato;
    }

    public void setDesc_Trato(String desc_Trato) {
        this.desc_Trato = desc_Trato;
    }

    public boolean isActivada() {
        return activada;
    }

    public void setActivada(boolean activada) {
        this.activada = activada;
    }
}
