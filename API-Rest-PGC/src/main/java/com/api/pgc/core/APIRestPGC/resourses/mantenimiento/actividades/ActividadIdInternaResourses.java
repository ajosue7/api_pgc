package com.api.pgc.core.APIRestPGC.resourses.mantenimiento.actividades;


/*
 * Definicion de las Librerias a importar de la Clase
 */
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.actividades.ActividadIdInternaRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "ActividadIdInternaAPI" , description = "Operaciones sobre el Modulo de Actividades Id Interna")
public class ActividadIdInternaResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadIdInternaRepository _actividadIdInternaRepository;

    /**
     * Metodo que despliega la Lista de todas las idInternas de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version  19/12/2018/v1.0
     * @return Lista de Internas de una Actividad de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todas las Id Internas de una Actividad de la BD")
    @GetMapping(value = ID_INTERNA_ENDPOINT, produces = "application/json; charset=UTF-8" )
    public HashMap<String, Object> getAllActvidadesIdInternas() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas las Id Internas registradas en la BD";

        try{
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", _actividadIdInternaRepository.findAll() );
            msgExeptions.map.put( "countRecords", _actividadIdInternaRepository.count() );
            //Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega las Id Internas asociadas a la Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version  19/12/2018/v1.0
     * @return Estado de la BD
     * @param codIdInterna Identificador de la Id Interna a Buscar
     */
    @ApiOperation(value = "Retorna la Id Interna enviado a buscar de la BD")
    @GetMapping(value = ID_INTERNA_ENDPOINT_FIND_BY_CODIGO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getIdInternaByCod( @ApiParam(value="Identificador de la Id Interna a Buscar", required=true)
                                                  @PathVariable("codIdInterna") String codIdInterna ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            if ( _actividadIdInternaRepository.findByCodIdInterna( codIdInterna ) == null ){
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de la Id Interna Consultada";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            }else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de la Id Interna Consultada";
                msgExeptions.map.put("data", _actividadIdInternaRepository.findByCodIdInterna( codIdInterna ));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN

}
