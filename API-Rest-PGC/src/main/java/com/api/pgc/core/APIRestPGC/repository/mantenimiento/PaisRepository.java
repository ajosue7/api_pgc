package com.api.pgc.core.APIRestPGC.repository.mantenimiento;

import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblPais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository  extends JpaRepository<TblPais, Integer> {

    /**
     * Metodo que despliega el Pais de la BD
     * @autor Nahum Martinez | NAM
     * @version  03/07/2018/v1.0
     * @return Pais de la BD, por paramtro de ID
     * @param idPais
     */
    TblPais findByIdPais ( long idPais );
}
