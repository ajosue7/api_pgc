package com.api.pgc.core.APIRestPGC.repository.mantenimiento;


import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTratos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TratosRepository extends JpaRepository<TblTratos, Integer> {

    /**
     * Metodo que despliega el tipo de trato de la BD
     * @autor Jorge Escamilla | JOE
     * @version  25/06/2019/v1.0
     * @return Tipo de trato de la BD, por paramtro de ID
     * @param idTrato
     */
    TblTratos findByIdTrato(long idTrato);

    /**
     * Metodo que despliega los Tipo de trato de Perfil de la BD
     * @autor Jorge Escamilla | JOE
     * @version  25/06/2019/v1.0
     * @return Listado de los Tipo de trato de Perfil de la BD, por parametro de ID Grupo
     * @param tblGrupo
     */
   // List<TblTratos> findByIdGrupo(TblGrupo tblGrupo);

    /**
     * Metodo que despliega los Tipos de Perfiles de la BD con el Grupo como parametro
     *
     * @param tblGrupo
     * @return Tipos de Perfiles de la BD, por paramtro de ID Grupo
     * @autor Nahum Martinez | NAM
     * @version 08/01/2019/v1.0
     */
  //  @Query("SELECT COUNT(e) FROM TblTipo e WHERE e.idGrupo = :idGrupo ")
    //List<TblTipo> getCountTiposPerfiles(@Param("idGrupo") TblGrupo tblGrupo);

}
