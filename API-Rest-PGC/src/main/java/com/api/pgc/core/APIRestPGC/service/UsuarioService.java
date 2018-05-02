package com.api.pgc.core.APIRestPGC.service;

import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import com.api.pgc.core.APIRestPGC.repository.seguridad.UsuariosRepository;
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
public class UsuarioService extends TblUsuarios implements UserDetailsService {

    @Autowired
    @Qualifier("usuariosRepository")
    private UsuariosRepository usuariosRepository;

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        TblUsuarios tblUsuarios = usuariosRepository.findByCodUsuario( username );

        return new User ( tblUsuarios.getCodUsuario(), tblUsuarios.getPasswordUsuario(),
                tblUsuarios.isActivo(), tblUsuarios.isActivo(),tblUsuarios.isActivo(),tblUsuarios.isActivo(),
                buildgrante( tblUsuarios.getRol()) );
    }

    public List<GrantedAuthority> buildgrante(byte rol){
        String[] roles = {"LECTOR","USUARIO","ADMINISTRADOR"};
        List<GrantedAuthority> auths = new ArrayList<>();

        for( int i = 0; i < rol; i++ ){
            auths.add( new SimpleGrantedAuthority( roles[i] ));
        }
        return auths;
    }



}
