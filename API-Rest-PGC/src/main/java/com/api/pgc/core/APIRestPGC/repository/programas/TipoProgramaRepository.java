/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.programas;

import com.api.pgc.core.APIRestPGC.models.programas.TblTipoPrograma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoProgramaRepository extends JpaRepository<TblTipoPrograma, Integer> {

    /**
     * Metodo que despliega el Tipo de Sector de la BD
     *
     * @param idTipoPrograma
     * @return Tipo Programa de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    TblTipoPrograma findByIdTipoPrograma(long idTipoPrograma);


    /**
     * Metodo que despliega el Tipo de Programa de la BD
     *
     * @param codTipoPrograma
     * @return TipoPrograma de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    TblTipoPrograma findByCodTipoPrograma(String codTipoPrograma);


    /**
     * Metodo que despliega el Tipo de Programa de la BD
     *
     * @param codTipoPrograma
     * @return Tipo de Programa de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    long countByCodTipoPrograma(String codTipoPrograma);


    /**
     * Metodo que despliega el Tipo de Programa de la BD
     *
     * @param idTipoPrograma
     * @return TipoPrograma de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 17/04/2019/v1.0
     */
    long countByIdTipoPrograma(long idTipoPrograma);

}
