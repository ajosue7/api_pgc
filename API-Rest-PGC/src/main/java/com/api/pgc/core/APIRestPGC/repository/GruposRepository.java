package com.api.pgc.core.APIRestPGC.repository;


import com.api.pgc.core.APIRestPGC.models.TblGrupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GruposRepository extends JpaRepository<TblGrupo, Integer> {
}
