package com.api.pgc.core.APIRestPGC.resourses.actividades.financiamiento.detalle;


import com.api.pgc.core.APIRestPGC.repository.actividades.financiamiento.detalle.ActividadModalidadAyudaRepository;
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
@Api(value = "ModalidadAyudaApi", description = "Operaciones sobre el Modulo de Proyectos Financiamiento", tags = "Financiamiento de Proyecto")
public class ModalidadAyudaResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadModalidadAyudaRepository _actividadModalidadAyudaRepository;

    /**
     * Metodo que despliega la Lista de todos las Modalidades de Ayuda de la BD
     *
     * @return Lista de Modalidades de Ayuda de la BD
     * @autor Nahum Martinez | NAM
     * @version 23/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos las Modalidades de Ayuda de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = MOD_AYUDA_ACT_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllModalidadAyuda() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos las Modalidades de Ayuda registrados en la BD";

        try {
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put("data", _actividadModalidadAyudaRepository.findAll());
            msgExeptions.map.put("totalRecords", _actividadModalidadAyudaRepository.count());
            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getAllModalidadAyuda


    /**
     * Metodo que despliega la Modalidades de Ayuda de la BD
     *
     * @param idModalidadAyuda Identificador de la Modalidades de Ayuda a Buscar
     * @return Modalidades de Ayuda de la BD
     * @autor Nahum Martinez | NAM
     * @version 23/05/2019/v1.0
     */
    @ApiOperation(value = "Retorna la Modalidades de Ayuda enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = MOD_AYUDA_ACT_ENDPOINT_FIND_BY_ID, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getByIdModalidadAyuda(@ApiParam(value = "Identificador dla Modalidades de Ayuda a Buscar", required = true)
                                              @PathVariable("idModalidadAyuda") long idModalidadAyuda) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (_actividadModalidadAyudaRepository.findByIdModalidadAyuda(idModalidadAyuda) == null) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato dla Modalidades de Ayuda consultado";

                msgExeptions.map.put("data", _actividadModalidadAyudaRepository.findByIdModalidadAyuda(idModalidadAyuda));
                msgExeptions.map.put("findRecord", false);
                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle dla Modalidades de Ayuda Consultado";
                msgExeptions.map.put("findRecord", true);
                msgExeptions.map.put("data", _actividadModalidadAyudaRepository.findByIdModalidadAyuda(idModalidadAyuda));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } //FIN | getByIdModalidadAyuda
}
