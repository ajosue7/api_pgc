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
    public static final String TIPOS_ENDPOINT_FIND_BY_IDGRUPO = "/tipos/findByIdGrupo/{idGrupo}";

    // Mapeo de las Rutas del Modulo de Tratos
    public static final String TRATOS_ENDPOINT = "/tratos_contacto";
    public static final String TRATOS_ENDPOINT_FIND_BY_ID = "/tratos_contacto/findById/{idTrato}";
    public static final String TRATOS_ENDPOINT_FIND_BY_IDGRUPO = "/tratos_contacto/findByIdGrupo/{idGrupo}";

    // Mapeo de las Rutas del Modulo de Ubicaciones | Pais | Departamentos | Municipios
    public static final String PAIS_ENDPOINT = "/ubicacion-geografica/pais";
    public static final String PAIS_ENDPOINT_FIND_BY_ID = "/ubicacion-geografica/pais/findByIdPais/{idPais}";
    public static final String DEPTO_ENDPOINT = "/ubicacion-geografica/departamento";
    public static final String DEPTO_ENDPOINT_FIND_BY_ID = "/ubicacion-geografica/pais/findByIdDepartamento/{idDepartamento}";
    public static final String MUNIC_ENDPOINT = "/ubicacion-geografica/municipio";
    public static final String MUNIC_ENDPOINT_FIND_BY_ID = "/ubicacion-geografica/pais/findByIdMunicipio/{idMunicipio}";

    // Mapeo de las Rutas del Modulo de Ubicaciones | Nivel de Implementacion | Nivel Ubicacion Implentacion | Ubicacion Implementacion
    public static final String NIVEL_IMPLEMENTACION_ENDPOINT = "/ubicacion-geografica/nivel-implementacion";
    public static final String NIVEL_IMPLEMENTACION_FIND_BY_ID = "/ubicacion-geografica/nivel-implementacion/findByIdNivel/{idNivel}";
    public static final String NIVEL_UBICACION_IMPLEMENTACION_ENDPOINT = "/ubicacion-geografica/nivel-ubicacion-implementacion";
    public static final String NIVEL_UBICACION_IMPLEMENTACION_FIND_BY_ID = "/ubicacion-geografica/nivel-ubicacion-implementacion/findByIdNivelUbicacion/{idNivelUbicacion}";
    public static final String NIVEL_UBICACION_IMPLEMENTACION_FIND_BY_ID_NIVEL_IMPL = "/ubicacion-geografica/nivel-ubicacion-implementacion/findByIdNivelImplementacion/{idNivelImplementacion}";
    public static final String UBICACION_IMPLEMENTACION_ENDPOINT = "/ubicacion-geografica/ubicacion-implementacion";
    public static final String UBICACION_IMPLEMENTACION_ENDPOINT_FIND_BY_ID = "/ubicacion-geografica/ubicacion-implementacion/findByIdUbicacion/{idUbicacion}";
    public static final String UBICACION_IMPLEMENTACION_ENDPOINT_FIND_BY_ID_NIVEL_IMPL = "/ubicacion-geografica/ubicacion-implementacion/findByIdNivelImplementacion/{idNivelImplementacion}";
    public static final String UBICACION_IMPLEMENTACION_ENDPOINT_FIND_BY_ID_UBIC_NIVEL_IMPL = "/ubicacion-geografica/ubicacion-implementacion/findByIdNivelUbicacion/{idNivelUbicacion}";
    public static final String UBICACION_IMPLEMENTACION_ENDPOINT_FIND_BY_ID_UBIC_NIVEL_IMPL_2 = "/ubicacion-geografica/ubicacion-implementacion/findByIdNivelImplementacionAndIdNivelUbicacion/{idNivelUbicacion}/{idNivelImplementacion}";

    /*==================================================================================================================
     *================================================================================================================ */

    // Organizaciones de la API ******************************************************************************
    // Mapeo de las Rutas del Modulo de Organizaciones
    public static final String ORGANIZACIONES_ENDPOINT = "/organizaciones";
    public static final String ORGANIZACIONES_ENDPOINT_FIND_BY_ID = "/organizaciones/findByIdOrganizacion/{idOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_FIND_BY_CODIGO = "/organizaciones/findByCodOrganizacion/{codOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_FIND_BY_CODIGO_COUNT = "/organizaciones/countByCodOrganizacion/{codOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_FIND_BY_ID_TIPO = "/organizaciones/findByIdTipoOrganizacion/{idTipoOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_FIND_BY_ID_PAIS = "/organizaciones/findByIdPaisOrganizacion/{idPaisOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_FIND_BY_ID_TIPO_PAIS = "/organizaciones/findByIdTipoPaisOrganizacion/{idTipoOrganizacion}/{idPaisOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_FIND_BY_ID_TIPO_PAIS_CATEGORIA = "/organizaciones/findByIdTipoPaisCategoriaOrganizacion/{idTipoOrganizacion}/{idPaisOrganizacion}/{idCategoriaOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_NEW = "/organizaciones/new";
    public static final String ORGANIZACIONES_ENDPOINT_EDIT = "/organizaciones/edit/{idOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_DELETE = "/organizaciones/delete/{idOrganizacion}";
    public static final String ORGANIZACIONES_ENDPOINT_FIND_BY_BOOLEAN = "/organizaciones/findByBoolean/{caseBoolean}";

    public static final String TIPO_ORGANIZACIONES_ENDPOINT = "/tipos-organizaciones";
    public static final String TIPO_ORGANIZACIONES_ENDPOINT_FIND_BY_ID = "/tipos-organizaciones/findByIdTipoOrganizacion/{idTipoOrganizacion}";
    // public static final String TIPO_ORGANIZACIONES_ENDPOINT_FIND_BY_IDGRUPO = "/tipos-organizaciones/findByIdGrupo/{idGrupo}";
    public static final String TIPO_ORGANIZACIONES_ENDPOINT_NEW = "/tipos-organizaciones/new";
    public static final String TIPO_ORGANIZACIONES_ENDPOINT_EDIT = "/tipos-organizaciones/update/{idTipoOrganizacion}";
    public static final String TIPO_ORGANIZACIONES_ENDPOINT_DELETE = "/tipos-organizaciones/delete/{idTipoOrganizacion}";

    public static final String CATEGORIA_ORGANIZACIONES_ENDPOINT = "/categorias-organizaciones";
    public static final String CATEGORIA_ORGANIZACIONES_ENDPOINT_FIND_BY_ID = "/categorias-organizaciones/findByIdCatOrganizacion/{idCatOrganizacion}";
    public static final String CATEGORIA_ORGANIZACIONES_ENDPOINT_FIND_BY_ID_TIPO_ORGANIZACION = "/categorias-organizaciones/findByIdTipoOrganizacion/{idTipoOrganizacion}";
    public static final String CATEGORIA_ORGANIZACIONES_ENDPOINT_NEW = "/categorias-organizaciones/new";
    public static final String CATEGORIA_ORGANIZACIONES_ENDPOINT_EDIT = "/categorias-organizaciones/update/{idCatOrganizacion}";
    public static final String CATEGORIA_ORGANIZACIONES_ENDPOINT_DELETE = "/categorias-organizaciones/delete/{idCatOrganizacion}";


    /*==================================================================================================================
     *================================================================================================================ */

    // Espacios de Trabajos de la API ******************************************************************************
    // Mapeo de las Rutas del Modulo de Estados
    public static final String ESPACIOS_TRABAJO_ENDPOINT = "/espacios-trabajo";
    // JOE|2019-18-06|Arreglando problemas de uri
    public static final String ESPACIOS_TRABAJO_ENDPOINT_FIND_BY_ID = "/espacios-trabajo/findByIdEspaciotrabajo/{idEspacioTrabajo}";
    public static final String ESPACIOS_TRABAJO_ENDPOINT_NEW = "/espacios-trabajo/new";
    public static final String ESPACIOS_TRABAJO_ENDPOINT_FIND_BY_CODIGO = "/espacios-trabajo/findByCodEspaciotrabajo/{codEspaciotrabajo}";
    public static final String ESPACIOS_TRABAJO_ENDPOINT_EDIT = "/espacios-trabajo/edit/{idEspacioTrabajo}";
    public static final String ESPACIOS_TRABAJO_ENDPOINT_DELETE = "/espacios-trabajo/delete/{idEspaciotrabajo}";
    public static final String TIPO__ESPACIOS_TRABAJO_ENDPOINT_FIND_BY_ID = "/tipos-espacios-trabajo/findByIdTipoEspaciotrabajo/{idTipoEspaciotrabajo}";
    public static final String ESPACIOS_TRABAJO_ENDPOINT_FIND_BY_ID_PAIS = "/espacios-trabajo/findByIdPais/{idPais}";


    //Espacios de Trabajo de Usuaio de la API

    public static final String ESPACIOS_TRABAJO_USUARIO_ENDPOINT = "/espacios-trabajo-usuario";
    public static final String ESPACIOS_TRABAJO_USUARIO_ENDPOINT_FIND_BY_IDUS = "/espacios-trabajo-usuario/findByIdEspaciotrabajousuario/{idEspacioTrabajoUsuario}";
    public static final String ESPACIOS_TRABAJO_USUARIO_ENDPOINT_NEW = "/espacios-trabajo-usuario/new";
    public static final String ESPACIOS_TRABAJO_USUARIOS_ENDPOINT_FIND_BY_ID = "/espacios-trabajo-usuario/findByIdUsuarioEspacioTrabajo/{idUsuarioEspacioTrabajo}";


    /*==================================================================================================================
     *================================================================================================================ */

    // Mantenimientos funcionales de la Informacion de las Actividades *************************************************
    // Mapeo de las Rutas del Modulo de Actividades
    public static final String ACTIVITY_ENDPOINT_NEW = "/activities/new-activity";
    public static final String ACTIVITY_ENDPOINT_EDIT = "/activities/edit-activity/{idActivity}";
    public static final String ACTIVITY_ENDPOINT_DELETE = "/activities/delete-activity/{idActivity}";
    public static final String ACTIVITY_ENDPOINT = "/activities";
    public static final String ACTIVITY_ENDPOINT_FIND_BY_ID = "/activities/findByIdActivity/{idActivity}";
    public static final String ACTIVITY_ENDPOINT_FIND_BY_COD = "/activities/findByIdCodActivity/{codActivity}";

    // Mapeo de las Rutas del Sub Modulo de Sector de Ejecucion
    public static final String SECTOR_EJECUTOR_ENDPOINT = "/mant-actividades/sector-ejecutor";
    public static final String SECTOR_EJECUTOR_ENDPOINT_FIND_BY_ID = "/mant-actividades/sector-ejecutor/findById/{idSectorEjecutor}";

    // Mapeo de las Rutas del Sub Modulo de Sector de Ejecucion
    public static final String TIPO_INICIATIVA_ENDPOINT = "/mant-actividades/tipo-iniciativa";
    public static final String TIPO_INICIATIVA_ENDPOINT_FIND_BY_ID = "/mant-actividades/tipo-iniciativa/findById/{idTipoIniciativa}";

    // Mapeo de las Rutas del Sub Modulo de Estrategias
    public static final String ESTRATEGIAS_ENDPOINT = "/mant-actividades/estrategia";
    public static final String ESTRATEGIAS_ENDPOINT_FIND_BY_ID = "/mant-actividades/estrategia/findById/{idEstrategia}";

    // Mapeo de las Rutas del Sub Modulo de Presupuesto
    public static final String PRESUPUESTO_ENDPOINT = "/mant-actividades/presupuesto";
    public static final String PRESUPUESTO_ENDPOINT_FIND_BY_ID = "/mant-actividades/presupuesto/findById/{idPresupuesto}";

    // Mapeo de las Rutas del Sub Modulo de Actividades Financ | NAM 2019-05-20
    public static final String MONENDA_ACT_ENDPOINT = "/mant-actividades/moneda-actividad";
    public static final String MONENDA_ACT_ENDPOINT_FIND_BY_ID = "/mant-actividades/moneda-actividad/findById/{idMoneda}";

    public static final String MOD_AYUDA_ACT_ENDPOINT = "/mant-actividades/modalidad-ayuda";
    public static final String MOD_AYUDA_ACT_ENDPOINT_FIND_BY_ID = "/mant-actividades/modalidad-ayuda/findById/{idModalidadAyuda}";

    public static final String TIPO_FINANC_ACT_ENDPOINT = "/mant-actividades/tipo-financiamiento";
    public static final String TIPO_FINANC_ACT_ENDPOINT_FIND_BY_ID = "/mant-actividades/tipo-financiamiento/findById/{idTipoFinanciamiento}";

    public static final String TIPO_TRANSAC_ACT_ENDPOINT = "/mant-actividades/tipo-transaccion";
    public static final String TIPO_TRANSAC_ACT_ENDPOINT_FIND_BY_ID = "/mant-actividades/tipo-transaccion/findById/{idTipoTransaccion}";

    // Mapeo de las Rutas del Sub Modulo de Id Internas
    public static final String ID_INTERNA_ENDPOINT = "/mant-actividades/id-interna";
    public static final String ID_INTERNA_ENDPOINT_FIND_BY_CODIGO = "/mant-actividades/id-interna/findByCodInterna/{codIdInterna}";
    public static final String ID_INTERNA_ENDPOINT_FIND_BY_CODIGO_COUNT = "/mant-actividades/id-interna/countByCodInterna/{codIdInterna}";
    public static final String ID_INTERNA_ENDPOINT_NEW = "/mant-actividades/id-interna/new";
    public static final String ID_INTERNA_ENDPOINT_EDIT = "/mant-actividades/id-interna/edit/{idInterna}";
    public static final String ID_INTERNA_ENDPOINT_DELETE = "/mant-actividades/id-interna/delete/{codIdInterna}";

    // Mapeo de las Rutas del Sub Modulo de Planificacion
    public static final String ID_PLANIFICACION_ENDPOINT = "/mant-actividades/planificacion";
    public static final String ID_PLANIFICACION_ENDPOINT_FIND_BY_ID_ACTIVIDAD_PLAN = "/mant-actividades/planificacion/findByIdActividadPlan/{idActividadPlan}";
    // public static final String ID_PLANIFICACION_ENDPOINT_FIND_BY_CODIGO_COUNT = "/mant-actividades/id-interna/countByCodInterna/{codIdInterna}";
    public static final String ID_PLANIFICACION_ENDPOINT_NEW = "/mant-actividades/planificacion/new";
    public static final String ID_PLANIFICACION_ENDPOINT_EDIT = "/mant-actividades/planificacion/edit/{idActividadPlan}";

    // Mapeo de las Rutas del Sub Modulo de Ubicaciones
    public static final String UBICACIONES_ACT_ENDPOINT = "/mant-actividades/ubicaciones";
    public static final String UBICACIONES_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD = "/mant-actividades/ubicaciones/findByIdActividadUbicacion/{idActividad}";
    public static final String UBICACIONES_ACT_ENDPOINT_NEW = "/mant-actividades/ubicaciones/new";
    public static final String UBICACIONES_ACT_ENDPOINT_EDIT = "/mant-actividades/ubicaciones/edit/{idActividadUbicacion}";
    public static final String UBICACIONES_ACT_ENDPOINT_DELETE = "/mant-actividades/ubicaciones/delete/{idUbicacionImpl}/{idActividad}";

    // Mapeo de las Rutas del Sub Modulo Recursos del Proyecto
    public static final String RECURSOS_DOC_ENDPOINT = "/mant-actividades/recursos-proyecto";
    public static final String RECURSOS_DOC_ENDPOINT_FIND_BY_CODIGO_ACTIVIDAD = "/mant-actividades/recursos-proyecto/findByCodActividadRecurso/{codActividadRecurso}";
    public static final String RECURSOS_DOC_ENDPOINT_FIND_BY_ID_ACTIVIDAD = "/mant-actividades/recursos-proyecto/findByIdActividadRecurso/{idActividadRecurso}";
    public static final String RECURSOS_DOC_ENDPOINT_NEW = "/mant-actividades/recursos-proyecto/new";
    public static final String RECURSOS_DOC_ENDPOINT_EDIT = "/mant-actividades/recursos-proyecto/edit/{idActividadRecurso}";
    public static final String RECURSOS_DOC_ENDPOINT_DELETE = "/mant-actividades/recursos-proyecto/delete/{idActividadRecurso}";
    public static final String RECURSOS_DOC_UPLOAD_FILE = "/mant-actividades/recursos-proyecto/uploadFile";
    public static final String RECURSOS_DOC_UPLOAD_FILE_ARRAY = "/mant-actividades/recursos-proyecto/uploadMultipleFiles";
    public static final String RECURSOS_DOC_UPLOAD_FILE_DOWLOAD = "/mant-actividades/recursos-proyecto/downloadFile/{fileName:.+}";
    public static final String RECURSOS_TIPO_ENDPOINT = "/mant-actividades/recursos-proyecto/tipos";
    public static final String RECURSOS_TIPO_ENDPOINT_FIND_BY_ID = "/mant-actividades/recursos-proyecto/tipos/{idTipoRecursos}";

    //Mapeo de las Rutas del Sub Modulo de Contactos

    // Espacios de Trabajos de la API ******************************************************************************
    // Mapeo de las Rutas del Modulo contactos
    public static final String CONTACTOS_ENDPOINT = "/actividades_contactos";
    public static final String CONTACTOS_ENDPOINT_FIND_BY_ID = "/actividades_contactos/findByIdContacto/{idContacto}";
    public static final String CONTACTOS_ENDPOINT_NEW = "/actividades_contactos/new";
    public static final String CONTACTOS_ENDPOINT_FIND_BY_CODIGO = "/actividades_contactos/findByCodigoContacto/{codigoContacto}";
    public static final String CONTACTOS_ENDPOINT_EDIT = "/actividades_contactos/edit/{idContacto}";
    public static final String CONTACTOS_ENDPOINT_DELETE = "/actividades_contactos/delete/{idContacto}";

    // Mapeo de las Rutas del Modulo de contacto proyecto
    public static final String CONTACTO_PROYECTO_ENDPOINT = "/contacto_proyecto";
    public static final String CONTACTO_PROYECTO_ENDPOINT_FIND_BY_ID = "/contacto_proyecto/findById/{idContactoProyecto}";

    // Mapeo de las Rutas del Sub Modulo de Organizaciones
    // | Socio al Desarrollo |
    public static final String ORGANIZACIONES_ACT_ENDPOINT_SOCIO_DESARROLLO = "/mant-actividades/organizaciones/socio-desarrollo";
    public static final String ORGANIZACIONES_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_SOCIO_DESARROLLO = "/mant-actividades/organizaciones/socio-desarrollo/findByIdActividad/{idActividad}";
    public static final String ORGANIZACIONES_ACT_ENDPOINT_FIND_BY_COD_PROGRAMA_SOCIO_DESARROLLO = "/mant-actividades/organizaciones/socio-desarrollo/findByCodigoActividad/{codigoActividad}";
    public static final String ORGANIZACIONES_ACT_ENDPOINT_NEW_SOCIO_DESARROLLO = "/mant-actividades/organizaciones/socio-desarrollo/new";
    public static final String ORGANIZACIONES_ACT_ENDPOINT_DELETE_SOCIO_DESARROLLO = "/mant-actividades/organizaciones/socio-desarrollo/delete/{codigoActividad}";
    // mapeo financiera
    public static final String ORGANIZACIONES_ACT_ENDPOINT_ADMON_FINANCIERO = "/mant-actividades/organizaciones/admon-financiero";
    public static final String ORGANIZACIONES_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_ADMON_FINANCIERO = "/mant-actividades/organizaciones/admon-financiero/findByIdActividad/{idActividad}";
    public static final String ORGANIZACIONES_ACT_ENDPOINT_FIND_BY_COD_PROGRAMA_ADMON_FINANCIERO = "/mant-actividades/organizaciones/admon-financiero/findByCodigoActividad/{codigoActividad}";
    public static final String ORGANIZACIONES_ACT_ENDPOINT_NEW_ADMON_FINANCIERO = "/mant-actividades/organizaciones/admon-financiero/new";
    public static final String ORGANIZACIONES_ENDPOINT_EDIT_ADMON_FINANCIERO = "/mant-actividades/organizaciones/admon-financiero/findByIdActividad/{idActividad}";
    public static final String ORGANIZACIONES_ACT_ENDPOINT_DELETE_ADMON_FINANCIERO = "/mant-actividades/organizaciones/admon-financiero/delete/{codigoActividad}";

    //mapeo agencia beneficiaria
    public static final String ORGANIZACIONES_ACT_ENDPOINT_AGENCIA_BENEFICIARIA = "/mant-actividades/organizaciones/agencia-beneficiaria";
    public static final String ORGANIZACIONES_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_AGENCIA_BENEFICIARIA = "/mant-actividades/organizaciones/agencia-beneficiaria/findByIdActividad/{idActividad}";
    public static final String ORGANIZACIONES_ACT_ENDPOINT_FIND_BY_COD_PROGRAMA_AGENCIA_BENEFICIARIA = "/mant-actividades/organizaciones/agencia-beneficiaria/findByCodigoActividad/{codigoActividad}";
    public static final String ORGANIZACIONES_ACT_ENDPOINT_NEW_AGENCIA_BENEFICIARIA = "/mant-actividades/organizaciones/agencia-beneficiaria/new";
    public static final String ORGANIZACIONES_ENDPOINT_EDIT_AGENCIA_BENEFICIARIA = "/mant-actividades/organizaciones/agencia-beneficiaria/edit/{idActividadAgenciaBeneficiaria}";
    public static final String ORGANIZACIONES_ACT_ENDPOINT_DELETE_AGENCIA_BENEFICIARIA = "/mant-actividades/organizaciones/agencia-beneficiaria/delete/{codigoActividad}";


    // | Unidad Ejecutora |
    public static final String ORGANIZACIONES_ACT_ENDPOINT_UNIDAD_EJECUTORA = "/mant-actividades/organizaciones/unidad-ejecutora";
    public static final String ORGANIZACIONES_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_UNIDAD_EJECUTORA = "/mant-actividades/organizaciones/unidad-ejecutora/findByIdActividad/{idActividad}";
    public static final String ORGANIZACIONES_ACT_ENDPOINT_FIND_BY_COD_PROGRAMA_UNIDAD_EJECUTORA = "/mant-actividades/organizaciones/unidad-ejecutora/findByCodigoActividad/{codigoActividad}";
    public static final String ORGANIZACIONES_ACT_ENDPOINT_NEW_UNIDAD_EJECUTORA = "/mant-actividades/organizaciones/unidad-ejecutora/new";
    public static final String ORGANIZACIONES_ACT_ENDPOINT_DELETE_UNIDAD_EJECUTORA = "/mant-actividades/organizaciones/unidad-ejecutora/delete/{codigoActividad}";
    public static final String ORGANIZACIONES_ACT_ENDPOINT_EDIT_UNIDAD_EJECUTORA = "/mant-actividades/organizaciones/unidad-ejecutora/edit/{idActividadUnidadEjecutora}";

    // Mapeo de las Rutas del Sub Modulo de Financiamiento
    // Encabezado de Financiamiento ====================================================================================
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_FINANC_ENC = "/mant-actividades/financiamiento/encabezado";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_FINANC_ENC = "/mant-actividades/financiamiento/encabezado/findByIdActividad/{idActividad}";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_COD_FINANC_ENC = "/mant-actividades/financiamiento/encabezado/findByCodigoFinancEnc/{codigoFinancEnc}";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_NEW_FINANC_ENC = "/mant-actividades/financiamiento/encabezado/new";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_EDIT_FINANC_ENC = "/mant-actividades/financiamiento/encabezado/edit/{idActividadFinancEnc}";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_DELETE_FINANC_ENC = "/mant-actividades/financiamiento/encabezado/delete/{codigoFinancEnc}";

    // Detalle de Financiamiento =======================================================================================
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_FINANC_DET = "/mant-actividades/financiamiento/detalle";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_FINANC_DET = "/mant-actividades/financiamiento/detalle/findByIdActividadFinancEnc/{idActividadFinancEnc}";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_ENC_AND_ID_SOCIO_DESARROLLO = "/mant-actividades/financiamiento/detalle/findByIdActividadFinancEncAndIdSocioDesarollo/{idActividadFinancEnc}/idSocioDesarrollo/{idSocioDesarrollo}";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_COD_FINANC_DET = "/mant-actividades/financiamiento/detalle/findByCodigoFinancDet/{codigoFinancDet}";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_NEW_FINANC_DET = "/mant-actividades/financiamiento/detalle/new";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_EDIT_FINANC_DET = "/mant-actividades/financiamiento/detalle/edit/{idActividadFinancDet}";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_DELETE_FINANC_DET = "/mant-actividades/financiamiento/detalle/delete/{codigoFinancDet}";

    // Detalle de Compromisos =======================================================================================
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_FINANC_DET_COMPROMISO = "/mant-actividades/financiamiento/detalle-compromiso";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_FINANC_DET_COMPROMISO = "/mant-actividades/financiamiento/detalle-compromiso/findByIdActividadFinancDetCompromiso/{idActividadFinancDetCompromiso}";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_FINANC_DET_2 = "/mant-actividades/financiamiento/detalle-compromiso/findByIdActividadDet/{idActividadDet}";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_COD_FINANC_DET_COMPROMISO = "/mant-actividades/financiamiento/detalle-compromiso/findByCodigoFinancDetCompromiso/{codigoFinancDetCompromiso}";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_NEW_FINANC_DET_COMPROMISO = "/mant-actividades/financiamiento/detalle-compromiso/new";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_EDIT_FINANC_DET_COMPROMISO = "/mant-actividades/financiamiento/detalle-compromiso/edit/{idActividadFinancDetCompromiso}";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_DELETE_FINANC_DET_COMPROMISO = "/mant-actividades/financiamiento/detalle-compromiso/delete/{codigoFinancCompromiso}";

    // Detalle de Desembolso =======================================================================================
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_FINANC_DET_DESEMBOLSO = "/mant-actividades/financiamiento/detalle-desembolso";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_FINANC_DET_DESEMBOLSO = "/mant-actividades/financiamiento/detalle-desembolso/findByIdActividadFinancDetDesembolso/{idActividadFinancDetDesembolso}";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_FINANC_DET_DESEMBOLSO_2 = "/mant-actividades/financiamiento/detalle-desembolso/findByIdActividadDet/{idActividadDet}";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_COD_FINANC_DET_DESEMBOLSO = "/mant-actividades/financiamiento/detalle-desembolso/findByCodigoFinancDetDesembolso/{codigoFinancDetDesembolso}";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_NEW_FINANC_DET_DESEMBOLSO = "/mant-actividades/financiamiento/detalle-desembolso/new";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_EDIT_FINANC_DET_DESEMBOLSO = "/mant-actividades/financiamiento/detalle-desembolso/edit/{idActividadFinancDetDesembolso}";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_DELETE_FINANC_DET_DESEMBOLSO = "/mant-actividades/financiamiento/detalle-desembolso/delete/{codigoFinancDesembolso}";

    // Detalle de Gastos =======================================================================================
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_FINANC_DET_GASTO = "/mant-actividades/financiamiento/detalle-gasto";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_FINANC_DET_GASTO = "/mant-actividades/financiamiento/detalle-gasto/findByIdActividadFinancDetGasto/{idActividadFinancDetGasto}";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_ID_ACTIVIDAD_FINANC_DET_GASTO_2 = "/mant-actividades/financiamiento/detalle-gasto/findByIdActividadDet/{idActividadDet}";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_FIND_BY_COD_FINANC_DET_GASTO = "/mant-actividades/financiamiento/detalle-gasto/findByCodigoFinancDetGasto/{codigoFinancDetGasto}";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_NEW_FINANC_DET_GASTO = "/mant-actividades/financiamiento/detalle-gasto/new";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_EDIT_FINANC_DET_GASTO = "/mant-actividades/financiamiento/detalle-gasto/edit/{idActividadFinancDetGasto}";
    public static final String FINANCIAMIENTO_ACT_ENDPOINT_DELETE_FINANC_DET_GASTO = "/mant-actividades/financiamiento/detalle-gasto/delete/{codigoFinancGasto}";

    /*==================================================================================================================
     *================================================================================================================ */

    // Mapeo de las Rutas de Sectores y Programas
    // Sectores de Proyectos ===========================================================================================
    // Sectores OCDE/CAD
    public static final String SECTORES_OCDE_ENDPOINT = "/sectores/ocde-cad";
    public static final String SECTORES_OCDE_ACT_ENDPOINT = "/mant-actividades/sectores/ocde-cad-proyecto";
    public static final String SECTORES_OCDE_ENDPOINT_FIND_BY_COD_SECTOR = "/mant-actividades/sectores/ocde-cad-proyecto/findByCodigoActividad/{codigoActividad}";
    public static final String SECTORES_OCDE_ENDPOINT_FIND_BY_ID_ACTIVIDAD = "/mant-actividades/sectores/ocde-cad-proyecto/findByIdActividad/{idActividad}";
    public static final String SECTORES_OCDE_ENDPOINT_FIND_BY_ID_SECTOR = "/sectores/ocde-cad/findByIdSector/{idSector}";
    public static final String SECTORES_OCDE_ENDPOINT_FIND_BY_ID_TIPO_NIVEL_PADRE = "/sectores/ocde-cad/findByIdNivelSector/{idNivelSector}/findBySectorPadreId/{sectorPadreId}";
    public static final String SECTORES_OCDE_ENDPOINT_FIND_BY_ID_NIVEL_SECTOR = "/sectores/ocde-cad/findByIdNivelSector/{idNivelSector}";
    public static final String SECTORES_OCDE_ENDPOINT_NEW = "/mant-actividades/sectores/ocde-cad-proyecto/new";
    public static final String SECTORES_OCDE_ENDPOINT_EDIT = "/mant-actividades/sectores/ocde-cad-proyecto/edit";
    public static final String SECTORES_OCDE_ENDPOINT_DELETE = "/mant-actividades/sectores/ocde-cad-proyecto/delete/{codigoActividad}";

    // Sectores Gobierno
    public static final String SECTORES_GOBIERNO_ENDPOINT = "/sectores/gobierno";
    public static final String SECTORES_GOBIERNO_ACT_ENDPOINT = "/mant-actividades/sectores/gobierno-proyecto";
    public static final String SECTORES_GOBIERNO_ENDPOINT_FIND_BY_COD_SECTOR = "/mant-actividades/sectores/gobierno-proyecto/findByCodigoActividad/{codigoActividad}";
    public static final String SECTORES_GOBIERNO_ENDPOINT_FIND_BY_ID_ACTIVIDAD = "/mant-actividades/sectores/gobierno-proyecto/findByIdActividad/{idActividad}";
    public static final String SECTORES_GOBIERNO_ENDPOINT_FIND_BY_ID_SECTOR = "/sectores/gobierno/findByIdSector/{idSector}";
    public static final String SECTORES_GOBIERNO_ENDPOINT_FIND_BY_ID_TIPO_NIVEL_PADRE = "/sectores/gobierno/findByIdNivelSector/{idNivelSector}/findBySectorPadreId/{sectorPadreId}";
    public static final String SECTORES_GOBIERNO_ENDPOINT_FIND_BY_ID_NIVEL_SECTOR = "/sectores/gobierno/findByIdNivelSector/{idNivelSector}";
    public static final String SECTORES_GOBIERNO_ENDPOINT_NEW = "/mant-actividades/sectores/gobierno-proyecto/new";
    public static final String SECTORES_GOBIERNO_ENDPOINT_EDIT = "/mant-actividades/sectores/gobierno-proyecto/edit";
    public static final String SECTORES_GOBIERNO_ENDPOINT_DELETE = "/mant-actividades/sectores/gobierno-proyecto/delete/{codigoActividad}";

    // Sectores Campos Transversales
    public static final String SECTORES_CAMPOS_ENDPOINT = "/sectores/campos-transversales";
    public static final String SECTORES_CAMPOS_ACT_ENDPOINT = "/mant-actividades/sectores/campos-transversales-proyecto";
    public static final String SECTORES_CAMPOS_ENDPOINT_FIND_BY_COD_SECTOR = "/mant-actividades/sectores/campos-transversales-proyecto/findByCodigoActividad/{codigoActividad}";
    public static final String SECTORES_CAMPOS_ENDPOINT_FIND_BY_ID_ACTIVIDAD = "/mant-actividades/sectores/campos-transversales-proyecto/findByIdActividad/{idActividad}";
    public static final String SECTORES_CAMPOS_ENDPOINT_FIND_BY_ID_SECTOR = "/sectores/campos-transversales/findByIdSector/{idSector}";
    public static final String SECTORES_CAMPOS_ENDPOINT_FIND_BY_ID_TIPO_NIVEL_PADRE = "/sectores/campos-transversales/findByIdNivelSector/{idNivelSector}/findBySectorPadreId/{sectorPadreId}";
    public static final String SECTORES_CAMPOS_ENDPOINT_FIND_BY_ID_NIVEL_SECTOR = "/sectores/campos-transversales/findByIdNivelSector/{idNivelSector}";
    public static final String SECTORES_CAMPOS_ENDPOINT_NEW = "/mant-actividades/sectores/campos-transversales-proyecto/new";
    public static final String SECTORES_CAMPOS_ENDPOINT_EDIT = "/mant-actividades/sectores/campos-transversales-proyecto/edit";
    public static final String SECTORES_CAMPOS_ENDPOINT_DELETE = "/mant-actividades/sectores/campos-transversales-proyecto/delete/{codigoActividad}";

    // Programas ODS
    public static final String SECTORES_ODS_ENDPOINT = "/sectores/ods";
    public static final String SECTORES_ODS_ACT_ENDPOINT = "/mant-actividades/sectores/ods-proyecto";
    public static final String SECTORES_ODS_ENDPOINT_FIND_BY_COD_SECTOR = "/mant-actividades/sectores/ods-proyecto/findByCodigoActividad/{codigoActividad}";
    public static final String SECTORES_ODS_ENDPOINT_FIND_BY_ID_ACTIVIDAD = "/mant-actividades/sectores/ods-proyecto/findByIdActividad/{idActividad}";
    public static final String SECTORES_ODS_ENDPOINT_FIND_BY_ID_SECTOR = "/sectores/ods/findByIdSector/{idSector}";
    public static final String SECTORES_ODS_ENDPOINT_FIND_BY_ID_TIPO_NIVEL_PADRE = "/sectores/ods/findByIdNivelSector/{idNivelSector}/findBySectorPadreId/{sectorPadreId}";
    public static final String SECTORES_ODS_ENDPOINT_FIND_BY_ID_NIVEL_SECTOR = "/sectores/ods/findByIdNivelSector/{idNivelSector}";
    public static final String SECTORES_ODS_ENDPOINT_NEW = "/mant-actividades/sectores/ods-proyecto/new";
    public static final String SECTORES_ODS_ENDPOINT_EDIT = "/mant-actividades/sectores/ods-proyecto/edit";
    public static final String SECTORES_ODS_ENDPOINT_DELETE = "/mant-actividades/sectores/ods-proyecto/delete/{codigoActividad}";

    // Programas e Nacion ==============================================================================================
    // Programas Plan de Nacion
    public static final String PROGRAMAS_PLAN_NACION_ENDPOINT = "/programas/plan-nacion";
    public static final String PROGRAMAS_PLAN_NACION_ACT_ENDPOINT = "/mant-actividades/programas/plan-nacion-proyecto";
    public static final String PROGRAMAS_PLAN_NACION_ENDPOINT_FIND_BY_COD_PROGRAMA = "/mant-actividades/programas/plan-nacion-proyecto/findByCodigoActividad/{codigoActividad}";
    public static final String PROGRAMAS_PLAN_NACION_ENDPOINT_FIND_BY_ID_ACTIVIDAD = "/mant-actividades/programas/plan-nacion-proyecto/findByIdActividad/{idActividad}";
    public static final String PROGRAMAS_PLAN_NACION_ENDPOINT_FIND_BY_ID_PROGRAMA = "/programas/plan-nacion/findByIdPrograma/{idPrograma}";
    public static final String PROGRAMAS_PLAN_NACION_ENDPOINT_FIND_BY_ID_TIPO_NIVEL_PADRE = "/programas/plan-nacion/findByIdNivelPrograma/{idNivelPrograma}/findByProgramaPadreId/{sectorPadreId}";
    public static final String PROGRAMAS_PLAN_NACION_ENDPOINT_FIND_BY_ID_NIVEL_PROGRAMA = "/programas/plan-nacion/findByIdNivelPrograma/{idNivelPrograma}";
    public static final String PROGRAMAS_PLAN_NACION_ENDPOINT_NEW = "/mant-actividades/programas/plan-nacion-proyecto/new";
    public static final String PROGRAMAS_PLAN_NACION_ENDPOINT_EDIT = "/mant-actividades/programas/plan-nacion-proyecto/edit";
    public static final String PROGRAMAS_PLAN_NACION_ENDPOINT_DELETE = "/mant-actividades/programas/plan-nacion-proyecto/delete/{codigoActividad}";

    // Programas Vida Mejor
    public static final String PROGRAMAS_VIDA_MEJOR_ENDPOINT = "/programas/vida-mejor";
    public static final String PROGRAMAS_VIDA_MEJOR_ACT_ENDPOINT = "/mant-actividades/programas/vida-mejor-proyecto";
    public static final String PROGRAMAS_VIDA_MEJOR_ENDPOINT_FIND_BY_COD_PROGRAMA = "/mant-actividades/programas/vida-mejor-proyecto/findByCodigoActividad/{codigoActividad}";
    public static final String PROGRAMAS_VIDA_MEJOR_ENDPOINT_FIND_BY_ID_ACTIVIDAD = "/mant-actividades/programas/vida-mejor-proyecto/findByIdActividad/{idActividad}";
    public static final String PROGRAMAS_VIDA_MEJOR_ENDPOINT_FIND_BY_ID_PROGRAMA = "/programas/vida-mejor/findByIdPrograma/{idPrograma}";
    public static final String PROGRAMAS_VIDA_MEJOR_ENDPOINT_FIND_BY_ID_TIPO_NIVEL_PADRE = "/programas/vida-mejor/findByIdNivelPrograma/{idNivelPrograma}/findByProgramaPadreId/{sectorPadreId}";
    public static final String PROGRAMAS_VIDA_MEJOR_ENDPOINT_FIND_BY_ID_NIVEL_PROGRAMA = "/programas/vida-mejor/findByIdNivelPrograma/{idNivelPrograma}";
    public static final String PROGRAMAS_VIDA_MEJOR_ENDPOINT_NEW = "/mant-actividades/programas/vida-mejor-proyecto/new";
    public static final String PROGRAMAS_VIDA_MEJOR_ENDPOINT_EDIT = "/mant-actividades/programas/vida-mejor-proyecto/edit";
    public static final String PROGRAMAS_VIDA_MEJOR_ENDPOINT_DELETE = "/mant-actividades/programas/vida-mejor-proyecto/delete/{codigoActividad}";

    // Programas Vision de Pais
    public static final String PROGRAMAS_VISION_PAIS_ENDPOINT = "/programas/vision-pais";
    public static final String PROGRAMAS_VISION_PAIS_ACT_ENDPOINT = "/mant-actividades/programas/vision-pais-proyecto";
    public static final String PROGRAMAS_VISION_PAIS_ENDPOINT_FIND_BY_COD_PROGRAMA = "/mant-actividades/programas/vision-pais-proyecto/findByCodigoActividad/{codigoActividad}";
    public static final String PROGRAMAS_VISION_PAIS_ENDPOINT_FIND_BY_ID_ACTIVIDAD = "/mant-actividades/programas/vision-pais-proyecto/findByIdActividad/{idActividad}";
    public static final String PROGRAMAS_VISION_PAIS_ENDPOINT_FIND_BY_ID_PROGRAMA = "/programas/vision-pais/findByIdPrograma/{idPrograma}";
    public static final String PROGRAMAS_VISION_PAIS_ENDPOINT_FIND_BY_ID_TIPO_NIVEL_PADRE = "/programas/vision-pais/findByIdNivelPrograma/{idNivelPrograma}/findByProgramaPadreId/{sectorPadreId}";
    public static final String PROGRAMAS_VISION_PAIS_ENDPOINT_FIND_BY_ID_NIVEL_PROGRAMA = "/programas/vision-pais/findByIdNivelPrograma/{idNivelPrograma}";
    public static final String PROGRAMAS_VISION_PAIS_ENDPOINT_NEW = "/mant-actividades/programas/vision-pais-proyecto/new";
    public static final String PROGRAMAS_VISION_PAIS_ENDPOINT_EDIT = "/mant-actividades/programas/vision-pais-proyecto/edit";
    public static final String PROGRAMAS_VISION_PAIS_ENDPOINT_DELETE = "/mant-actividades/programas/vision-pais-proyecto/delete/{codigoActividad}";

    // Programas Politicas Publicas
    public static final String PROGRAMAS_POLITICA_PUBLICA_ENDPOINT = "/programas/politica-publica";
    public static final String PROGRAMAS_POLITICA_PUBLICA_ACT_ENDPOINT = "/mant-actividades/programas/politica-publica-proyecto";
    public static final String PROGRAMAS_POLITICA_PUBLICA_ENDPOINT_FIND_BY_COD_PROGRAMA = "/mant-actividades/programas/politica-publica-proyecto/findByCodigoActividad/{codigoActividad}";
    public static final String PROGRAMAS_POLITICA_PUBLICA_ENDPOINT_FIND_BY_ID_ACTIVIDAD = "/mant-actividades/programas/politica-publica-proyecto/findByIdActividad/{idActividad}";
    public static final String PROGRAMAS_POLITICA_PUBLICA_ENDPOINT_FIND_BY_ID_PROGRAMA = "/programas/politica-publica/findByIdPrograma/{idPrograma}";
    public static final String PROGRAMAS_POLITICA_PUBLICA_ENDPOINT_FIND_BY_ID_TIPO_NIVEL_PADRE = "/programas/politica-publica/findByIdNivelPrograma/{idNivelPrograma}/findByProgramaPadreId/{sectorPadreId}";
    public static final String PROGRAMAS_POLITICA_PUBLICA_ENDPOINT_FIND_BY_ID_NIVEL_PROGRAMA = "/programas/politica-publica/findByIdNivelPrograma/{idNivelPrograma}";
    public static final String PROGRAMAS_POLITICA_PUBLICA_ENDPOINT_NEW = "/mant-actividades/programas/politica-publica-proyecto/new";
    public static final String PROGRAMAS_POLITICA_PUBLICA_ENDPOINT_EDIT = "/mant-actividades/programas/politica-publica-proyecto/edit";
    public static final String PROGRAMAS_POLITICA_PUBLICA_ENDPOINT_DELETE = "/mant-actividades/programas/politica-publica-proyecto/delete/{codigoActividad}";


    /*==================================================================================================================
     *================================================================================================================ */

    // Mapeo de las Rutas del Modulo de Usuarios
    public static final String USUARIOS_ENDPOINT = "/usuarios";
    public static final String USUARIOS_ENDPOINT_FIND_BY_COD = "/usuarios/findByCod/{codUsuario}";
    public static final String USUARIOS_ENDPOINT_FIND_BY_ID = "/usuarios/findById/{idUsuario}";
    public static final String USUARIOS_ENDPOINT_FIND_BY_MAIL = "/usuarios/findByMail/{emailUsuario}";
    public static final String USUARIOS_ENDPOINT_NEW = "/usuarios/new";
    public static final String USUARIOS_ENDPOINT_UPDATE = "/usuarios/update/{idUsuario}";
    public static final String USUARIOS_ENDPOINT_DELETE = "/usuarios/delete/{idUsuario}";
    public static final String USUARIOS_ENDPOINT_TIPO = "/usuarios/kind";
    public static final String USUARIOS_ENDPOINT_FIND_BY_ROL = "/usuarios/findByRol/{emailUsuario}";

    /*==================================================================================================================
     *================================================================================================================ */

    // Mapeo de las Rutas del Modulo de Perfiles de Usuario
    public static final String PERFILES_ENDPOINT = "/profiles";
    public static final String PERFILES_ENDPOINT_NEW = "/profiles/new";
    public static final String PERFILES_ENDPOINT_EDIT = "/profiles/edit/{idPerfil}";
    public static final String PERFILES_ENDPOINT_DELETE = "/profiles/delete/{idPerfil}";
    public static final String PERFILES_ENDPOINT_TIPOS = "/profiles/kind";

    /*==================================================================================================================
     *================================================================================================================ */

    // Mapeo de las Rutas del Modulo de Secuencias
    public static final String SECUENCIAS_ENDPOINT = "/secuencias";
    public static final String SECUENCIAS_ENDPOINT_FIND_BY_ID = "/secuencias/findByIdSecuencia/{idSecuencia}";
    public static final String SECUENCIAS_ENDPOINT_FIND_BY_COD = "/secuencias/findByCodSecuencia/{codSecuencia}";
    public static final String SECUENCIAS_ENDPOINT_UPDATE = "/secuencias/update/{idSecuencia}";

    // Mapeo de las Rutas del Modulo de Roles
    public static final String ROLES_ENDPOINT = "/roles";
    public static final String ROLES_FIND_BY_ID_GRUPO = "/roles/findByIdGrupo/{idGrupo}";

}
