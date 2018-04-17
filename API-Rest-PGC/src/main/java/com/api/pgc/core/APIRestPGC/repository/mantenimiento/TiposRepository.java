package com.api.pgc.core.APIRestPGC.repository.mantenimiento;

import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiposRepository  extends JpaRepository<TblTipo, Integer> {

    /**
     * Metodo que despliega el Tipo de la BD
     * @autor Nahum Martinez | NAM
     * @version  12/04/2018/v1.0
     * @return Tipo de la BD, por paramtro de ID
     * @param idTipo
     */
    TblTipo findByIdTipo(long idTipo);

}
