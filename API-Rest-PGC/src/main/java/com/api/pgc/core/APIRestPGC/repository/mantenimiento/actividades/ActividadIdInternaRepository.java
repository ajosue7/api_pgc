package com.api.pgc.core.APIRestPGC.repository.mantenimiento.actividades;

import com.api.pgc.core.APIRestPGC.models.mantenimiento.actividades.TblActividadIdInterna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadIdInternaRepository extends JpaRepository<TblActividadIdInterna, Integer> {
    /**
     * Metodo que despliega las IdInternas de la BD
     * @autor Nahum Martinez | NAM
     * @version  19/12/2018/v1.0
     * @return IdInterna de la BD, por paramtro de ID
     * @param codIdInterna
     */
    TblActividadIdInterna findByCodIdInterna(String codIdInterna);
}
