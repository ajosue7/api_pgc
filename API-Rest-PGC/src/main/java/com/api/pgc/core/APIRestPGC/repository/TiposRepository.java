package com.api.pgc.core.APIRestPGC.repository;

import com.api.pgc.core.APIRestPGC.models.TblTipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiposRepository  extends JpaRepository<TblTipo, Integer> {
}
