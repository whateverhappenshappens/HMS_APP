package com.yashaswi.Hospital.Management.controllers;

import com.yashaswi.Hospital.Management.models.Exceptions.AuthorizationException;
import com.yashaswi.Hospital.Management.models.Exceptions.ExceptionHandlingControllerAdvice;
import com.yashaswi.Hospital.Management.models.Exceptions.InvalidSessionException;
import com.yashaswi.Hospital.Management.models.Exceptions.UsersConflictExcept;
import com.yashaswi.Hospital.Management.models.enums.AccessorType;
import com.yashaswi.Hospital.Management.models.responses.NewDoctorRequest;
import com.yashaswi.Hospital.Management.models.responses.UserDetailsResponse;
import com.yashaswi.Hospital.Management.services.authentication.Authenticated;
import com.yashaswi.Hospital.Management.models.responses.NewNormalUserRequest;
import com.yashaswi.Hospital.Management.models.responses.NewUserResponse;
import com.yashaswi.Hospital.Management.services.authorization.Authorized;
import com.yashaswi.Hospital.Management.services.booking.BookingService;
import com.yashaswi.Hospital.Management.services.session.SessionService;
import com.yashaswi.Hospital.Management.services.user.UserService;
import com.yashaswi.Hospital.Management.services.user.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

import java.util.Map;

import static org.hibernate.query.sqm.tree.SqmNode.log;
@Slf4j
@RestController
@RequestMapping("hms/")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userServiceImpl;
    private BookingService bookingServiceImpl;
    private  ExceptionHandlingControllerAdvice exceptionHandlingControllerAdvice;
    private final SessionService sessionServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl,SessionService sessionServiceImpl){
        this.userServiceImpl = userServiceImpl;
        this.sessionServiceImpl = sessionServiceImpl;

    }
    @PostMapping("user/registerUser")
    public ResponseEntity<NewUserResponse> createNewUser(@RequestBody NewNormalUserRequest newNormalUserRequest) {
        try {
            return new ResponseEntity<>(userServiceImpl.createNewUser(newNormalUserRequest), HttpStatus.CREATED);
        }
        catch (Exception e){
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @CrossOrigin(origins = "*")
    @Authenticated
    @GetMapping("user/getRole")
    public ResponseEntity<Map<String,String>> getUserRoleFromToken(){
        try{
            log.info("Starting");
            var res  = sessionServiceImpl.getUserRoleFromSession();
            log.info("Response = {} ", res);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        catch (InvalidSessionException e){
            log.error("error", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @Authenticated
    @Authorized(allowedRoles = {"ADMIN"})
    @PostMapping("admin/registerAdmin")
    public ResponseEntity<NewUserResponse> registerAdmin (@RequestBody NewNormalUserRequest newNormalUserRequest){
        try{
            return new ResponseEntity<>(userServiceImpl.createAdmin(newNormalUserRequest), HttpStatus.CREATED);
        }catch (UsersConflictExcept conflictExcept){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Authenticated
    @Authorized(allowedRoles= {"ADMIN"})
    @PostMapping("admin/registerDoctor")
    public ResponseEntity<NewUserResponse> registerDoctor(@RequestBody NewDoctorRequest newDoctorRequest) {
        try {
            return new ResponseEntity<>(userServiceImpl.createDoctor(newDoctorRequest), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
//
    @Authenticated
    @Authorized(allowedRoles= {"ADMIN"})
    @PutMapping("user/{userId}/block-user")
    public ResponseEntity<Boolean>deleteUserById(@PathVariable("userId") Long userId){
        try{
            return new ResponseEntity<>(userServiceImpl.blockUserAccount(userId),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Authenticated
    @Authorized(allowedRoles= {"ADMIN","USER"})
    @PutMapping("user/{userId}/deactivate-user")
    public ResponseEntity<Boolean> deactivateUserById(@PathVariable("userId") Long userId){
        try{
            return new ResponseEntity<>(userServiceImpl.deactivateUserAccount(userId),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Authenticated
    @Authorized(allowedRoles= {"ADMIN"})
    @PutMapping("user/{userId}/reactivate-user")
    public ResponseEntity<Boolean> reactivateUserById(@PathVariable("userId") Long userId){
        try{
            return new ResponseEntity<>(userServiceImpl.reactivateUserAccount(userId),HttpStatus.OK);
        }
        catch (Exception e){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/health")
    public String checkHealth(){
        String dummy = System.getenv("mynewvar");
        return dummy + "i am healthy";
    }


}
