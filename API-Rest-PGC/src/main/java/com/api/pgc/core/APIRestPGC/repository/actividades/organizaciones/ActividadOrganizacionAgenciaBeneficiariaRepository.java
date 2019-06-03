/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.actividades.organizaciones;

import com.api.pgc.core.APIRestPGC.models.actividades.TblActividad;
import com.api.pgc.core.APIRestPGC.models.actividades.organizaciones.TblActividadOrganizacionAgenciaBeneficiaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActividadOrganizacionAgenciaBeneficiariaRepository extends JpaRepository<TblActividadOrganizacionAgenciaBeneficiaria, Integer> {
    /**
     * Metodo que despliega los Agencia Beneficiaria de la BD
     *
     * @param codigoActividad
     * @return Agencia Beneficiaria Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 02/05/2019/v1.0
     */
    TblActividadOrganizacionAgenciaBeneficiaria findByCodigoActividad(String codigoActividad);


    /**
     * Metodo que despliega los Agencia Beneficiaria de la BD
     *
     * @param idActividadAgenciaBeneficiaria
     * @return Agencia Beneficiaria Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 02/05/2019/v1.0
     */
    TblActividadOrganizacionAgenciaBeneficiaria findByIdActividadAgenciaBeneficiaria(long idActividadAgenciaBeneficiaria);

    /**
     * Metodo que despliega los Agencia Beneficiaria de la BD
     *
     * @param idActividadAgenciaBeneficiaria
     * @return Agencia Beneficiaria Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 02/05/2019/v1.0
     */
    long  countByIdActividadAgenciaBeneficiaria(long idActividadAgenciaBeneficiaria);


    /**
     * Metodo que despliega las Agencia Beneficiaria de la BD
     *
     * @param codigoActividad
     * @return Agencia Beneficiaria de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 02/05/2019/v1.0
     */
    long countByCodigoActividad(String codigoActividad);


    /**
     * Metodo que despliega los Agencia Beneficiaria de la BD
     *
     * @param tblActividad
     * @return Agencia Beneficiaria del Proyecto de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 02/05/2019/v1.0
     */
    @Query("SELECT e FROM TblActividadOrganizacionAgenciaBeneficiaria e WHERE e.idActividad = :idActividad")
    List<TblActividadOrganizacionAgenciaBeneficiaria> getCodigoActividadOrganizacion(@Param("idActividad") TblActividad tblActividad);

    /**
     * Metodo que despliega las Agencia Beneficiaria de la BD
     *
     * @param tblActividad
     * @return Agencia Beneficiaria de la BD, por paramtro de Id Programa Plan de Nacion
     * @autor Nahum Martinez | NAM
     * @version 02/05/2019/v1.0
     */
    long countByIdActividad(TblActividad tblActividad);


    /**
     * Metodo que Borra el Sector de la BD con el Id como parametro
     * en la Tabla Agencia Beneficiaria Actividades
     *
     * @param codigoActividad
     * @return Agencia Beneficiaria de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 02/05/2019/v1.0
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TblActividadOrganizacionAgenciaBeneficiaria e WHERE e.codigoActividad = :codigoActividad")
    void deleletedCodigoActividad(@Param("codigoActividad") String codigoActividad);
}
