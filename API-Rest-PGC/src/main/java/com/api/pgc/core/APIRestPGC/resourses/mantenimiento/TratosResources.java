package com.api.pgc.core.APIRestPGC.resourses.mantenimiento;

//Imports de la Clase
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.TratosRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "tratosapi" , description = "Operaciones sobre el Modulo de Tratos", tags = "Tratos")
public class TratosResources {

    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    TratosRepository _tratosRepository;




    /**
     * Metodo que despliega la Lista de todos los Tratos contacto de la BD
     * @autor Jorge Escamilla | JOE
     * @version  27/06/2019/v1.0
     * @return Lista de Tratos de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todos los tratos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = TRATOS_ENDPOINT, produces = "application/json")
    public HashMap<String, Object>  getAllTratos() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Tratos registrados en la BD";

        try{
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", _tratosRepository.findAll() );
            //Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Trato de la BD
     * @autor Jorge Escamilla | JOE
     * @version  27/06/2019/v1.0
     * @return Tratos de la BD
     * @param idTrato Identificador del Trato a Buscar
     */
    @ApiOperation(value = "Retorna el Trato enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro Encontrado"),
            @ApiResponse(code = 401, message = "No estas Autenticado"),
            @ApiResponse(code = 403, message = "No estas Autorizado para usar el Servicio"),
            @ApiResponse(code = 404, message = "Recurso no encontrado")})
    @GetMapping( value = TRATOS_ENDPOINT_FIND_BY_ID, produces = "application/json")
    public HashMap<String, Object> getTratos( @ApiParam(value="Identificador del Trato a Buscar", required=true)
                                                @PathVariable ("idTrato") long idTrato  ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            if( _tratosRepository.findByIdTrato(idTrato) == null ){
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Trato consultado";
                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            }else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Trato Consultado";
                msgExeptions.map.put("data", _tratosRepository.findByIdTrato(idTrato));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN




}
