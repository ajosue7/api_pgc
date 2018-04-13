package com.api.pgc.core.APIRestPGC.resourses;

//Imports de la Clase
import com.api.pgc.core.APIRestPGC.models.TblGrupo;
import com.api.pgc.core.APIRestPGC.models.TblTipo;
import com.api.pgc.core.APIRestPGC.repository.GruposRepository;
import com.api.pgc.core.APIRestPGC.repository.TiposRepository;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
     * Metodo que despliega el Grupo de la BD
     * @autor Nahum Martinez | NAM
     * @version  11/04/2018/v1.0
     * @return Grupo de la BD
     * @param idTipo Identificador del Tipo a Buscar
     */
    @ApiOperation(value = "Retorna el Tipo enviado a buscar de la BD")
    @GetMapping( value = "/show/{idTipo}", produces = "application/json")
    public TblTipo getTipo( @ApiParam(value="Identificador del Tipo a Buscar", required=true) @PathVariable ("idTipo") long idTipo  ){
        return tiposRepository.findByIdTipo( idTipo );
    }



    /**
     * Metodo que Solcita un json con los datos de la Entidad Tipos
     * @autor Nahum Martinez | NAM
     * @version  10/04/2018/v1.0
     * @return Mensaje de Confirmacion de Registro de tipo
     * @param tipoJson Obtiene desde el request los datos del tipo a ingresar
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del nuevo Tipo",
            notes = "Se debe incluir en la Estructura del JsonBean el Identificador de Grupo")
    @PostMapping(value = "/add", produces = "application/json")
    public String addGroup( @ApiParam(value="Json del nuevo Tipo a Ingresar, con Grupo asociado", required=true)
                                       @RequestBody final TblTipo tipoJson) throws Exception {
        //Ejecuta el try Cacth
        String msgMethod = null;

        try {
            //Busca el Grupo, desde el Reporsitorio con el Parametro del Json enviado ( "idGrupo": { "idGrupo": valor })
            TblGrupo tG = gruposRepository.findByIdGrupo( tipoJson.getIdGrupo().getIdGrupo() );

            //Graba los Datos de Tipos
            try {
                //Setea el valor Buscado de la Entidad Grupos
                tipoJson.setIdGrupo(tG);

                //Realizamos la Persistencia de los Datos
                tiposRepository.save(tipoJson);

                //return tiposRepository.findAll();
                msgMethod = "Se ha Ingresado de forma satisfactoria!!";
            }catch ( Exception ex ){
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Tipo";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        }catch ( Exception ex ){
            msgMethod = "No existe el Grupo que buscas, por favor verfica que el Grupo ingresado es correcto.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }

        //Retorno del Method
        return msgMethod;
    }

}
