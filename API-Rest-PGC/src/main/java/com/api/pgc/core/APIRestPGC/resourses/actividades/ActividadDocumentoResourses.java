/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.actividades;


/*
 * Definicion de las Librerias a importar de la Clase
 */
import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.TblActividadDocumento;
import com.api.pgc.core.APIRestPGC.models.actividades.TblActividadIdInterna;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTipo;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblOrganizacion;
import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadDocumentoRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadIdInternaRepository;
import com.api.pgc.core.APIRestPGC.repository.actividades.ActividadRepository;
import com.api.pgc.core.APIRestPGC.repository.mantenimiento.TiposRepository;
import com.api.pgc.core.APIRestPGC.repository.organizaciones.OrganizacionRepository;
import com.api.pgc.core.APIRestPGC.repository.seguridad.UsuariosRepository;
import com.api.pgc.core.APIRestPGC.resourses.fileUpload.FileController;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "ActividadDocumentoAPI" , description = "Operaciones sobre el Modulo de Proyectos Recursos", tags = "Recursos del Proyecto")
public class ActividadDocumentoResourses {
    // Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    ActividadDocumentoRepository _actividadDocumentoRepository;

    @Autowired
    ActividadRepository _actividadRepository;

    @Autowired
    TiposRepository _tiposRepository;

    @Autowired
    UsuariosRepository _usuariosRepository;

    @Autowired
    FileController _fileController;

    /**
     * Metodo que despliega la Lista de todas los Documentos de una Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version  07/04/2019/v1.0
     * @return Lista de Documentos de una Actividad de la BD
     */
    @ApiOperation(value = "Retorna el Listado de Todss los Documentos de una Actividad de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = RECURSOS_DOC_ENDPOINT, produces = "application/json; charset=UTF-8" )
    public HashMap<String, Object> getAllActvidadesDocumentos() throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todas los Documentos asociados a un Proyecto registradas en la BD";

        try{
            // Sobreescirbe el Metodo de Mensajes
            msgExeptions.map.put( "data", _actividadDocumentoRepository.findAll() );
            msgExeptions.map.put( "countRecords", _actividadDocumentoRepository.count() );
            // Retorno del json
            return msgExeptions.msgJson( msgMethod, 200 );
        }catch ( Exception ex ){
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega los Documentos asociados a la Actividad de la BD
     * @autor Nahum Martinez | NAM
     * @version  07/04/2019/v1.0
     * @return Recursos del Proyecto de la BD
     * @param codActividadDocumento Identificador de Acividad de los Recursos a Buscar
     */
    @ApiOperation(value = "Retorna los Recursos enviado a buscar de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = RECURSOS_DOC_ENDPOINT_FIND_BY_ID_ACTIVIDAD, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getByCodActividadDocumento( @ApiParam(value="Identificador del Proyecto a Buscar", required=true)
                                                  @PathVariable("codActividadDocumento") String codActividadDocumento) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try{
            if ( _actividadDocumentoRepository.findByCodActividadDocumento( codActividadDocumento ) == null ){
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Documentos asociados al PRoyecto consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            }else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de los Documentos consultados, por el Proyecto";
                msgExeptions.map.put("data", _actividadDocumentoRepository.findByCodActividadDocumento( codActividadDocumento ));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        }catch ( Exception ex ) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que Solicita un json con los datos de la Entidad Documentos con Relacion
     * a Actividades
     * @param _idActividadDocumentoJson Obtiene desde el request los datos de los Documentos a ingresar
     * @return Mensaje de Confirmacion de Registro de Documentos asociados a Actividad
     * @autor Nahum Martinez | NAM
     * @version 07/04/2019/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean de los Nuevos Documentos del proyecto", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = RECURSOS_DOC_ENDPOINT_NEW, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,  produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> newAtividadDocumento(@ApiParam(value = "Json de los Documentos del Proyecto a Ingresar", required = true)
                                                             @RequestBody @Valid final TblActividadDocumento _idActividadDocumentoJson,
                                                        @RequestParam("file") MultipartFile file) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Fecha de Ingrso
        Date dateActual = new Date();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Json enviado ( "idActividad": {"idActividad": valor })
            TblActividad _tblActividad = _actividadRepository.findByIdActividad( _idActividadDocumentoJson.getIdActividad().getIdActividad() );

            try {
                // Busca el Tipo de Documento, desde el Reporsitorio con el Parametro del Json enviado ( "idTipoDocumento": {"idTipo": valor })
                TblTipo _tblTipo = _tiposRepository.findByIdTipo( _idActividadDocumentoJson.getIdTipoDocumento().getIdTipo() );
                TblUsuarios _tblUsuarios = _usuariosRepository.findByIdUsuario( _idActividadDocumentoJson.getIdUsuario().getIdUsuario() );

                // Busca el Proyecto con el Proposito de validar que no se meta otro Item mas,
                // desde el Reporsitorio de Planificacion con el Parametro del Json enviado ( "idActividadDocumento": _tblActividad )

                if( _actividadDocumentoRepository.countByCodActividadDocumento( _idActividadDocumentoJson.getCodActividadDocumento() ) > 0 ) {
                    msgMethod = "Ya Existe un registro con el Código de Documento para este Proyecto !!";

                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    // Seteo de las Fecha y Hora de Creacion
                    _idActividadDocumentoJson.setFechaCreacion(dateActual);
                    _idActividadDocumentoJson.setHoraCreacion(dateActual);

                    // Seteamos la Actividad de Documento y Tipo de Documento y Usuario
                    _idActividadDocumentoJson.setIdActividad(_tblActividad);
                    _idActividadDocumentoJson.setIdTipoDocumento(_tblTipo);
                    _idActividadDocumentoJson.setIdUsuario(_tblUsuarios);

                    // Guardar la Imagen en HD
                    // _fileController.uploadFile();

                    // Realizamos la Persistencia de los Datos
                    _actividadDocumentoRepository.save( _idActividadDocumentoJson );
                    _actividadDocumentoRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Documento para este Proyecto, se ha Ingresado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Intentar Grabar el Documento del Proyecto, con Tipo de Documento Indicado !!";
                throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No existe el Proyecto que buscas, por favor verfica que lo has ingresado correctamente.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN



    /**
     * Metodo que Solicita un json con los datos de la Entidad Documentos del Proyecto con Relacion
     * a Actividades
     * @param  codActividadDocumento Identificador del Documento a Eliminar
     * @return Mensaje de Confirmacion de Eliminacion del Documento
     * @autor Nahum Martinez | NAM
     * @version 07/04/2019/v1.0
     */
    @ApiOperation(value = "Elimina de la BD, la Información enviada por el codigo del Documento", authorizations = {@Authorization(value = "Token-PGC")})
    @DeleteMapping(value = RECURSOS_DOC_ENDPOINT_DELETE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> deletedActividadDocumento(@ApiParam(value = "Codigo del Documento del Proyecto a Eliminar", required = true)
                                                              @PathVariable("codActividadDocumento") String codActividadDocumento) throws Exception {
        // Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Busca la Actividad, desde el Reporsitorio con el Parametro del Codigo enviado ( codActividadDocumento )
            TblActividadDocumento _tblActividadDocumento = _actividadDocumentoRepository.findByCodActividadDocumento( codActividadDocumento );

            try {
                if( _actividadDocumentoRepository.countByCodActividadDocumento( codActividadDocumento ) > 0 ) {
                    // Realizamos la Persistencia de los Datos
                    //_actividadIdInternaRepository.delete( _tblActividadIdInterna );
                    _actividadDocumentoRepository.deleletedCodActividadDocumento( codActividadDocumento );
                    _actividadDocumentoRepository.flush();

                    // Retorno de la Funcion
                    msgMethod = "El Documento para este Proyecto, se ha Eliminado de forma satisfactoria!!";

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    msgMethod = "No Existe un registro de Documento para este Proyecto !!";
                    throw new SQLException("Se ha producido una excepción con el mensaje : " + msgMethod);
                }
            } catch (Exception ex) {
                msgMethod = "Ha Ocurrido un error al Eliminar el Documento del Proyecto !!";
                throw new SQLException("Se ha producido una excepción con el mensaje: " + msgMethod, ex);
            }
        } catch (Exception ex) {
            msgMethod = "No Existe un registro de Id Interna para este Proyecto , por favor verfica que lo has ingresado correctamente o que existe.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    } // FIN

}
