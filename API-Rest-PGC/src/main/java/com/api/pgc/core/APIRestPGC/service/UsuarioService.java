package com.api.pgc.core.APIRestPGC.service;


import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import com.api.pgc.core.APIRestPGC.repository.seguridad.UsuariosRepository;
import com.api.pgc.core.APIRestPGC.resourses.seguirdad.UsuariosResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("usuarioService")
public class UsuarioService implements UserDetailsService {

    @Autowired
    @Qualifier("usuariosRepository")
    private UsuariosRepository usuariosRepository;

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        TblUsuarios tblUsuarios = usuariosRepository.findByCodUsuario( username );

        System.out.println( "**** En UsuarioService *************************  " + tblUsuarios.getCodUsuario() );
        return new User( tblUsuarios.getCodUsuario(), tblUsuarios.getPasswordUsuario(),
                true, true,true,true,
                buildgrante( 1 )  );
    }

    public List<GrantedAuthority> buildgrante(int rol){
        String[] roles = {"Lector", "Usuario", "Admin"};
        List<GrantedAuthority> auths = new ArrayList<>();

        System.out.println( "**** En UsuarioService *************************  Roles ******************* " + rol );
        for( int i = 0; i < rol; i++ ){
            auths.add( new SimpleGrantedAuthority( roles[i] ));
        }

        return auths;
    }

}
