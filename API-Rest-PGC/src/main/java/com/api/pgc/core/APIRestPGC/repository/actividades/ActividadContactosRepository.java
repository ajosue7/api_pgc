/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividadContactos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ActividadContactosRepository extends JpaRepository<TblActividadContactos, Integer> {

    /**
     * Metodo que despliega Los contactos de la BD
     * @autor Jorge Escamilla | JOE
     * @version  25/06/2019/v1.0
     * @return contactos de la BD, por paramtro de ID
     * @param idContacto
     */
    TblActividadContactos findByIdContacto(long idContacto);


    /**
     * Metodo que despliega los contacto de la BD
     *
     * @param codigoContacto
     * @return contacto Proyecto de la BD, por parametro de Codigo
     * @autor Jorge Escamilla | JOE
     * @version 25/06/2019/v1.0
     */
    TblActividadContactos findByCodigoContacto(String codigoContacto);

    /**
     * Metodo que despliega la contactos de la BD
     *
     * @param idContacto
     * * @return Contactos de la BD, por paramtro de ID
     * @autor Jorge Escmailla | JOE
     * @version 25/06/2019/v1.0
     */
    long countByIdContacto(long idContacto);

    /**
     * Metodo que despliega las Recursos de la BD
     *
     * @param codigoContacto
     * @return contactos de la BD, por paramtro de Codigo
     * @autor Jorge Escamilla | JOE
     * @version 25/06/2019/v1.0
     */
    long countByCodigoContacto(String codigoContacto);


    /**
     * Metodo que Borra el Sector de la BD con el Id como parametro
     * en la Tabla contactos
     *
     * @param codigoContacto
     * @return Contactos de la BD, por paramtro de ID
     * @autor Jorge Escamilla | JOE
     * @version 25/06/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividadContactos e WHERE e.codigoContacto = :codigoContacto")
    void deleletedCodigoContacto(@Param("codigoContacto") String codigoContacto);


}
