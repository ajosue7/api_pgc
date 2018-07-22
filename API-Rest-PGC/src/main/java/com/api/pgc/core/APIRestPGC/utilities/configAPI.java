package com.api.pgc.core.APIRestPGC.utilities;

public class configAPI {
    // Constantes Generales de la API
    public static final String NAME_API = "/api";

    public static final String VERSION_API = "/v1";

    public static final String API_BASE_PATH = NAME_API + VERSION_API;

    // Mapping Modulo Auth
    // public static final String LOGIN_URL = "v1";

    // Mapping Modulo de Mantenimientos
    // Estados de la API
    public static final String ESTADOS_ENDPOINT = "/estados";
    public static final String ESTADOS_ENDPOINT_FIND_BY_ID = ESTADOS_ENDPOINT + "/findById/{idEstado}";

    // Grupos de la API
    public static final String GRUPOS_ENDPOINT = "/grupos";
    public static final String GRUPOS_ENDPOINT_FIND_BY_ID = GRUPOS_ENDPOINT + "/findById/{idGrupo}";

    // Tipos de la API
    public static final String TIPOS_ENDPOINT = "/tipos";
    public static final String TIPOS_ENDPOINT_FIND_BY_ID = TIPOS_ENDPOINT + "/findById/{idTipo}";

    // Modulo de Segurirdad / Usuarios de la API ***********************************************************************
    public static final String USUARIOS_ENDPOINT = "/usuarios";
    public static final String USUARIOS_ENDPOINT_FIND_BY_COD = USUARIOS_ENDPOINT + "/findByCod/{codUsuario}";
    public static final String USUARIOS_ENDPOINT_FIND_BY_ID = USUARIOS_ENDPOINT + "/findById/{idUsuario}";
    public static final String USUARIOS_ENDPOINT_NEW = USUARIOS_ENDPOINT + "/new";
    public static final String USUARIOS_ENDPOINT_FIND_BY_MAIL = USUARIOS_ENDPOINT + "/findByMail/{emailUsuario}";


    // *****************************************************************************************************************
    // Mapping de los EndPoints, que estaran en la White List, sin validacion de JWT
    public static final String ESTADOS_ENDPOINT_LIST1 = API_BASE_PATH + ESTADOS_ENDPOINT + "/findById/{idEstado}";
}
