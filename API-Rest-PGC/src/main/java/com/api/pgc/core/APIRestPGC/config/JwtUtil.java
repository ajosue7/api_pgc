package com.api.pgc.core.APIRestPGC.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

import static java.util.Collections.emptyList;

public class JwtUtil {

    // Método para crear el JWT y enviarlo al cliente en el header de la respuesta
    static void addAuthentication(HttpServletResponse res, String username) {

        System.out.println("Datos en: JwtUtil de la Funcion addAuthentication() - username **********  " +  username);
        /*String token = Jwts.builder()
                .setSubject(username)
                // Hash con el que firmaremos la clave
                .signWith(SignatureAlgorithm.HS512, "P@tit0")
                .compact();
        System.out.println("Datos en: JwtUtil de la Funcion addAuthentication() - token **********  " +  token);
        //agregamos al encabezado el token
        res.addHeader("Authorization", "Bearer " + token);*/

        String id = UUID.randomUUID().toString().replace("-", "");

        Date now = new Date();
        Date exp = new Date(System.currentTimeMillis() + (1000*300)); // 300 seconds

        System.out.println("Datos en: JwtUtil de la Funcion addAuthentication() - id **********  " +  id);

        String token;
        //try {
            token = Jwts.builder()
                    .setId(id)
                    .setSubject(username)
                    .setIssuedAt(now)
                    .setNotBefore(now)
                    .setExpiration(exp)
                    .signWith(SignatureAlgorithm.HS256, "P@tit0")
                    .compact();

            System.out.println("Datos en: JwtUtil de la Funcion addAuthentication() - token **********  " +  token);

            //agregamos al encabezado el token
            res.addHeader("Authorization", "Bearer " + token);
        /*} catch (Exception e) {
            System.out.println( e.getMessage() );
            token = id;
        }//FIN Cath*/

    }//FIN | addAuthentication



    // Método para validar el token enviado por el cliente
    static Authentication getAuthentication(HttpServletRequest request) {

        // Obtenemos el token que viene en el encabezado de la peticion
        String token = request.getHeader("Authorization");

        // si hay un token presente, entonces lo validamos
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey("P@tit0")
                    .parseClaimsJws(token.replace("Bearer", "")) //este metodo es el que valida
                    .getBody()
                    .getSubject();

            // Recordamos que para las demás peticiones que no sean /login
            // no requerimos una autenticacion por username/password
            // por este motivo podemos devolver un UsernamePasswordAuthenticationToken sin password
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                    null;
        }
        return null;
    }

}
