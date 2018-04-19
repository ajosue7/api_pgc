package com.api.pgc.core.APIRestPGC.models.seguridad;


import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblGrupo;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTipo;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_usuarios")
public class TblUsuarios {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID_USUARIO", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idUsuario;

    @Column(name = "COD_USUARIO", nullable = false, length=20)
    @ApiModelProperty(notes = "Codigo Usuario", required = true)
    private String codUsuario;

    @Column(name = "NOMBRE1_USUARIO", nullable = false, length=25)
    @ApiModelProperty(notes = "Primer Nombre", required = true)
    private String nombre1Usuario;

    @Column(name = "NOMBRE2_USUARIO", length=25)
    @ApiModelProperty(notes = "Segundo Nombre")
    private String nombre2Usuario;

    @Column(name = "APELLIDO1_USUARIO", nullable = false, length=25)
    @ApiModelProperty(notes = "Primer Apellido", required = true)
    private String apellido1Usuario;

    @Column(name = "APELLIDO2_USUARIO", length=25)
    @ApiModelProperty(notes = "Segundo Apellido")
    private String apellido2Usuario;

    @Column(name = "EMAIL_USUARIO", nullable = false, length=80)
    @ApiModelProperty(notes = "Email", required = true)
    private String emailUsuario;

    @Column(name = "INICIALES_USUARIO", length=4)
    @ApiModelProperty(notes = "Iniciales")
    private String inicialesUsuario;

    @Column(name = "PASSWORD_USUARIO", nullable = false, length=200)
    @ApiModelProperty(notes = "Iniciales Usuario", required = true)
    private String passwordUsuario;

    @Column(name = "IMAGEN_USUARIO")
    @ApiModelProperty(notes = "Imagen del Usuario")
    private String imagenUsuario;

    //Relaciones de Tablas
    //Mapeo de la Relacion de la Tabla de Usuarios con Tipos
    //Muchos Usuario = 1 Tipo
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TIPO_USUARIO", referencedColumnName = "ID_TIPO")
    @ApiModelProperty(notes = "Entidad del Tipo, se envia desde un Json (\"idTipoUsuario\": { \"idTipo\": \"valor\" })", required = true)
    private TblTipo idTipoUsuario;


    /**
     * Constructor de la Clase
     */
    public TblUsuarios() {
    }


    /**
     * Metodos Getters y Setters
     */
    public TblTipo getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(TblTipo idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getNombre2Usuario() {
        return nombre2Usuario;
    }

    public void setNombre2Usuario(String nombre2Usuario) {
        this.nombre2Usuario = nombre2Usuario;
    }

    public String getApellido1Usuario() {
        return apellido1Usuario;
    }

    public void setApellido1Usuario(String apellido1Usuario) {
        this.apellido1Usuario = apellido1Usuario;
    }

    public String getApellido2Usuario() {
        return apellido2Usuario;
    }

    public void setApellido2Usuario(String apellido2Usuario) {
        this.apellido2Usuario = apellido2Usuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getInicialesUsuario() {
        return inicialesUsuario;
    }

    public void setInicialesUsuario(String inicialesUsuario) {
        this.inicialesUsuario = inicialesUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    public String getImagenUsuario() {
        return imagenUsuario;
    }

    public void setImagenUsuario(String imagenUsuario) {
        this.imagenUsuario = imagenUsuario;
    }
}
