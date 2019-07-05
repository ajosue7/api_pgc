package com.api.pgc.core.APIRestPGC.models.actividades;


import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblGrupo;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTipo;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_contacto_proyecto",
        indexes = {@Index(name = "idx_id_contacto_proyecto", columnList = "ID_CONTACTO_PROYECTO")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"ID_CONTACTO_PROYECTO"})})
//@SequenceGenerator( name = "id_tipo_sequence", sequenceName = "id_tipo_sequence", initialValue = 1, allocationSize = 1)
public class TblContactoProyecto {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTACTO_PROYECTO", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idContactoProyecto;

    @Column(name = "DESC_CONTACTO_PROYECTO", nullable = false, length = 15)
    @ApiModelProperty(notes = "Descripcion del contacto proyecto", required = true)
    private String desc_Contacto_Proyecto;

    @Column(name = "ACTIVADA")
    private boolean activada;


    //Mapeo de la Relacion de la Tabla de contacto-proyecto con tipos
    // Muchos contacto-proyecto = 1 tipos
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TIPO", referencedColumnName = "ID_TIPO")
    @ApiModelProperty(notes = "Entidad del Tipo, se envia desde un Json (\"idTipo\": { \"idTipo\": \"valor\" })", required = true)
    private TblTipo idTipo;

    //Mapeo de la Relacion de la Tabla de contacto-proyecto con grupos
    // Muchos contacto-proyecto = 1 grupos
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO")
    @ApiModelProperty(notes = "Entidad del Grupo, se envia desde un Json (\"idGrupo\": { \"idGrupo\": \"valor\" })", required = true)
    private TblGrupo idGrupo;

    //Mapeo de la Relacion de la Tabla de contacto-proyecto con actividades contacto
    // Muchos contacto-proyecto = 1 actividades contacto
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CONTACTO", referencedColumnName = "ID_CONTACTO")
    @ApiModelProperty(notes = "Entidad del Contacto, se envia desde un Json (\"idContacto\": { \"idContacto\": \"valor\" })", required = true)
    private TblActividadContactos idContacto;


    //Constructor vacio de la Clase, solo para Jpa
    public TblContactoProyecto() {
        //Este lo usa Jpa para realizar los Mapping
    }

    public long getIdContactoProyecto() {
        return idContactoProyecto;
    }

    public void setIdContactoProyecto(long idContactoProyecto) {
        this.idContactoProyecto = idContactoProyecto;
    }

    public String getDesc_Contacto_Proyecto() {
        return desc_Contacto_Proyecto;
    }

    public void setDesc_Contacto_Proyecto(String desc_Contacto_Proyecto) {
        this.desc_Contacto_Proyecto = desc_Contacto_Proyecto;
    }

    public boolean isActivada() {
        return activada;
    }

    public void setActivada(boolean activada) {
        this.activada = activada;
    }

    public TblTipo getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TblTipo idTipo) {
        this.idTipo = idTipo;
    }

    public TblGrupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(TblGrupo idGrupo) {
        this.idGrupo = idGrupo;
    }

    public TblActividadContactos getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(TblActividadContactos idContacto) {
        this.idContacto = idContacto;
    }
}
