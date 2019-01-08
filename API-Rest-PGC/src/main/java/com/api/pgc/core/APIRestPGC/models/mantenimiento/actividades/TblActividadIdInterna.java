package com.api.pgc.core.APIRestPGC.models.mantenimiento.actividades;

import com.api.pgc.core.APIRestPGC.models.organizaciones.TblOrganizacion;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_actividades_id_internas",
        indexes = {@Index(name = "idx_codigo_id_interna", columnList = "CODIGO_ID_INTERNA" )},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_ID_INTERNA"})})
public class TblActividadIdInterna {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID_INTERNA", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idInterna;

    @Column(name = "CODIGO_ID_INTERNA", nullable = false, length=50)
    @ApiModelProperty(notes = "Codigo de la Estrategia de la Actividad", required = true)
    private String codIdInterna;

    //Mapeo de la Relacion de la Tabla de Id Interna con Organizaciones
    // Muchos Id Internas = 1 Organizacion
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ORGANIZACION", referencedColumnName = "ID_ORGANIZACION")
    @ApiModelProperty(notes = "Entidad de la Organizacion, se envia desde un Json (\"idOrganizacion\": { \"idOrganizacion\": \"valor\" })", required = true)
    private TblOrganizacion idOrganizacionIdInterna;


    // Constructor vacio de la Clase, solo para Jpa
    public TblActividadIdInterna() {
        // Este lo usa Jpa para realizar los Mapping
    }


    public long getIdInterna() {
        return idInterna;
    }

    public void setIdInterna(long idInterna) {
        this.idInterna = idInterna;
    }

    public String getCodigoIdInterna() {
        return codIdInterna;
    }

    public void setCodigoIdInterna(String codigoIdInterna) {
        this.codIdInterna = codigoIdInterna;
    }

    public TblOrganizacion getIdOrganizacionIdInterna() {
        return idOrganizacionIdInterna;
    }

    public void setIdOrganizacionIdInterna(TblOrganizacion idOrganizacionIdInterna) {
        this.idOrganizacionIdInterna = idOrganizacionIdInterna;
    }
}
