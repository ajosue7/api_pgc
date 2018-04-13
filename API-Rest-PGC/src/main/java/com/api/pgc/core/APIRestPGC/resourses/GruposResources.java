package com.api.pgc.core.APIRestPGC.resourses;


import com.api.pgc.core.APIRestPGC.models.TblGrupo;
import com.api.pgc.core.APIRestPGC.repository.GruposRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/grupos")
@Api(value = "gruposapi" , description = "Operaciones sobre el Modulo de Grupos")
public class GruposResources {

    @Autowired
    GruposRepository gruposRepository;

    /**
     * Metodo que despliega la Lista de todos los Grupos de la BD
     * @autor Nahum Martinez | NAM
     * @version  10/04/2018/v1.0
     * @return Lista de Grupos de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Grupos de la BD")
    @GetMapping(value = "/list", produces = "application/json")
    public List<TblGrupo> getAllGroup(){
        return gruposRepository.findAll();
    }


    /**
     * Metodo que despliega el Grupo de la BD
     * @autor Nahum Martinez | NAM
     * @version  11/04/2018/v1.0
     * @return Grupo de la BD
     * @param idGrupo Identificador del Grupo a Buscar
     */
    @ApiOperation(value = "Retorna el Grupo enviado a buscar de la BD")
    @GetMapping(value = "/show/{idGrupo}", produces = "application/json")
    public TblGrupo getGroup( @ApiParam(value="Identificador del Grupo a Buscar", required=true) @PathVariable ("idGrupo") long idGrupo  ){
        return gruposRepository.findByIdGrupo( idGrupo );
    }


    /**
     * Metodo que Solcita un json con los datos de la Entidad Grupos
     * @autor Nahum Martinez | NAM
     * @version  10/04/2018/v1.0
     * @return Mensaje de Confirmacion de Registro de grupo
     * @param grupoJson Obtiene desde el request los datos del grupo a ingresar
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del nuevo Grupo")
    @PostMapping(value = "/add", produces = "application/json")
    public List<TblGrupo> addGroup( @ApiParam(value="Json del nuevo Grupo a Ingresar", required=true) @RequestBody final TblGrupo grupoJson){
        gruposRepository.save(grupoJson);
        return gruposRepository.findAll();
    }

}
