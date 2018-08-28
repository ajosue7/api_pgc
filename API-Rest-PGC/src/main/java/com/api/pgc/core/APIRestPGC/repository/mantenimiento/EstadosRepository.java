package com.api.pgc.core.APIRestPGC.repository.mantenimiento;

import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstadosRepository extends JpaRepository<TblEstado, Integer> {

    /**
     * Metodo que despliega el Esatdo de la BD
     * @autor Nahum Martinez | NAM
     * @version  11/04/2018/v1.0
     * @return Esatdo de la BD, por paramtro de ID
     * @param idEstado
     */
    TblEstado findByIdEstado(long idEstado);

    //@Query("select e.idEstado from TblEstado e")
    //List<TblEstado> findByIdEstado (long idEstado);

}
