package org.ui.postgresql.adminui.jwt.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.ui.postgresql.adminui.jwt.TokenObject;

public class JwtService{

    private String secret;

    public JwtService() {
    }

    public JwtService(String secret){
        this.secret = secret;
    }

    public TokenObject decryptToken(String token) {
        JWTVerifier verified = JWT.require(Algorithm.HMAC512(secret)).build();
        DecodedJWT jwt = verified.verify(token);

        String email = jwt.getClaim("email").asString();
        long createDate = jwt.getClaim("createDate").asLong();
        long validDateEnd = jwt.getClaim("validDateEnd").asLong();

        TokenObject tokenObject = new TokenObject();
        tokenObject.setEmail(email);
        tokenObject.setCreateDate(createDate);
        tokenObject.setValidDateEnd(validDateEnd);
        return tokenObject;
    }

    public String createToken(TokenObject data) {
        String signed = JWT.create()
                .withClaim("email",data.getEmail())
                .withClaim("createDate", data.getCreateDate())
                .withClaim("validDateEnd", data.getValidDateEnd())
                .sign(Algorithm.HMAC512(secret));
        return signed;
    }
}
