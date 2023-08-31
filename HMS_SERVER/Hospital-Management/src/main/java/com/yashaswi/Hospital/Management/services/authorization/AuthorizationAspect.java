package com.yashaswi.Hospital.Management.services.authorization;

import com.yashaswi.Hospital.Management.models.Exceptions.AuthenticationException;
import com.yashaswi.Hospital.Management.models.Exceptions.AuthorizationException;
import com.yashaswi.Hospital.Management.models.entities.Sessions;
import com.yashaswi.Hospital.Management.services.session.SessionService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Aspect
@Component
public class AuthorizationAspect {
    private final HttpServletRequest httpServletRequest;
    private final SessionService sessionService;
    private final AuthorizationService authorizationService;

    @Autowired
    public AuthorizationAspect(HttpServletRequest httpServletRequest, SessionService sessionService, AuthorizationService authorizationService) {
        this.httpServletRequest = httpServletRequest;
        this.sessionService = sessionService;
        this.authorizationService = authorizationService;
    }

//    JoinPoint joinPoint,
    @Before("@annotation(Authorized)")
    public void authorize(JoinPoint joinPoint) throws AuthorizationException {
        String token = httpServletRequest.getHeader("xHmAuthToken");
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
//        List<String> allowedRoles = Arrays.stream(ms.getMethod().getAnnotation(Authorized.class).allowedRoles()).toList();
//        List<String> allowedRoles = Arrays.stream(authorized.allowedRoles()).toList();
        List<String> allowedRoles = Arrays.stream(ms.getMethod().getAnnotation(Authorized.class).allowedRoles()).toList();


        authorizationService.authorizeRole(token,allowedRoles);


//        todo : improve logic (hint path is ..../user/{user_id})/....)
//        Optional<Long> pathVariable_userId = Optional.of(Long.parseLong(httpServletRequest.getServletPath().split("/")[3]));
//        Long arr = Long.parseLong(ms.getParameterNames()[0]);
        if(token == null || token.isEmpty()) {
           throw new AuthorizationException("Missing token");
        }
        else {
            Sessions sessions=sessionService.getSessionByToken(token);
            if (sessions == null || !sessions.isValid()) {
                throw new AuthorizationException("Invalid or expired token");
            }
//            pathVariable_userId.ifPresent(aLong -> authorizationService.authorizeUser(token, aLong));
//            authorizationService.authorizeUser(token,arr);
        }
    }

}
