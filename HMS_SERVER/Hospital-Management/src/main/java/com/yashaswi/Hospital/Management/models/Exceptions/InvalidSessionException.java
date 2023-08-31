package com.yashaswi.Hospital.Management.models.Exceptions;

public class InvalidSessionException extends Exception{
    public InvalidSessionException (String msg){
        super(msg);
    }
}
