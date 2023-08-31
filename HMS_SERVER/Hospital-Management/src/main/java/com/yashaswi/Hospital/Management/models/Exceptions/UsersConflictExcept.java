package com.yashaswi.Hospital.Management.models.Exceptions;

public class UsersConflictExcept extends RuntimeException{
    public UsersConflictExcept(String msg){
        super(msg);
    }

    public UsersConflictExcept(){
        super("default error message");
    }

    public String getPersonalCause(){
        return "this was caused due to "+ super.getMessage();
    }

}
