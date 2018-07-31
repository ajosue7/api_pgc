package com.api.pgc.core.APIRestPGC.utilities;

import java.util.Date;

public class configAPI {
    // Constantes del Modulo de General de la API
    public static final String API_BASE_PATH = "/api/v1";


    //Mapeo de las Rutas del Modulo de Estados
    public static final String ESTADOS_ENDPOINT = "/estados";
    public static final String ESTADOS_ENDPOINT_FIND_BY_ID = "/estados/findById/{idEstado}";
    public static final String ESTADOS_ENDPOINT_LIST1 = API_BASE_PATH + "/estados/findById/{idEstado}";

    //Mapeo de las Rutas del Modulo de Grupos
    public static final String GRUPOS_ENDPOINT = "/grupos";
    public static final String GRUPOS_ENDPOINT_FIND_BY_ID = "/grupos/findById/{idGrupo}";

    //Mapeo de las Rutas del Modulo de Tipos
    public static final String TIPOS_ENDPOINT = "/tipos";
    public static final String TIPOS_ENDPOINT_FIND_BY_ID = "/tipos/findById/{idTipo}";

    //Mapeo de las Rutas del Modulo de Usuarios
    public static final String USUARIOS_ENDPOINT = "/usuarios";
    public static final String USUARIOS_ENDPOINT_FIND_BY_COD = "/usuarios/findByCod/{codUsuario}";
    public static final String USUARIOS_ENDPOINT_FIND_BY_ID = "/usuarios/findById/{idUsuario}";
    public static final String USUARIOS_ENDPOINT_FIND_BY_MAIL = "/usuarios/findByMail/{emailUsuario}";
    public static final String USUARIOS_ENDPOINT_NEW = "/usuarios/new";

}
