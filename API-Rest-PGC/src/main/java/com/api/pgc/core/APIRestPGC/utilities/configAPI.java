package com.api.pgc.core.APIRestPGC.utilities;

import java.util.Date;

public class configAPI {
    // Constantes del Modulo de General de la API
    public static final String API_BASE_PATH = "/api/v1";

    /*==================================================================================================================
     *================================================================================================================ */

    // Mantenimientos Genericos de la API ******************************************************************************
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


    /*==================================================================================================================
     *================================================================================================================ */

    // Espacios de Trabajos de la API ******************************************************************************
        //Mapeo de las Rutas del Modulo de Estados
        public static final String ESPACIOS_TRABAJO_ENDPOINT = "/espacios-trabajo";
        public static final String ESPACIOS_TRABAJO_ENDPOINT_FIND_BY_ID = "/espacios-trabajo/findById/{idEspacioTrabajo}";
        public static final String ESPACIOS_TRABAJO_ENDPOINT_NEW = "/espacios-trabajo/new";

    /*==================================================================================================================
     *================================================================================================================ */

    // Mantenimientos funcionales de la Informacion de las Actividades *************************************************
        //Mapeo de las Rutas del Sub Modulo de Sector de Ejecucion
        public static final String SECTOR_EJECUTOR_ENDPOINT = "/mant-actividades/sector-ejecutor";
        public static final String SECTOR_EJECUTOR_ENDPOINT_FIND_BY_ID = "/mant-actividades/sector-ejecutor/findById/{idSectorEjecutor}";

        //Mapeo de las Rutas del Sub Modulo de Estrategias
        public static final String ESTRATEGIAS_ENDPOINT = "/mant-actividades/estrategia";
        public static final String ESTRATEGIAS_ENDPOINT_FIND_BY_ID = "/mant-actividades/estrategia/findById/{idEstrategia}";

        //Mapeo de las Rutas del Sub Modulo de Presupuesto
        public static final String PRESUPUESTO_ENDPOINT = "/mant-actividades/presupuesto";
        public static final String PRESUPUESTO_ENDPOINT_FIND_BY_ID = "/mant-actividades/presupuesto/findById/{idEstrategia}";

    /*==================================================================================================================
     *================================================================================================================ */

    //Mapeo de las Rutas del Modulo de Usuarios
    public static final String USUARIOS_ENDPOINT = "/usuarios";
        public static final String USUARIOS_ENDPOINT_FIND_BY_COD = "/usuarios/findByCod/{codUsuario}";
        public static final String USUARIOS_ENDPOINT_FIND_BY_ID = "/usuarios/findById/{idUsuario}";
        public static final String USUARIOS_ENDPOINT_FIND_BY_MAIL = "/usuarios/findByMail/{emailUsuario}";
        public static final String USUARIOS_ENDPOINT_NEW = "/usuarios/new";

}
