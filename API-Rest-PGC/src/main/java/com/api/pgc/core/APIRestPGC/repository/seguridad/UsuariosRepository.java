package com.api.pgc.core.APIRestPGC.repository.seguridad;

import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuariosRepository extends JpaRepository<TblUsuarios, Integer> {
    /**
     * Metodo que despliega el Usuario de la BD
     *
     * @param idUsuario
     * @return usuario de la BD, por paramtro de ID
     * prueba 3 para sincronizacion
     * @autor Nahum Martinez | NAM
     * @version 18/04/2018/v1.0
     */
    TblUsuarios findByIdUsuario(long idUsuario);

    TblUsuarios findByCodUsuario(String codUsuario);

    TblUsuarios findByEmailUsuario(String emailUsuario);

    /**
     * Metodo que despliega el rol de usuario de la BD con el email como parametro
     *
     * @param emailUsuario
     * @return Organizacion de la BD, por paramtro de Email
     * @autor Nahum Martinez | NAM
     * @version 25/06/2019/v1.0
     */
    @Query("SELECT u.rol FROM TblUsuarios u WHERE u.emailUsuario = :emailUsuario")
    List<TblUsuarios> getUserRolByEmail(@Param("emailUsuario") String emailUsuario);
    // TblUsuarios findByRol( String emailUsuario );

}
