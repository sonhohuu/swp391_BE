package com.FPTU.security.jwt;

import com.FPTU.model.User;
import com.FPTU.model.UserRole;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@RequiredArgsConstructor
public class JwtTokenManager {

    private final JwtProperties jwtProperties;

    /**
     * generate Token.
     *
     * @param user User
     * @return String
     */
    public String generateToken(User user) {

        final String username = user.getUsername();
        final UserRole userRole = user.getUserRole();
        final String address = user.getAddress();
        final String img = user.getImg();
        final String email = user.getEmail();

        return JWT.create()
                .withSubject(username)
                .withIssuer(jwtProperties.getIssuer())
                .withClaim("role", userRole.name())
                .withClaim("img", img)
                .withClaim("address", address)
                .withClaim("email", email)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()
                        + jwtProperties.getExpirationMinute() * 60 * 100000))
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey().getBytes()));
    }

    /**
     * get username from token.
     *
     * @param token String
     * @return String
     */
    public String getUsernameFromToken(String token) {

        final DecodedJWT decodedJwt = getDecodedJwt(token);
        return decodedJwt.getSubject();
    }

    /**
     * validate token.
     *
     * @param token                 String
     * @param authenticatedUsername String
     * @return boolean
     */
    public boolean validateToken(String token, String authenticatedUsername) {

        final String usernameFromToken = getUsernameFromToken(token);

        final boolean equalsUsername = usernameFromToken.equals(authenticatedUsername);
        final boolean tokenExpired = isTokenExpired(token);

        return equalsUsername && !tokenExpired;
    }

    private boolean isTokenExpired(String token) {

        final Date expirationDateFromToken = getExpirationDateFromToken(token);
        return expirationDateFromToken.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {

        final DecodedJWT decodedJwt = getDecodedJwt(token);

        return decodedJwt.getExpiresAt();
    }

    private DecodedJWT getDecodedJwt(String token) {

        final JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(
                jwtProperties.getSecretKey().getBytes())).build();

        return jwtVerifier.verify(token);
    }

}
