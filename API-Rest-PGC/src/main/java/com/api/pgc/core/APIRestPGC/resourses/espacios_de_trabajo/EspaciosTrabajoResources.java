package com.api.pgc.core.APIRestPGC.resourses.espacios_de_trabajo;

//Imports de la Clase

import com.api.pgc.core.APIRestPGC.models.espacios_de_trabajo.TblEspaciosTrabajo;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblEstado;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTipo;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblPais;
import com.api.pgc.core.APIRestPGC.repository.espacios_de_trabajo.EspaciosTrabajoRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.EstadosRepository;
import com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica.PaisRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.TiposRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "espaciotrabajoapi" , description = "Operaciones sobre el Modulo de Espacios de Trabajo", tags = "Espacios de Trabajo")
public class EspaciosTrabajoResources {

    //Propiedades de la Clase
    private String msgMethod = null;

    @Autowired
    EspaciosTrabajoRepository espaciosTrabajoRepository;

    @Autowired
    TiposRepository tiposRepository;

    @Autowired
    EstadosRepository estadosRepository;

    @Autowired
    PaisRepository paisRepository;

    /**
     * Metodo que despliega la Lista de todos los Espacios de Trabajo de la BD
     * @autor Nahum Martinez | NAM
     * @version  11/10/2018/v1.0
     * @return Lista de Espacios de Trabajo de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Espacios de Trabajo de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = ESPACIOS_TRABAJO_ENDPOINT, produces = "application/json")
    public HashMap<String, Object>  getAllEspaciosTrabajo() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Espacios de Trabajo registrados en la BD";

        try{
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", espaciosTrabajoRepository.findAll() );
            //Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Espacio de Trabajo de la BD
     * @autor Nahum Martinez | NAM
     * @version  11/10/2018/v1.0
     * @return Espacio de Trabajo de la BD
     * @param idEspacioTrabajo Identificador del Tipo a Buscar
     */
    @ApiOperation(value = "Retorna el Tipo enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro Encontrado"),
            @ApiResponse(code = 401, message = "No estas Autenticado"),
            @ApiResponse(code = 403, message = "No estas Autorizado para usar el Servicio"),
            @ApiResponse(code = 404, message = "Recurso no encontrado")})
    @GetMapping( value = ESPACIOS_TRABAJO_ENDPOINT_FIND_BY_ID, produces = "application/json")
    public HashMap<String, Object> getEspacioTrabajo( @ApiParam(value="Identificador del Espacio de Trabajo a Buscar", required=true)
                                                @PathVariable ("idEspacioTrabajo") long idEspacioTrabajo  ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            if( espaciosTrabajoRepository.findByIdEspacioTrabajo(idEspacioTrabajo) == null ){
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Espacio de Trabajo consultado";
                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            }else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Espacio de Trabajo Consultado";
                msgExeptions.map.put("data", espaciosTrabajoRepository.findByIdEspacioTrabajo(idEspacioTrabajo));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que Solcita un json con los datos de la Entidad Espacios de Trabajo
     * @autor Nahum Martinez | NAM
     * @version  11/10/2018/v1.0
     * @return Mensaje de Confirmacion de Registro de tipo
     * @param espacioTrabajoJson Obtiene desde el request los datos del Espacio de Trabajo a ingresar
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del nuevo Espacio de Trabajo",
            notes = "Se debe incluir en la Estructura del JsonBean el Identificador de Espacio de Trabajo", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = ESPACIOS_TRABAJO_ENDPOINT_NEW, produces = "application/json")
    public HashMap<String, Object> addEspacioTrabajo(@ApiParam(value="Json del nuevo Tipo a Ingresar, con Grupo asociado", required=true)
                                       @RequestBody final TblEspaciosTrabajo espacioTrabajoJson) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingreso
        Date dateActual = new Date();

        try {
            //Busca el Tipo de Espacio de Trabajo, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoEspacioTrabajo": { "idTipo": valor })
            TblTipo tTE = tiposRepository.findByIdTipo( espacioTrabajoJson.getIdTipoEspacioTrabajo().getIdTipo() );

            //Busca el Estado de Espacio de Trabajo, desde el Reporsitorio con el Parametro del Json enviado ( "idEstadoEspacioTrabajo": { "idEstado": valor })
            TblEstado tEE = estadosRepository.findByIdEstado( espacioTrabajoJson.getIdEstadoEspacioTrabajo().getIdEstado() );

            //Busca el Pais de Espacio de Trabajo, desde el Reporsitorio con el Parametro del Json enviado ( "idPais": { "idPais": valor })
            TblPais tPE = paisRepository.findByIdPais( espacioTrabajoJson.getIdPais().getIdPais() );

            //Graba los Datos de Tipos
            try {
                espacioTrabajoJson.setFechaCreacion(dateActual);
                espacioTrabajoJson.setHoraCreacion(dateActual);
                //Setea el valor Buscado de la Entidad Espacios de Trabajo | Tipo
                espacioTrabajoJson.setIdTipoEspacioTrabajo(tTE);

                //Setea el valor Buscado de la Entidad Espacios de Trabajo | Estados
                espacioTrabajoJson.setIdEstadoEspacioTrabajo(tEE);

                //Setea el valor Buscado de la Entidad Espacios de Trabajo | Pais
                espacioTrabajoJson.setIdPais(tPE);


                //Realizamos la Persistencia de los Datos
                espaciosTrabajoRepository.save(espacioTrabajoJson);

                //return tiposRepository.findAll();
                msgMethod = "Se ha Ingresado de forma satisfactoria!!";

                //Retorno del json
                return msgExeptions.msgJson( msgMethod, 200 );

            }catch ( Exception ex ){
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Tipo";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        }catch ( Exception ex ){
            msgMethod = "No existe el Tipo de Espacio de Trabajo que buscas, por favor verfica que el Grupo ingresado es correcto.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN

    /**
     * Metodo que Solcita un json con los datos de la Entidad de Organizacion
     *
     * @param _espacioTrabajoJson Obtiene desde el request los datos de la Organizacion a ingresar
     * @param idEspacioTrabajo    Identificador de la tabla
     * @return Mensaje de Confirmacion de Registro de la Organizacion
     * @autor Nahum Martinez | NAM
     * @version 14/02/2019/v1.0
     */
    @ApiOperation(value = "Actualiza a la BD, la Información enviada por el Bean de la Organizacion", authorizations = {@Authorization(value = "Token-PGC")})
    @PutMapping(value = ESPACIOS_TRABAJO_ENDPOINT_EDIT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> editEspacioTrabajo(@ApiParam(value = "Json de EspacioTrabajo a Ingresar", required = true)
                                                    @PathVariable("idEspacioTrabajo") long idEspacioTrabajo,
                                                    @RequestBody final TblEspaciosTrabajo _espacioTrabajoJson) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingreso
        Date dateActual = new Date();

        // Buscamos la Organizacion solicitada para la Modificacion
        try {
            // Buacamos la Organizacion segun el Parametro enviado
            TblEspaciosTrabajo _tblEspacio = espaciosTrabajoRepository.findByIdEspacioTrabajo(idEspacioTrabajo);

            if (espaciosTrabajoRepository.countByIdEspacioTrabajo(idEspacioTrabajo) > 0) {
                // Buacamos el Tipo Organizacion segun el Parametro enviado
                TblTipo _tblTipoE = tiposRepository.findByIdTipo(_espacioTrabajoJson.getIdTipoEspacioTrabajo().getIdTipo());

                // Buacamos el Cat Organizacion segun el Parametro enviado
                TblEstado _tblEstadoE = estadosRepository.findByIdEstado(_espacioTrabajoJson.getIdEstadoEspacioTrabajo().getIdEstado());

                // Buacamos el Pais segun el Parametro enviado
                TblPais _tblPais = paisRepository.findByIdPais(_espacioTrabajoJson.getIdPais().getIdPais());

                try {
                    // Realizamos la Persistencia de los Datos
                    _tblEspacio.setActivo(_espacioTrabajoJson.isActivo());
                    _tblEspacio.setCodEspacioTrabajo(_espacioTrabajoJson.getCodEspacioTrabajo());
                    _tblEspacio.setNombreEspacioTrabajo(_espacioTrabajoJson.getNombreEspacioTrabajo());
                    _tblEspacio.setDescripcionEspacioTrabajo(_espacioTrabajoJson.getDescripcionEspacioTrabajo());
                    _tblEspacio.setEspacioPadre(_espacioTrabajoJson.isEspacioPadre());
                    _tblEspacio.setVistaPublica(_espacioTrabajoJson.isVistaPublica());

                    _tblEspacio.setIdPais(_tblPais);
                    _tblEspacio.setIdEstadoEspacioTrabajo(_tblEstadoE);
                    _tblEspacio.setIdTipoEspacioTrabajo(_tblTipoE);

                    espaciosTrabajoRepository.save(_tblEspacio);
                    espaciosTrabajoRepository.flush();

                    // return Repository
                    msgMethod = "Se ha Actualizado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } catch (Exception ex) {
                    msgMethod = "Hay problemas al momento de Actualizar la Organizacion.";
                    throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
                }
            } else {
                //Retorno del json
                msgMethod = "No se encuentra una Organizacion con el parametro enviado !!";
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            msgMethod = "Hay problemas al momento de Actualizar la Organizacion.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | editOrganizacion


    /**
     * Metodo que Solcita un json con los datos de la Entidad de Organizacion
     *
     * @param idEspaciotrabajo Identificador de la tabla
     * @return Mensaje de Confirmacion de Registro de la espaciotrabajo
     * @autor Nahum Martinez | NAM
     * @version 25/02/2019/v1.0
     */
    @ApiOperation(value = "Inhabilita a la BD, la Información enviada por el Bean de espaciotrabajo", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = ESPACIOS_TRABAJO_ENDPOINT_DELETE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deleteEspaciotrabajo(@ApiParam(value = "Id de la Espaciotrabajo a Inhabilitar", required = true)
                                                      @PathVariable("idEspaciotrabajo") long idEspaciotrabajo) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Inhabilitacion
        Date dateActual = new Date();

        // Buscamos la Organizacion solicitada para la Modificacion
        try {
            // Buacamos la Organizacion segun el Parametro enviado
            TblEspaciosTrabajo _tblEspaciotrabajo = espaciosTrabajoRepository.findByIdEspacioTrabajo(idEspaciotrabajo);

            if (espaciosTrabajoRepository.countByIdEspacioTrabajo(idEspaciotrabajo) > 0) {
                try {
                    // Realizamos la Persistencia de los Datos
                    _tblEspaciotrabajo.setActivo(false);

                    espaciosTrabajoRepository.save(_tblEspaciotrabajo);
                    espaciosTrabajoRepository.flush();

                    // return Repository
                    msgMethod = "Se ha Inhabilitado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } catch (Exception ex) {
                    msgMethod = "Hay problemas al momento de Inhabilitar la Organizacion.";
                    throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
                }
            } else {
                //Retorno del json
                msgMethod = "No se encuentra una Organizacion con el parametro enviado !!";
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            msgMethod = "Hay problemas al momento de Inhabilitar la Organizacion.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deleteEspaciotrabajo


}
