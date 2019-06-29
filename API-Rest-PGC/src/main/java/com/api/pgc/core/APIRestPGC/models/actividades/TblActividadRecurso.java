/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.actividades;

import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTipo;
import com.api.pgc.core.APIRestPGC.models.recursos.TblTipoRecurso;
import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_actividades_contactos",
        indexes = {@Index(name = "idx_codigo_actividades_contactos", columnList = "CODIGO_ACTIVIDAD_RECURSO")})
public class TblActividadRecurso {
    // Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ACTIVIDAD_RECURSO", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idActividadRecurso;

    @Column(name = "CODIGO_ACTIVIDAD_RECURSO", nullable = false, length = 50)
    @ApiModelProperty(notes = "Codigo de la Actividad", required = true)
    private String codigoActividadRecurso;

    @Column(name = "TITULO", nullable = false, length = 50)
    @ApiModelProperty(notes = "Titulo del recurso", required = true)
    private String titulo;


    @Column(name = "NOTA", nullable = false, length = 50)
    @ApiModelProperty(notes = "Titulo del recurso", required = true)
    private String nota;

    @Column(name = "DESCRIPCION", nullable = false, length = 50)
    @ApiModelProperty(notes = "Url del recurso", required = true)
    private String descripcion;

    @Column(name = "URL_ACTIVIDAD_RECURSO_DOCUMENTO", nullable = false, length = 50)
    @ApiModelProperty(notes = "Url del recurso", required = true)
    private String UrlActividadRecursoDocumento;

    @Column(name = "URL_ACTIVIDAD_RECURSO_LINK", nullable = false, length = 50)
    @ApiModelProperty(notes = "Url del recurso", required = true)
    private String urlActividadRecursoLink;







    // Mapeo de la Relacion de la Tabla de Planifiacion con Actividades
    // Muchos Recurso = 1 Actividad
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID_ACTIVIDAD")
    @ApiModelProperty(notes = "Entidad de la Actividad, se envia desde un Json (\"idActividad\": { \"idActividad\": \"valor\" })", required = true)
    private TblActividad idActividad;


    // Mapeo de la Relacion de la Tabla de Recursos con Tipo de Recursos
    // Muchos Recursos = 1 Tipo Recurso
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "ID_TIPO_RECURSOS", referencedColumnName = "ID_TIPO_RECURSOS")
    @ApiModelProperty(notes = "Entidad del Tipo de Recurso, se envia desde un Json (\"idTipoRecursos\": { \"idTipoRecursos\": \"valor\" })", required = true)
    private TblTipoRecurso idTipoRecursos;


    // Mapeo de la Relacion de la Tabla de Recursos con Usuarios
    // Muchos Recursos = 1 Usuario
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ApiModelProperty(notes = "Entidad del Usuario, se envia desde un Json (\"idUsuario\": { \"idUsuario\": \"valor\" })", required = true)
    private TblUsuarios idUsuario;

    // Relación con Tabla TblTipo
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "ID_TIPO", referencedColumnName = "ID_TIPO")
    @ApiModelProperty(notes = "Entidad del Usuario, se envia desde un Json (\"idTipo\": { \"idTipo\": \"valor\" })", required = true)
    private TblTipo idTipo;





    @Column(name = "ACTIVO")
    @ApiModelProperty(notes = "Activo", readOnly = true)
    private boolean activo = true;


    /*
     *Constructor vacio de la Clase, solo para Jpa
     */
    public TblActividadRecurso() {
        // Este lo usa Jpa para realizar los Mapping
    }

    /*
     * Defincion de los Metodos getters y Setters
     */

    public long getIdActividadRecurso() {
        return idActividadRecurso;
    }

    public void setIdActividadRecurso(long idActividadRecurso) {
        this.idActividadRecurso = idActividadRecurso;
    }

    public String getCodigoActividadRecurso() {
        return codigoActividadRecurso;
    }

    public void setCodigoActividadRecurso(String codigoActividadRecurso) {
        this.codigoActividadRecurso = codigoActividadRecurso;
    }


    public TblActividad getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(TblActividad idActividad) {
        this.idActividad = idActividad;
    }

    public TblTipoRecurso getIdTipoRecursos() {
        return idTipoRecursos;
    }

    public void setIdTipoRecurso(TblTipoRecurso idTipoRecursos) {
        this.idTipoRecursos = idTipoRecursos;
    }

    public void setIdTipoRecursos(TblTipoRecurso idTipoRecursos) {
        this.idTipoRecursos = idTipoRecursos;
    }

    public TblUsuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(TblUsuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TblTipo getIdTipo (){return idTipo; }

    public void setIdTipo(TblTipo idTipo) {this.idTipo = idTipo;}


    public String getUrlActividadRecursoLink() {
        return urlActividadRecursoLink;
    }

    public void setUrlActividadRecursoLink(String urlActividadRecursoLink) {
        this.urlActividadRecursoLink = urlActividadRecursoLink;
    }

    public String getUrlActividadRecursoDocumento() {
        return UrlActividadRecursoDocumento;
    }

    public void setUrlActividadRecursoDocumento(String urlActividadRecursoDocumento) {
        UrlActividadRecursoDocumento = urlActividadRecursoDocumento;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }



    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
