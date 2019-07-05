package com.api.pgc.core.APIRestPGC.resourses.actividades;

//Imports de la Clase
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadContactosRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.ContactoProyectoRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.GruposRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.TiposRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "ContactoProyectoapi" , description = "Operaciones sobre el Modulo de ContactoProyecto", tags = "ContactoProyecto")
public class ContactoProyectoResources {

    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ContactoProyectoRepository _ContactoProyectoRepository;

    @Autowired
    GruposRepository _gruposRepository;

    @Autowired
    TiposRepository _tipoosRepository;

    @Autowired
    ActividadContactosRepository _ActividadesContactosRepository;


    /**
     * Metodo que despliega la Lista de todos los contacto-proyeccto de la BD
     * @autor Jorge Escamilla | JOE
     * @version  26/06/2019/v1.0
     * @return Lista de Grupos de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todos los contacto-proyecto de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = CONTACTO_PROYECTO_ENDPOINT, produces = "application/json")
    public HashMap<String, Object>  getAllContactoProyecto() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los ContactoProyecto registrados en la BD";

        try{
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", _ContactoProyectoRepository.findAll() );
            //Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Grupo de la BD
     * @autor Jorge Escamilla | JOE
     * @version  26/06/2019/v1.0
     * @return Grupo de la BD
     * @param idContactoProyecto Identificador del Tipo a Buscar
     */
    @ApiOperation(value = "Retorna el ContactoProyecto enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro Encontrado"),
            @ApiResponse(code = 401, message = "No estas Autenticado"),
            @ApiResponse(code = 403, message = "No estas Autorizado para usar el Servicio"),
            @ApiResponse(code = 404, message = "Recurso no encontrado")})
    @GetMapping( value = CONTACTO_PROYECTO_ENDPOINT_FIND_BY_ID, produces = "application/json")
    public HashMap<String, Object> getContactoProyecto( @ApiParam(value="Identificador del ContactoProyecto a Buscar", required=true)
                                                @PathVariable ("idContactoProyecto") long idContactoProyecto ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            if( _ContactoProyectoRepository.findByIdContactoProyecto(idContactoProyecto) == null ){
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del ContactoProyecto consultado";
                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            }else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del ContactoProyecto Consultado";
                msgExeptions.map.put("data", _ContactoProyectoRepository.findByIdContactoProyecto(idContactoProyecto));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN



}
