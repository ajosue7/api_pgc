/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.actividades;

import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTratos;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblOrganizacion;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_actividades_contactos",
        indexes = {@Index(name = "idx_codigo_contacto", columnList = "CODIGO_CONTACTO")})
public class TblActividadContactos {
    // Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTACTO", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idContacto;

    @Column(name = "CODIGO_CONTACTO", nullable = false, length = 50)
    @ApiModelProperty(notes = "Codigo de la Actividad", required = true)
    private String codigoContacto;

    @Column(name = "NOMBRE_CONTACTO", nullable = false, length = 100)
    @ApiModelProperty(notes = "nombre del contacto", required = true)
    private String nombreContacto;

    @Column(name = "APELLIDO_CONTACTO", nullable = false, length = 100)
    @ApiModelProperty(notes = "apellido del contacto", required = true)
    private String apellidoContacto;

    @Column(name = "FUNCION_CONTACTO", nullable = false, length = 100)
    @ApiModelProperty(notes = "funcion del contacto", required = true)
    private String funcionContacto;

    @Column(name = "ORGANIZACION_CONTACTO", nullable = false, length = 100)
    @ApiModelProperty(notes = "organizacion del contacto", required = true)
    private String organizacionContacto;

    @Column(name = "D_FISICA_CONTACTO", nullable = false, length = 100)
    @ApiModelProperty(notes = "direcion fisica del contacto", required = true)
    private String dFisicaContacto;

    @Column(name = "TELEFONO1_CONTACTO", nullable = false, length = 15)
    @ApiModelProperty(notes = "telefono1 del contacto", required = true)
    private String telefono1Contacto;

    @Column(name = "TELEFONO2_CONTACTO", nullable = false, length = 15)
    @ApiModelProperty(notes = "telefono2 del contacto", required = true)
    private String telefono2Contacto;

    @Column(name = "EXT1_CONTACTO", nullable = false, length = 15)
    @ApiModelProperty(notes = "extencion1 del contacto", required = true)
    private String ext1Contacto;

    @Column(name = "EXT2_CONTACTO", nullable = false, length = 15)
    @ApiModelProperty(notes = "extenccion2 del contacto", required = true)
    private String ext2Contacto;

    @Column(name = "EMAIL1_CONTACTO", nullable = false, length = 100)
    @ApiModelProperty(notes = "email1 del contacto", required = true)
    private String email1Contacto;

    @Column(name = "EMAIL2_CONTACTO", nullable = false, length = 100)
    @ApiModelProperty(notes = "email2 del contacto", required = true)
    private String email2Contacto;

    // Mapeo de la Relacion de la Tabla de contactos con tipo trato o titulo
    // Muchos contactos  = 1 Tipo de trato o titulo
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TRATO", referencedColumnName = "ID_TRATO")
    @ApiModelProperty(notes = "Entidad de id trato, se envia desde un Json (\"idTrato\": { \"idTrato\": \"valor\" })", required = true)
    private TblTratos idTrato;

    // Mapeo de la Relacion de la Tabla de contactos con organizacion
    // Muchos contactos  = muchas organizacino
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ORGANIZACION", referencedColumnName = "ID_ORGANIZACION")
    @ApiModelProperty(notes = "Entidad de id trato, se envia desde un Json (\"idOrganizacion\": { \"idOrganizacion\": \"valor\" })", required = true)
    private TblOrganizacion idOrganizacion;


    // Campos de Auditoria
    @Column(name = "FECHA_CREACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha de Creacion", readOnly = true)
    private Date fechaCreacion = new Date();

    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    @ApiModelProperty(notes = "Hora de Creacion del Recurso, formato hh:mm:ss", readOnly = true)
    private Date horaCreacion = new Date();


    @Column(name = "ACTIVO")
    @ApiModelProperty(notes = "Activo", readOnly = true)
    private boolean activo = true;

    /*
     *Constructor vacio de la Clase, solo para Jpa
     */
    public TblActividadContactos() {
        // Este lo usa Jpa para realizar los Mapping
    }

    /*
     * Defincion de los Metodos getters y Setters
     */

    public long getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(long idContacto) {
        this.idContacto = idContacto;
    }

    public String getCodigoContacto() {
        return codigoContacto;
    }

    public void setCodigoContacto(String codigoContacto) {
        this.codigoContacto = codigoContacto;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getApellidoContacto() {
        return apellidoContacto;
    }

    public void setApellidoContacto(String apellidoContacto) {
        this.apellidoContacto = apellidoContacto;
    }

    public String getFuncionContacto() {
        return funcionContacto;
    }

    public void setFuncionContacto(String funcionContacto) {
        this.funcionContacto = funcionContacto;
    }

    public String getOrganizacionContacto() {
        return organizacionContacto;
    }

    public void setOrganizacionContacto(String organizacionContacto) {
        this.organizacionContacto = organizacionContacto;
    }

    public String getdFisicaContacto() {
        return dFisicaContacto;
    }

    public void setdFisicaContacto(String dFisicaContacto) {
        this.dFisicaContacto = dFisicaContacto;
    }

    public String getTelefono1Contacto() {
        return telefono1Contacto;
    }

    public void setTelefono1Contacto(String telefono1Contacto) {
        this.telefono1Contacto = telefono1Contacto;
    }

    public String getTelefono2Contacto() {
        return telefono2Contacto;
    }

    public void setTelefono2Contacto(String telefono2Contacto) {
        this.telefono2Contacto = telefono2Contacto;
    }

    public String getExt1Contacto() {
        return ext1Contacto;
    }

    public void setExt1Contacto(String ext1Contacto) {
        this.ext1Contacto = ext1Contacto;
    }

    public String getExt2Contacto() {
        return ext2Contacto;
    }

    public void setExt2Contacto(String ext2Contacto) {
        this.ext2Contacto = ext2Contacto;
    }

    public String getEmail1Contacto() {
        return email1Contacto;
    }

    public void setEmail1Contacto(String email1Contacto) {
        this.email1Contacto = email1Contacto;
    }

    public String getEmail2Contacto() {
        return email2Contacto;
    }

    public void setEmail2Contacto(String email2Contacto) {
        this.email2Contacto = email2Contacto;
    }

    public TblTratos getIdTrato() {
        return idTrato;
    }

    public void setIdTrato(TblTratos idTrato) {
        this.idTrato = idTrato;
    }

    public TblOrganizacion getIdOrganizacion() {
        return idOrganizacion;
    }

    public void setIdOrganizacion(TblOrganizacion idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(Date horaCreacion) {
        this.horaCreacion = horaCreacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
