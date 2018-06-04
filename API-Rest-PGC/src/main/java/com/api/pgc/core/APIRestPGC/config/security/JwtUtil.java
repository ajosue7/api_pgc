package com.api.pgc.core.APIRestPGC.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import static java.util.Collections.emptyList;
// Constantes del Modulo de Seguridad
import static com.api.pgc.core.APIRestPGC.config.security.SecurityConstants.*;

public class JwtUtil {

    // Método para crear el JWT y enviarlo al cliente en el header de la respuesta
    static void addAuthentication(HttpServletResponse res, String username) throws IOException {

        System.out.println("Datos en: JwtUtil de la Funcion addAuthentication() - username **********  " +  username);

        //Parametros de Salida del Response
        res.setContentType("application/x-json;charset=UTF-8");
        //res.setContentType("Access-Control-Allow-Origin: *");
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setCharacterEncoding("UTF-8");

        JSONObject jsonResponse = new JSONObject();

        // Parametros para crear el Token
        // Usuario Ramdon
        String id = UUID.randomUUID().toString().replace("-", "");

        System.out.println("Datos en: JwtUtil de la Funcion addAuthentication() - id **********  " +  id);

        // Generacion del Token
        String token;
            token = Jwts.builder()
                    .setId(id)
                    .setSubject(username)
                    .setIssuedAt(NOW_TIME)
                    .setNotBefore(NOW_TIME)
                    .setExpiration(EXPIRATION_TIME)
                    .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET)
                    .compact();

            System.out.println("Datos en: JwtUtil de la Funcion addAuthentication() - token **********  " +  token);

        if( token != null ){
            //agregamos al encabezado el token
            res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

            //Seteo del Json a ver
            try {
                jsonResponse.put("token", token);
                jsonResponse.put("userName",username);
                jsonResponse.put("message", "Valor del Token de la Sesion, tienes 24 horas para usarlo, " +
                        "despues tu session finalizara");
                jsonResponse.put("status", HttpStatus.OK.value());
                res.getWriter().write(jsonResponse.toString());
            } catch (JSONException e) {
                res.getWriter().write("Error Critico al realizar la peticion del Token");
                e.printStackTrace();
            }
        }
    }//FIN | addAuthentication



    // Método para validar el token enviado por el cliente
    static Authentication getAuthentication(HttpServletRequest request) {

        // Obtenemos el token que viene en el encabezado de la peticion
        String token = request.getHeader("Authorization");

        request.setAttribute("expired", "Mensaje de NAM");

        if (token != null) {
            System.out.println("Funcion getAuthentication Paso 1 ***************  " + token);
            String user = Jwts.parser()
                    .setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token.replace(HEADER_STRING, "")) //este metodo es el que valida
                    .getBody()
                    .getSubject();

            // Recordamos que para las demás peticiones que no sean /login
            // no requerimos una autenticacion por username/password
            // por este motivo podemos devolver un UsernamePasswordAuthenticationToken sin password
            System.out.println("Funcion getAuthentication Paso 2 ***************  " + user);
            if( user != null ){
                return new UsernamePasswordAuthenticationToken(user, null, emptyList());
            }else{
                System.out.println("User null ++++++++++++ ");
            }
            return null;
        }
        System.out.println("Funcion getAuthentication Paso 3 ***************  ");
            //return new UsernamePasswordAuthenticationToken("nahum", null, emptyList());
        return null;
    }// FIN | getAuthentication

}
