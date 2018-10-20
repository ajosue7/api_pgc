package com.api.pgc.core.APIRestPGC.repository.organizaciones;

import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblEstado;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblGrupo;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblOrganizacion;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblTipoOrganizacion;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblPais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizacionRepository extends JpaRepository<TblOrganizacion, Integer> {

    /**
     * Metodo que despliega la Organizacion de la BD
     *
     * @param idOrganizacion
     * @return Organizacion de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 15/10/2018/v1.0
     */
    TblOrganizacion findByIdOrganizacion(long idOrganizacion);


    /**
     * Metodo que despliega la Organizacion de la BD con el Tipo como parametro
     *
     * @param tblTipoOrganizacion
     * @return Organizacion de la BD, por paramtro de ID Tipo
     * @autor Nahum Martinez | NAM
     * @version 15/10/2018/v1.0
     */
     @Query("SELECT e FROM TblOrganizacion e WHERE e.idTipoOrganizacionT = :idTipoOrganizacion ")
    List<TblOrganizacion> getTipoOrganizacion(@Param("idTipoOrganizacion") TblTipoOrganizacion tblTipoOrganizacion);


    /**
     * Metodo que despliega la Organizacion de la BD con el Tipo y Pais como parametro
     *
     * @param tblTipoOrganizacion
     * @param tblPaisOrganizacion
     * @return Organizacion de la BD, por paramtro de ID Tipo y ID Pais
     * @autor Nahum Martinez | NAM
     * @version 15/10/2018/v1.0
     */
    @Query("SELECT e FROM TblOrganizacion e WHERE e.idTipoOrganizacionT = :idTipoOrganizacion AND e.idPaisOrganizacion = :idPaisOrganizacion ")
    List<TblOrganizacion> getTipoPaisOrganizacion(@Param("idTipoOrganizacion") TblTipoOrganizacion tblTipoOrganizacion, @Param("idPaisOrganizacion") TblPais tblPaisOrganizacion);

}
