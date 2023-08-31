package com.yashaswi.Hospital.Management.controllers;

import com.yashaswi.Hospital.Management.dao.SessionsDao;
import com.yashaswi.Hospital.Management.models.Exceptions.AuthenticationException;
import com.yashaswi.Hospital.Management.services.authentication.Authenticated;
import com.yashaswi.Hospital.Management.services.user.UserServiceImpl;
import com.yashaswi.Hospital.Management.services.authentication.AuthenticationServiceImpl;
import com.yashaswi.Hospital.Management.services.authentication.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("hms/")
@CrossOrigin
public class LoginController {

    AuthenticationService authenticationService;
    HttpServletRequest request;

    @Autowired
    public LoginController(AuthenticationServiceImpl authService,HttpServletRequest request){
        this.authenticationService = authService;
        this.request = request;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("login")
    public ResponseEntity<Map<String,String>> login(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){


        try{
            return ResponseEntity.ok(authenticationService.authenticateUser(authorizationHeader));
        } catch (Exception e){
            log.error("Error", e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @Authenticated
    @CrossOrigin(origins = "*")
    @GetMapping("getUid")
    public ResponseEntity<Long> getUserIdFromToken(@RequestHeader("xHmAuthToken") String token){
        try{
            return ResponseEntity.ok(authenticationService.getUserIdFromToken(token));
        }
        catch (AuthenticationException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
