package com.api.pgc.core.APIRestPGC.config;

import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import org.json.JSONObject;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.security.core.Authentication;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Las peticiones que no sean /login pasarán por este filtro
 * el cuál se encarga de pasar el "request" a nuestra clase de utilidad JwtUtil
 * para que valide el token.
 */
public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse response1 = (HttpServletResponse) response;
        Authentication authentication = JwtUtil.getAuthentication((HttpServletRequest)request);

        System.out.println("Dato de la Funcion doFilter 1 ***************************  " + authentication );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);


        return;
    }

    public HashMap<String, Object> msgError(){
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        return msgExeptions.msgJson( "Error Controaldo de NAM ***** ", 200 );
    }

}
