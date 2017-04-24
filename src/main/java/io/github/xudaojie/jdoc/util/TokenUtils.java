package io.github.xudaojie.jdoc.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;

/**
 * Created by xdj on 2017/4/22.
 */
public class TokenUtils {
    public static final String SECRET = "jdoc";

    public static DecodedJWT verify(String token) throws JWTVerificationException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (UnsupportedEncodingException exception) {
            //UTF-8 encoding not supported
        }
//        catch (JWTVerificationException exception){
//            //Invalid signature/claims
//        }
        return null;
    }

    public static DecodedJWT decode(String token) throws JWTDecodeException {
        DecodedJWT jwt = JWT.decode(token);
//        try {
//            DecodedJWT jwt = JWT.decode(token);
//        } catch (JWTDecodeException e){
//            // Invalid token
//        }

        return jwt;
    }

    public static DecodedJWT verify(String token, String withIssuer) throws JWTVerificationException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(withIssuer)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (UnsupportedEncodingException exception) {
            //UTF-8 encoding not supported
        }
        return null;
    }

    public static String create() throws JWTCreationException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .sign(algorithm);
        } catch (UnsupportedEncodingException exception) {
            //UTF-8 encoding not supported
        }
        return null;
    }

    public static String create(String withIssuer) throws JWTCreationException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withIssuer(withIssuer)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException exception) {
            //UTF-8 encoding not supported
        }
        return null;
    }
}