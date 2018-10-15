package com.api.pgc.core.APIRestPGC.resourses.organizaciones;


import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblEstado;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblGrupo;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblTipoOrganizacion;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.EstadosRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.GruposRepository;
import com.api.pgc.core.APIRestPGC.repository.organizaciones.TipoOrganizacionRepository;
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
@Api(value = "tipoorganizacionapi", description = "Operaciones sobre el Modulo de Tipo de Organizacion")
public class TipoOrganizacionesResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    TipoOrganizacionRepository tipoOrganizacionRepository;


    /**
     * Metodo que despliega la Lista de todos los Tipos de Organizaciones de la BD
     *
     * @return Lista de Estados de la BD
     * @autor Nahum Martinez | NAM
     * @version 13/10/2018/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Tipos de Organizaciones de la BD")
    @GetMapping(value = ORGANIZACIONES_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllTipoOrganizaciones() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Tipos de Organizaciones registrados en la BD";

        try {
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", tipoOrganizacionRepository.findAll());

            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Tipo de Organizacion de la BD
     *
     * @param idTipoOrganizacion Identificador del Tipo de Organizacion a Buscar
     * @return Tipo de Organizacion de la BD
     * @autor Nahum Martinez | NAM
     * @version 13/10/2018/v1.0
     */
    @ApiOperation(value = "Retorna el Tipo de Organizacion enviado a buscar de la BD")
    @GetMapping(value = ORGANIZACIONES_ENDPOINT_FIND_BY_ID, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getTipoOrganizacion(@ApiParam(value = "Identificador del Tipo de Organizacion a Buscar", required = true)
                                                       @PathVariable("idTipoOrganizacion") long idTipoOrganizacion) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (tipoOrganizacionRepository.findByIdTipoOrganizacion(idTipoOrganizacion) == null) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Tipo de Organizacion consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Tipo de Organizacion Consultado";
                msgExeptions.map.put("data", tipoOrganizacionRepository.findByIdTipoOrganizacion(idTipoOrganizacion));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que Solcita un json con los datos de la Entidad Tipo de Organizacion
     *
     * @param tipoOrganizacionJson Obtiene desde el request los datos del Tipo de Organizacion a ingresar
     * @return Mensaje de Confirmacion de Registro de Tipo de Organizacion
     * @autor Nahum Martinez | NAM
     * @version 13/10/2018/v1.0
     */
    //@ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del nuevo Estado")
    //@PostMapping(value = "/estados", produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addTipoOrganizacion(@ApiParam(value = "Json del nuevo Tipo de Organizacion a Ingresar", required = true)
                                                       @RequestBody final TblTipoOrganizacion tipoOrganizacionJson) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            //Realizamos la Persistencia de los Datos
            tipoOrganizacionRepository.save(tipoOrganizacionJson);

            //return tiposRepository.findAll();
            msgMethod = "Se ha Ingresado de forma satisfactoria!!";

            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            msgMethod = "No existe el Grupo que buscas, por favor verfica que el Estado ingresado es correcto.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }

        //return estadosRepository.findAll();
    }

}
