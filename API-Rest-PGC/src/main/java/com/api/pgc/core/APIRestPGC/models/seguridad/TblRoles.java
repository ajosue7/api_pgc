package com.api.pgc.core.APIRestPGC.models.seguridad;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_roles",
        indexes = {@Index(name = "idx_cod_rol", columnList = "COD_ROL" )},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"COD_ROL"})})
public class TblRoles {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID_ROL", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idRol;

    @Column(name = "COD_ROL", nullable = false, length=10)
    @ApiModelProperty(notes = "Codigo Rol", required = true)
    private String codUsuario;

    @Column(name = "DESC_ROL", nullable = false, length=100)
    @ApiModelProperty(notes = "Descripcion del Rol", required = true)
    private String nombre1Usuario;

    @Column(name = "HABILITADA")
    @ApiModelProperty(notes = "Habilitado")
    private boolean habilitada;


    /**
     * Constructor de la Clase
     */
    public TblRoles() {
    }


    /**
     * Metodos Getters y Setters
     */
    public long getIdRol() {
        return idRol;
    }

    public void setIdRol(long idRol) {
        this.idRol = idRol;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNombre1Usuario() {
        return nombre1Usuario;
    }

    public void setNombre1Usuario(String nombre1Usuario) {
        this.nombre1Usuario = nombre1Usuario;
    }

    public boolean isHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }
}
