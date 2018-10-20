package com.api.pgc.core.APIRestPGC.resourses.organizaciones;


import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblGrupo;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblGrupoOrganizacion;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblOrganizacion;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblTipoOrganizacion;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblPais;
import com.api.pgc.core.APIRestPGC.repository.organizaciones.GrupoOrganizacionRepository;
import com.api.pgc.core.APIRestPGC.repository.organizaciones.OrganizacionRepository;
import com.api.pgc.core.APIRestPGC.repository.organizaciones.TipoOrganizacionRepository;
import com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica.PaisRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "organizacionapi", description = "Operaciones sobre el Modulo de Organizacion")
public class OrganizacionesResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    OrganizacionRepository organizacionRepository;

    @Autowired
    TipoOrganizacionRepository tipoOrganizacionRepository;

    @Autowired
    GrupoOrganizacionRepository grupoOrganizacionRepository;

    @Autowired
    PaisRepository paisRepository;


    /**
     * Metodo que despliega la Lista de todos las Organizaciones de la BD
     *
     * @return Lista de las Organizaciones de la BD
     * @autor Nahum Martinez | NAM
     * @version 15/10/2018/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos las Organizaciones de la BD")
    @GetMapping(value = ORGANIZACIONES_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllOrganizaciones() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todass las Organizaciones registrados en la BD";

        try {
            //Sobreescirbe el Metodo de Mensajes
            if ( organizacionRepository.findAll().isEmpty() || organizacionRepository.findAll() == null ) {
                msgMethod = "No Existen, Organizaciones resgitradas en la Base de Daros, Contacta al Administrador";

                msgExeptions.map.put("error", "No data found" );
                msgExeptions.map.put("data", organizacionRepository.findAll() );
            }else {
                msgExeptions.map.put("data", organizacionRepository.findAll() );
            }

            //Retorno del JSON
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega la Organizacion de la BD
     *
     * @param idOrganizacion Identificador de Organizacion a Buscar
     * @return Organizacion de la BD
     * @autor Nahum Martinez | NAM
     * @version 15/10/2018/v1.0
     */
    @ApiOperation(value = "Retorna el Tipo de Organizacion enviado a buscar de la BD")
    @GetMapping(value = ORGANIZACIONES_ENDPOINT_FIND_BY_ID, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getOrganizacion(@ApiParam(value = "Identificador de Organizacion a Buscar", required = true)
                                                       @PathVariable("idOrganizacion") long idOrganizacion) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if (organizacionRepository.findByIdOrganizacion(idOrganizacion) == null ) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Organizacion consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de Organizacion Consultado";
                msgExeptions.map.put("data", organizacionRepository.findByIdOrganizacion(idOrganizacion));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que Solcita un json con los datos de la Entidad de Organizacion
     *
     * @param organizacionJson Obtiene desde el request los datos de Organizacion a ingresar
     * @return Mensaje de Confirmacion de Registro de Organizacion
     * @autor Nahum Martinez | NAM
     * @version 15/10/2018/v1.0
     */
    @ApiOperation(value = "Ingresa a la BD, la Información enviada por el Bean de la nueva Organizacion")
    @PostMapping(value = ORGANIZACIONES_USUARIO_ENDPOINT_NEW, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> addOrganizacion(@ApiParam(value = "Json de la nueva Organizacion a Ingresar", required = true)
                                                       @RequestBody final TblOrganizacion organizacionJson) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Buscar el Tipo de Organizacion
            TblTipoOrganizacion tblTipoOrganizacion = tipoOrganizacionRepository.findByIdTipoOrganizacion( organizacionJson.getIdTipoOrganizacionT().getIdTipoOrganizacion() );

            // Buscar el Grupo de Organizacion
            TblGrupoOrganizacion tblGrupoOrganizacion = grupoOrganizacionRepository.findByIdGrupoOrganizacion( organizacionJson.getIdGrupoOrganizazion().getIdGrupoOrganizacion() );

            if ( tblTipoOrganizacion.isActivo() == true ) {
                // Buscar el el Pais de Organizacion
                TblPais tblPais = paisRepository.findByIdPais( organizacionJson.getIdPaisOrganizacion().getIdPais() );

                if ( tblPais.getInicialesPais() != null ) {
                    //Setea el valor Buscando de la Entidad Tipos de Usuario
                    organizacionJson.setIdTipoOrganizacionT( tblTipoOrganizacion );
                    organizacionJson.setIdPaisOrganizacion( tblPais );
                    organizacionJson.setIdGrupoOrganizazion( tblGrupoOrganizacion );

                    //Realizamos la Persistencia de los Datos
                    organizacionRepository.save( organizacionJson );

                    //return organizacionRepository.findAll();
                    msgMethod = "Se ha Ingresado de forma satisfactoria!!";
                }else {
                    msgMethod = "No existe el Pais de Organizacion que buscas, por favor verfica que el Identificador correcto ingresado es correcto.";
                }
            }else {
                msgMethod = "No existe el Tipo de Organizacion que buscas, por favor verfica que el Identificador correcto ingresado es correcto.";
            }

            //Retorno del json
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            msgMethod = "Los datos Ingresados no son los correctos, pofavor verifica que correspondan a la informacion utilizada en la BD.";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }

    /**
     * Metodo que despliega la Organizacion de la BD, enviando el Tipo de Organizacion
     *
     * @param idTipoOrganizacion Identificador del Estado a Buscar
     * @return Organizaciones de la BD
     * @autor Nahum Martinez | NAM
     * @version 15/10/2018/v1.0
     */
    @ApiOperation(value = "Retorna las Organizaciones enviado a buscar de la BD, por el Tipo Indicado")
    @GetMapping(value = ORGANIZACIONES_ENDPOINT_FIND_BY_ID_TIPO, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getTipoOrganizacion(@ApiParam(value = "Identificador del Tipo de Organizacion a Buscar", required = true)
                                                   @PathVariable("idTipoOrganizacion") long idTipoOrganizacion) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Buscamos el Tipo de Organizacion
            TblTipoOrganizacion tblTipoOrganizacion = tipoOrganizacionRepository.findByIdTipoOrganizacion( idTipoOrganizacion );

            if (organizacionRepository.getTipoOrganizacion( tblTipoOrganizacion ) == null ||
                    organizacionRepository.getTipoOrganizacion( tblTipoOrganizacion ).isEmpty()) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se has encontrado dato de las Organizaciones consultadas, con el Tipo solicitado, porfavor ingresa un Tipo de Organizacion valido";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("data", organizacionRepository.getTipoOrganizacion( tblTipoOrganizacion ));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de las Organizaciones Consultadas";
                msgExeptions.map.put("data", organizacionRepository.getTipoOrganizacion( tblTipoOrganizacion ));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN


    /**
     * Metodo que despliega la Organizacion de la BD, enviando el Tipo de Organizacion y Pais
     *
     * @param idTipoOrganizacion Identificador del Tipo y Pais de Organizacion a Buscar
     * @param idPaisOrganizacion Identificador del Pais de Organizacion a Buscar
     * @return Organizaciones de la BD
     * @autor Nahum Martinez | NAM
     * @version 15/10/2018/v1.0
     */
    @ApiOperation(value = "Retorna las Organizaciones enviado a buscar de la BD, por el Tipo y Pais Indicado")
    @GetMapping(value = ORGANIZACIONES_ENDPOINT_FIND_BY_ID_TIPO_PAIS, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getTipoPaisOrganizacion(@ApiParam(value = "Identificador del Tipo y Pais de Organizacion a Buscar", required = true)
                                                       @PathVariable("idTipoOrganizacion") long idTipoOrganizacion,
                                                           @PathVariable("idPaisOrganizacion") long idPaisOrganizacion ) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            // Buscamos el Tipo de Organizacion
            TblTipoOrganizacion tblTipoOrganizacion = tipoOrganizacionRepository.findByIdTipoOrganizacion( idTipoOrganizacion );
            TblPais tblPais = paisRepository.findByIdPais( idPaisOrganizacion );

            if ( organizacionRepository.getTipoPaisOrganizacion( tblTipoOrganizacion, tblPais ).isEmpty()) {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se has encontrado dato de las Organizaciones consultadas, con el Tipo y Pais solicitado, porfavor ingresa un Tipo y Pais de Organizacion valido";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("data", organizacionRepository.getTipoPaisOrganizacion( tblTipoOrganizacion, tblPais ));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 400);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de las Organizaciones Consultadas";
                msgExeptions.map.put("data", organizacionRepository.getTipoPaisOrganizacion( tblTipoOrganizacion, tblPais ));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }//FIN
}
