/*
 * Copyright (c) 2018.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.utilities;

/**
 * @author nahum.martinez
 * @version 1.0
 * @apiNote Clase que Mapea todos EndPoints de la API
 */

public class configAPI {
    // Constantes del Modulo de General de la API
    public static final String API_BASE_PATH = "/api/v1";

    /*==================================================================================================================
     *================================================================================================================ */

    // Mantenimientos Genericos de la API ******************************************************************************
    // Mapeo de las Rutas del Modulo de Estados
    public static final String ESTADOS_ENDPOINT = "/estados";
    public static final String ESTADOS_ENDPOINT_FIND_BY_ID = "/estados/findById/{idEstado}";
    public static final String ESTADOS_ENDPOINT_FIND_BY_IDGRUPO = "/estados/findByIdGrupo/{idGrupo}";
    public static final String ESTADOS_ENDPOINT_LIST1 = API_BASE_PATH + "/estados/findById/{idEstado}";

    // Mapeo de las Rutas del Modulo de Grupos
    public static final String GRUPOS_ENDPOINT = "/grupos";
    public static final String GRUPOS_ENDPOINT_FIND_BY_ID = "/grupos/findById/{idGrupo}";

    // Mapeo de las Rutas del Modulo de Tipos
    public static final String TIPOS_ENDPOINT = "/tipos";
    public static final String TIPOS_ENDPOINT_FIND_BY_ID = "/tipos/findById/{idTipo}";

    // Mapeo de las Rutas del Modulo de Ubicaciones | Pais
    public static final String PAIS_ENDPOINT = "/ubicacion-geografica/pais";
    public static final String PAIS_ENDPOINT_FIND_BY_ID = "/ubicacion-geografica/pais/findByIdPais/{idPais}";


    /*==================================================================================================================
     *================================================================================================================ */

    // Organizaciones de la API ******************************************************************************
    // Mapeo de las Rutas del Modulo de Organizaciones
    public static final String ORGANIZACIONES_ENDPOINT = "/organizaciones";
    public static final String ORGANIZACIONES_ENDPOINT_FIND_BY_ID = "/organizaciones/findByIdOrganizacion/{idOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_FIND_BY_ID_TIPO = "/organizaciones/findByIdTipoOrganizacion/{idTipoOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_FIND_BY_ID_PAIS = "/organizaciones/findByIdPaisOrganizacion/{idPaisOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_FIND_BY_ID_TIPO_PAIS = "/organizaciones/findByIdTipoPaisOrganizacion/{idTipoOrganizacion}/{idPaisOrganizacion}";
    public static final String ORGANIZACIONES_USUARIO_ENDPOINT_NEW = "/organizaciones/new";

    public static final String TIPO_ORGANIZACIONES_ENDPOINT = "/tipos-organizaciones";
    public static final String TIPO_ORGANIZACIONES_ENDPOINT_FIND_BY_ID = "/tipos-organizaciones/findByIdTipoOrganizacion/{idTipoOrganizacion}";
    // public static final String TIPO_ORGANIZACIONES_ENDPOINT_FIND_BY_IDGRUPO = "/tipos-organizaciones/findByIdGrupo/{idGrupo}";


    /*==================================================================================================================
     *================================================================================================================ */

    // Espacios de Trabajos de la API ******************************************************************************
    // Mapeo de las Rutas del Modulo de Estados
    public static final String ESPACIOS_TRABAJO_ENDPOINT = "/espacios-trabajo";
    public static final String ESPACIOS_TRABAJO_ENDPOINT_FIND_BY_ID = "/espacios-trabajo/findByIdEspacio/{idEspacioTrabajo}";
    public static final String ESPACIOS_TRABAJO_ENDPOINT_NEW = "/espacios-trabajo/new";

    public static final String ESPACIOS_TRABAJO_USUARIO_ENDPOINT = "/espacios-trabajo-usuario";
    public static final String ESPACIOS_TRABAJO_USUARIO_ENDPOINT_FIND_BY_ID = "/espacios-trabajo-usuario/findByIdUsuario/{idUsuario}";
    public static final String ESPACIOS_TRABAJO_USUARIO_ENDPOINT_NEW = "/espacios-trabajo-usuario/new";

    /*==================================================================================================================
     *================================================================================================================ */

    // Mantenimientos funcionales de la Informacion de las Actividades *************************************************
    // Mapeo de las Rutas del Sub Modulo de Sector de Ejecucion
    public static final String SECTOR_EJECUTOR_ENDPOINT = "/mant-actividades/sector-ejecutor";
    public static final String SECTOR_EJECUTOR_ENDPOINT_FIND_BY_ID = "/mant-actividades/sector-ejecutor/findById/{idSectorEjecutor}";

    // Mapeo de las Rutas del Sub Modulo de Estrategias
    public static final String ESTRATEGIAS_ENDPOINT = "/mant-actividades/estrategia";
    public static final String ESTRATEGIAS_ENDPOINT_FIND_BY_ID = "/mant-actividades/estrategia/findById/{idEstrategia}";

    // Mapeo de las Rutas del Sub Modulo de Presupuesto
    public static final String PRESUPUESTO_ENDPOINT = "/mant-actividades/presupuesto";
    public static final String PRESUPUESTO_ENDPOINT_FIND_BY_ID = "/mant-actividades/presupuesto/findById/{idEstrategia}";

    // Mapeo de las Rutas del Sub Modulo de Id Internas
    public static final String ID_INTERNA_ENDPOINT = "/mant-actividades/id-interna";
    public static final String ID_INTERNA_ENDPOINT_FIND_BY_CODIGO = "/mant-actividades/id-interna/findByCodInterna/{codIdInterna}";

    /*==================================================================================================================
     *================================================================================================================ */

    // Mapeo de las Rutas del Modulo de Usuarios
    public static final String USUARIOS_ENDPOINT = "/usuarios";
    public static final String USUARIOS_ENDPOINT_FIND_BY_COD = "/usuarios/findByCod/{codUsuario}";
    public static final String USUARIOS_ENDPOINT_FIND_BY_ID = "/usuarios/findById/{idUsuario}";
    public static final String USUARIOS_ENDPOINT_FIND_BY_MAIL = "/usuarios/findByMail/{emailUsuario}";
    public static final String USUARIOS_ENDPOINT_NEW = "/usuarios/new";

    /*==================================================================================================================
     *================================================================================================================ */

    // Mapeo de las Rutas del Modulo de Perfiles de Usuario
    public static final String PERFILES_ENDPOINT = "/profiles";
    public static final String PERFILES_ENDPOINT_NEW = "/profiles/new";
    public static final String PERFILES_ENDPOINT_TIPOS = "/profiles/kind";

}
