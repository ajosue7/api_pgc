package com.api.pgc.core.APIRestPGC.resourses.actividades.financiamiento.detalle;


import com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle.ActividadTipoFinanciamientoRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "estadosapi", description = "Operaciones sobre el Modulo de Estados", tags = "Estados")
public class TipoFinanciamientoResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadTipoFinanciamientoRepository _actividadTipoFinanciamientoRepository;

    /**
     * Metodo que despliega la Lista de todos Tipos de Financiamiento de Ayuda de la BD
     *
     * @return Lista de Tipos de Financiamiento de Ayuda de la BD
     * @autor Nahum Martinez | NAM
     * @version 23/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos Tipos de Financiamiento de Ayuda de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = TIPO_FINANC_ACT_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllTiposFinanciamiento() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos Tipos de Financiamiento de Ayuda registrados en la BD";

        try {
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadTipoFinanciamientoRepository.findAll());
            msgExeptions.map.put("totalRecords", _actividadTipoFinanciamientoRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getAllTiposFinanciamiento


    /**
     * Metodo que despliega la Tipos de Financiamiento de Ayuda de la BD
     *
     * @param idTipoFinanciamiento Identificador de la Tipos de Financiamiento de Ayuda a Buscar
     * @return Tipos de Financiamiento de Ayuda de la BD
     * @autor Nahum Martinez | NAM
     * @version 23/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna la Tipos de Financiamiento de Ayuda enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = TIPO_FINANC_ACT_ENDPOINT_FIND_BY_ID, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getByIdTiposFinanciamiento(@ApiParam(value = "Identificador dla Tipos de Financiamiento de Ayuda a Buscar", required = true)
                                                              @PathVariable("idTiposFinanciamiento") long idTipoFinanciamiento) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadTipoFinanciamientoRepository.findByIdTipoFinanciamiento(idTipoFinanciamiento) == null) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato dla Tipos de Financiamiento de Ayuda consultado";

                msgExeptions.map.put("data", _actividadTipoFinanciamientoRepository.findByIdTipoFinanciamiento(idTipoFinanciamiento));
                msgExeptions.map.put("findRecord", false);
                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle dla Tipos de Financiamiento de Ayuda Consultado";
                msgExeptions.map.put("findRecord", true);
                msgExeptions.map.put("data", _actividadTipoFinanciamientoRepository.findByIdTipoFinanciamiento(idTipoFinanciamiento));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } //FIN | getByIdTiposFinanciamiento

}
