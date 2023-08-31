package com.yashaswi.Hospital.Management.services.authentication;

import com.yashaswi.Hospital.Management.models.Exceptions.AuthenticationException;
import com.yashaswi.Hospital.Management.models.entities.Sessions;
import com.yashaswi.Hospital.Management.services.session.SessionService;
import com.yashaswi.Hospital.Management.services.session.SessionServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthenticationAspect  {
    private final HttpServletRequest request;
    private final SessionService sessionService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationAspect(HttpServletRequest request, SessionServiceImpl sessionService, AuthenticationServiceImpl authenticationService) {
        this.request = request;
        this.sessionService = sessionService;
        this.authenticationService = authenticationService;
    }
//point cut:-before joint cut
    @Before("@annotation(Authenticated)")
    public void authenticate() {
        String token = request.getHeader("xHmAuthToken");
        String auth = request.getHeader("Authorization");


        if (token == null || token.isEmpty()) {
            if(auth == null || auth.isEmpty())
                throw new AuthenticationException("Missing authentication token");
            else{
                authenticationService.authenticateUser(auth);
            }
        }
        else {
            Sessions session = sessionService.getSessionByToken(token);
            if (session == null || !session.isValid()) {
                throw new AuthenticationException("Invalid or expired token");
            }
        }
    }
}

