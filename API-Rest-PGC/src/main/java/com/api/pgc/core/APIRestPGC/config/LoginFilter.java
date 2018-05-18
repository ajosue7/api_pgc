package com.api.pgc.core.APIRestPGC.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.concurrent.ExecutionException;


public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    //Encoder el Password
    @Autowired
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();

    private RememberMeServices rememberMeServices = new NullRememberMeServices();

    public LoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        System.out.println("Url de la Clase LoginFilter  ****************************  " + url);
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException {
        //jecutamos el llamado al Login
        InputStream body = req.getInputStream();

        Usuario user = new ObjectMapper().readValue(body, Usuario.class);

        System.out.println("Paso 1 - attemptAuthentication ******** " + user.getPasswordUsuario());

        try {
            //System.out.println( encoder.encode( user.getPasswordUsuario() ) );

            //Retornamos los Parametros enviados por el JsonBean
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getCodUsuario(),
                            //encoder.encode( user.getPasswordUsuario() ), //Encoder PassWord
                            user.getPasswordUsuario(), //Encoder PassWord
                            Collections.emptyList()
                    )
            );
        } catch (BadCredentialsException  ex) {
            System.out.println("Paso 2 - attemptAuthentication ******** ");
            //throw new BadCredentialsException("Username / Password was not found");
            //return getFailureHandler().onAuthenticationFailure(req, res, ex.getMessage().);
            return null;
            //return getFailureHandler().onAuthenticationFailure( req, res, failed );
        } /*catch (IOException e) {
            System.out.println("Paso 3 - attemptAuthentication ******** ");
            //throw new IOException("Error sdsdsd");

            //return null;
        }*/

    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
            System.out.println("**************************** successfulAuthentication ***************************** ");
        // Si la autenticacion fue exitosa, agregamos el token a la respuesta
        System.out.println("Dato d rs: ********* " + auth.getName());
        JwtUtil.addAuthentication(res, auth.getName());
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        SecurityContextHolder.clearContext();

        if (logger.isDebugEnabled()) {
            logger.debug("Authentication request failed: " + failed.toString(), failed);
            logger.debug("Updated SecurityContextHolder to contain null Authentication");
            logger.debug("Delegating to authentication failure handler " + failureHandler);
        }

        rememberMeServices.loginFail(request, response);

        failureHandler.onAuthenticationFailure(request, response, failed);
    }

}
