package com.yashaswi.Hospital.Management.services.session;

import com.yashaswi.Hospital.Management.models.Exceptions.InvalidSessionException;
import com.yashaswi.Hospital.Management.models.entities.Sessions;

import java.util.Map;

public interface SessionService {
    public Sessions getSessionByToken(String token);

    Map<String,String> getUserRoleFromSession() throws InvalidSessionException;
}
