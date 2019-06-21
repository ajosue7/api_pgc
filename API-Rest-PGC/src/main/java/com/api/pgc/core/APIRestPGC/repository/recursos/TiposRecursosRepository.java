/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.recursos;

import com.api.pgc.core.APIRestPGC.models.recursos.TblTipoRecurso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiposRecursosRepository extends JpaRepository<TblTipoRecurso, Integer> {

    /**
     * Metodo que despliega el Tipo de Recurso de la BD
     *
     * @param idTipoRecursos
     * @return Tipo de Recurso de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 11/04/2019/v1.0
     */
    TblTipoRecurso findByIdTipoRecursos(long idTipoRecursos);

}
