package com.api.pgc.core.APIRestPGC.repository.mantenimiento;

import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblEstado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadosRepository extends JpaRepository<TblEstado, Integer> {

    /**
     * Metodo que despliega el Esatdo de la BD
     * @autor Nahum Martinez | NAM
     * @version  11/04/2018/v1.0
     * @return Esatdo de la BD, por paramtro de ID
     * @param idEstado
     */
    TblEstado findByIdEstado(long idEstado);

}
