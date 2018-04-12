package com.api.pgc.core.APIRestPGC.resourses;

//Imports de la Clase
import com.api.pgc.core.APIRestPGC.models.TblGrupo;
import com.api.pgc.core.APIRestPGC.models.TblTipo;
import com.api.pgc.core.APIRestPGC.repository.GruposRepository;
import com.api.pgc.core.APIRestPGC.repository.TiposRepository;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/tipos")
@Api(value = "tiposapi" , description = "Operaciones sobre el Modulo de Tipos")
public class TiposResources {

    @Autowired
    TiposRepository tiposRepository;

    @Autowired
    GruposRepository gruposRepository;

    /**
     * Metodo que despliega la Lista de todos los Tipos de la BD
     * @autor Nahum Martinez | NAM
     * @version  10/04/2018/v1.0
     * @return Lista de Grupos de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Tipos de la BD")
    @GetMapping(value = "/list", produces = "application/json")
    public List<TblTipo> getAllTipo(){
        return tiposRepository.findAll();
    }


    /**
     * Metodo que Solcita un json con los datos de la Entidad Tipos
     * @autor Nahum Martinez | NAM
     * @version  10/04/2018/v1.0
     * @return Mensaje de Confirmacion de Registro de tipo
     * @param tipoJson Obtiene desde el request los datos del tipo a ingresar
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del nuevo Tipo")
    @PostMapping(value = "/add")
    public List<TblTipo> addGroup(@RequestBody final TblTipo tipoJson) {
        System.out.println("Dato de Parametro de Send  1 ************************   " + tipoJson.getCodTipo() );

        System.out.println("Dato de Parametro de Send  2 ************************   " + tipoJson.toString() );

        TblGrupo tG = gruposRepository.findByIdGrupo(1);

        tipoJson.setIdGrupo(tG);

        tiposRepository.save(tipoJson);
        return tiposRepository.findAll();
    }

}
