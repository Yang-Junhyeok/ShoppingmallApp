package com.example.test1104.exception;

public class OutOfSttockException extends RuntimeException{
    public OutOfSttockException(String message){
        super(message);
    }
}
