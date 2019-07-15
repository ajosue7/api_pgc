package com.api.pgc.core.APIRestPGC.resourses.actividades;

//Imports de la Clase

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividadContactos;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTratos;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblOrganizacion;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblPais;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadContactosRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.TratosRepository;
import com.api.pgc.core.APIRestPGC.repository.organizaciones.OrganizacionRepository;
import com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica.PaisRepository;
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
@Api(value = "contactoapi" , description = "Operaciones sobre el Modulo de contactos", tags = "Contactos")
public class ActividadContactosResourses {

    //Propiedades de la Clase
    private String msgMethod = null;

    @Autowired
    ActividadContactosRepository _actividadContactosRepository;

    @Autowired
    TratosRepository _tratosRepository ;

    @Autowired
    OrganizacionRepository _organizacionRepository;

    @Autowired
    PaisRepository _paisRepository;

    /**
     * Metodo que despliega la Lista de todos los contcatos de la BD
     * @autor Jorge Escamilla | JOE
     * @version  25/06/2019/v1.0
     * @return Lista de contactos de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todos los contactos de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = CONTACTOS_ENDPOINT, produces = "application/json")
    public HashMap<String, Object>  getAllContacto() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los contactos registrados en la BD";

        try{
            //Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", _actividadContactosRepository.findAll() );
            //Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega el Espacio de Trabajo de la BD
     * Jorge Escamilla | JOE
     * @version  25/06/2019/v1.0
     * @return Espacio de Trabajo de la BD
     * @param idContacto Identificador del Tipo a Buscar
     */
    @ApiOperation(value = "Retorna el Tipo enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro Encontrado"),
            @ApiResponse(code = 401, message = "No estas Autenticado"),
            @ApiResponse(code = 403, message = "No estas Autorizado para usar el Servicio"),
            @ApiResponse(code = 404, message = "Recurso no encontrado")})
    @GetMapping( value = CONTACTOS_ENDPOINT_FIND_BY_ID, produces = "application/json")
    public HashMap<String, Object> getContacto( @ApiParam(value="Identificador del contacto a Buscar", required=true)
                                                @PathVariable ("idContacto") long idContacto  ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            if( _actividadContactosRepository.findByIdContacto(idContacto) == null ){
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato del Espacio de Trabajo consultado";
                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            }else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle del Espacio de Trabajo Consultado";
                msgExeptions.map.put("data", _actividadContactosRepository.findByIdContacto(idContacto));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que Solcita un json con los datos de la Entidad Espacios de Trabajo
     * Jorge Escamilla | JOE
     * @version  25/06/2019/v1.0
     * @return Mensaje de Confirmacion de Registro de tipo
     * @param _actividadContactosJson Obtiene desde el request los datos del Espacio de Trabajo a ingresar
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean del nuevo contacto",
            notes = "Se debe incluir en la Estructura del JsonBean el Identificador de contacto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = CONTACTOS_ENDPOINT_NEW, produces = "application/json")
    public HashMap<String, Object> addContacto(@ApiParam(value="Json del nuevo Tipo a Ingresar, con Grupo asociado", required=true)
                                               @RequestBody final TblActividadContactos _actividadContactosJson) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingreso
        Date dateActual = new Date();

        try {
            //Busca el Tipo de Espacio de Trabajo, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoEspacioTrabajo": { "idTipo": valor })
            TblTratos tRT = _tratosRepository.findByIdTrato( _actividadContactosJson.getIdTrato().getIdTrato() );

            //Busca el Estado de Espacio de Trabajo, desde el Reporsitorio con el Parametro del Json enviado ( "idEstadoEspacioTrabajo": { "idEstado": valor })
            TblOrganizacion tORg = _organizacionRepository.findByIdOrganizacion( _actividadContactosJson.getIdOrganizacion().getIdOrganizacion() );

            //Busca el Pais de Espacio de Trabajo, desde el Reporsitorio con el Parametro del Json enviado ( "idPais": { "idPais": valor })
            TblPais tP = _paisRepository.findByIdPais( _actividadContactosJson.getIdPais().getIdPais() );

            //Graba los Datos de Tipos
            try {
                _actividadContactosJson.setFechaCreacion(dateActual);
                _actividadContactosJson.setHoraCreacion(dateActual);
                //Setea el valor Buscado de la Entidad Contactos | Tipo
                _actividadContactosJson.setIdTrato(tRT);

                //Setea el valor Buscado de la Entidad Contactos | Estados
                _actividadContactosJson.setIdOrganizacion(tORg);

                //Setea el valor Buscado de la Entidad Contactos | Pais
                _actividadContactosJson.setIdPais(tP);


                //Realizamos la Persistencia de los Datos
                _actividadContactosRepository.save(_actividadContactosJson);

                //return tiposRepository.findAll();
                msgMethod = "Se ha Ingresado de forma satisfactoria!!";

                //Retorno del json
                return msgExeptions.msgJson( msgMethod, 200 );

            }catch ( Exception ex ){
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el trato";
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
     * @param _actividadContactosJson Obtiene desde el request los datos de la Organizacion a ingresar
     * @param idContacto    Identificador de la tabla
     * @return Mensaje de Confirmacion de Registro del contacto
     * @autor Jorge Escamilla | JOE
     * @version 25/06/2019/v1.0
     */
    @ApiOperation(value = "Actualiza a la BD, la Información enviada por el Bean de la Organizacion", authorizations = {@Authorization(value = "Token-PGC")})
    @PutMapping(value = CONTACTOS_ENDPOINT_EDIT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> editContacto(@ApiParam(value = "Json de contactos a Ingresar", required = true)
                                                @PathVariable("idContacto") long idContacto,
                                                @RequestBody final TblActividadContactos _actividadContactosJson) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingreso
        Date dateActual = new Date();

        // Buscamos la Organizacion solicitada para la Modificacion
        try {
            // Buacamos la Organizacion segun el Parametro enviado
            TblActividadContactos _actvidadContacto = _actividadContactosRepository.findByIdContacto(idContacto);

            if (_actividadContactosRepository.countByIdContacto(idContacto) > 0) {
                // Buacamos el Tipo de Trato segun el Parametro enviado
                TblTratos _tblTratos = _tratosRepository.findByIdTrato(_actividadContactosJson.getIdTrato().getIdTrato());

                // Buacamos la organizacion segun el Parametro enviado
                TblOrganizacion _tblOrg = _organizacionRepository.findByIdOrganizacion(_actividadContactosJson.getIdOrganizacion().getIdOrganizacion());

                // Buacamos el Pais segun el Parametro enviado
                 TblPais _tblPais = _paisRepository.findByIdPais(_actividadContactosJson.getIdPais().getIdPais());

                try {
                    // Realizamos la Persistencia de los Datos
                    _actvidadContacto.setActivo(_actividadContactosJson.isActivo());
                    _actvidadContacto.setNombreContacto(_actividadContactosJson.getNombreContacto());
                    _actvidadContacto.setApellidoContacto(_actividadContactosJson.getApellidoContacto());
                    _actvidadContacto.setFuncionContacto(_actividadContactosJson.getFuncionContacto());
                    _actvidadContacto.setOrganizacionContacto(_actividadContactosJson.getOrganizacionContacto());
                    _actvidadContacto.setdFisicaContacto(_actividadContactosJson.getdFisicaContacto());
                    _actvidadContacto.setEmail1Contacto(_actividadContactosJson.getEmail1Contacto());
                    _actvidadContacto.setEmail2Contacto(_actividadContactosJson.getEmail2Contacto());
                    _actvidadContacto.setTelefono1Contacto(_actividadContactosJson.getTelefono1Contacto());
                    _actvidadContacto.setTelefono2Contacto(_actividadContactosJson.getTelefono2Contacto());
                    _actvidadContacto.setExt1Contacto(_actividadContactosJson.getExt1Contacto());
                    _actvidadContacto.setExt2Contacto(_actividadContactosJson.getExt2Contacto());



                    _actvidadContacto.setIdPais(_tblPais);
                    _actvidadContacto.setIdTrato(_tblTratos);
                    _actvidadContacto.setIdOrganizacion(_tblOrg);

                    _actividadContactosRepository.save(_actvidadContacto);
                    _actividadContactosRepository.flush();

                    // return Repository
                    msgMethod = "Se ha Actualizado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } catch (Exception ex) {
                    msgMethod = "Hay problemas al momento de Actualizar el contacto.";
                    throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
                }
            } else {
                //Retorno del json
                msgMethod = "No se encuentra un contacto con el parametro enviado !!";
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            msgMethod = "Hay problemas al momento de Actualizar el contacto.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | editContacto


    /**
     * Metodo que Solcita un json con los datos de la Entidad de contactos
     *
     * @param idContacto Identificador de la tabla
     * @return Mensaje de Confirmacion de Registro de la tabla de contactos
     * @autor Jorge Escamilla | JOE
     * @version 25/006/2019/v1.0
     */
    @ApiOperation(value = "Inhabilita a la BD, la Información enviada por el Bean de contactos", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = CONTACTOS_ENDPOINT_DELETE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deleteContacto(@ApiParam(value = "Id de contacto Inhabilitar", required = true)
                                                        @PathVariable("idContacto") long idContacto) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Inhabilitacion
        Date dateActual = new Date();

        // Buscamos la Organizacion solicitada para la Modificacion
        try {
            // Buacamos la Organizacion segun el Parametro enviado
            TblActividadContactos _actividadContacto = _actividadContactosRepository.findByIdContacto(idContacto);

            if (_actividadContactosRepository.countByIdContacto(idContacto) > 0) {
                try {
                    // Realizamos la Persistencia de los Datos
                    _actividadContacto.setActivo(false);

                    _actividadContactosRepository.save(_actividadContacto);
                    _actividadContactosRepository.flush();

                    // return Repository
                    msgMethod = "Se ha Inhabilitado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } catch (Exception ex) {
                    msgMethod = "Hay problemas al momento de Inhabilitar el contacto.";
                    throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
                }
            } else {
                //Retorno del json
                msgMethod = "No se encuentra el contacto con el parametro enviado !!";
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            msgMethod = "Hay problemas al momento de Inhabilitar el contacto.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN | deleteContacto


}
