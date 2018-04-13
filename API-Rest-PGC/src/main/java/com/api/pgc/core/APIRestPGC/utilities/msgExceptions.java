package com.api.pgc.core.APIRestPGC.utilities;

import com.api.pgc.core.APIRestPGC.models.TblTipo;

import java.util.HashMap;
import java.util.List;

public class msgExceptions {
    //Propiedades de la Clase
    public String msgMethod = null;
    public HashMap<String, Object> map = new HashMap<>();



    public HashMap<String, Object> msgJson(String msgMethodIn, int statusIn ){
        //msgMethod = "Lista de todos los Grupos Disponibles";
        //Parametros de la Clase
        map.put("message", msgMethodIn);
        map.put("status", statusIn);
        //map.put("data", modelIn);

        //Retorno del Mensaje
        return map;
    }

}
