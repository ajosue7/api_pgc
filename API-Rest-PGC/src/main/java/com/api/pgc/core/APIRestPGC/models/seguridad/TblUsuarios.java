package com.api.pgc.core.APIRestPGC.models.seguridad;

import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblEstado;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblPais;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTipo;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_usuarios",
        indexes = {@Index(name = "idx_cod_usuario", columnList = "COD_USUARIO" )})
public class TblUsuarios implements Serializable {
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
    @ApiModelProperty(notes = "Password Usuario", required = true)
    private String passwordUsuario;

    @Column(name = "IMAGEN_USUARIO")
    @ApiModelProperty(notes = "Imagen del Usuario")
    private String imagenUsuario;

    @Column(name = "FECHA_CREACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    //@ApiModelProperty(notes = "Fecha de Creacion del Usuario, formato hh:mm:ss")
    private Date fechaCreacion;

    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    //@ApiModelProperty(notes = "Hora de Creacion del Usuario, formato hh:mm:ss")
    private Date horaCreacion = new Date();

    @Column(name = "FECHA_MODIFICACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    //@ApiModelProperty(notes = "Fecha de Modificacion del Usuario")
    private Date fechaModificacion;

    @Column(name = "HORA_MODIFICACION")
    @Temporal(TemporalType.TIME)
    //@ApiModelProperty(notes = "Hora de Modificacion del Usuario, formato hh:mm:ss")
    private Date horaModificacion;

    @Column(name="ROL")
    @ApiModelProperty(notes = "Indica el Rol del Usuario dentro del Sistema")
    private byte rol;

    @Column(name = "ACTIVO")
    @ApiModelProperty(notes = "Indica si el Usuario esta Activo dentro del Sistema")
    private boolean activo;


    //Relaciones de Tablas
    //Mapeo de la Relacion de la Tabla de Usuarios con Tipos
    //Muchos Usuario = 1 Tipo
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TIPO_USUARIO", referencedColumnName = "ID_TIPO")
    @ApiModelProperty(notes = "Entidad del Tipo, se envia desde un Json (\"idTipoUsuario\": { \"idTipo\": \"valor\" })",
            required = true)
    private TblTipo idTipoUsuario;

    //Mapeo de la Relacion de la Tabla de Usuarios con Estados
    //Muchos Usuario = 1 Estado
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ESTADO_USUARIO", referencedColumnName = "ID_ESTADO")
    @ApiModelProperty(notes = "Entidad del Estado, se envia desde un Json (\"idEstadoUsuario\": { \"idEstado\": \"valor\" })",
            required = true)
    private TblEstado idEstadoUsuario;


    //Mapeo de la Relacion de la Tabla de Usuarios con Pais
    //Muchos Usuario = 1 Pais
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PAIS_USUARIO", referencedColumnName = "ID_PAIS")
    @ApiModelProperty(notes = "Entidad del País, se envia desde un Json (\"idPaisUsuario\": { \"idPais\": \"valor\" })",
            required = true)
    private TblPais idPais;


    /**
     * Constructor de la Clase
     */
    public TblUsuarios() {
    }

    /**
     * Metodos Getters y Setters
     */
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

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getHoraModificacion() {
        return horaModificacion;
    }

    public void setHoraModificacion(Date horaModificacion) {
        this.horaModificacion = horaModificacion;
    }


    public TblTipo getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(TblTipo idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public TblEstado getIdEstadoUsuario() {
        return idEstadoUsuario;
    }

    public void setIdEstadoUsuario(TblEstado idEstadoUsuario) {
        this.idEstadoUsuario = idEstadoUsuario;
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

    public byte getRol() {
        return rol;
    }

    public void setRol(byte rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public TblPais getIdPais() {
        return idPais;
    }

    public void setIdPais(TblPais idPais) {
        this.idPais = idPais;
    }
}
