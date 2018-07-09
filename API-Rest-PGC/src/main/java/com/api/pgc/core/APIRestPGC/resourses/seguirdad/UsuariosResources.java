package com.api.pgc.core.APIRestPGC.resourses.seguirdad;

//Imports de la Clase
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblEstado;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblPais;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTipo;
import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.EstadosRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.PaisRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.TiposRepository;
import com.api.pgc.core.APIRestPGC.repository.seguridad.UsuariosRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

// @CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/rest")
@Api(value = "userApi" , description = "Operaciones sobre el Modulo de Usuarios")
public class UsuariosResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    TiposRepository tiposRepository;

    @Autowired
    EstadosRepository estadosRepository;

    @Autowired
    PaisRepository paisRepository;


    //Metodos Principales de la Clase
    /**
     * Metodo que despliega la Lista de todos los Usuarios de la BD
     * @autor Nahum Martinez | NAM
     * @version  18/04/2018/v1.0
     * @return Lista de Usuarios de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Usuarios de la BD")
    @GetMapping(value = "/usuarios", produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllUsers() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            msgMethod = "Listado de todos los Usuarios registrados en la BD";

            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", usuariosRepository.findAll() );
            msgExeptions.map.put("totalRecords", usuariosRepository.count());

            //Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Usuario de la BD
     * @autor Nahum Martinez | NAM
     * @version  18/04/2018/v1.0
     * @return Usuario de la BD
     * @param codUsuario Identificador del Tipo a Buscar
     */
    @ApiOperation(value = "Retorna el Usuario enviado a buscar de la BD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro Encontrado"),
            @ApiResponse(code = 401, message = "No estas Autenticado"),
            @ApiResponse(code = 403, message = "No estas Autorizado para usar el Servicio"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error Interno del Servidor")})
    @GetMapping( value = "/usuarios/user/{codUsuario}", produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getUserByCod( @ApiParam(value="Identificador del Usuario a Buscar, por Código Interno", required=true)
                                            @PathVariable ("codUsuario") String codUsuario  ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            if( usuariosRepository.findByCodUsuario(codUsuario) == null ){
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Usuario consultado";
                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            }else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Usuario Consultado";
                msgExeptions.map.put("data", usuariosRepository.findByCodUsuario(codUsuario));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Usuario de la BD
     * @autor Nahum Martinez | NAM
     * @version  18/04/2018/v1.0
     * @return Usuario de la BD
     * @param idUsuario Identificador del Tipo a Buscar
     */
    @ApiOperation(value = "Retorna el Usuario enviado a buscar de la BD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro Encontrado"),
            @ApiResponse(code = 401, message = "No estas Autenticado"),
            @ApiResponse(code = 403, message = "No estas Autorizado para usar el Servicio"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error Interno del Servidor")})
    @GetMapping( value = "/usuarios/id/{idUsuario}", produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getUserById( @ApiParam(value="Identificador del Usuario a Buscar, por ID", required=true)
                                            @PathVariable ("idUsuario") long idUsuario  ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            if( usuariosRepository.findByIdUsuario(idUsuario) == null ){
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Usuario consultado";
                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 404);
            }else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Usuario Consultado";
                msgExeptions.map.put("data", usuariosRepository.findByIdUsuario(idUsuario));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que Solcita un json con los datos de la Entidad Usuarios
     * @autor Nahum Martinez | NAM
     * @version  18/04/2018/v1.0
     * @return Mensaje de Confirmacion de Registro de Usuario
     * @param userJson Obtiene desde el request los datos del usuario a ingresar
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del nuevo Usuario",
           notes = "Se debe incluir en la Estructura del JsonBean el Identificador de Datos de Relacion")
    @PostMapping(value = "/usuarios/new", produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addUsuario(@ApiParam(value="Json del nuevo Usuario a Ingresar, con Relacion asociado", required=true)
                                            @RequestBody final TblUsuarios userJson) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        Date dateActual = new Date();

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        try {
            //Busca el Tipo, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoUsuario": { "idTipo": valor })
            TblTipo tTipos = tiposRepository.findByIdTipo( userJson.getIdTipoUsuario().getIdTipo() );

            //Busca el Estado, desde el Reporsitorio con el Parametro del Json enviado ( "idEstadoUsuario": { "idEstado": valor })
            TblEstado tEstados = estadosRepository.findByIdEstado( userJson.getIdEstadoUsuario().getIdEstado() );

            //Busca el Estado, desde el Reporsitorio con el Parametro del Json enviado ( "idEstadoUsuario": { "idEstado": valor })
            TblPais tPais = paisRepository.findByIdPais( userJson.getIdPais().getIdPais() );

            //Graba los Datos de Tipos
            try {
                //Setea el valor Buscando de la Entidad Tipos de Usuario
                userJson.setIdTipoUsuario( tTipos );
                userJson.setIdEstadoUsuario( tEstados );
                userJson.setIdPais( tPais );

                //Seteo de las Fecha y Hora de Creacion
                userJson.setFechaCreacion( dateActual );
                userJson.setHoraCreacion( dateActual );

                userJson.setPasswordUsuario( encoder.encode( userJson.getPasswordUsuario() ) );

                System.out.println( "Dato de Parametro de Tipo ******************  " + tTipos.getIdTipo() );
                //Realizamos la Persistencia de los Datos
                usuariosRepository.save(userJson);

                //return tiposRepository.findAll();
                msgMethod = "Se ha Ingresado de forma satisfactoria!!";

                //Retorno del json
                return msgExeptions.msgJson( msgMethod, 200 );
            }catch ( Exception ex ){
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Usuario";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        }catch ( Exception ex ){
            msgMethod = "Ha Ocurrido un error al Intentar Grabar el Usuario";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Usuario de la BD
     * @autor Nahum Martinez | NAM
     * @version  18/04/2018/v1.0
     * @return Usuario de la BD
     * @param emailUsuario Emial del Usuario a buscar
     */
    @ApiOperation(value = "Retorna el Usuario enviado a buscar de la BD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro Encontrado"),
            @ApiResponse(code = 401, message = "No estas Autenticado"),
            @ApiResponse(code = 403, message = "No estas Autorizado para usar el Servicio"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error Interno del Servidor")})
    @GetMapping( value = "/usuarios/user/mail/{emailUsuario}", produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getUserByEmail( @ApiParam(value="Identificador del Usuario a Buscar, por Código Interno", required=true)
                                                 @PathVariable ("emailUsuario") String emailUsuario ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            if( usuariosRepository.findByEmailUsuario(emailUsuario) == null ){
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Usuario consultado";
                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            }else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Usuario Consultado";
                msgExeptions.map.put("data", usuariosRepository.findByEmailUsuario(emailUsuario));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


}
