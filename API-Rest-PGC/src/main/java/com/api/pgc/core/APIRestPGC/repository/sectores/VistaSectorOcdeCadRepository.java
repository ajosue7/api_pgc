/*
 * Copyright (c) 2019.  Nahum Martinez
 */

/**
 * @author nahum.martinez
 * @date 22/03/2019
 * @name SectorOcdeCadRepository
 */

package com.api.pgc.core.APIRestPGC.repository.sectores;

import com.api.pgc.core.APIRestPGC.models.sectores.VSectorOcdeCad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VistaSectorOcdeCadRepository extends JpaRepository<VSectorOcdeCad, Integer> {
    // Querys de la Vista

    /**
     * Metodo que despliega el Sector OCDE/CAD de la BD con el Nivel de Sector como parametro
     *
     * @return SECTOR OCDE/CAD de la BD, por parametro de ID Nivel de Sector
     * @autor Nahum Martinez | NAM
     * @version 19/04/2019/v1.0
     */
    @Query("SELECT e.idSector, e.nombreSector FROM VSectorOcdeCad e")
    List<VSectorOcdeCad> getVistaSectorOcde();
}
