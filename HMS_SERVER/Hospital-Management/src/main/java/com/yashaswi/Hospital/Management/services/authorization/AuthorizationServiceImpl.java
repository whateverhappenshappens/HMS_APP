package com.yashaswi.Hospital.Management.services.authorization;

import com.yashaswi.Hospital.Management.dao.SessionsDao;
import com.yashaswi.Hospital.Management.models.Exceptions.AuthenticationException;
import com.yashaswi.Hospital.Management.models.entities.Sessions;
import com.yashaswi.Hospital.Management.models.entities.User;
import com.yashaswi.Hospital.Management.services.authentication.AuthenticationServiceImpl;
import com.yashaswi.Hospital.Management.services.session.SessionService;
import com.yashaswi.Hospital.Management.services.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    UserService userService;
    SessionService sessionService;

    public AuthorizationServiceImpl(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @Value("${app.jwt.secret}")
    private String jwtSecret;


    @Override
    public boolean authorizeUser(String token, Long pathVariable_userId) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret.getBytes())
                .parseClaimsJws(token)
                .getBody();

        Long userId = claims.get("user_id", Long.class);
        String userType = claims.get("type", String.class);
        if(userType.equals("ADMIN") || Objects.equals(userId, pathVariable_userId)){
            return true;
        }
        else {
            throw new AuthenticationException("user id from token and path not same");

        }
    }

    @Override
    public boolean authorizeRole(String token, List<String> roles) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret.getBytes())
                .parseClaimsJws(token)
                .getBody();
        String userType = claims.get("type", String.class);
        if(roles.contains(userType)){
            return true;
        }
        else throw new AuthenticationException("not authorised");
    }
}
