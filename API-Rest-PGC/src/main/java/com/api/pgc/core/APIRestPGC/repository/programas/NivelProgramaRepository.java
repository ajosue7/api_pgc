/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.programas;

import com.api.pgc.core.APIRestPGC.models.programas.TblNivelPrograma;
import com.api.pgc.core.APIRestPGC.models.sectores.TblNivelSector;
import com.api.pgc.core.APIRestPGC.models.sectores.TblTipoSector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NivelProgramaRepository extends JpaRepository<TblNivelPrograma, Integer> {

    /**
     * Metodo que despliega el Nivel de Programa de la BD
     *
     * @param idNivelPrograma
     * @return Nivel de Programa de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    TblNivelPrograma findByIdNivelPrograma(long idNivelPrograma);


    /**
     * Metodo que despliega el Nivel de Programa de la BD
     *
     * @param codigoNivelPrograma
     * @return Nivel de Programa de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    TblNivelPrograma findByCodigoNivelPrograma(String codigoNivelPrograma);


    /**
     * Metodo que despliega el Nivel de Programa de la BD
     *
     * @param codigoNivelPrograma
     * @return Nivel de Sector de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    long countByCodigoNivelPrograma(String codigoNivelPrograma);


    /**
     * Metodo que despliega el Nivel de Programa de la BD
     *
     * @param idNivelPrograma
     * @return Nivel de Programa de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    long countByIdNivelPrograma(long idNivelPrograma);

}
