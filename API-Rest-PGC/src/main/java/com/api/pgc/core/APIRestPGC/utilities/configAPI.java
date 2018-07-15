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
    public static final String ESTADOS_ENDPOINT_FIND_BY_ID = ESTADOS_ENDPOINT + "/{idEstado}";

    // Grupos de la API
    public static final String GRUPOS_ENDPOINT = "/grupos";
    public static final String GRUPOS_ENDPOINT_FIND_BY_ID = GRUPOS_ENDPOINT + "/{idGrupo}";

    // Tipos de la API
    public static final String TIPOS_ENDPOINT = "/tipos";
    public static final String TIPOS_ENDPOINT_FIND_BY_ID = TIPOS_ENDPOINT + "/{idTipo}";

    // Mapping de los EndPoints, que estaran en la White List, sin validacion de JWT
    public static final String ESTADOS_ENDPOINT_LIST1 = API_BASE_PATH + ESTADOS_ENDPOINT + "/{idEstado}";
}
