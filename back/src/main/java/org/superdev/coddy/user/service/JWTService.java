package org.superdev.coddy.user.service;


import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.superdev.coddy.user.data.IUser;
import org.superdev.coddy.user.data.JWTPrincipal;
import org.superdev.coddy.user.data.Token;
import org.superdev.coddy.user.elasticsearch.entity.UserEntity;
import org.superdev.coddy.user.utils.SecurityUtils;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JWTService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTService.class);

    private static final int DEFAULT_JWT_SESSION_TIMEOUT_MINUTE = 60 * 24;

    private static final byte[] DEFAULT_JWT_SECRET = SecurityUtils.generateSecret();

    private static final String JWT_TOKEN_ERROR = "Invalid JWT token";

    /**
     * This method will generate the token with this payload :
     * <p>
     * {@code
     * {
     * "login": "jdoe",
     * "firstname": "John",
     * "nbf": 1479975670,
     * "permissions": ["write","read"],
     * "ip" : "127.0.0.1"
     * "exp": 1479976870,
     * "lastname": "Doe"
     * }
     * }
     * </p>
     * <p>
     * All fields in the payload are personal data from the {@link IUser user}
     *
     * @param user : the {@link IUser user} who need a new token
     * @return a new {@link Token token}
     */
    public Token generateToken(IUser user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(SecurityUtils.PayloadFields.LOGIN.getName(), user.getLogin());
        claims.put(SecurityUtils.PayloadFields.FIRST_NAME.getName(), user.getFirstName());
        claims.put(SecurityUtils.PayloadFields.LAST_NAME.getName(), user.getLastName());
        claims.put(SecurityUtils.PayloadFields.PERMISSIONS.getName(), user.getPermissions());

        Date notBefore = user instanceof UserEntity ? new Date() :
                (((JWTPrincipal) user).getBeginActivationSession() != null ? ((JWTPrincipal) user).getBeginActivationSession() : new Date());

        return this.generateToken(claims, notBefore);
    }

    /**
     * if the token is valid, return a new instance of {@link JWTPrincipal} from JSON Web Token
     * which is needed by {@link org.superdev.coddy.user.filter.JWTSecurityContext}.
     *
     * @param token : the token to validate
     * @return a new instance of {@link JWTPrincipal} from JSON Web Token
     * @throws ForbiddenException     : if the token is not valid
     * @throws NotAuthorizedException : if the token session is out of date
     */
    public JWTPrincipal validateToken(String token) {

        try {
            Jws<Claims> parseClaimsJws = Jwts.parser()
                    .setSigningKey(JWTService.DEFAULT_JWT_SECRET)
                    .parseClaimsJws(token);
            this.validateToken(parseClaimsJws);

            final String login = (String) parseClaimsJws.getBody().get(SecurityUtils.PayloadFields.LOGIN.getName());
            final String firstname = (String) parseClaimsJws.getBody().get(SecurityUtils.PayloadFields.FIRST_NAME.getName());
            final String lastname = (String) parseClaimsJws.getBody().get(SecurityUtils.PayloadFields.LAST_NAME.getName());
            List<String> permissions = (List<String>) parseClaimsJws.getBody().get(SecurityUtils.PayloadFields.PERMISSIONS.getName());
            Date notBefore = parseClaimsJws.getBody().getNotBefore();

            return new JWTPrincipal(login, firstname, lastname, permissions, notBefore);

        } catch (SignatureException | MalformedJwtException | MissingClaimException ex) {
            LOGGER.debug(ex.getMessage(), ex);
            LOGGER.info("the current token {} is not valid", token);

            throw new ForbiddenException(JWTService.JWT_TOKEN_ERROR);
        } catch (ExpiredJwtException ex) {
            LOGGER.debug(ex.getMessage(), ex);
            LOGGER.debug("token session expired");

            throw new NotAuthorizedException(
                    "Unauthorized: token expired");
        }

    }

    private void validateToken(Jws<Claims> claims) {

        final String[] keys = new String[]{
                SecurityUtils.PayloadFields.LOGIN.getName(),
                SecurityUtils.PayloadFields.FIRST_NAME.getName(),
                SecurityUtils.PayloadFields.LAST_NAME.getName(),
                SecurityUtils.PayloadFields.EXPIRATION.getName(),
                SecurityUtils.PayloadFields.NOT_BEFORE.getName(),
                SecurityUtils.PayloadFields.PERMISSIONS.getName(),
        };

        this.validateKeys(claims, keys);
    }

    private void validateKeys(final Jws<Claims> claims, final String[] keys) {
        for (String key : keys) {
            validateKey(claims, key);
        }
    }

    private void validateKey(final Jws<Claims> claims, final String key) {
        if (!(claims.getBody().containsKey(key))) {
            LOGGER.debug("In the body of the current token, the key {} doesn't match the type list", key);

            throw new ForbiddenException(JWTService.JWT_TOKEN_ERROR);
        }
    }

    private Token generateToken(Map<String, Object> claims, Date notBefore) {


        LocalDateTime expiration = LocalDateTime.now().plusMinutes(JWTService.DEFAULT_JWT_SESSION_TIMEOUT_MINUTE);

        return new Token(Jwts.builder()
                .setClaims(claims)
                .setNotBefore(notBefore)
                .setExpiration(Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant()))
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SignatureAlgorithm.HS512, JWTService.DEFAULT_JWT_SECRET)
                .compact());
    }

}
