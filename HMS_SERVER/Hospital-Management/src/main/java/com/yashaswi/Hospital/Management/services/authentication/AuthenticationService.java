package com.yashaswi.Hospital.Management.services.authentication;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Map;

public interface AuthenticationService {

    public Map<String, String> authenticateUser(String auth);
    public String[] extractUsernameAndPassword(String authorizationHeader);

    public String authenticateUsingBasicAuth(String username, String password) throws AccountNotFoundException;


    Long getUserIdFromToken(String token);
}
