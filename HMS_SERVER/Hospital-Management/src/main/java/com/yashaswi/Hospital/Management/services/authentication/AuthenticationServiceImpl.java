package com.yashaswi.Hospital.Management.services.authentication;

import com.yashaswi.Hospital.Management.dao.SessionsDao;
import com.yashaswi.Hospital.Management.models.Exceptions.AuthenticationException;
import com.yashaswi.Hospital.Management.models.Exceptions.UsersConflictExcept;
import com.yashaswi.Hospital.Management.models.entities.Sessions;
import com.yashaswi.Hospital.Management.models.entities.User;
import com.yashaswi.Hospital.Management.models.enums.UserAccountStatus;
import com.yashaswi.Hospital.Management.services.session.SessionService;
import com.yashaswi.Hospital.Management.services.session.SessionServiceImpl;
import com.yashaswi.Hospital.Management.services.user.UserService;
import com.yashaswi.Hospital.Management.services.user.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    UserService userService;
    SessionsDao sessionsDao;
    SessionService sessionService;

    //  trying to implement jwt
    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private int jwtExpiration;

//

    @Autowired
    public AuthenticationServiceImpl(SessionsDao sessionsDao, UserServiceImpl userService, SessionServiceImpl sessionService) {
        this.userService = userService;
        this.sessionsDao = sessionsDao;
        this.sessionService = sessionService;
    }

    @Override
    public Map<String, String> authenticateUser(String auth) {

        String[] usernameAndPassword = this.extractUsernameAndPassword(auth);
        String username = usernameAndPassword[0];
        String password = usernameAndPassword[1];
//        Claims claims = Jwts.parser()
//                .setSigningKey(jwtSecret.getBytes())
//                .parseClaimsJws(auth)
//                .getBody();
//        String userId = claims.get("user_id", Long.class).toString();
//        String userType = claims.get("type", String.class);
        try {
            Map<String, String> res = new HashMap<>();

            String token = this.authenticateUsingBasicAuth(username, password);
            res.put("token", token);
            return res;
        } catch (Exception e) {
            throw new AuthenticationException("Auth failed");

        }
    }

    @Override
    public String[] extractUsernameAndPassword(String authorizationHeader) {
        // Authorization header will have value "Basic <base64-encoded-username-and-password>"
        // Extract the base64-encoded username and password and decode it
        String base64Credentials = authorizationHeader.substring("Basic ".length()).trim();
        byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(decodedBytes);
        return credentials.split(":", 2);
    }

    @Override
    public String authenticateUsingBasicAuth(String username, String password)  {
        Optional<User> user = Optional.ofNullable(userService.getUserByUserName(username));
        if(user.isEmpty()){
            throw new UsersConflictExcept("user not found for this matching username");
        }
        if (user.get().getUserAccountStatus() == UserAccountStatus.BLOCKED) {
                throw new UsersConflictExcept("user is blocked by admin");
        }
        if (user.get().getUserAccountStatus() == UserAccountStatus.DEACTIVATED) {
                throw new UsersConflictExcept("account is deactivated");
        }
        if (user.get().isPasswordValid(password)) {
                return createSession(user);
        }
        else {
            throw new UsersConflictExcept("credential doesnt match");
        }
    }

    @Override
    public Long getUserIdFromToken(String token) {
        Sessions session = sessionService.getSessionByToken(token);
        if(session.isValid()) {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("user_id", Long.class);
        }
        else throw new AuthenticationException("session is not valid");
    }

    private String createSession(Optional<User> user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtExpiration);
//        expiration.toString() + user.getUserName();
        String token =  Jwts.builder()
                .setSubject(user.get().getUserName())
                .claim("user_id", user.get().getId())
                .claim("type",user.get().getType())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes())
                .compact();

        Sessions sessions = Sessions.builder()
                .token(token)
                .type(user.get().getType())
                .validTill(new Timestamp(expiration.getTime()))
                .userId(user.get().getId())
                .build();
        sessionsDao.save(sessions);
        return token;
    }

}
