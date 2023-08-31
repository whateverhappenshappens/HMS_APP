package com.yashaswi.Hospital.Management.services.authorization;

import java.util.List;

public interface AuthorizationService {
    boolean  authorizeUser(String token, Long pathVariable_userId);
    boolean  authorizeRole(String token, List<String> roles);

}
