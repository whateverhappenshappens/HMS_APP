package com.yashaswi.Hospital.Management.models.Exceptions;

public class AuthorizationException extends Exception{
    public  AuthorizationException (){
        super();
    }
    public AuthorizationException (String msg){
        super(msg);
    }

}
