package com.api.pgc.core.APIRestPGC.repository.espacios_de_trabajo;

import com.api.pgc.core.APIRestPGC.models.espacios_de_trabajo.TblEspaciosTrabajoUsuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspaciosTrabajoUsuarioRepository extends JpaRepository<TblEspaciosTrabajoUsuarios, Integer> {

    /**
     * Metodo que despliega el Espacio de Trabajo de la BD
     *
     * @param idEspacioTrabajoUsuario
     * @return Espacios de Trabajo de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 11/10/2018/v1.0
     */
    TblEspaciosTrabajoUsuarios findByIdUsuarioEspacioTrabajo(long idEspacioTrabajoUsuario);

}
