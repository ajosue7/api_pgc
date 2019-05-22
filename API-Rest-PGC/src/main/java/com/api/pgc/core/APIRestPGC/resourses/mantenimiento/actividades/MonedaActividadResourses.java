/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.mantenimiento.actividades;


import com.api.pgc.core.APIRestPGC.repository.mantenimiento.actividades.MonedaActividadRepository;
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
@Api(value = "monedaActividadApi", description = "Operaciones sobre el Modulo de Mantenimiento de Actividades", tags = "Mantenimiento de Actividades")
public class MonedaActividadResourses {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    MonedaActividadRepository _monedaActividadRepository;


    /**
     * Metodo que despliega la Lista de todos las Monedas de una Actividad de la BD
     *
     * @return Lista de Monedas de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 20/05/2018/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos las Monedas de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = MONENDA_ACT_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllMonedas() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos las Monedas de Actividad para una Actividad registrados en la BD";

        try {
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _monedaActividadRepository.findAll());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } //FIN | getAllMonedas


    /**
     * Metodo que despliega la Moneda de la BD
     *
     * @param idMoneda Identificador de la Moneda a Buscar
     * @return Moneda Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version 20/05/2018/v1.0
     */
    @ApiOperation(value = "Retorna la Moneda enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = MONENDA_ACT_ENDPOINT_FIND_BY_ID, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getByIdMoneda(@ApiParam(value = "Identificador dla Moneda a Buscar", required = true)
                                                 @PathVariable("idMoneda") long idMoneda) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_monedaActividadRepository.findByIdMonedaActividad(idMoneda) == null) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de la Monedas Consultada";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de la Monedas Consultada";
                msgExeptions.map.put("data", _monedaActividadRepository.findByIdMonedaActividad(idMoneda));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } //FIN | getByIdMoneda
}
