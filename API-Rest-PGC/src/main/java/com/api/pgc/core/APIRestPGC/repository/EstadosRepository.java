package com.api.pgc.core.APIRestPGC.repository;

import com.api.pgc.core.APIRestPGC.models.TblEstado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadosRepository extends JpaRepository<TblEstado, Integer> {
}
