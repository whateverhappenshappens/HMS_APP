package com.yashaswi.Hospital.Management.services.session;

import com.yashaswi.Hospital.Management.dao.SessionsDao;
import com.yashaswi.Hospital.Management.models.Exceptions.InvalidSessionException;
import com.yashaswi.Hospital.Management.models.Exceptions.UsersConflictExcept;
import com.yashaswi.Hospital.Management.models.entities.Sessions;
import com.yashaswi.Hospital.Management.models.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService{

    SessionsDao sessionsDao;
    private final HttpServletRequest request;

    @Autowired
    public SessionServiceImpl(SessionsDao sessionsDao,HttpServletRequest request){
        this.sessionsDao = sessionsDao;
        this.request = request;
    }

    @Override
    public Sessions getSessionByToken(String token) {
        Optional<Sessions> sessionsOptional = sessionsDao.findById(token);
        return sessionsOptional.orElse(null);
    }

    @Override
    public Map<String,String> getUserRoleFromSession() throws InvalidSessionException {
        String token = request.getHeader("xHmAuthToken");
        Optional<Sessions> sessionsOptional = sessionsDao.findById(token);
        if(sessionsOptional.isPresent()){
            Map<String,String> res =new HashMap<>();
                res.put("role",sessionsOptional.get().getType().toString());
                return res;
            }
        else {
            throw new InvalidSessionException("no session found for token");
        }

    }
}
