package com.example.School_management_System.exceptions;

public class TeacherDoesNotExistException extends Throwable{
    public TeacherDoesNotExistException(String message){
        super(message);
    }
}
