package com.api.pgc.core.APIRestPGC.resourses;


import com.api.pgc.core.APIRestPGC.models.TblEstado;
import com.api.pgc.core.APIRestPGC.repository.EstadosRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/estados")
@Api(value = "estadosapi" , description = "Operaciones sobre el Modulo de Estados")
public class EstadosResources {

    @Autowired
    EstadosRepository estadosRepository;

    /**
     * Metodo que despliega la Lista de todos los Estados de la BD
     * @autor Nahum Martinez | NAM
     * @version  10/04/2018/v1.0
     * @return Lista de Estados de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Estados de la BD")
    @GetMapping(value = "/list", produces = "application/json" )
    public List<TblEstado> getAllEst(){
        return estadosRepository.findAll();
    }


    /**
     * Metodo que Solcita un json con los datos de la Entidad Estados
     * @autor Nahum Martinez | NAM
     * @version  10/04/2018/v1.0
     * @return Mensaje de Confirmacion de Registro de estado
     * @param estadoJson Obtiene desde el request los datos del estado a ingresar
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del nuevo Estado")
    @PostMapping(value = "/add")
    public List<TblEstado> addEst(@RequestBody final TblEstado estadoJson){
        estadosRepository.save(estadoJson);
        return estadosRepository.findAll();
    }

}
