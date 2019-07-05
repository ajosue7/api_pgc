package com.api.pgc.core.APIRestPGC.repository.actividades;


import com.api.pgc.core.APIRestPGC.models.actividades.TblContactoProyecto;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactoProyectoRepository extends JpaRepository<TblContactoProyecto, Integer> {

    /**
     * Metodo que despliega el contato-proyecto de la BD
     * @autor Jorge Escamilla | JOE
     * @version  27/06/2019/v1.0
     * @return contacto-proyectode la BD, por paramtro de ID
     * @param idContactoProyecto
     */
    TblContactoProyecto findByIdContactoProyecto(long idContactoProyecto);

    /**
     * Metodo que despliega los contacto-proyecto de Perfil de la BD
     * @autor Jorge Escamilla | JOE
     * @version  27/06/2019/v1.0
     * @return Listado de los contacto-proyecto de Perfil de la BD, por parametro de ID Grupo
     * @param tblGrupo
     */
      List<TblContactoProyecto> findByIdGrupo(TblGrupo tblGrupo);

    /**
     * Metodo que despliega los contacto-proyecto de la BD con el Grupo como parametro
     *
     * @param tblGrupo
     * @return Contacto-proyecto de la BD, por paramtro de ID Grupo
     * @autor Jorge Escamilla | JOE
     * @version 27/06/2019/v1.0
     */
   @Query("SELECT COUNT(e) FROM TblContactoProyecto e WHERE e.idGrupo = :idGrupo ")
   List<TblContactoProyecto> getCountContactoProyectoPerfiles(@Param("idGrupo") TblGrupo tblGrupo);

}
