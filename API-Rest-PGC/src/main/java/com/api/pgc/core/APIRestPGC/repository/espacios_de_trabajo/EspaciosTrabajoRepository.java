package com.api.pgc.core.APIRestPGC.repository.espacios_de_trabajo;

import com.api.pgc.core.APIRestPGC.models.espacios_de_trabajo.TblEspaciosTrabajo;
import com.api.pgc.core.APIRestPGC.models.mantenimiento.TblTipo;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblPais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EspaciosTrabajoRepository extends JpaRepository<TblEspaciosTrabajo, Integer> {

    /**
     * Metodo que despliega el Espacio de Trabajo de la BD
     * @autor Nahum Martinez | NAM
     * @version  11/10/2018/v1.0
     * @return Espacios de Trabajo de la BD, por paramtro de ID
     * @param idEspacioTrabajo
     */
    TblEspaciosTrabajo findByIdEspacioTrabajo(long idEspacioTrabajo);

    /**
     * Metodo que despliega la Organizacion de la BD
     *
     * @param idEspacioTrabajo
     * * @return espaciotrabajo de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 14/02/2019/v1.0
     */
    long countByIdEspacioTrabajo(long idEspacioTrabajo);


    /**
     * Metodo que despliega la Organizacion de la BD
     *
     * @param codEspaciotrabajo
     * @return espaciotrabajo de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 09/01/2019/v1.0
     */
    TblEspaciosTrabajo findByCodEspacioTrabajo(String codEspaciotrabajo);







}
