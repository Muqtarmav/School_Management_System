package com.example.School_management_System.exceptions;

public class StudentDoesNotExistException extends Throwable{

    public StudentDoesNotExistException(String message){
        super(message);
    }
}
