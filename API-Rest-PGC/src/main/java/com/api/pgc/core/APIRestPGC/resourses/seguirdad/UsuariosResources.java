package com.api.pgc.core.APIRestPGC.resourses.seguirdad;

//Imports de la Clase
import com.api.pgc.core.APIRestPGC.config.Usuario;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblEstado;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblGrupo;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTipo;
import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.EstadosRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.TiposRepository;
import com.api.pgc.core.APIRestPGC.repository.seguridad.UsuariosRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "rest/usuarios")
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


    //Metodos Principales de la Clase
    /**
     * Metodo que despliega la Lista de todos los Usuarios de la BD
     * @autor Nahum Martinez | NAM
     * @version  18/04/2018/v1.0
     * @return Lista de Usuarios de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Usuarios de la BD")
    @GetMapping(value = "/list", produces = "application/json")
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
     * @param idUsuario Identificador del Tipo a Buscar
     */
    @ApiOperation(value = "Retorna el Usuario enviado a buscar de la BD")
    @GetMapping( value = "/show/{idUsuario}", produces = "application/json")
    public HashMap<String, Object> getUser( @ApiParam(value="Identificador del Usuario a Buscar", required=true)
                                            @PathVariable ("idUsuario") long idUsuario  ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            if( usuariosRepository.findByIdUsuario(idUsuario) == null ){
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Usuario consultado";
                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
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
    @PostMapping(value = "/add", produces = "application/json")
    public HashMap<String, Object> addUsuario(@ApiParam(value="Json del nuevo Usuario a Ingresar, con Relacion asociado", required=true)
                                            @RequestBody final TblUsuarios userJson) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        Date dateActual = new Date();

        try {
            //Busca el Tipo, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoUsuario": { "idTipo": valor })
            TblTipo tP = tiposRepository.findByIdTipo( userJson.getIdTipoUsuario().getIdTipo() );

            //Busca el Estado, desde el Reporsitorio con el Parametro del Json enviado ( "idEstadoUsuario": { "idEstado": valor })
            TblEstado tE = estadosRepository.findByIdEstado( userJson.getIdEstadoUsuario().getIdEstado() );

            //Graba los Datos de Tipos
            try {
                //Setea el valor Buscando de la Entidad Tipos de Usuario
                userJson.setIdTipoUsuario(tP);
                userJson.setIdEstadoUsuario(tE);

                //Seteo de las Fecha y Hora de Creacion
                userJson.setFechaCreacion( dateActual );
                userJson.setHoraCreacion( dateActual );

                System.out.println( "Dato de Parametro de Tipo ******************  " + tP.getIdTipo() );
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

}
